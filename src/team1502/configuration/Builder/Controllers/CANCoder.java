package team1502.configuration.Builder.Controllers;

import team1502.configuration.CAN.CanInfo;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.ICAN;
import team1502.configuration.CAN.Manufacturer;
import team1502.configuration.Parts.Part;

import java.util.function.Function;

import team1502.configuration.Builder.Builder;
import team1502.configuration.Builder.Encoder;

public class CANCoder extends Controller /*Encoder /* implements ICAN */{

    public CANCoder(String name, Function<CANCoder, Builder> fn) {
        super(name, DeviceType.GyroSensor, Manufacturer.REVRobotics);
        //CanInfo(new CanInfo(DeviceType.GyroSensor, Manufacturer.REVRobotics));
    }

    public CANCoder(Part part) {
        super(DeviceType.GyroSensor);
        setPart(part);
    }
}