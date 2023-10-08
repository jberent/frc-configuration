package team1502.configuration;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.Manufacturer;

public class Controller {
    DeviceType deviceType; // for CAN assignments
    Manufacturer manufacturer; // for CAN assignments
    String name;

    public Controller(String name, double power) {
        this.PowerProfile(power);
    }
    
    public Controller(String name, DeviceType deviceType) {
        this.deviceType = deviceType;
    }
    
    public Controller(DeviceType deviceType, Manufacturer manufacturer) {
        this.deviceType = deviceType;
        this.manufacturer = manufacturer;
    }

    public Controller Manufacturer(Manufacturer revrobotics) {
        return this;
    }

    public Controller Device(SupportedDevices motor) {
        return this;
    }

    public Controller PowerProfile(double peakPower) {
        return this;
    }

}
