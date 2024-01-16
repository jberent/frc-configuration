package team1502.configuration.Builder;

import java.util.function.Function;

import team1502.Wpi.CANSparkMaxLowLevel;
import team1502.configuration.Parts.Part;

public class Motor extends Builder {
    //private Function<Motor, Builder> buildFunction;
    
    //Define
    public Motor(String name, Function<Motor, Builder> fn) {
        super("Motor", name, fn);
        //buildFunction = fn;
    }

    //Build
    public Motor(Function<Motor, Builder> fn) {
        super("Motor");
        //buildFunction = fn;
    }

    public Motor MotorType(CANSparkMaxLowLevel.MotorType motorType) {
          return (Motor)Value("motorType", motorType);
    }


    @Override
    public Motor Build(Part part) {
        super.Build(part);
        return this;
    }
/*
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
