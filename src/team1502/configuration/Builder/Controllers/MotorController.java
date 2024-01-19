package team1502.configuration.Builder.Controllers;

import java.util.function.Function;

import team1502.Wpi.CANSparkMax.IdleMode;
import team1502.configuration.Builder.Builder;
import team1502.configuration.Builder.Motor;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.Manufacturer;

public class MotorController extends Controller {

    // Define
    public MotorController(String name, Function<MotorController, Builder> fn) {
        super(name, DeviceType.MotorController, fn);
    }
    
    //Build Proxy / Eval
    public MotorController() {
        super(DeviceType.MotorController);
    }
    
    // public MotorController(String name, Manufacturer manufacturer){
    //     super(name, DeviceType.MotorController, manufacturer);
    // }

    public MotorController Motor(String partName, Function<Motor, Builder> fn) {
        Install("Motor", partName, fn);
        return this;
    }

    public Motor Motor() {
        var eval =  new Motor();
        eval.setPart(getPart("Motor"));
        return eval;
    }

    public MotorController IdleMode(IdleMode value) {
        setValue("idleMode", value);
        return this;
    }
    
    public MotorController Reversed() {
        setValue("isReversed", true);
        return this;
    }}
