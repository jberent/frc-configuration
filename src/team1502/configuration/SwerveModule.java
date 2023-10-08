package team1502.configuration;

public class SwerveModule extends ConfigurationItem {

    public SwerveModule(Motor frontRightDriveMotor, Motor frontRightTurnMotor, Encoder frontRightEncoder) {
    }
    public SwerveModule Motor(String name, String motor) {
        return this;
    }
    public SwerveModule Encoder(String name, String encoder) {
        return this;
    }

}
