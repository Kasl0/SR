import Ice
import sys

sys.path.append("generated")
import Home

class Client(Ice.Application):
    def run(self, argv):
        with Ice.initialize(sys.argv, "config.client") as communicator:
            try:
                while True:
                    command = input("> ")
                    words = command.split()

                    match words[0]:

                        case "Devices":
                            devices_proxy = Home.DevicesPrx.checkedCast(communicator.propertyToProxy("Devices.Proxy"))
                            if not devices_proxy:
                                raise RuntimeError("Invalid devices proxy")
                            match words[1]:
                                case "getDeviceList":
                                    devices = devices_proxy.getDeviceList()
                                    print(devices)
                                case _:
                                    print("Devices does not have that function")

                        case "FireSensor1":
                            fire_sensor_proxy = Home.FireSensorPrx.checkedCast(communicator.propertyToProxy("FireSensor1.Proxy"))
                            if not fire_sensor_proxy:
                                raise RuntimeError("Invalid fire sensor 1 proxy")
                            match words[1]:
                                case "getName":
                                    result = fire_sensor_proxy.getName()
                                    print(result)
                                case "getReading":
                                    result = fire_sensor_proxy.getReading()
                                    print(result)
                                case "setAlarmThreshold":
                                    fire_sensor_proxy.setAlarmThreshold(float(words[2]))
                                case "getAlarmThreshold":
                                    result = fire_sensor_proxy.getAlarmThreshold()
                                    print(result)
                                case "isAlarm":
                                    result = fire_sensor_proxy.isAlarm()
                                    print(result)
                                case _:
                                    print("Fire sensor does not have that function")
                        
                        case "Light1":
                            light_proxy = Home.LightPrx.checkedCast(communicator.propertyToProxy("Light1.Proxy"))
                            if not light_proxy:
                                raise RuntimeError("Invalid light 1 proxy")
                            match words[1]:
                                case "getName":
                                    result = light_proxy.getName()
                                    print(result)
                                case "activate":
                                    light_proxy.activate()
                                case "deactivate":
                                    light_proxy.deactivate()
                                case "isActive":
                                    result = light_proxy.isActive()
                                    print(result)
                                case "getBrightness":
                                    result = light_proxy.getBrightness()
                                    print(result)
                                case "setBrightness":
                                    light_proxy.setBrightness(float(words[2]))
                                case _:
                                    print("Light does not have that function")
                            
                        case "LedStrip1":
                            led_strip_proxy = Home.LedStripPrx.checkedCast(communicator.propertyToProxy("LedStrip1.Proxy"))
                            if not led_strip_proxy:
                                raise RuntimeError("Invalid led strip 1 proxy")
                            match words[1]:
                                case "getName":
                                    result = led_strip_proxy.getName()
                                    print(result)
                                case "activate":
                                    led_strip_proxy.activate()
                                case "deactivate":
                                    led_strip_proxy.deactivate()
                                case "isActive":
                                    result = led_strip_proxy.isActive()
                                    print(result)
                                case "getBrightness":
                                    result = led_strip_proxy.getBrightness()
                                    print(result)
                                case "setBrightness":
                                    led_strip_proxy.setBrightness(float(words[2]))
                                case "setNumberOfLeds":
                                    led_strip_proxy.setNumberOfLeds(int(words[2]))
                                case "getNumberOfLeds":
                                    result = led_strip_proxy.getNumberOfLeds()
                                    print(result)
                                case "setLedColor":
                                    rgb = Home.RGB(int(words[3]), int(words[4]), int(words[5]))
                                    led_strip_proxy.setLedColor(int(words[2]), rgb)
                                case "getLedColor":
                                    rgb = led_strip_proxy.getLedColor(int(words[2]))
                                    print(f"Red: {rgb.red}, Green: {rgb.green}, Blue: {rgb.blue}")
                                case _:
                                    print("Light does not have that function")
                        
                        case "quit":
                            break

                        case _:
                            print("Command does not exist")

            except Ice.Exception as ex:
                print(ex)

        return 0

if __name__ == "__main__":
    app = Client()
    sys.exit(app.main(sys.argv))
