package team1502.configuration;

import team1502.configuration.CAN.DeviceType;

public class CANSensor extends Controller {
    public CANSensor(String name) {
        super(name, DeviceType.GearToothSensor);
    }
}