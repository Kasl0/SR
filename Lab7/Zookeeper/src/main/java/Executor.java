import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class Executor implements Watcher, Runnable, NodeMonitorListener
{
    private String znode;
    private ZooKeeper zooKeeper;
    private NodeMonitor nodeMonitor;
    private String app;
    private String[] args;
    private Process child;

    public Executor(String server, String znode, String app, String[] args) throws IOException {
        this.znode = znode;
        this.app = app;
        this.args = args;
        this.zooKeeper = new ZooKeeper(server, 3000, this);
        nodeMonitor = new NodeMonitor(zooKeeper, znode, this);
    }

    public void process(WatchedEvent event) {
        nodeMonitor.process(event);
    }

    public void run() {
        try {
            synchronized (this) {
                while (!nodeMonitor.isDead()) {
                    wait();
                }
            }
        } catch (InterruptedException ignored) {
        }
    }

    public void closing(int rc) {
        synchronized (this) {
            notifyAll();
        }
    }

    public void exists(String path, boolean exists) {
        if (exists) {
            System.out.println(printTree(znode, 0));
        }

        if (path.equals(znode)) {
            if (!exists && child != null) {
                System.out.println("Killing process");
                child.destroy();
                try {
                    child.waitFor();
                } catch (InterruptedException ignored) {
                }
                child = null;
            } else if (exists && child == null) {
                try {
                    System.out.println("Starting child");
                    String[] exec = new String[args.length + 1];
                    exec[0] = app;
                    System.arraycopy(args, 0, exec, 1, args.length);
                    child = Runtime.getRuntime().exec(exec);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (znode.equals(path.substring(0, znode.length())) && exists) {
            System.out.println("Current number of descendants: " + getDescendantsCount(znode));
        }
    }

    public int getDescendantsCount(String znode) {
        try {
            List<String> children = zooKeeper.getChildren(znode, false);
            int sum = 0;
            for (String child : children) {
                sum += getDescendantsCount(znode + "/" + child);
            }
            return children.size() + sum;
        } catch (KeeperException | InterruptedException e) {
            return -1; // znode was deleted
        }
    }

    public String printTree(String node, int level) {
        String output = "";
        try {
            for (int i = 0; i < level; i++) {
                output += "--";
            }
            output += node;
            output += "\n";

            List<String> children = zooKeeper.getChildren(node, true);

            for (String child : children) {
                output += printTree(node + "/" + child, level + 1);
            }
        } catch (KeeperException | InterruptedException e) {
            return output; // znode was deleted
        }
        return output;
    }
}
