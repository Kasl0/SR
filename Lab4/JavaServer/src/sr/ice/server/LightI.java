package sr.ice.server;

import Home.DeviceDeactivatedException;
import Home.InvalidArgumentException;
import Home.Light;
import com.zeroc.Ice.Current;

public class LightI extends ActuatorDeviceI implements Light {
    float brightness;

    @Override
    public float getBrightness(Current current) throws DeviceDeactivatedException {
        if (!this.isActive(current)) {
            throw new DeviceDeactivatedException();
        }
        return brightness;
    }

    @Override
    public void setBrightness(float brightness, Current current) throws DeviceDeactivatedException, InvalidArgumentException {
        if (!this.isActive(current)) {
            throw new DeviceDeactivatedException();
        }
        if (brightness <= 0 || brightness >= 1) {
            throw new InvalidArgumentException();
        }
        this.brightness = brightness;
    }
}
