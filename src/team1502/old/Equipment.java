package team1502.old;

import java.util.HashMap;

import team1502.configuration.Robot;
import team1502.configuration.SupportedDevices;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.Manufacturer;
import team1502.configuration.Controllers.Controller;

public class Equipment {
    private Robot _robot;
    private HashMap<String, Controller> _equipmentMap = new HashMap<>(); 

    public Equipment(Robot robot) {
        _robot = robot;
    }

    public Equipment Add(Controller controller) { //throws Exception {
        if (_equipmentMap.containsKey(controller.name)) throw new IllegalArgumentException(controller.name + " already defined for MotorController");
        _equipmentMap.put(controller.name, controller);
        return this;  
    }

    public Equipment Define(SupportedDevices known) {
        return Add(known.createEquipment());
    }

    public Equipment Define(String name, SupportedDevices known) {
        return Add(known.createEquipment(name));
    }

    public Equipment Define(DeviceType device, Manufacturer manufacturer) {
        return Add(new Controller(device.toString(), device,  manufacturer));
    }

}
