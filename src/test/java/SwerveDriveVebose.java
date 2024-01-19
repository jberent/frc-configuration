package test.java;

import team1502.Wpi.CANSparkMax.IdleMode;
import team1502.Wpi.CANSparkMaxLowLevel;
import team1502.configuration.Robot;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.Manufacturer;

public final class SwerveDriveVebose {
    public static Robot CreateRobot0a() { return Robot
        .Create("robot_0", r -> r
            .Parts(define -> define
                .Part("NEO", n -> n
                    .Value("motorType", CANSparkMaxLowLevel.MotorType.kBrushless)
                )
            )
            .Build(hw -> hw
                .Part("NEO", p -> p.Value("FreeSpeedRPM", 5_820.0))
                .Part("MotorController", "DriveMotor", m -> m
                    .CanInfo(c -> c
                        .Device(DeviceType.MotorController)
                        .Manufacturer(Manufacturer.REVRobotics))
                    .Install("Motor2", g -> g.Create("NEO", null))
                    // useful ?? doesn't this change the name of the NEO?
                    .Install("Motor", g -> g.Existing("NEO", null))
                )
            )
            .Values(k -> k
                .Value("NEO_MotorType", "NEO", m -> m.getValue("motorType"))
                .Motor("NEO_MotorType_2", "NEO", m -> m.MotorType())
                .Value("DriveMotor.Motor.FreeSpeedRPM", "DriveMotor", m -> m.getPart("Motor").getDouble("FreeSpeedRPM"))
            )
        );
    }

    public static Robot CreateRobot0b() { return Robot
        .Create("robot_0", r -> r
            .Parts(define -> define
                .Motor("NEO", n -> n
                    .MotorType(CANSparkMaxLowLevel.MotorType.kBrushless)
                    .FreeSpeedRPM(5_820.0)
                )
            )
            .Build(hw -> hw
                .Motor("NEO", m -> m)
                .MotorController("DriveMotor", b -> b
                    .Motor("NEO", m -> m)
                    .IdleMode(IdleMode.kBrake)
                )
            )
            .Values(k -> k
                .Motor("NEO_MotorType", "NEO", m -> m.MotorType())
                .MotorController("DriveMotor.Motor.FreeSpeedRPM", "DriveMotor", m -> m.Motor().FreeSpeedRPM())
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
