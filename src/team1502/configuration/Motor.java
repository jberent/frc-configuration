package team1502.configuration;

import team1502.configuration.CAN.CanInfo;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.ICAN;
import team1502.configuration.CAN.Manufacturer;

public class Motor extends ConfigurationItem  implements ICAN {

    public Motor() {}
    public Motor(String name){
        super(name);
        canInfo = new CanInfo(DeviceType.MotorController, Manufacturer.REVRobotics);
    }
    
    private CanInfo canInfo;

    @Override
    public CanInfo GetCanInfo() {
        return canInfo;
    }

}
