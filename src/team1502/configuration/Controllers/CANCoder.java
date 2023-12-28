package team1502.configuration.Controllers;

import team1502.configuration.CAN.CanInfo;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.ICAN;
import team1502.configuration.CAN.Manufacturer;
import team1502.configuration.Parts.Encoder;

public class CANCoder extends Encoder /* implements ICAN */{

    public CANCoder(String name) {
        Name(name);
        CanInfo(new CanInfo(DeviceType.GyroSensor, Manufacturer.REVRobotics));
    }
}