package team1502.configuration.Controllers;

import java.util.function.Function;

import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.Manufacturer;

public class GyroSensor extends Controller {
    public GyroSensor(String name) {
        super(name, DeviceType.GyroSensor);
    }
    public GyroSensor(String name, Manufacturer manufacturer) {
        super(name, DeviceType.GyroSensor, manufacturer);
    }
    public GyroSensor(String name, Manufacturer manufacturer, Function<GyroSensor,?> fn) {
        super(name, DeviceType.GyroSensor, manufacturer);
        fn.apply(this);
    }

    public GyroSensor IsReversed(boolean isReversed) {
        return this;
    }
}
