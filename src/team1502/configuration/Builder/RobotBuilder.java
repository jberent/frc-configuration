package team1502.configuration.Builder;

import java.util.HashMap;
import java.util.function.Function;

import team1502.configuration.CAN.CanMap;
import team1502.configuration.Parts.Part;
import team1502.configuration.Controllers.GyroSensor;
import team1502.configuration.Factory.PartFactory;

public class RobotBuilder /*extends Builder*/{
    private PartFactory _partFactory;
    private HashMap<String, Part> _partMap = new HashMap<>(); 
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


    public Part getPart(String name) {
        return _partMap.get(name);
    }

    public CanMap getCanMap() {return _canMap;}

    //@Override
    private Part createPart(String newName, String partName) {
        var partBuilder = _partFactory.getBuilder(partName);
        Part part = partBuilder.buildPart(this);
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
    public RobotBuilder GyroSensor(String name, int canNumber, Function<GyroSensor, Part> fn)
    {        
        var part = createPart(name);
        fn.apply(part);
        install(part);
        return this;
    }    


    // public RobotBuilder SwerveDrive(Function<SwerveBuilder, SwerveBuilder> fn) {
    //     var swerve = new SwerveBuilder(this);
    //     fn.apply(swerve);
    //     return this;
    // }
}
