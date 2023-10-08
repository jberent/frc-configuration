package team1502.configuration;

import team1502.configuration.CAN.DeviceType;

public class Gyro extends Controller {
    public Gyro(String name) {
        super(name, DeviceType.GyroSensor);
    }
}
