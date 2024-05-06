package sr.ice.server;

import Home.FireSensor;
import Home.InvalidArgumentException;
import com.zeroc.Ice.Current;

public class FireSensorI extends SensorDeviceI implements FireSensor {
    private float alarmThreshold;

    @Override
    public void setAlarmThreshold(float alarmThreshold, Current current) throws InvalidArgumentException {
        if (alarmThreshold <= 0 || alarmThreshold >= 1) {
            throw new InvalidArgumentException();
        }
        this.alarmThreshold = alarmThreshold;
    }

    @Override
    public float getAlarmThreshold(Current current) {
        return alarmThreshold;
    }

    @Override
    public boolean isAlarm(Current current) {
        return getReading(current) >= alarmThreshold;
    }
}
