package team1502.configuration.Builder;

import java.util.HashMap;
import java.util.function.Function;

import team1502.configuration.Builder.Controllers.GyroSensor;
import team1502.configuration.Builder.Controllers.MotorController;
import team1502.configuration.CAN.CanMap;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.Parts.Part;
import team1502.configuration.Factory.PartFactory;

public class RobotBuilder implements IBuild /*extends Builder*/{
    private PartFactory _partFactory;
    //private HashMap<String, Part> _partMap = new HashMap<>(); 
    private HashMap<String, Builder> _buildMap = new HashMap<>(); // built parts wrapped in builder
    private HashMap<String, Function<String,Object>> _valueMap = new HashMap<>(); 
    
    private CanMap _canMap = new CanMap();
    
    private RobotBuilder(PartFactory partFactory) {
        //super(null);
        _partFactory = partFactory;
    }

    public static RobotBuilder Create(PartFactory partFactory) {
        var robot = new RobotBuilder(partFactory);
        return robot;
    }

    public static RobotBuilder Create(PartFactory partFactory, Function<RobotBuilder, RobotBuilder> fn) {
        var robot = new RobotBuilder(partFactory);
        fn.apply(robot);
        return robot;
    }

    // IBuild INTERFACE
    public void install(Builder builder) {
        _buildMap.put(builder.getName(), builder);
    }

    public Builder getInstalled(String name) {
        return _buildMap.get(name);
    }

    @Override // IBuild
    public Builder createBuilder(String partName, Function<? extends Builder, Builder> fn) {
        var builder = _partFactory.getBuilder(partName);
        builder.create((IBuild)this, partName, fn);
        return builder;
    }

    @Override // IBuild
    public Builder modifyBuilder(String partName, Function<? extends Builder, Builder> fn) {
        var builder = getInstalled(partName);
        builder.apply(fn); // fail fast
        // if (builder != null) {
        //     builder.apply(fn);
        // }
        return builder;
    }

    private Builder installBuilder(Builder builder) {
        install(builder);
        return builder;
    }

    private Builder installBuilder(String partName, Builder builder, Function<? extends Builder, Builder> fn) {
        builder.Name(partName);
        _partFactory.useBuilder(builder);
        builder.create((IBuild)this, fn);
        return installBuilder(builder);
    }

    private Builder installBuilder(String partName, Function<? extends Builder, Builder> fn) {
        var builder = createBuilder(partName, fn);
        return installBuilder(builder);
    }

    // BUILDER AND BUILDER SUBCLASSES
    
    public RobotBuilder Part(DeviceType deviceType, String partName, Function<Builder, Builder> fn) {
        return Part(deviceType.toString(), partName, fn);
    }

    public RobotBuilder Part(String buildType, String partName, Function<Builder, Builder> fn) {
        var builder = new Builder(buildType, partName, fn);
        builder.create((IBuild)this);
        install(builder);
        return this;
    }

    public RobotBuilder Part(String partName, Function<Builder, Builder> fn) {
        installBuilder(partName, fn);
        return this;

    }

    public RobotBuilder Motor(String partName, Function<Motor, Builder> fn) {        
        installBuilder(partName, new Motor(), fn);
        return this;
    }    
    public RobotBuilder MotorController(String partName, Function<team1502.configuration.Builder.Controllers.MotorController, Builder> fn) {        
        installBuilder(partName, new MotorController(), fn);
        return this;
    }    


    // VALUES and VALUE EXPRESSIONS

    public CanMap getCanMap() {return _canMap;}

    // Eval as-a Motor as long is in the right "Shape"
    public RobotBuilder Motor(String valueName, String partName, Function<Motor, Object> fn) {
        _valueMap.put(valueName, s -> getValue(partName, new Motor(), fn));   
        return this;
    }
    public RobotBuilder MotorController(String valueName, String partName, Function<MotorController, Object> fn) {
        _valueMap.put(valueName, s -> getValue(partName, new MotorController(), fn));   
        return this;
    }
    


    public RobotBuilder Value(String valueName, String partName, Function<? extends Builder, Object> fn) {
        _valueMap.put(valueName, s -> getValue(partName, fn));   
        return this;
    }
    public Object Value(String valueName) {
        var fn = _valueMap.get(valueName);
        return fn.apply(valueName);
    }

    private <T extends Builder> Object getValue(String partName, T builder, Function<T, Object> fn) {
        var susBuilder = getInstalled(partName);
        return susBuilder.evalWith(fn, builder);
    
    }
    private <T extends Builder> Object getValue(String partName, Function<T, Object> fn) {
        var builder = getInstalled(partName);
        return fn.apply((T)builder);
    }

/*

    @Override // IBuild
    public Part getPart(String name) {
        return _partMap.get(name);
    }
    @Override // IBuild
    public Part createPart(String newName, String partName) {
        var builder = _partFactory.getBuilder(partName);
        Part part = builder.build((IBuild)this, newName);
        part.name= newName;
        return part;
    }
    private Part createPart(String partName) {
        return createPart(partName, partName);
    }

    //@Override
    protected void install(Part part) {
        _partMap.put(part.name, part);
        if (part.hasCanInfo()) {
            _canMap.install(part);
        }
    }
    private Builder getBuilder(String partName) {
        var builder = getInstalled(partName);
        if (builder == null) {
            builder = _partFactory.getBuilder(partName);
            builder.create((IBuild)this, partName);
            builder.install((IBuild)this);
        }
        return builder;
    }
 
    public RobotBuilder Build(String newName, String partName, Function<Part, Part> fn)
    {        
        var part = createPart(newName, partName);
        fn.apply(part);
        install(part);
        return this;
    }

    public RobotBuilder Build(String name, Function<Part, Part> fn)
    {        
        return Build(name, name, fn);
    }    

    public RobotBuilder Part(String name, Function<Part, Part> fn)
    {        
        return Build(name, name, fn);
    }

 */
    // public RobotBuilder GyroSensor(String name, int canNumber, Function<GyroSensor, Part> fn)
    // {        
    //     var part = createPart(name);
    //     fn.apply(part);
    //     install(part);
    //     return this;
    // }    

    // public RobotBuilder SwerveDrive(Function<SwerveBuilder, SwerveBuilder> fn) {
    //     var swerve = new SwerveBuilder(this);
    //     fn.apply(swerve);
    //     return this;
    // }
}
