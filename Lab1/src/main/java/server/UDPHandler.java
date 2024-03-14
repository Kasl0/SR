package server;

import common.Message;
import common.UDPService;

import static server.Server.broadcastTCPMessage;

public class UDPHandler implements Runnable {
    private UDPService UDPService;

    public UDPHandler(UDPService UDPService) {
        this.UDPService = UDPService;
    }

    @Override
    public void run() {
        while(true) {
            Message receivedMessage = UDPService.receiveMessage();
            System.out.println("Received UDP '" + receivedMessage.getContent() + "' from " + receivedMessage.getNick());
            UDPService.broadcastMessage(receivedMessage);
        }
    }
}
