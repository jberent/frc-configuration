package team1502.old;

import java.util.function.Function;

public class RoboRio {
    public RoboRio PWM(Function<PulseWidthModulatedBus, PulseWidthModulatedBus> fn) {
        return this;
    }
    public RoboRio DIO(Function<DigitalBus, DigitalBus> fn) {
        return this;
    }

}
