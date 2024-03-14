package common;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class MulticastService {
    static private final int receiveBufferSize = 1024;
    static private final int portNumber = 6789;
    private final InetAddress group;
    private final MulticastSocket s;

    public MulticastService() throws IOException {
        this.group = InetAddress.getByName("228.5.6.7");
        this.s = new MulticastSocket(portNumber);
        s.joinGroup(group);
    }

    public Message receiveMessage() {
        byte[] receiveBuffer = new byte[receiveBufferSize];
        Arrays.fill(receiveBuffer, (byte) 0);
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        try {
            s.receive(receivePacket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new Message(new String(receivePacket.getData()).trim(), receivePacket.getPort());
    }

    public void sendMessage(Message message) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(message.toString().getBytes(), message.toString().length(), group, portNumber);
        s.send(datagramPacket);
    }
}
