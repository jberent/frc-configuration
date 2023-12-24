package team1502.configuration;

import java.util.function.Function;

import team1502.configuration.Builder.PartBuilder;
import team1502.configuration.Builder.RobotBuilder;
import team1502.configuration.CAN.CanMap;
import team1502.configuration.Controllers.Controller;
import team1502.configuration.Controllers.PowerDistributionModule;
import team1502.configuration.Factory.DeviceFactory;
import team1502.configuration.Factory.Part;
import team1502.configuration.Factory.PartFactory;

public class Robot {

    public String name;
    private PartFactory _partFactory = new PartFactory();
    private DeviceFactory _deviceFactory = new DeviceFactory(this);
    
    private Equipment _equipment = new Equipment(this);
    //private Devices _devices = new Devices(_equipment);
    private Devices _devices = new Devices(this);
    
    public static Robot Create(String name, Function<Robot, Robot> fn) {
        var robot = new Robot();
        robot.name = name;
        return fn.apply(robot);

    }

    static Robot Create(Function<Robot, Robot> fn) {
        var robot = new Robot();
        return fn.apply(robot);

    }
    
    public Robot Parts(Function<PartFactory, PartFactory> fn) {
        fn.apply(_partFactory);
        return this;
    }

    public Part createPart(String name) {
        PartBuilder partBuilder = _partFactory.getBuilder(name);
        Part part = partBuilder.buildPart();
        return part;
    }

    public Part getPart(String name) {
        return _devices.getPart(name);
    }
    
    public CanMap getCanMap() {return _devices.getCanMap();}

    public Robot Equipment(Function<Equipment, Equipment> fn) {
        fn.apply(_equipment);
        return this;
    }

    public Robot Devices(Function<Devices, Devices> fn) {
        fn.apply(_devices);
        return this;
    }
    public Robot Install(Function<DeviceFactory, DeviceFactory> fn) {
        fn.apply(_deviceFactory);
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

    // public Robot SwerveDrive(Function<SwerveDrive, SwerveDrive> fn) {
    //     fn.apply(_devices);
    //     return this;
    // }
    public Robot Build(Function<RobotBuilder, RobotBuilder> fn) {
        var robotBuilder = RobotBuilder.Create(_partFactory);
        fn.apply(robotBuilder);
        return this;
    }

}
