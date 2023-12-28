package team1502.configuration.Builder;

import java.util.HashMap;
import java.util.function.Function;

import team1502.configuration.CAN.CanMap;
import team1502.configuration.Parts.Part;
import team1502.configuration.Factory.PartFactory;

public class RobotBuilder extends Builder{
    private PartFactory _partFactory;
    private HashMap<String, Part> _partMap = new HashMap<>(); 
    private CanMap _canMap = new CanMap();
    
    private RobotBuilder(PartFactory partFactory) {
        super(null);
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

    @Override
    public Part createPart(String name) {
        var partBuilder = _partFactory.getBuilder(name);
        Part part = partBuilder.buildPart(this);
        return part;
    }

    @Override
    protected void install(Part part) {
        _partMap.put(part.name, part);
        if (part.hasCanInfo()) {
            _canMap.install(part);
        }
    }


    public RobotBuilder SwerveDrive(Function<SwerveBuilder, SwerveBuilder> fn) {
        var swerve = new SwerveBuilder(this);
        fn.apply(swerve);
        return this;
    }
}
