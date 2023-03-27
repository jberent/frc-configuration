package team1502.configuration.CAN;

public class CanInfo {
    DeviceType DeviceType;
    Manufacturer Manufacturer;
    int DeviceNumber;

    public CanInfo() {}
    public CanInfo(DeviceType deviceType, Manufacturer manufacturer) {
        this.DeviceType = deviceType;
        this.Manufacturer = manufacturer;
    }
    public CanInfo(DeviceType deviceType, Manufacturer manufacturer, int deviceNumber) {
        this(deviceType, manufacturer);
        this.DeviceNumber = deviceNumber;
    }

    public int GetDeviceTypeId() {return DeviceType.DeviceID;}
    public int GetManufacturerId() {return Manufacturer.ManufacturerID;}
}