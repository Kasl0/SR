import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.Stat;

public class NodeMonitor implements Watcher, StatCallback {
    private ZooKeeper zooKeeper;
    private String znode;
    private boolean dead;
    private NodeMonitorListener listener;

    public NodeMonitor(ZooKeeper zooKeeper, String znode, NodeMonitorListener listener) {
        this.zooKeeper = zooKeeper;
        this.znode = znode;
        this.listener = listener;
        zooKeeper.exists(znode, true, this, null); // check if the node exists
    }

    public void process(WatchedEvent event) {
        String path = event.getPath();
        if (event.getType() == Event.EventType.None) {
            switch (event.getState()) {
                case SyncConnected:
                    break;
                case Expired:
                    dead = true;
                    listener.closing(Code.SESSIONEXPIRED.intValue());
                    break;
            }
        } else {
            if (path != null) {
                zooKeeper.exists(znode, true, this, null);
                try {
                    zooKeeper.getChildren(znode, true);
                } catch (KeeperException e) {
                    return; // znode was deleted
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    public void processResult(int rc, String path, Object ctx, Stat stat) {
        boolean exists;
        switch (rc) {
            case Code.Ok:
                exists = true;
                break;
            case Code.NoNode:
                exists = false;
                break;
            case Code.SessionExpired:
            case Code.NoAuth:
                dead = true;
                listener.closing(rc);
                return;
            default:
                zooKeeper.exists(znode, true, this, null);
                return;
        }

        listener.exists(path, exists);
    }

    public boolean isDead() {
        return dead;
    }
}
