package team1502.configuration;

import java.util.function.Consumer;
import java.util.function.Function;

public class Devices {

    public String name;

    public Devices() {}
    public Devices(String name) {this.name = name;}

    public Devices Common(Runnable fn) {
        return this;
    }
    public Devices Common(Consumer<Devices> fn) {
        return this;
    }

    public Devices Motor(String name) {
        return this;
    }
    public Devices RoboRIO(String name, String version) {
        return this;
    }
    public Devices Radio(String name) {
        return this;
    }
    public Devices RadioPowerModule(String name) {
        return this;
    }
    public Devices RadioBarrelJack(String name) {
        return this;
    }
    public Devices Pi(String name, String ipAddress) {
        return this;
    }
    public Devices Encoder(String name) {
        return this;
    }
    public Devices EthernetSwitch(String name) {
        return this;
    }
    public Devices Gyro(String name) {
        return this;
    }
    public Devices PneumaticsHub(String name) {
        return this;
    }
    public Devices PH(String name, Function<PneumaticsHub, PneumaticsHub> fn) {
        return this;
    }
    public Devices Compressor(String name) {
        return this;
    }
    public Devices Solenoid(String name) {
        return this;
    }
    public Devices DoubleSolenoid(String name) {
        return this;
    }
    public Devices DC(String name) {
        return this;
    }
    public Devices MPM(String name, Function<MiniPowerModule, MiniPowerModule> fn) {
        return this;
    }
    public Devices SwerveDrive(Function<SwerveDrive, SwerveDrive> fn) {
        return this;
    }
    public Devices Subsystem(String name, Function<Devices, Devices> fn) {
        var subsystem = new Devices(name);
        fn.apply(subsystem);
        return this;
    }
}
