package team1502.configuration.Builder.Controllers;

import java.util.function.Function;

import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.Manufacturer;

public class GyroSensor extends Controller {
    public GyroSensor(String name) {
        super(name, DeviceType.GyroSensor);
    }
    public GyroSensor(String name, Manufacturer manufacturer) {
        super(name, DeviceType.GyroSensor, manufacturer);
    }
    public GyroSensor(String name, Manufacturer manufacturer, Function<GyroSensor,?> fn) {
        super(name, DeviceType.GyroSensor, manufacturer);
        fn.apply(this);
    }

/*
    // Pigeon2 configuration items:
    public double MountPoseYaw = 0;
    public double MountPosePitch = 0;
    public double MountPoseRoll = 0;
    public boolean EnableCompass = false;
    public boolean DisableTemperatureCompensation = false;
    public boolean DisableNoMotionCalibration = false;
    public double XAxisGyroError = 0;
    public double YAxisGyroError = 0;
    public double ZAxisGyroError = 0;
*/

    public static final String ISREVERSED = "isReversed";
    public GyroSensor IsReversed(boolean reversed) {
        setValue(ISREVERSED, reversed);
        return this;
    }

    public Boolean isReversed() {
        return getBoolean(ISREVERSED);
    }

}
