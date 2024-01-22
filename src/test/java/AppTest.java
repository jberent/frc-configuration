package test.java;

import org.junit.jupiter.api.Test;

import team1502.Wpi.CANSparkMaxLowLevel;
import team1502.Wpi.CANSparkMax.IdleMode;
import team1502.configuration.Robot;
import team1502.configuration.SupportedDevices;
import team1502.configuration.Builders.Controllers.GyroSensor;
import team1502.configuration.Builders.Controllers.MotorController;
import team1502.configuration.CAN.CanMap;
import team1502.configuration.CAN.CanInfo;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.Manufacturer;
import team1502.configuration.Parts.Part;
// import team1502.old.CANConfiguration;
// import team1502.old.Motor;
//import team1502.configuration.Parts.GyroPart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.MessageFormat;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;

public class AppTest {
/*
    @Test
    public void xTest1() throws Exception {
        Motor FrontLeftDriveMotor = new Motor();

        var CAN = new CANConfiguration();
        // CAN.Id(1, FrontLeftDriveMotor);
        // assertEquals(1, FrontLeftDriveMotor.getCanId());

    }
 */
    public void Test0() {
        LogMessage("");
        LogMessage("== TEST 0 ===============");

        var robot0a = SwerveDriveVerbose.CreateRobot0a();
        LogMessage("Robot: {0}", robot0a.name);
        var robot0b = SwerveDriveVerbose.CreateRobot0b();
        LogMessage("Robot: {0}", robot0b.name);
        
        var values = robot0a.Values();
        var mt = values.getValue("NEO.MotorType");
        var mt_2 = values.getValue("NEO.MotorType#2");
        
        // It is expected that a part will be installed from the parts-library
        // and therefore be unique. Sharing/reusing already built parts is not advised.

        // after "build" that Installs an existing built part,
        //  the key is the same, but the part is now re-named
        //  as an examlpe of side-effects of installing existing parts
        assertTrue("not-NEO" == (String)values.getValue("NEO.name"));
        
        assertTrue(5_820.0 == (double)values.getValue("NEO.FreeSpeedRPM"));
        assertTrue(5_820.0 == (double)values.getValue("DriveMotor.Motor*.FreeSpeedRPM"));
        assertTrue(1_000.0 == (double)values.getValue("DriveMotor.Motor.FreeSpeedRPM"));
        LogMessage("Motor.Note = {0} {1}rmp", 
            values.Eval(e -> e.Part("DriveMotor", m -> m.getPart("Motor").getValue("About"))),
            values.Eval(e -> e.Part("DriveMotor", m -> m.getPart("Motor").getValue("freeSpeedRPM"))));
        LogMessage("not-NEO.Note = {0} {1}rpm",
            values.Eval(e -> e.Part("DriveMotor", m -> m.getPart("not-NEO").getValue("About"))),
            values.Eval(e -> e.Part("DriveMotor", m -> m.getPart("not-NEO").getValue("freeSpeedRPM"))));


        values = robot0b.Values();
        assertTrue(null != values.getValue("Motor.Build Note"));
        var b_mt = values.getValue("ArmMotor.MotorType");
        assertTrue(5_820.0 == (double)values.getValue("DriveMotor.Motor.FreeSpeedRPM"));
        
        assertTrue(CANSparkMaxLowLevel.MotorType.kBrushless == mt);
        assertTrue(CANSparkMaxLowLevel.MotorType.kBrushless == mt_2);
        assertTrue(CANSparkMaxLowLevel.MotorType.kBrushless == b_mt);

        LogMessage("");
        LogMessage("^^ TEST 0 ===============");
        LogMessage("");
        Test1();
    }

    private void LogMotorInfo(Robot robot, String motorControllerName) {
        LogMessage("Motor {0} {1} - IdleMode.{2} {3}rpm {4}", motorControllerName,
            robot.getValue("MotorController.Motor.MotorType", motorControllerName),
            robot.getValue("MotorController.IdleMode", motorControllerName),
            robot.getValue("MotorController.Motor.FreeSpeedRPM", motorControllerName),
            (boolean)robot.getValue("MotorController.Reversed", motorControllerName) ? "Reversed" : ""
        );
    }
    private void LogCanInfo(Robot robot, String controllerName) {
        LogMessage("{0} CAN={1} Manufacturer={2}", controllerName,
            robot.getValue("MotorController.CanNumber", controllerName),
            robot.getValue("MotorController.Manufacturer", controllerName)
        );
    }

