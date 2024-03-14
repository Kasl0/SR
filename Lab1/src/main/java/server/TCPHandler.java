package server;

import common.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static server.Server.broadcastTCPMessage;

public class TCPHandler implements Runnable {
    private final Socket clientSocket;
    private PrintWriter out;

    public TCPHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            // in & out streams
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // read msg, send response
            while (true) {
                Message receivedMessage = new Message(in.readLine());
                System.out.println("Received TCP '" + receivedMessage.getContent() + "' from " + receivedMessage.getNick());
                broadcastTCPMessage(receivedMessage, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message) {
        out.println(message);
    }
}
