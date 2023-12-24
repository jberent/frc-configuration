package team1502.configuration.Factory;

import java.util.HashMap;
import java.util.function.Function;

import team1502.configuration.Builder.PartBuilder;
import team1502.configuration.CAN.Manufacturer;

public class PartFactory {
    private HashMap<String, PartBuilder> _partMap = new HashMap<>(); 

    public PartFactory Part(String name, Function<Part, Part> fn) {
        _partMap.put(name,  new PartBuilder<Part>(name, nm -> new Part(name), fn));

        return this;
    }

    public PartFactory Gyro(String name, Manufacturer mfr, Function<GyroPart, Part> fn) {
        _partMap.put(name, new PartBuilder<GyroPart>(name, nm -> new GyroPart(name, mfr), fn));
        return this;
    }

    public PartBuilder getBuilder(String name) {
        return _partMap.get(name);
    }

    public PartFactory MotorController(String name, Function<Part, Part> fn) {
        // get parent name for full path
        return Part(name, fn);
    }
}
