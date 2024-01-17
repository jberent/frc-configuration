package team1502.configuration.Builder.Controllers;

import java.util.function.Function;

import team1502.Wpi.CANSparkMax.IdleMode;
import team1502.configuration.Builder.Builder;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.Manufacturer;

public class MotorController extends Controller {

        //Define
    public MotorController(String name, Function<MotorController, Builder> fn) {
        super(name, DeviceType.MotorController, fn);
    }

    public MotorController(String name, Manufacturer manufacturer){
        super(name, DeviceType.MotorController, manufacturer);
    }

    public MotorController Motor(String partName) {
        //include("Motor", partName, null);
        return this;
    }

    public MotorController IdleMode(IdleMode value) {
        setValue("idleMode", value);
        return this;
    }
    
    public MotorController Reversed() {
        setValue("isReversed", true);
        return this;
    }}
