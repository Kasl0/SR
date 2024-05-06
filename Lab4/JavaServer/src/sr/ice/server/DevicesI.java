package sr.ice.server;

import Home.Devices;
import com.zeroc.Ice.Current;

public class DevicesI implements Devices {
    private String[] names;
    @Override
    public String[] getDeviceList(Current current) {
        return names;
    }

    @Override
    public void addDevice(String name, Current current) {
        if (names == null) {
            String[] newNames = new String[1];
            newNames[0] = name;
            names = newNames;
            return;
        }
        String[] newNames = new String[names.length + 1];
        for (int i = 0; i < names.length; i++) {
            newNames[i] = names[i];
        }
        newNames[newNames.length - 1] = name;
        names = newNames;
    }
}
