package team1502.configuration.Controllers;

import team1502.configuration.CAN.DeviceType;

public class MotorController extends Controller {

    public MotorController(String name){
        super(name, DeviceType.MotorController);
    }
}
