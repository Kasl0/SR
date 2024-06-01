public class Main {
    private static final String SERVER = "localhost:2181";
    private static final String ZNODE = "/a";
    private static final String APP = "mspaint.exe";
    private static final String ARGS[] = new String[0];
    public static void main(String[] args) {
        try {
            new Executor(SERVER, ZNODE, APP, ARGS).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
