package team1502.configuration.Builder;

import team1502.Wpi.CANSparkMax.IdleMode;

public class MotorControllerBuilder extends ControllerBuilder {

    public MotorControllerBuilder(Builder parent) {
        super(parent);
        //TODO Auto-generated constructor stub
    }

    public MotorControllerBuilder IdleMode(IdleMode value) {
        setValue("idleMode", value);
        return this;
    }
    
    public MotorControllerBuilder Reversed() {
        setValue("isReversed", true);
        return this;
    }
    
}
