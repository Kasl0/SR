package client;

import common.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPReceiver implements Runnable {
    private Socket socketTCP;

    public TCPReceiver(Socket socketTCP) {
        this.socketTCP = socketTCP;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socketTCP.getInputStream()));

            // wait for messages
            while (true) {
                Message receivedMessage = new Message(in.readLine());
                System.out.println("Received TCP '" + receivedMessage.getContent() + "' from " + receivedMessage.getNick());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
