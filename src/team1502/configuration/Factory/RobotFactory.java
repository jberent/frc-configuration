package team1502.configuration.Factory;

import java.util.function.Function;

public class RobotFactory {
    public String name;
    private PartFactory _partFactory = new PartFactory();

    public static RobotFactory Create(String name, Function<RobotFactory, RobotFactory> fn) {
        var robot = new RobotFactory();
        robot.name = name;
        return fn.apply(robot);

    }
   
}
