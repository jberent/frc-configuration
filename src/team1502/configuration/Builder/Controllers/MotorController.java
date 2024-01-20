package team1502.configuration.Builder.Controllers;

import java.util.function.Function;

import team1502.Wpi.CANSparkMax.IdleMode;
import team1502.configuration.Builder.Builder;
import team1502.configuration.Builder.Motor;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.Manufacturer;
import team1502.configuration.Parts.Part;
import team1502.configuration.Builder.GearBox;
import team1502.configuration.Builder.PID;

public class MotorController extends Controller {
    private static final String NAME = "MotorController"; 

    // Define
    public MotorController(String name, Manufacturer manufacturer, Function<MotorController, Builder> fn) {
        super(name, DeviceType.MotorController, manufacturer, fn);
    }
    public MotorController(String name, Function<MotorController, Builder> fn) {
        super(name, DeviceType.MotorController, fn);
    }
    
    //Build Proxy / Eval
    public MotorController() {
        super(DeviceType.MotorController);
    }
    public MotorController(Part part) {
        super(DeviceType.MotorController);
        setPart(part);
    }
    
    public MotorController Motor(String partName) {
        return Motor(partName, null);
    }
    public MotorController Motor(String partName, Function<Motor, Builder> fn) {
        Install("Motor", partName, fn);
        return this;
    }

    public Motor Motor() {
        var eval =  new Motor();
        eval.setPart(getPart("Motor"));
        return eval;
    }

    public IdleMode IdleMode() { return (IdleMode)getValue("idleMode"); }
    public MotorController IdleMode(IdleMode value) {
        setValue("idleMode", value);
        return this;
    }
    
    public boolean Reversed() {
        var result = getBoolean("isReversed");
        return result == null ? false : result;
    }
    public MotorController Reversed(boolean value) {
        setValue("isReversed", value);
        return this;
    }

    public GearBox GearBox() { return new GearBox(this); }
    public MotorController GearBox(Function<GearBox, Builder> fn) {
        return (MotorController)Install(new GearBox(fn));
    }
    
    public PID PID() { return new PID(this); }
    public MotorController PID(double p, double i, double d) {
        return (MotorController)Install(new PID(p,i,d));
    }
}
