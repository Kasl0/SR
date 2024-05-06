package sr.ice.server;

import Home.Device;
import com.zeroc.Ice.Current;

public class DeviceI implements Device {
    private String name;

    @Override
    public void setName(String name, Current current) {
        this.name = name;
    }

    @Override
    public String getName(Current current) {
        return this.name;
    }
}
