package team1502.configuration.Builder.Controllers;

import team1502.configuration.CAN.DeviceType;

public class GearToothSensor extends Controller {
    public GearToothSensor(String name) {
        super(name, DeviceType.GearToothSensor);
    }
}