package team1502.configuration.Factory;

import team1502.configuration.Parts.Encoder;
import team1502.configuration.Parts.Part;
import team1502.old.Motor;

public class SwerveModule extends Part {

    public SwerveModule(Motor frontRightDriveMotor, Motor frontRightTurnMotor, Encoder frontRightEncoder) {
    }
    public SwerveModule Motor(String name, String motor) {
        return this;
    }
    public SwerveModule Encoder(String name, String encoder) {
        return this;
    }
/*
  
    public static final double WHEEL_DIAMETER_METERS = Units.inchesToMeters(4);
    public static final double DRIVE_GEAR_RATIO = 1 / ((14.0 / 50.0) * (27.0 / 17.0) * (15.0 / 45.0));
    public static final double STEER_GEAR_RATIO = 1 / ((14.0 / 50.0) * (10.0 / 60.0));
    public static final double DRIVE_METERS_PER_ENCODER_REV = (WHEEL_DIAMETER_METERS * Math.PI) / DRIVE_GEAR_RATIO;
    public static final double DRIVE_ENCODER_MPS_PER_REV = DRIVE_METERS_PER_ENCODER_REV / 60; 

 */
}