    public void Test1() {
        LogMessage("");
        LogMessage("== TEST 1 ===============");

        var robot = SwerveDriveVerbose.CreateRobot1();
        LogMessage("Robot: {0}", robot.name);

        LogCanInfo(robot, "Motor#0");
        LogMotorInfo(robot, "Motor#0");

        LogCanInfo(robot, "Motor#1");
        LogMotorInfo(robot, "Motor#1");
        LogCanInfo(robot, "Motor#3");
        LogMotorInfo(robot, "Motor#3");

        LogMessage("{0} GearRatio: {1}", "Motor#0", robot.getValue("MotorController.GearRatio", "Motor#0"));
        LogMessage("{0} GearRatio: {1}", "Motor#1", robot.getValue("MotorController.GearRatio", "Motor#1"));

        LogMessage("{0} PID p:{1}", "Motor#0", robot.getValue("MotorController.PID.p", "Motor#0"));


        /*
         * 
         var robot1a = SwerveDriveVebose.CreateRobot1a();
         var canMap = robot1a.getCanMap();
         var gyro = robot1a.getPart("Pigeon2");
         var turn = robot1a.getPart("TurningMotor");
        var drive = robot1a.getPart("DrivingMotor");
        
        System.out.println();
        ShowCAN(canMap);
        System.out.println();
        System.out.println("=== ROBOT CONFIG ===");
        ShowGyro(gyro);
        ShowMotor(turn);
        ShowMotor(drive);
        
        var robot1b = SwerveDriveVebose.CreateRobot1b();
        canMap = robot1b.getCanMap();
        gyro = robot1b.getPart("Pigeon2");
        turn = robot1b.getPart("TurningMotor");
        drive = robot1b.getPart("DrivingMotor");
        
        System.out.println();
        ShowCAN(canMap);
        System.out.println();
        System.out.println("=== ROBOT CONFIG ===");
        ShowGyro(gyro);
        ShowMotor(turn);
        ShowMotor(drive);
        */
        LogMessage("");
        LogMessage("^^ TEST 1 ===============");
        LogMessage("");

        Test2();
    }

