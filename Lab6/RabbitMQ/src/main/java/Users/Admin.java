package Users;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Admin {
    public static void main(String[] argv) throws Exception {

        // info
        System.out.println("Admin");

        // connection & channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // queues
        String ADMIN_QUEUE = "admin_queue";
        channel.queueDeclare(ADMIN_QUEUE, false, false, false, null);

        // exchange
        String EXCHANGE_NAME = "admin_exchange";
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        // consumer (handle msg)
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Log received: " + message);
                String[] words = message.split(" ");
                channel.queueDeclare(words[0], false, false, false, null);
                channel.queueBind(words[0], EXCHANGE_NAME, "");
            }
        };

        // start listening
        System.out.println("Waiting for logs...");
        channel.basicConsume(ADMIN_QUEUE, true, consumer);

        // wait for stdin input and sent to all queues
        while (true) {
            String message = new java.util.Scanner(System.in).nextLine();
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println("Sent: " + message);
        }

        // close
        // channel.close();
        // connection.close();
    }
}
