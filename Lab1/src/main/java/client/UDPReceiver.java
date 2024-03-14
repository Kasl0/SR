package client;

import common.Message;
import common.UDPService;

public class UDPReceiver implements Runnable {
    private UDPService UDPService;

    public UDPReceiver(UDPService UDPService) {
        this.UDPService = UDPService;
    }

    @Override
    public void run() {
        while (true) {
            Message receivedMessage = UDPService.receiveMessage();
            System.out.println("Received UDP '" + receivedMessage.getContent() + "' from " + receivedMessage.getNick());
        }
    }
}
