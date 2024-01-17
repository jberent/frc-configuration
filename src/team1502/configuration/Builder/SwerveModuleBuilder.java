package team1502.configuration.Builder;

import java.util.function.Function;

import team1502.configuration.Builder.Controllers.MotorController;

public class SwerveModuleBuilder extends Builder {

    public SwerveModuleBuilder(Builder parent) {
        super("SwerveModule");
        //TODO Auto-generated constructor stub
    }
    
    public SwerveModuleBuilder MotorController(String name, Function<MotorController, Builder> fn) {
        
        return this;
    }

    public SwerveModuleBuilder Reversed() {
        return this;
    }
}
