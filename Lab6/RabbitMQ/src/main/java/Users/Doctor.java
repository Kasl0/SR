package Users;

import Requests.Request;
import Requests.RequestType;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Doctor {
    public static void main(String[] argv) throws Exception {

        // connection & channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // queues
        int doctorId = (int) (Math.random() * 1000);
        String DOCTOR_QUEUE = "doctor" + doctorId;
        System.out.println(DOCTOR_QUEUE);
        channel.queueDeclare(DOCTOR_QUEUE, false, false, false, null);

        String ADMIN_QUEUE = "admin_queue";
        channel.queueDeclare(ADMIN_QUEUE, false, false, false, null);

        for (RequestType requestType : RequestType.values()) {
            channel.queueDeclare(requestType.toString(), false, false, false, null);
        }

        // exchange
        String EXCHANGE_NAME = "order_exchange";
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        for (RequestType requestType : RequestType.values()) {
            channel.queueBind(requestType.toString(), EXCHANGE_NAME, requestType.toString());
            channel.queueBind(ADMIN_QUEUE, EXCHANGE_NAME, requestType.toString());
        }

        // generate random request
        RequestType requestType = RequestType.values()[(int) (Math.random() * RequestType.values().length)];

        // generate random name
        String name = "";
        for (int i = 0; i < 5; i++) {
            name += (char) ((int) (Math.random() * 26) + 65);
        }

        // send request
        Request request = new Request(DOCTOR_QUEUE, requestType, name);
        String message = request.toString();
        channel.basicPublish(EXCHANGE_NAME, request.getRequestType().toString(), null, message.getBytes());
        System.out.println("Sent: " + message);

        // consumer (handle msg)
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received: " + message);
            }
        };

        // start listening
        System.out.println("Waiting for results...");
        channel.basicConsume(DOCTOR_QUEUE, true, consumer);

        // close
        // channel.close();
        // connection.close();
    }
}
