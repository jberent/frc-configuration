package team1502.configuration.Builder.Controllers;

import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.Manufacturer;

public class Accelerometer extends Controller {
    public Accelerometer(String name) {
        super(name, DeviceType.Accelerometer, null);
    }
    public Accelerometer(String name, Manufacturer manufacturer) {
        super(name, DeviceType.Accelerometer, manufacturer);
    }
}
