package team1502.configuration.Controllers;

import team1502.configuration.CAN.DeviceType;

public class Accelerometer extends Controller {
    public Accelerometer(String name) {
        super(name, DeviceType.Accelerometer);
    }
}
