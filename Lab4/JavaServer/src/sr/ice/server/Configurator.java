package sr.ice.server;

import com.zeroc.Ice.Current;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;

public class Configurator {

    public static void initializeDevices(ObjectAdapter adapter) {
        DevicesI devicesServant1 = new DevicesI();

        String fireSensorName = "FireSensor1";
        FireSensorI fireSensorServant1 = new FireSensorI();
        fireSensorServant1.setName(fireSensorName, new Current());
        devicesServant1.addDevice(fireSensorName, new Current());
        adapter.add(fireSensorServant1, new Identity(fireSensorName, "FireSensor"));

        String lightName = "Light1";
        LightI lightServant1 = new LightI();
        lightServant1.setName(lightName, new Current());
        devicesServant1.addDevice(lightName, new Current());
        adapter.add(lightServant1, new Identity(lightName, "Light"));

        String ledStripName = "LedStrip1";
        LedStripI ledStripServant1 = new LedStripI();
        ledStripServant1.setName(ledStripName, new Current());
        devicesServant1.addDevice(ledStripName, new Current());
        adapter.add(ledStripServant1, new Identity(ledStripName, "LedStrip"));

        adapter.add(devicesServant1, new Identity("Devices", "Devices"));
    }
}