    @Test
    public void Test2()
    {
        LogMessage("");
        LogMessage("== TEST 2 ===============");

        var robot = SwerveDriveVerbose.CreateRobot2();
        LogMessage("Robot: {0}", robot.name);
        LogMessage("Module: {0} MotorType:{1} {2}", "Module#1",
            robot.getValue("SwerveModule.TurningMotor.Motor.MotorType", "Module#1"),
            (boolean)robot.getValue("SwerveModule.TurningMotor.Reversed", "Module#1") ? "Reversed" : ""
            );
        LogMessage("Module: {0} MotorType:{1} {2}", "Module#2",
            robot.getValue("SwerveModule.TurningMotor.Motor.MotorType", "Module#2"),
            (boolean)robot.getValue("SwerveModule.TurningMotor.Reversed", "Module#2") ? "Reversed" : ""
            );

            // var mc = (MotorController)robot.getValue("SwerveModule.TurningMotor", "Module#1");
            // var mtr = (MotorController)robot.getValue("MotorController.Motor", mc);
        var modules = robot.getValue("SwerveDrive.Modules");
        
        LogMessage("");
        LogMessage("^^ TEST 2 ===============");
        LogMessage("");


        /*
         * 
         var robot2a = CreateRobot2a();
         assertEquals((int)robot2a.getPart("Pigeon2").getCanInfo().deviceNumber, 14);
         assertTrue(robot2a.getPart("Pigeon2").getCanInfo().deviceType == DeviceType.GyroSensor);
         assertTrue(robot2a.getPart("Pigeon2").getCanInfo().manufacturer == Manufacturer.CTRElectronics);
         assertEquals(robot2a.getPart("Pigeon2").getPowerProfile().channel, 8);
         assertTrue(robot2a.getPart("Pigeon2").getPowerProfile().peakPower == 0.4);
         assertTrue(robot2a.getPart("Pigeon2").getBoolean("isReversed") == true);
         
         assertEquals((int)robot2a.getPart("Pigeon2b").getCanInfo().deviceNumber,15);
         assertEquals(robot2a.getPart("Pigeon2b").getPowerProfile().channel, 9);
         assertTrue(robot2a.getPart("Pigeon2b").getBoolean(GyroSensor.ISREVERSED) == false);
         
         assertEquals((int)robot2a.getPart("Pigeon3").getCanInfo().deviceNumber,16);
         var tm = robot2a.getPart("TurningMotor");
         
         CanMap canMap = new CanMap();
         Part ci1 = new Part("part 1",null).CanInfo(new CanInfo(DeviceType.Accelerometer, Manufacturer.CTRElectronics, 0));
         Part ci2 = new Part("part 2",null).CanInfo(new CanInfo(DeviceType.Accelerometer, Manufacturer.CTRElectronics, 1));
         Part ci3 = new Part("part 3",null).CanInfo(new CanInfo(DeviceType.GyroSensor, Manufacturer.CTRElectronics, 1));
         Part ci4 = new Part("part 4",null).CanInfo(new CanInfo(DeviceType.GyroSensor, Manufacturer.KauaiLabs, 1));
         canMap.install(ci1);
         canMap.install(ci2);
         canMap.install(ci3);
         canMap.install(ci4);
         canMap.install(new Part("part 5",null).CanInfo(new CanInfo(DeviceType.GyroSensor, Manufacturer.KauaiLabs, 1)));
         
         ShowCAN(canMap);
         ConfigRobot(robot2a);
         */
    }
    /* 
    public void ConfigRobot(Robot robot) {
        System.out.println();
        ShowCAN(robot.getCanMap());
        System.out.println();
        System.out.println("=== ROBOT CONFIG ===");
        ShowGyro(robot.getPart("Pigeon2"));
        ShowMotor(robot.getPart("TurningMotor"));
    }
    private void ShowGyro(Part part)
    {
        System.out.println(MessageFormat.format("public static final {0} gyro = new {1}({2});", part.name, part.name, part.getCanId() ));
        System.out.println(MessageFormat.format("public static final boolean GYRO_REVERSED = {0};", part.getBoolean("isReversed") ));
    }
    
    private void ShowMotor(Part part)
    {
        var motor = part.getPart("Motor");
        System.out.println(MessageFormat.format("public static final boolean {0}Reversed = {1};", part.name, part.getBoolean("isReversed") ));
        System.out.println(MessageFormat.format("public static final CANSparkMax.IdleMode {0}Brake = IdleMode.{1};", part.name, part.getValue("idleMode") ));
        System.out.println(MessageFormat.format("public static final CANSparkMax {0} = new CANSparkMax({1}, CANSparkMaxLowLevel.MotorType.{2});", part.name, part.getCanId(), motor.getValue("motorType") ));
        
        var gearBox = part.getPart("GearBox");
        var stages = gearBox.getPart("Stages").getPieces();
        Part.print("public static final double STEER_GEAR_RATIO = 1 / (");
        List<String> ratios = stages.stream().map(s -> s.format("({0}.0 / {1}.0)", "drivingTeeth", "drivenTeeth")).toList();
        Part.print(String.join(" * ", ratios));
        Part.println(");");
        
        double _gearRatio = part.getGearRatio();
        Part.println("// computed as: 1/" + _gearRatio + " = " + (1/_gearRatio));
        
        
    }
    
    public void ShowCAN(CanMap canMap) {
        System.out.println();
        System.out.println("=== CAN MAP ===");
        
        canMap.getDevices()
        .forEach(d -> {
            if (d.getErrors().isEmpty()) {
                System.out.print("  ");
            } else {
                System.out.print("! ");
            }
            System.out.println(MessageFormat.format("\"{0}\" {1} #{2} ({3})",
            d.getName(),
            d.getCanInfo().deviceType.DeviceName,
            d.getCanId(),
            d.getCanInfo().manufacturer.ManufacturerName));
            
            d.getErrors().forEach(e -> System.out.println(MessageFormat.format("    {0}", e)));
        });
        
    }
    */    
    
    @Test
    public void Test3()
    {
        // var robot1a = CreateRobot1a();
        // var robot1b = CreateRobot1b();
        // assertTrue(robot1a.getPart("Pigeon2").getCanInfo().deviceType == DeviceType.GyroSensor);
        // assertTrue(robot1a.getPart("Pigeon2").getCanInfo().manufacturer == Manufacturer.CTRElectronics);
        // assertEquals(robot1a.getPart("Pigeon2").getPowerProfile().peakPower, 0.4);
        
        // assertTrue(robot1a.getPart("Pigeon2").getCanInfo().deviceType == robot1b.getPart("Pigeon2").getCanInfo().deviceType);
        // assertTrue(robot1a.getPart("Pigeon2").getCanInfo().manufacturer == robot1b.getPart("Pigeon2").getCanInfo().manufacturer);
    }
    
    public static void Log(Robot robot)
    {
        //robot.
    }

