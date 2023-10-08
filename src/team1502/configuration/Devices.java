package team1502.configuration;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.spi.ResourceBundleControlProvider;

public class Devices {

    public String name;

    public Devices() {}
    public Devices(String name) {this.name = name;}

    // public Devices Common(Runnable fn) {
    //     return this;
    // }
    // public Devices Common(Consumer<Devices> fn) {
    //     return this;
    // }

    public Devices Include(Runnable fn) {
        fn.run();
        return this;
    }
    public Devices Device(String name, double power) {
        return this;
    }
    public Devices Device(String name, String device) { // one of known devices
        return this;
    }

    public Devices Motor(String name, String motor) {
        return this;
    }
    public Devices RoboRIO(String name, String version, Function<RoboRio, RoboRio> fn) {
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
    public Devices Gyro(String name, String gyro) {
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
    public Devices DC(String name, Function<Devices,Devices> fn) {
        return this;
    }
    public Devices MPM(String name, Function<MiniPowerModule, MiniPowerModule> fn) {
        return this;
    }
    public Devices RIO(String name, Function<RoboRio, RoboRio> fn) {
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

    private HashMap<String, Controller> motorMap = new HashMap<>(); 
    public Devices MotorController(String name, Function<MotorController, Controller> fn) { //throws Exception {
        if (motorMap.containsKey(name)) throw new IllegalArgumentException(name + " already defined for MotorController");
        
        motorMap.put(name, fn.apply(new MotorController(name)));
        return this;  
    }

    // these might be device-type: motor controller ???
    private HashMap<String, Controller> sensorMap = new HashMap<>(); 
    public Devices CANSensor(String name, Function<CANSensor, Controller> fn) { //throws Exception {
        if (sensorMap.containsKey(name)) throw new IllegalArgumentException(name + " already defined for CAN sensor");
        
        sensorMap.put(name, fn.apply(new CANSensor(name)));
        return this;  
    }
        
    private HashMap<String, Controller> accelerometerMap = new HashMap<>(); 
    public Devices Accelerometer(String name, Function<Accelerometer, Controller> fn) { //throws Exception {
        if (accelerometerMap.containsKey(name)) throw new IllegalArgumentException(name + " already defined for accelerometer");
        
        accelerometerMap.put(name, fn.apply(new Accelerometer(name)));
        return this;  
    }
        
    private HashMap<String, Controller> gyroMap = new HashMap<>(); 
    public Devices Gyro(String name, Function<Gyro, Controller> fn) { //throws Exception {
        if (gyroMap.containsKey(name)) throw new IllegalArgumentException(name + " already defined for gyro");
        
        gyroMap.put(name, fn.apply(new Gyro(name)));
        return this;  
    }


}
