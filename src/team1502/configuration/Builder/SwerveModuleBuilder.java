package team1502.configuration.Builder;

import java.util.function.Function;

public class SwerveModuleBuilder extends Builder {

    public SwerveModuleBuilder(Builder parent) {
        super(parent);
        //TODO Auto-generated constructor stub
    }
    
    public SwerveModuleBuilder MotorController(String name, Function<MotorControllerBuilder, Builder> fn) {
        
        return this;
    }

    public SwerveModuleBuilder Reversed() {
        return this;
    }
}
