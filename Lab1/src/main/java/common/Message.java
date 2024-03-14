package common;

public class Message {
    static private final String separator = ";";
    private final String nick;
    private final String content;
    private int receivedFromPort;

    public Message(String nick, String content) {
        this.nick = nick;
        this.content = content;
    }

    public Message(String string) {
        String[] parts = string.split(separator);
        this.nick = parts[0];
        this.content = parts[1];
    }

    public Message(String string, int port) {
        String[] parts = string.split(separator);
        this.nick = parts[0];
        this.content = parts[1];
        this.receivedFromPort = port;
    }

    public String getNick() {
        return nick;
    }

    public String getContent() {
        return content;
    }

    public int getReceivedFromPort() {
        return receivedFromPort;
    }

    @Override
    public String toString() {
        return nick + separator + content;
    }
}
