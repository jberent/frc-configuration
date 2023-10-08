package test.java;

import org.junit.jupiter.api.Test;

import team1502.configuration.CANConfiguration;
import team1502.configuration.Motor;
import team1502.configuration.Robot;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    public void Test1() throws Exception {
        Motor FrontLeftDriveMotor = new Motor();

        var CAN = new CANConfiguration();
        CAN.Id(1, FrontLeftDriveMotor);
        assertEquals(1, FrontLeftDriveMotor.GetCanId());

    }

    public static void Log(Robot robot)
    {
        robot.
    }
}
