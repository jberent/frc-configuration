package team1502.configuration.Controllers;
import team1502.configuration.PowerProfile;
import team1502.configuration.SupportedDevices;
import team1502.configuration.CAN.CanInfo;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.Manufacturer;
import team1502.configuration.Parts.Part;

// a CAN controller of a certain type, a template (or definition?)
public class Controller extends Part {
    // public DeviceType deviceType; // for CAN assignments
    // public Manufacturer manufacturer; // for CAN assignments
    // public String name;
    // public PowerProfile powerProfile;

    
    public Controller(String name, DeviceType deviceType) {
        CanInfo(new CanInfo(deviceType, null));
    }
    public Controller(String name, DeviceType deviceType, Manufacturer manufacturer) {
        CanInfo(new CanInfo(deviceType, manufacturer));
    }
    public Controller Manufacturer(Manufacturer manufacturer) {
        CanInfo(i->i.Manufacturer(manufacturer));
        return this;
    }
    
    public Controller PowerProfile(double peakPower) {
        PowerProfile(w->w.PeakPower(peakPower));
        return this;
    }

/*
    public Controller(String name, double power) {
        this.PowerProfile(power);
    }
    public Controller(String name, DeviceType deviceType, Manufacturer manufacturer) {
        this.name = name;
        this.deviceType = deviceType;
        this.manufacturer = manufacturer;
    }

    public Controller(DeviceType deviceType, Manufacturer manufacturer) {
        this.name = deviceType.DeviceName;
        this.deviceType = deviceType;
        this.manufacturer = manufacturer;
    }


    public Controller Device(DeviceType deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public Controller PowerProfile(PowerProfile profile) {
        this.powerProfile = profile;
        return this;
    }
 * 
 */
    public Controller Device(SupportedDevices deviceType) {
        //this.deviceType = deviceType;
        return this;
    }
}
