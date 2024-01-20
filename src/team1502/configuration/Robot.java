package team1502.configuration;

import java.util.function.Function;

import team1502.configuration.Builder.Builder;
import team1502.configuration.Builder.PartBuilder;
import team1502.configuration.Builder.RobotBuilder;
import team1502.configuration.CAN.CanMap;
import team1502.configuration.Factory.DeviceFactory;
import team1502.configuration.Parts.Part;
import team1502.configuration.Factory.PartFactory;

public class Robot /*extends Part*/ {

    public String name;
    private PartFactory _partFactory = new PartFactory();
    private RobotBuilder _robotBuilder;
    private Evaluator _evaluator;
    //private DeviceFactory _deviceFactory = new DeviceFactory(this);
    
    //private Equipment _equipment = new Equipment(this);
    //private Devices _devices = new Devices(_equipment);
    //private Devices _devices = new Devices(this);
    
    public static Robot Create(String name, Function<Robot, Robot> fn) {
        var robot = new Robot();
        robot.name = name;
        return fn.apply(robot);

    }

    static Robot Create(Function<Robot, Robot> fn) {
        var robot = new Robot();
        return fn.apply(robot);

    }

    private RobotBuilder getBuilder() {
        if (_robotBuilder == null) {
            _robotBuilder = RobotBuilder.Create(_partFactory);
        }
        return _robotBuilder;
    }
    
    private Evaluator getEvaluator() {
        if (_evaluator == null) {
            _evaluator = new Evaluator(getBuilder());
        }
        return _evaluator;
    }

    //public Part getPart(String name) {return _robotBuilder.getPart(name); }
    public CanMap getCanMap() {return _robotBuilder.getCanMap();}

    public Robot Parts(Function<PartFactory, PartFactory> fn) {
        fn.apply(_partFactory);
        return this;
    }
    
    public Robot Build(Function<RobotBuilder, RobotBuilder> fn) {
        fn.apply(getBuilder());
        return this;
    }

    public Evaluator Values() {
        return getEvaluator();
    }
    public Robot Values(Function<Evaluator, Evaluator> fn) {
        fn.apply(getEvaluator());
        return this;
    }
    public Object getValue(String valueName, String partName) {
        return Values().getValue(valueName, partName);
    }
/*
 * 
    // public Robot Build2(String name, Function<Part, Part> fn) {
    //     var builder = _partFactory.getBuilder(name);
    //     Part part = builder.buildPart(_robotBuilder);
    //     fn.apply(part);
    //     addPart(part);
    //     return this;   
    // }

    public Part createPart(String name) {
        var builder = _partFactory.getBuilder(name);
        Part part = builder.buildPart(_robotBuilder);
        return part;
    }
 */

/*

    public Robot Build(SupportedDevices device) {
        var p = device.createEquipment();
        // switch (device) {
        //     case Pigeon2:
        //         CreateGyro(device, g -> g
        //             .Manufacturer(Manufacturer.CTRElectronics)
        //             .PowerProfile(0.4));
        //         break;
        
        //     default:
        //         break;
        // }
        return this;
    }

    
    // public Robot SwerveDrive(Function<SwerveDrive, SwerveDrive> fn) {
        //     fn.apply(_devices);
        //     return this;
        // }
        public Robot Install(Function<DeviceFactory, DeviceFactory> fn) {
            fn.apply(_deviceFactory);
            return this;
        }
 */
    
}
