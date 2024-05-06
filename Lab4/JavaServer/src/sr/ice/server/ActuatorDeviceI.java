package sr.ice.server;

import Home.ActuatorDevice;
import com.zeroc.Ice.Current;

public class ActuatorDeviceI extends DeviceI implements ActuatorDevice {
    private boolean isActive;

    @Override
    public void activate(Current current) {
        isActive = true;
    }

    @Override
    public void deactivate(Current current) {
        isActive = false;
    }

    @Override
    public boolean isActive(Current current) {
        return isActive;
    }
}
