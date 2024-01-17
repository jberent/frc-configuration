package team1502.configuration.Factory;

import java.util.HashMap;
import java.util.function.Function;

import team1502.configuration.Builder.Builder;
import team1502.configuration.Builder.IBuild;
import team1502.configuration.Builder.Motor;
import team1502.configuration.Builder.PartBuilder;
import team1502.configuration.Builder.RobotBuilder;
import team1502.configuration.Builder.Controllers.GyroSensor;
import team1502.configuration.Builder.Controllers.MotorController;
import team1502.configuration.CAN.Manufacturer;
import team1502.configuration.Parts.Part;

public class PartFactory {
    private HashMap<String, PartBuilder> _partMap = new HashMap<>(); 
    private HashMap<String, Builder> _builderMap = new HashMap<>(); 
    
    private IBuild _build;
    private RobotBuilder _robotBuilder;
    public void setBuilder(RobotBuilder rb) {
        _robotBuilder=rb;
    }
    
    public PartFactory Part(String name, Function<Builder, Builder> fn) {
        _builderMap.put(name,  new Builder(name, _build, fn));
        return this;
    }

    public Builder getBuilder(String name) {
        return _builderMap.get(name);
    }

    public PartFactory Motor(String name, Function<Motor, Builder> fn) {
        _builderMap.put(name,  new Motor(name, fn));
        return this;
    }
    public PartFactory MotorController(String name, Function<MotorController, Builder> fn) {
        _builderMap.put(name,  new MotorController(name, fn));
        return this;
    }

    /*
     * 
    public PartFactory Part(String name, Function<Part, Part> fn) {
        _partMap.put(name,  new PartBuilder<Part>(name, nm -> new Part(name, _robotBuilder), fn));

        return this;
    }

    public PartFactory Gyro(String name, Manufacturer manufacturer, Function<GyroSensor, Part> fn) {
        _partMap.put(name, new PartBuilder<GyroSensor>(name, nm -> new GyroSensor(name, manufacturer), fn));
        return this;
    }

    public PartFactory MotorController(String name, Manufacturer manufacturer, Function<MotorController, Part> fn) {
        _partMap.put(name, new PartBuilder<MotorController>(name, nm -> new MotorController(name, manufacturer), fn));
        return this;
    }

    public PartFactory MotorController(String name, Function<Part, Part> fn) {
        // get parent name for full path
        return Part(name, fn);
    }
     */
}
