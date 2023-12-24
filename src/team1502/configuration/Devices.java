package team1502.configuration;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.spi.ResourceBundleControlProvider;

import team1502.configuration.CAN.CanMap;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.ICAN;
import team1502.configuration.CAN.Manufacturer;
import team1502.configuration.Controllers.Accelerometer;
import team1502.configuration.Controllers.GearToothSensor;
import team1502.configuration.Controllers.Controller;
import team1502.configuration.Controllers.GyroSensor;
import team1502.configuration.Controllers.MotorController;
import team1502.configuration.Controllers.PneumaticsController;
import team1502.configuration.Factory.GyroPart;
import team1502.configuration.Factory.Part;

public class Devices {
    private Robot _robot;
    private HashMap<String, Part> _partMap = new HashMap<>(); 


    private Equipment _equipment;
    
    public String name; // ?
    
    
    public Devices() {}
    public Devices(Equipment equipment) {}
    public Devices(Robot robot) {_robot = robot;}
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
    public Devices PH(String name, Function<PneumaticsController, PneumaticsController> fn) {
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
        // SwerveDrive swerveDrive = new SwerveDrive(this);
        // swerveDrive.Install(fn);
        // Part part = _robot.createPart(name);
        // part = fn.apply(part);
        // install(part);
        // return this;

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
    public Devices CANSensor(String name, Function<GearToothSensor, Controller> fn) { //throws Exception {
        if (sensorMap.containsKey(name)) throw new IllegalArgumentException(name + " already defined for CAN sensor");
        
        sensorMap.put(name, fn.apply(new GearToothSensor(name)));
        return this;  
    }
        
    private HashMap<String, Controller> accelerometerMap = new HashMap<>(); 
    public Devices Accelerometer(String name, Function<Accelerometer, Controller> fn) { //throws Exception {
        if (accelerometerMap.containsKey(name)) throw new IllegalArgumentException(name + " already defined for accelerometer");
        
        accelerometerMap.put(name, fn.apply(new Accelerometer(name)));
        return this;  
    }
        
    private HashMap<String, GyroSensor> gyroMap = new HashMap<>(); 
    public Devices DefineGyro(SupportedDevices device, Function<GyroSensor, GyroSensor> fn) { //throws Exception {
        var name = device.name();
        if (gyroMap.containsKey(name)) throw new IllegalArgumentException(name + " already defined for gyro");
        
        gyroMap.put(name, (GyroSensor)fn.apply(new GyroSensor(name)).Device(device));
        return this;
    }

    public GyroSensor CreateGyro(SupportedDevices device, Function<GyroSensor, ?> fn) { //throws Exception {
        GyroSensor gyro = CreateGyro(device);
        fn.apply(gyro);
        return gyro;
    }

    public GyroSensor CreateGyro(SupportedDevices device) { //throws Exception {
        var name = device.name();
        if (gyroMap.containsKey(name)) throw new IllegalArgumentException(name + " already defined for gyro");
        GyroSensor gyro =  (GyroSensor)(new GyroSensor(name).Device(device));
        gyroMap.put(name, gyro);
        return gyro;
    }
    
    public Devices Gyro(String name, Function<GyroSensor, GyroSensor> fn) { //throws Exception {
        if (gyroMap.containsKey(name)) throw new IllegalArgumentException(name + " already defined for gyro");        
        gyroMap.put(name, fn.apply(new GyroSensor(name)));
        return this;  
    }

    public Devices GyroSensor(int canId) {
        return this;
    }

    public GyroSensor getGyro(String name) {
        return gyroMap.get(name);
    }

    public Devices Define(SupportedDevices device) {
        switch (device) {
            case Pigeon2:
                CreateGyro(device, g -> g
                    .Manufacturer(Manufacturer.CTRElectronics)
                    .PowerProfile(0.4));
                break;
        
            default:
                break;
        }
        return this;
    }
    public Devices Define(DeviceType device) {
        new Controller(device, null);
        return this;
    }

    public Part getPart(String name) {
        return _partMap.get(name);
    }

    private CanMap _canMap = new CanMap();
    public CanMap getCanMap() {return _canMap;}

    public Devices Install(String deviceId, String partName, Function<Part, Part> fn)
    {        
        Part part = _robot.createPart(partName);
        part.name= deviceId;
        part = fn.apply(part);
        return install(part);
    }
    public Devices Install(String name, Function<Part, Part> fn)
    {        
        Part part = _robot.createPart(name);
        part = fn.apply(part);
        install(part);
        return this;
    }    

    public Devices InstallGyro(String name, Function<GyroPart, Part> fn)
    {        
        GyroPart part = (GyroPart)_robot.createPart(name);
        fn.apply(part);
        install(part);
        return this;
    }    

    private Devices install(Part part) {
        _partMap.put(part.name, part);
        if (part.hasCanInfo()) {
            _canMap.install(part);
        }
        return this;
    }


}
