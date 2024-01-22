package test.java;

import team1502.Wpi.CANSparkMax.IdleMode;
import team1502.Wpi.CANSparkMaxLowLevel.MotorType;
import team1502.Wpi.CANSparkMaxLowLevel;
import team1502.configuration.Robot;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.Manufacturer;

public final class SwerveDriveVerbose {
    public static Robot CreateRobot0a() { return Robot
        .Create("robot_Part_Test", r -> r
            .Parts(define -> define
                .Part("NEO", n -> n
                    .Type("Motor")
                    .Value("motorType", CANSparkMaxLowLevel.MotorType.kBrushless)
                    .Value("freeSpeedRPM", 1_000.0)
                )
            )
            .Build(hw -> hw
                // a Motor type
                .Part("NEO", p -> p
                    .Value("freeSpeedRPM", 5_820.0))
                // a MotorController type
                .Part("DriveMotor", m -> m
                    .Device(DeviceType.MotorController)
                    .Manufacturer(Manufacturer.REVRobotics)
                    .Install("Motor", g -> g.Create("NEO", n -> n
                        .Note("About", "NEO from  part-factory w/ 1000rpm")
                    ))
                        // "NEO" will now map to "not-NEO"
                    .Install("not-NEO", g -> g.Existing("NEO", n -> n
                        .Note("About", "built NEO modified w/ 5820rpm")
                        .Note("Warning", "this is likely a bad idea as it changes an existing part w/o renaming the key")
                    ))
                )
            )
            .Values(k -> k
                .Eval("NEO.MotorType", e -> e.Part("NEO", m -> m.getValue("motorType")))
                .Eval("NEO.MotorType#2", e -> e.Motor("NEO", m -> m.MotorType()))
                .Eval("NEO.name", e -> e.Motor("NEO", m -> m.getPart().name))
                .Eval("NEO.FreeSpeedRPM", e -> e.Motor("NEO", m -> m.FreeSpeedRPM()))
                .Eval("DriveMotor.Motor.FreeSpeedRPM", e -> e.Part("DriveMotor", m -> m.getPart("Motor").getDouble("freeSpeedRPM")))
                .Eval("DriveMotor.Motor*.FreeSpeedRPM", e -> e.Part("DriveMotor", m -> m.getPart("not-NEO").getDouble("freeSpeedRPM")))
            )
        );
    }

    public static Robot CreateRobot0b() { return Robot
        .Create("robot_Motor_Test", r -> r
            // Inventory Definitions
            .Parts(define -> define
                .Motor(n -> n.Note("About", "This makes a motor with the default name \"Motor\""))
                .Motor("NEO", n -> n
                    .MotorType(CANSparkMaxLowLevel.MotorType.kBrushless)
                    .FreeSpeedRPM(5_820.0)
                )
            )
            // Top-Level Parts
            .Build(hw -> hw
                .Motor(n -> n.Note("Build Note", "This makes a motor with the default name \"Motor\""))
                .Motor("ArmMotor", "NEO", m -> m)
                .MotorController("DriveMotor", "", b -> b
                    .Motor("NEO", m -> m)
                    .IdleMode(IdleMode.kBrake)
                )
            )
            // Configuration Values
            .Values(k -> k
                .Eval("Motor.Build Note", e -> e.Motor("Motor", m -> m.Note("Build Note")))
                .Eval("ArmMotor.MotorType", e -> e.Motor("ArmMotor", m -> m.MotorType()))
                .Eval("DriveMotor.Motor.FreeSpeedRPM", e -> e.MotorController("DriveMotor", m -> m.Motor().FreeSpeedRPM()))
            )
        );
    }

