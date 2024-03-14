package server;

import common.Message;
import common.UDPService;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final List<TCPHandler> clientsTCP = new ArrayList<>();

    public static void main(String[] args) {
        int portNumber = 12345;
        System.out.println("Server started");

        try (ServerSocket socketTCP = new ServerSocket(portNumber);
             DatagramSocket socketUDP = new DatagramSocket(portNumber)) {

            UDPService UDPService = new UDPService(socketUDP, portNumber);
            UDPHandler UDPHandler = new UDPHandler(UDPService);
            Thread clientUDPThread = new Thread(UDPHandler);
            clientUDPThread.start();

            while (true) {
                Socket clientSocket = socketTCP.accept();
                System.out.println("New client connected");

                // create a new thread for each client
                TCPHandler TCPHandler = new TCPHandler(clientSocket);
                clientsTCP.add(TCPHandler);
                Thread clientTCPThread = new Thread(TCPHandler);
                clientTCPThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void broadcastTCPMessage(Message message, TCPHandler sender) {
        for (TCPHandler client : clientsTCP) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }
}
