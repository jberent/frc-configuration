package team1502.configuration.Builder;

import java.util.function.Function;

import team1502.Wpi.CANSparkMaxLowLevel;
import team1502.configuration.Parts.Part;

public class Motor extends Builder {
    
    //Define
    public Motor(String name, Function<Motor, Builder> fn) {
        super("Motor", name, fn);
    }

    //Build / Eval
    public Motor() {
        super("Motor");
    }

    public Motor MotorType(CANSparkMaxLowLevel.MotorType motorType) {
          return (Motor)Value("motorType", motorType);
    }
    public Motor FreeSpeedRPM(double speed) {
          return (Motor)Value("freeSpeedRPM", speed);
    }

    public CANSparkMaxLowLevel.MotorType MotorType() {
          return (CANSparkMaxLowLevel.MotorType)getValue("motorType");
    }

/*

    @Override
    public Motor Build(Part part) {
        super.Build(part);
        return this;
    }
 * 
    @Override
    protected void onBuild(Part part, Function<Builder, Builder> fn) {
        if (buildFunction != null) {
            buildFunction.apply(this);
        }
        if (fn != null) {
            fn.apply(this);
        }
    
    }
 */
}
