package sr.ice.server;

import Home.SensorDevice;
import com.zeroc.Ice.Current;

import java.util.Random;

public class SensorDeviceI extends DeviceI implements SensorDevice {
    @Override
    public float getReading(Current current) {
        Random random = new Random();
        return random.nextFloat();
    }
}
