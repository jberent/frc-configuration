package team1502.old;

import java.util.function.Function;

import team1502.configuration.CAN.CanMap;
import team1502.configuration.Controllers.Controller;
import team1502.configuration.Controllers.PowerDistributionModule;

public class Robot {
    public String name;
    private Devices _devices = new Devices(this);

    public static Robot Create(String name, Function<Robot, Robot> fn) {
        var robot = new Robot();
        robot.name = name;
        return fn.apply(robot);

    }
    public CanMap getCanMap() {return _devices.getCanMap();}
    /*
    * 
    public Part getPart(String name) {
        return _devices.getPart(name);
    }
     
     public Robot Equipment(Function<Equipment, Equipment> fn) {
         fn.apply(_equipment);
         return this;
        }
        
        */
    public Robot Devices(Function<Devices, Devices> fn) {
        fn.apply(_devices);
        return this;
    }
    public Robot PDP(String name, Function<PowerDistributionModule, PowerDistributionModule> fn) {
        return this;
    }
    public Robot PDP(Function<PowerDistributionModule, PowerDistributionModule> fn) {
        return this;
    }
    
    public Robot CAN(Function<CANConfiguration, CANConfiguration> fn) {
        return this;
    }
    
    public Controller getGyro(String name) {
        return _devices.getGyro(name);
    }
    
}
