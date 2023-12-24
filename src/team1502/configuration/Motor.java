package team1502.configuration;

import team1502.configuration.CAN.CanInfo;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.ICAN;
import team1502.configuration.CAN.Manufacturer;

public class Motor extends ConfigurationItem  /*implements ICAN*/ {

    public Motor() {}
    public Motor(String name){
        super(name);
        canInfo = new CanInfo(DeviceType.MotorController, Manufacturer.REVRobotics);
    }
    
    private CanInfo canInfo;

    //@Override
    public CanInfo getCanInfo() {
        return canInfo;
    }
/*
    public static final CANSparkMax DRIVE_FRONT_LEFT = new CANSparkMax(15, CANSparkMaxLowLevel.MotorType.kBrushless);
    Motors.ANGLE_FRONT_LEFT.setInverted(DriveConstants.FrontLeftTurningMotorReversed);
    Motors.ANGLE_FRONT_LEFT.setIdleMode(DriveConstants.FrontLeftTurningMotorBrake);

    driveMotor.setClosedLoopRampRate(ModuleConstants.CLOSED_LOOP_RAMP_RATE);
    driveMotor.setSmartCurrentLimit(ModuleConstants.SMART_CURRENT_LIMIT);

    private final PIDController turningPIDController = new PIDController(ModuleConstants.MODULE_TURN_PID_CONTROLLER_P, ModuleConstants.MODULE_TURN_PID_CONTROLLER_I, ModuleConstants.MODULE_TURN_PID_CONTROLLER_D);
    this.turningPIDController.enableContinuousInput(-Math.PI, Math.PI);

    this.drivePIDController = this.driveMotor.getPIDController();
    this.drivePIDController.setP(ModuleConstants.MODULE_DRIVE_PID_CONTROLLER_P);
    this.drivePIDController.setI(ModuleConstants.MODULE_DRIVE_PID_CONTROLLER_I);
    this.drivePIDController.setD(ModuleConstants.MODULE_DRIVE_PID_CONTROLLER_D);
    this.drivePIDController.setFF(ModuleConstants.MODULE_DRIVE_PID_CONTROLLER_F);


  public REVLibError follow(final CANSparkMax leader, boolean invert) {


    */
}