    static Robot CreateRobot1a() {
        return Robot
            .Create("robot_1a", r -> r/*
            .Parts(hw -> hw
                .Gyro("Pigeon2", Manufacturer.CTRElectronics, g -> null
                     .PowerProfile(0.4))) */
            );         
    } 

    static Robot CreateRobot2a() {
        return Robot
            .Create("robot_1a", r -> r/*
            .Parts(define -> define
                .Part("Pigeon2", p -> p
                    .setValue("isReversed", false)
                    .CanInfo(c -> c
                    .Manufacturer(Manufacturer.CTRElectronics)
                        .Device(DeviceType.GyroSensor))
                    .PowerProfile(w -> w
                        .PeakPower(0.4))
                )
                .Gyro("Pigeon3", Manufacturer.CTRElectronics, g -> null
                    .IsReversed(true)
                     .CanInfo(c -> c.Number(16))
                )
                .Part("TurningMotor", m -> m
                    .Part("NEO", n -> n
                        .setValue("motorType", CANSparkMaxLowLevel.MotorType.kBrushless)
                        .setValue("FreeSpeed (RPM)", 5_820.0)
                    )
                    .Part("GearBox", g-> g
                        .setValue("GearRatioOption", "150/7:1")
                        .Part("Stages", gears -> gears
                            .Piece(s1 -> s1
                                .setValue("drivingTeeth", 14)
                                .setValue("drivenTeeth", 50)
                            )
                            .Piece(s2 -> s2
                                .setValue("drivingTeeth", 10)
                                .setValue("drivenTeeth", 60)
                            ))
                    )
                    .Part("PID", pid -> pid
                        .setValue("P", 3.4)
                        .setValue("I", 0.0)
                        .setValue("D", 0.0)
                    )
                    .CanInfo(c -> c
                        .Manufacturer(Manufacturer.REVRobotics)
                        .Device(DeviceType.MotorController)
                    )
                )
                .MotorController("DrivingMotor", m -> m
                    .CanInfo(c -> c
                        .Manufacturer(Manufacturer.REVRobotics)
                        .Device(DeviceType.MotorController)
                    )
                    .Part("NEO", n -> n
                        .setValue("motorType", CANSparkMaxLowLevel.MotorType.kBrushless)
                    )
                    .GearBox(g-> g.Note("GearRatioOption", "L2")
                        .Gear("Stage1", 14, 50)
                        .Gear("Stage2", 27, 17)
                        .Gear("Stage3", 15, 45)
                    )
                )*/
            )
            .Build(hw -> hw /*
                .Build("Pigeon2", d -> d
                    .setValue(GyroSensor.ISREVERSED, true)
                    .CanInfo(can -> can.Number(14))
                    .PowerProfile(p ->p.Channel(8))
                )
                .Build("Pigeon2b", "Pigeon2", d -> d
                    .setValue(GyroSensor.ISREVERSED, false)
                    .CanInfo(can -> can.Number(15))
                    .PowerProfile(p ->p.Channel(9))
                )
                .Build("Pigeon3", d -> d
                    //.IsReversed(true)
                    .CanInfo(can -> can.Number(16))
                    .PowerProfile(p ->p.Channel(10))
                )
                .Build("DrivingMotor", m -> m
                    .setValue("idleMode", IdleMode.kBrake)
                    .CanInfo(c -> c.Number(0))
                )
                .Build("TurningMotor", m -> m
                    .setValue("isReversed", true)
                    .setValue("idleMode", IdleMode.kCoast)
                    .CanInfo(c -> c.Number(1))
                ) 
            )*/
            // .Build(hw->hw
            //     .SwerveDrive(swerve->swerve
            //         .SwerveModule("Front.Left", sm->sm
            //             .MotorController("DrivingMotor", m -> m
            //                 .IdleMode(IdleMode.kBrake)
            //                 .CanNumber(0)
            //             )
            //             .MotorController("TurningMotor", m -> m
            //                 .IdleMode(IdleMode.kCoast)
            //                 .CanNumber(1)
            //             )
            //             .Reversed()
            //         )
            //     )
            // )
        );                    
    }
/*
 * 
        */
 static Robot CreateRobot1b() {
     return Robot
     .Create("robot_1b", r -> r
     /*.Build(SupportedDevices.Pigeon2) */);
     
 }
    static void CheckResults(Robot robot) {

    }

    static void LogMessage(String text, Object...args) {
        System.out.println(MessageFormat.format(text, args));
    }
    static void _Log(String format) {
        //System.out.println(MessageFormat.format("public static final {0} gyro = new {1}({2});", part.name, part.name, part.getCanId() ));

    }
}
