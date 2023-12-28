package team1502.configuration.Parts;

import team1502.configuration.CAN.CanInfo;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.Manufacturer;
import team1502.configuration.Parts.Part;

public class GyroPart extends Part {
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

    // public GyroPart(String name, Manufacturer mfr) {
    //     super(name);
    //     this.CanInfo(new CanInfo(DeviceType.GyroSensor, mfr));
    // }

    public GyroPart IsReversed(boolean reversed) {
        setValue(ISREVERSED, reversed);
        return this;
    }

    public Boolean isReversed() {
        return getBoolean(ISREVERSED);
    }
}
