package Users;

import Requests.Request;
import Requests.RequestType;
import Results.Result;
import com.rabbitmq.client.*;
import java.io.IOException;

public class Technician {
    public static void main(String[] argv) throws Exception {

        // get two different random specializations
        RequestType requestType = RequestType.values()[(int) (Math.random() * RequestType.values().length)];
        RequestType requestType2 = RequestType.values()[(int) (Math.random() * RequestType.values().length)];
        while (requestType == requestType2) {
            requestType2 = RequestType.values()[(int) (Math.random() * RequestType.values().length)];
        }

        // info
        System.out.println("Technician: " + requestType + " and " + requestType2);

        // connection & channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);

        // queues
        int technicianId = (int) (Math.random() * 1000);
        String TECHNICIAN_QUEUE = "technician" + technicianId;
        System.out.println(TECHNICIAN_QUEUE);
        channel.queueDeclare(TECHNICIAN_QUEUE, false, false, false, null);

        String ADMIN_QUEUE = "admin_queue";
        String QUEUE1_NAME = requestType.toString();
        String QUEUE2_NAME = requestType2.toString();
        channel.queueDeclare(ADMIN_QUEUE, false, false, false, null);
        channel.queueDeclare(QUEUE1_NAME, false, false, false, null);
        channel.queueDeclare(QUEUE2_NAME, false, false, false, null);

        // exchange
        String EXCHANGE_NAME = "log_exchange";
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        channel.queueBind(ADMIN_QUEUE, EXCHANGE_NAME, "");

        // consumer (handle msg)
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received: " + message);
                Request request = new Request(message);

                // send to doctor
                Result result = new Result(TECHNICIAN_QUEUE, request.getRequestType(), request.getPatientName());
                String message2 = result.toString();

                channel.queueDeclare(request.getDoctor(), false, false, false, null);
                channel.queueBind(request.getDoctor(), EXCHANGE_NAME, "");
                channel.basicPublish(EXCHANGE_NAME, "", null, message2.getBytes());
                channel.queueUnbind(request.getDoctor(), EXCHANGE_NAME, "");

                System.out.println("Sent: " + message2);
            }
        };

        Consumer listener = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received from admin: " + message);
            }
        };

        // start listening
        System.out.println("Waiting for orders...");
        channel.basicConsume(TECHNICIAN_QUEUE, true, listener);
        channel.basicConsume(QUEUE2_NAME, true, consumer);
        channel.basicConsume(QUEUE2_NAME, true, consumer);

        // close
        // channel.close();
        // connection.close();
    }
}