    public static Robot CreateRobot1() { return Robot
        .Create("robot_MotorController_Test", r -> r
            // Inventory Definitions
            .Parts(define -> define
                .Motor("NEO", m -> m
                    .MotorType(CANSparkMaxLowLevel.MotorType.kBrushless)
                    .FreeSpeedRPM(5_820.0)
                    .Note("NAME", "NEO")
                    .Note("VERSION", "V1.0/V1.1")
                    .Note("gearing", "8mm bore pinion gears")
                )
                .MotorController("TurningMotor", Manufacturer.REVRobotics, mc -> mc
                    .Motor("NEO")
                    .IdleMode(IdleMode.kCoast)
                    .GearBox(g-> g
                        .Gear("Stage1", 14, 50)
                        .Gear("Stage2", 10, 60)
                        .Note("GearRatio Option", "150/7:1")
                    )
                )
                .MotorController("DrivingMotor", Manufacturer.REVRobotics, mc -> mc
                    .Motor("NEO", m -> m.MotorType(MotorType.kBrushed))
                    .IdleMode(IdleMode.kBrake)
                    .GearBox(g-> g
                        .Gear("Stage1", 14, 50)
                        .Gear("Stage2", 27, 17)
                        .Gear("Stage3", 15, 45)
                        .Note("GearRatio Option", "L2")
                    )
                )
            )
            // Top-Level Parts
            .Build(hw -> hw
                .MotorController("Motor#0", "TurningMotor", mc -> mc
                    .PID(3.5, 0.0, 0.0)
                    .CanNumber(0)
                )
                .MotorController("Motor#1", "DrivingMotor", mc -> mc
                    .CanNumber(1)
                )
                .MotorController("Motor#2", "TurningMotor", mc -> mc
                    .CanNumber(2)
                )
                .MotorController("Motor#3", "DrivingMotor", mc -> mc
                    .Reversed(true)
                    .CanNumber(3)
                )
            )
            // Configuration Values
            .Values(k -> k
                .Eval("MotorController.Motor.MotorType", e -> e
                    .MotorController(e.partName(), m -> m.Motor().MotorType()))
                .Eval("MotorController.Motor.FreeSpeedRPM", e -> e
                    .MotorController(e.partName(), m -> m.Motor().FreeSpeedRPM()))
                .Eval("MotorController.IdleMode", e -> e
                    .MotorController(e.partName(), m -> m.IdleMode()))
                .Eval("MotorController.Reversed", e -> e
                    .MotorController(e.partName(), m -> m.Reversed()))
                .Eval("MotorController.CanNumber", e -> e
                    .MotorController(e.partName(), m -> m.CanNumber()))
                .Eval("MotorController.Manufacturer", e -> e
                    .MotorController(e.partName(), m -> m.Manufacturer()))
                .Eval("MotorController.GearRatio", e -> e
                    .MotorController(e.partName(), m -> m.GearBox().GearRatio()))
                .Eval("MotorController.PID.p", e -> e
                    .MotorController(e.partName(), m -> m.PID().P()))
            )
        );
    }

