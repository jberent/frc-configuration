package team1502.configuration.Builders;

import java.util.function.Function;

public class SwerveDrive extends Builder {
    private static final String NAME = "SwerveDrive";
    private static final String MODULE = "SwerveModule";

    public SwerveDrive() { super(NAME); }
    public SwerveDrive(Function<SwerveDrive, Builder> fn) { super(NAME, NAME, fn); }

    public SwerveDrive SwerveModule(String name, Function<SwerveModule, Builder> fn) {
        InstallPiece(name, MODULE, fn);
        return this;
    }

    @Override
    public Builder createBuilder() {
        return new SwerveDrive((Function<SwerveDrive, Builder>)buildFunction);
    }
    public Chassis Chassis(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Chassis'");
    }

}
/*
  
  public static final double WHEEL_BASE_WIDTH = Units.inchesToMeters(23.25);
  public static final double WHEEL_BASE_LENGTH = Units.inchesToMeters(23.25);


  public static final Translation2d FRONT_LEFT_MODULE = new Translation2d(WHEEL_BASE_LENGTH/2, WHEEL_BASE_WIDTH/2);
  public static final Translation2d FRONT_RIGHT_MODULE = new Translation2d(WHEEL_BASE_LENGTH/2, -WHEEL_BASE_WIDTH/2);
  public static final Translation2d BACK_LEFT_MODULE = new Translation2d(-WHEEL_BASE_LENGTH/2, WHEEL_BASE_WIDTH/2);
  public static final Translation2d BACK_RIGHT_MODULE = new Translation2d(-WHEEL_BASE_LENGTH/2, -WHEEL_BASE_WIDTH/2);

    public static final double GO_STRAIGHT_GAIN = 0.02;

 */
/*
  
    public static final double WHEEL_DIAMETER_METERS = Units.inchesToMeters(4);
    public static final double DRIVE_GEAR_RATIO = 1 / ((14.0 / 50.0) * (27.0 / 17.0) * (15.0 / 45.0));
    public static final double STEER_GEAR_RATIO = 1 / ((14.0 / 50.0) * (10.0 / 60.0));
    public static final double DRIVE_METERS_PER_ENCODER_REV = (WHEEL_DIAMETER_METERS * Math.PI) / DRIVE_GEAR_RATIO;
    public static final double DRIVE_ENCODER_MPS_PER_REV = DRIVE_METERS_PER_ENCODER_REV / 60; 

 */