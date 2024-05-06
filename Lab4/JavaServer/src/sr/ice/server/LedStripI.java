package sr.ice.server;

import Home.DeviceDeactivatedException;
import Home.InvalidArgumentException;
import Home.LedStrip;
import Home.RGB;
import com.zeroc.Ice.Current;

import java.util.ArrayList;
import java.util.List;

public class LedStripI extends LightI implements LedStrip {
    private int noLeds;
    private List<RGB> ledStrip;

    @Override
    public void setNumberOfLeds(int numberOfLeds, Current current) throws InvalidArgumentException {
        if (numberOfLeds <= 0) {
            throw new InvalidArgumentException();
        }
        noLeds = numberOfLeds;
        ledStrip = new ArrayList<>();
        for (int i=0; i<numberOfLeds; i++) {
            RGB led = new RGB(255, 255, 255);
            ledStrip.add(led);
        }
    }

    @Override
    public int getNumberOfLeds(Current current) {
        return noLeds;
    }

    @Override
    public void setLedColor(int ledIndex, RGB color, Current current) throws DeviceDeactivatedException, InvalidArgumentException {
        if (ledIndex < 0 || ledIndex >= noLeds) {
            throw new InvalidArgumentException();
        }
        if (color.red < 0 || color.red > 255 || color.green < 0 || color.green > 255 || color.blue < 0 || color.blue > 255) {
            throw new InvalidArgumentException();
        }
        if (!this.isActive(current)) {
            throw new DeviceDeactivatedException();
        }
        ledStrip.set(ledIndex, color);
    }

    @Override
    public RGB getLedColor(int ledIndex, Current current) throws DeviceDeactivatedException, InvalidArgumentException {
        if (ledIndex < 0 || ledIndex >= noLeds) {
            throw new InvalidArgumentException();
        }
        if (!this.isActive(current)) {
            throw new DeviceDeactivatedException();
        }
        return ledStrip.get(ledIndex);
    }
}
