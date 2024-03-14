package common;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;

public class UDPService {
    static private final int receiveBufferSize = 1024;
    static private final String hostname = "localhost";
    private final DatagramSocket socketUDP;
    private final int portNumber;
    private final ArrayList<Integer> broadcastPorts = new ArrayList<>();

    public UDPService(DatagramSocket socketUDP, int portNumber) {
        this.socketUDP = socketUDP;
        this.portNumber = portNumber;
    }

    public Message receiveMessage() {
        byte[] receiveBuffer = new byte[receiveBufferSize];
        Arrays.fill(receiveBuffer, (byte) 0);
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        try {
            socketUDP.receive(receivePacket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        broadcastPorts.add(receivePacket.getPort());
        return new Message(new String(receivePacket.getData()).trim(), receivePacket.getPort());
    }

    public void sendMessage(Message message) {
        try {
            InetAddress address = InetAddress.getByName(hostname);
            byte[] sendBuffer = message.toString().getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, portNumber);
            socketUDP.send(sendPacket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void broadcastMessage(Message message) {
        try {
            InetAddress address = InetAddress.getByName(hostname);
            byte[] sendBuffer = message.toString().getBytes();
            for (int port : broadcastPorts) {
                if (port != message.getReceivedFromPort()) {
                    DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, port);
                    socketUDP.send(sendPacket);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
