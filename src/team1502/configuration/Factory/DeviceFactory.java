package team1502.configuration.Factory;

import team1502.configuration.Robot;

public class DeviceFactory {
    private Robot _robot;
    private DeviceMap _deviceMap =  new DeviceMap();

    public DeviceFactory(Robot robot){
        _robot = robot;
    }


    // public DeviceFactory SwerveDrive(Function<SwerveDrive, SwerveDrive> fn) {
    //     SwerveDrive swerveDrive = new SwerveDrive(this);
    //     swerveDrive.Install(fn);
    //     Part part = _robot.createPart(name);
    //     part = fn.apply(part);
    //     install(part);
    //     return this;
        
    //     return this;
    // }
    
}