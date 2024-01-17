package team1502.configuration.Builder.Controllers;

import java.util.function.Function;

import team1502.configuration.Builder.Builder;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.Manufacturer;

public class Accelerometer extends Controller {
    public Accelerometer(String name, Function<Accelerometer, Builder> fn) {
        super(name, DeviceType.Accelerometer, fn);
    }
    public Accelerometer(String name, Manufacturer manufacturer) {
        super(name, DeviceType.Accelerometer, manufacturer);
    }
}
