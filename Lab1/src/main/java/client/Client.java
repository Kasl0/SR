package client;

import common.Message;
import common.MulticastService;
import common.UDPService;

import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        String commandUDP = "U";
        String commandMulticast = "M";
        String hostName = "localhost";
        int portNumber = 12345;

        try (Socket socketTCP = new Socket(hostName, portNumber); DatagramSocket socketUDP = new DatagramSocket()) {

            // read nick from console
            System.out.print("Client nick: ");
            Scanner scanner = new Scanner(System.in);
            String nick = scanner.nextLine();
            Message helloMessage = new Message(nick, "Hello!");

            // start waiting for TCP messages in another thread
            TCPReceiver TCPReceiver = new TCPReceiver(socketTCP);
            Thread TCPReceiverThread = new Thread(TCPReceiver);
            TCPReceiverThread.start();

            UDPService UDPService = new UDPService(socketUDP, portNumber);
            UDPService.sendMessage(helloMessage);
            MulticastService multicastService = new MulticastService();

            // start waiting for UDP messages in another thread
            UDPReceiver UDPReceiver = new UDPReceiver(UDPService);
            Thread UDPReceiverThread = new Thread(UDPReceiver);
            UDPReceiverThread.start();

            // start waiting for Multicast messages in another thread
            MulticastReceiver multicastReceiver = new MulticastReceiver(multicastService, nick);
            Thread multicastReceiverThread = new Thread(multicastReceiver);
            multicastReceiverThread.start();

            // out stream
            PrintWriter out = new PrintWriter(socketTCP.getOutputStream(), true);

            // start waiting for console input & send messages
            while (true) {
                String input = scanner.nextLine();
                if (Objects.equals(input, commandUDP)) {
                    Message message = new Message(nick, scanner.nextLine());
                    UDPService.sendMessage(message);
                } else if (Objects.equals(input, commandMulticast)) {
                    Message message = new Message(nick, scanner.nextLine());
                    multicastService.sendMessage(message);
                } else {
                    Message message = new Message(nick, input);
                    out.println(message);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