    public static Robot CreateRobot2() { return Robot
        .Create("robot_SwerveModule_Test", r -> r
            // Inventory Definitions
            .Parts(define -> define
                .GyroSensor("Pigeon2", Manufacturer.CTRElectronics, p -> p
                    .Reversed(false)
                    .PowerProfile(0.4)
                )
                .Motor("NEO", m -> m
                    .MotorType(CANSparkMaxLowLevel.MotorType.kBrushless)
                    .FreeSpeedRPM(5_820.0)
                    .Note("NAME", "NEO")
                    .Note("VERSION", "V1.0/V1.1")
                    .Note("gearing", "8mm bore pinion gears")
                )
                .SwerveModule(sm -> sm
                    .Encoder(Manufacturer.REVRobotics, cc -> cc
                        .Direction(false)
                    )
                    .TurningMotor(Manufacturer.REVRobotics, mc -> mc
                        .Motor("NEO")
                        .IdleMode(IdleMode.kCoast)
                        .GearBox(g-> g
                            .Gear("Stage1", 14, 50)
                            .Gear("Stage2", 10, 60)
                            .Note("GearRatio Option", "150/7:1")
                        )
                        .PID(3.5, 0.0, 0.0)
                    )                
                    .DrivingMotor(Manufacturer.REVRobotics, mc -> mc
                        .Motor("NEO")
                        .IdleMode(IdleMode.kBrake)
                        .GearBox(g-> g
                            .Gear("Stage1", 14, 50)
                            .Gear("Stage2", 27, 17)
                            .Gear("Stage3", 15, 45)
                            .Note("GearRatio Option", "L2")
                        )
                    )
                )
                .SwerveDrive(sd -> sd)
            )
            // Top-Level Parts
            .Build(hw -> hw
                .SwerveModule("Module#1", sm -> sm
                    .CanNumbers(0, 0, 1)
                    .Encoder(e -> e.MagneticOffset(92.2))
                    .Value("test", "this is a stand-alone part")           
                )
                .SwerveModule("Module#2", sm -> sm
                    .CanNumber(2)
                    .Encoder(e -> e.MagneticOffset(192.2))
                    .Reversed(true)
                )
                .SwerveDrive(sd -> sd
                    .SwerveModule("Module#1", sm -> sm
                        .CanNumbers(0, 0, 1)
                        .Encoder(e -> e.MagneticOffset(92.2))
                        .Value("test2", "this should not have 'test' note")           
                    )
                    .SwerveModule("Module#2", sm -> sm
                        .CanNumber(2)
                        .Encoder(e -> e.MagneticOffset(192.2))
                        .Reversed(true)
                    )
                )
            )
            // Configuration Values
            .Values(k -> k
                .Eval("SwerveModule.TurningMotor.Motor.MotorType", e -> e
                    .SwerveModule(e.partName(), m -> m.TurningMotor().Motor().MotorType()))
                .Eval("SwerveModule.TurningMotor.Reversed", e -> e
                    .SwerveModule(e.partName(), m -> m.TurningMotor().Reversed()))
                .Eval("SwerveModule.Encoder.Direction", e -> e
                    .SwerveModule(e.partName(), m -> m.Encoder().Direction()))
                .Eval("SwerveModule.Encoder.MagneticOffset", e -> e
                    .SwerveModule(e.partName(), m -> m.Encoder().MagneticOffset()))
                .Eval("SwerveModule.Encoder.MagneticOffset", e -> e
                    .SwerveModule(e.partName(), m -> m.Encoder().MagneticOffset()))
                .Eval("SwerveDrive.Modules", e -> e
                    .SwerveDrive(m -> m.getPieces()))



    
                .Eval("MotorController.Motor.FreeSpeedRPM", e -> e
                    .MotorController(e.partName(), m -> m.Motor().FreeSpeedRPM()))
                .Eval("MotorController.IdleMode", e -> e
                    .MotorController(e.partName(), m -> m.IdleMode()))
                .Eval("MotorController.Reversed", e -> e
                    .MotorController(e.partName(), m -> m.Reversed()))
                .Eval("MotorController.CanNumber", e -> e
                    .MotorController(e.partName(), m -> m.CanNumber()))
                .Eval("MotorController.Manufacturer", e -> e
                    .MotorController(e.partName(), m -> m.Manufacturer()))
                .Eval("MotorController.GearRatio", e -> e
                    .MotorController(e.partName(), m -> m.GearBox().GearRatio()))
                .Eval("MotorController.PID.p", e -> e
                    .MotorController(e.partName(), m -> m.PID().P()))
            )
        );
    }
    
