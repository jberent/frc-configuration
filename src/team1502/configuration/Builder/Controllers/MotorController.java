package team1502.configuration.Builder.Controllers;

import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.Manufacturer;

public class MotorController extends Controller {

    public MotorController(String name, Manufacturer manufacturer){
        super(name, DeviceType.MotorController, manufacturer);
    }

    public MotorController Motor(String partName) {
        //include("Motor", partName, null);
        return this;
    }
}
