package team1502.configuration;

public class SwerveModule extends ConfigurationItem {

    public SwerveModule(Motor frontRightDriveMotor, Motor frontRightTurnMotor, Encoder frontRightEncoder) {
    }
    public SwerveModule Motor(String name) {
        return this;
    }
    public SwerveModule Encoder(String name) {
        return this;
    }

}