    public static Robot CreateRobot1a() {
        return Robot
            .Create("robot_1a", r -> r
            /*
            .Parts(define -> define
                .Part("Pigeon2", p -> p
                    .CanInfo(c -> c
                        .Manufacturer(Manufacturer.CTRElectronics)
                        .Device(DeviceType.GyroSensor))
                    .PowerProfile(w -> w
                        .PeakPower(0.4))
                )
                .Part("NEO", n -> n
                    .Value("NAME", "NEO")
                    .Value("VERSION", "V1.0/V1.1")
                    .Value("gearing", "8mm bore pinion gears")
                    .Value("motorType", CANSparkMaxLowLevel.MotorType.kBrushless)
                    .Value("FreeSpeed (RPM)", 5_820.0)
                )
                .Part("TurningMotor", m -> m
                    .CanInfo(c -> c
                        .Manufacturer(Manufacturer.REVRobotics)
                        .Device(DeviceType.MotorController)
                    )
                    .Install("Motor", g-> g.Create("NEO"))
                    .Part("GearBox", g-> g
                        .Note("GearRatioOption", "150/7:1")
                        .Part("Stages", gears -> gears
                            .Piece(s1 -> s1
                                .Value("drivingTeeth", 14)
                                .Value("drivenTeeth", 50)
                            )
                            .Piece(s2 -> s2
                                .Value("drivingTeeth", 10)
                                .Value("drivenTeeth", 60)
                            ))
                    )
                    .Part("PID", pid -> pid
                        .Value("P", 3.4)
                        .Value("I", 0.0)
                        .Value("D", 0.0)
                    )
                )
                .Part("DrivingMotor", m -> m
                    .CanInfo(c -> c
                        .Manufacturer(Manufacturer.REVRobotics)
                        .Device(DeviceType.MotorController)
                    )
                    .Install("Motor", "NEO")
                    .GearBox(g->g
                        .Note("GearRatioOption", "L2")
                        .Part("Stages", gears -> gears
                            .Piece(s1 -> s1
                                .setValue("drivingTeeth", 14)
                                .setValue("drivenTeeth", 50)
                            )
                            .Piece(s1 -> s1
                                .setValue("drivingTeeth", 27)
                                .setValue("drivenTeeth", 17)
                            )
                            .Piece(s2 -> s2
                                .setValue("drivingTeeth", 15)
                                .setValue("drivenTeeth", 45)
                            ))
                    )
                )
            )
            .Build(hw -> hw
                .Part("Pigeon2", d -> d
                    .setValue(GyroSensor.ISREVERSED, true)
                    .CanInfo(can -> can.Number(14))
                    .PowerProfile(p ->p.Channel(8))
                )
                .Part("DrivingMotor", m -> m
                    .setValue("idleMode", IdleMode.kBrake)
                    .CanInfo(c -> c.Number(0))
                )
                .Part("TurningMotor", m -> m
                    .setValue("isReversed", true)
                    .setValue("idleMode", IdleMode.kCoast)
                    .CanInfo(c -> c.Number(1))
                )
            )
         */
        );                    
    }
    public static Robot CreateRobot1b() {
        return Robot
            .Create("robot_1b", r -> r
            /*
            .Parts(define -> define
    // Pigeon2 might actually be an accelerometer ??
                .Gyro("Pigeon2", Manufacturer.CTRElectronics, p -> p
                    .IsReversed(false)
                    .PowerProfile(0.4)
                )
                .Part("NEO", n -> n
                    .Value("NAME", "NEO")
                    .Value("VERSION", "V1.0/V1.1")
                    .Value("gearing", "8mm bore pinion gears")
                    .Value("motorType", CANSparkMaxLowLevel.MotorType.kBrushless)
                    .Value("FreeSpeed (RPM)", 5_820.0)
                )
                .MotorController("TurningMotor", Manufacturer.REVRobotics, m -> m
                    .Motor("NEO")
                    .Part("GearBox", g-> g
                        .Note("GearRatioOption", "150/7:1")
                        .Part("Stages", gears -> gears
                            .Piece(s1 -> s1
                                .Value("drivingTeeth", 14)
                                .Value("drivenTeeth", 50)
                            )
                            .Piece(s2 -> s2
                                .Value("drivingTeeth", 10)
                                .Value("drivenTeeth", 60)
                            ))
                    )
                    .Part("PID", pid -> pid
                        .Value("P", 3.4)
                        .Value("I", 0.0)
                        .Value("D", 0.0)
                    )
                )
                .MotorController("DrivingMotor", Manufacturer.REVRobotics, m -> m
                    .Motor("NEO")
                    .GearBox(g->g
                        .Note("GearRatioOption", "L2")
                        .Part("Stages", gears -> gears
                            .Piece(s1 -> s1
                                .setValue("drivingTeeth", 14)
                                .setValue("drivenTeeth", 50)
                            )
                            .Piece(s1 -> s1
                                .setValue("drivingTeeth", 27)
                                .setValue("drivenTeeth", 17)
                            )
                            .Piece(s2 -> s2
                                .setValue("drivingTeeth", 15)
                                .setValue("drivenTeeth", 45)
                            ))
                    )
                )
            )
            .Build(hw -> hw
                .Gyro("Pigeon2", d -> d
                    .setValue(GyroSensor.ISREVERSED, true)
                    .CanInfo(can -> can.Number(14))
                    .PowerProfile(p ->p.Channel(8))
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
            )
 */        );                    
    }
    
}
