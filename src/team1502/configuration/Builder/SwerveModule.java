package team1502.configuration.Builder;

import java.util.function.Function;

import team1502.configuration.Builder.Controllers.CANCoder;
import team1502.configuration.Builder.Controllers.MotorController;
import team1502.configuration.CAN.Manufacturer;

public class SwerveModule extends Builder {
    private static final String NAME = "SwerveModule";
    private static final String AbsoluteEncoder = "Encoder";
    private static final String TurningMotor = "TurningMotor";
    private static final String DrivingMotor = "DrivingMotor";
    private static final String isReversed = "isReversed";
    
    public SwerveModule() {}

    public SwerveModule(Function<SwerveModule, Builder> fn) {
        super(NAME, NAME, fn);
    }
    public CANCoder Encoder() {return new CANCoder(getPart(AbsoluteEncoder));}
    public MotorController TurningMotor() { return new MotorController(getPart(TurningMotor)); }
    public SwerveModule TurningMotor(Manufacturer manufacturer, Function<MotorController, Builder> fn) {
        Install(new MotorController(TurningMotor, manufacturer, fn));
        return this;
    }
    
    public MotorController DrivingMotor() { return new MotorController(getPart(DrivingMotor)); }
    public SwerveModule DrivingMotor(Manufacturer manufacturer, Function<MotorController, Builder> fn) {
        Install(new MotorController(DrivingMotor, manufacturer, fn));
        return this;
    }

    public boolean Reversed() {
        var result = getBoolean(isReversed);
        return result == null ? false : result;
    }
    public SwerveModule Reversed(boolean value) {
        TurningMotor().Reversed(value);
        DrivingMotor().Reversed(value);
        return this;
    }

    public int CanNumberEncoder() { return 0; }
    public int CanNumberTurningMotor() { return TurningMotor().CanNumber(); }
    public int CanNumberDrivingMotor() { return DrivingMotor().CanNumber(); }
    public SwerveModule CanNumbers(int absoluteEncoder, int turningMotor, int drivingMotor) {
        //Encoder().CanNumber(absoluteEncoder);
        TurningMotor().CanNumber(turningMotor);
        DrivingMotor().CanNumber(drivingMotor);
        return this;
    }
}
