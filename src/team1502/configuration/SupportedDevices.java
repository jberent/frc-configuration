package team1502.configuration;
import java.util.function.Function;

import team1502.configuration.Builder.Controllers.Controller;
import team1502.configuration.Builder.Controllers.GyroSensor;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.Manufacturer;

public enum SupportedDevices {
    MotorCANSparkMax ( "SparkMax", device -> new Controller("SparkMax", DeviceType.MotorController, Manufacturer.REVRobotics)),
    CANcoder("CANcoder", name-> new GyroSensor(name, Manufacturer.REVRobotics)),
    Pigeon2 (name -> new GyroSensor(name, Manufacturer.CTRElectronics, g -> g)); //.PowerProfile(0.4)));

    public Function<String,Controller> defineEquipment;
    public String equipmentName;

    private SupportedDevices(String name, Function<String,Controller> create) {
       equipmentName = name;
       defineEquipment = create;
    }
    private SupportedDevices(Function<String,Controller> create) {
        equipmentName = this.toString();
        defineEquipment = create;
    }

    public Controller createEquipment()
    {
        return createEquipment(equipmentName);
    }

    public Controller createEquipment(String name)
    {
        PowerProfile power = PowerProfile.getProfile(this);
        Controller equipment = defineEquipment.apply(name);
        if (power != null)
        //equipment.PowerProfile(power);
        return equipment;
    }
}