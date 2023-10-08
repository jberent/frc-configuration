package team1502.configuration;

import java.util.function.Function;

public class MiniPowerModule {
    public MiniPowerModule Ch(int channel, double fuze, String name) {
        return this;
    }
    public MiniPowerModule Ch(int channel, double fuze, Function<Devices,Devices> fn) {
        return this;
    }
    public MiniPowerModule Encoder(int channel, String name) {
        return this;
    }
    public MiniPowerModule Gyro(int channel, double fuze, String name) {
        return this;
    }

    
}
