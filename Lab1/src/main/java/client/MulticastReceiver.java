package client;

import common.Message;
import common.MulticastService;

import java.util.Objects;

public class MulticastReceiver implements Runnable  {
    private final MulticastService multicastService;
    private final String nick;

    public MulticastReceiver(MulticastService multicastService, String nick) {
        this.multicastService = multicastService;
        this.nick = nick;
    }

    @Override
    public void run() {
        while (true) {
            Message receivedMessage = multicastService.receiveMessage();
            if (!Objects.equals(receivedMessage.getNick(), nick)) {
                System.out.println("Received Multicast '" + receivedMessage.getContent() + "' from " + receivedMessage.getNick());
            }
        }
    }
}
