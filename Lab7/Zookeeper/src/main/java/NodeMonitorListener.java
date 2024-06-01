public interface NodeMonitorListener {
    /**
     * The existence status of the node has changed.
     */
    void exists(String path, boolean exists);

    /**
     * The ZooKeeper session is no longer valid.
     *
     * @param rc the ZooKeeper reason code
     */
    void closing(int rc);
}