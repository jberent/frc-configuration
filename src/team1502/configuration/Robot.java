package team1502.configuration;

import java.util.function.Function;

public class Robot {

    public String name;
    
    public static Robot Create(String name, Function<Robot, Robot> fn) {
        var robot = new Robot();
        robot.name = name;
        return fn.apply(robot);

    }

    static Robot Create(Function<Robot, Robot> fn) {
        var robot = new Robot();
        return fn.apply(robot);

    }
    
    public Robot Devices(Function<Devices, Devices> fn) {
        return this;
    }

    public Robot PDP(String name, Function<PDP, PDP> fn) {
        return this;
    }
    public Robot PDP(Function<PDP, PDP> fn) {
        return this;
    }

    public Robot CAN(Function<CANConfiguration, CANConfiguration> fn) {
        return this;
    }
}
