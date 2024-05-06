#ifndef HOME_ICE
#define HOME_ICE

module Home
{
    exception InvalidArgumentException {};

    interface Device
    {
        void setName(string name);
        string getName();
    };
    sequence<string> DeviceList;

    interface Devices
    {
        DeviceList getDeviceList();
        void addDevice(string name);
    };

    interface SensorDevice extends Device
    {
        float getReading();
    };

    interface FireSensor extends SensorDevice
    {
        void setAlarmThreshold(float alarmThreshold) throws InvalidArgumentException;
        float getAlarmThreshold();
        bool isAlarm();
    };

    interface ActuatorDevice extends Device
    {
        void activate();
        void deactivate();
        bool isActive();
    };

    exception DeviceDeactivatedException {}; // Exception thrown when device is deactivated

    interface Light extends ActuatorDevice
    {
        float getBrightness() throws DeviceDeactivatedException;
        void setBrightness(float brightness) throws DeviceDeactivatedException, InvalidArgumentException;
    };

    struct RGB
    {
        int red;
        int green;
        int blue;
    };

    interface LedStrip extends Light
    {
        void setNumberOfLeds(int numberOfLeds) throws InvalidArgumentException;
        int getNumberOfLeds();
        void setLedColor(int ledIndex, RGB color) throws DeviceDeactivatedException, InvalidArgumentException;
        RGB getLedColor(int ledIndex) throws DeviceDeactivatedException, InvalidArgumentException;
    };
};

#endif
