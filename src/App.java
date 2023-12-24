//import static org.junit.jupiter.api.Assertions.assertEquals;
import test.java.AppTest;

import team1502.configuration.CANConfiguration;
import team1502.configuration.Devices;
import team1502.configuration.Motor;
import team1502.configuration.Robot;
import team1502.configuration.SupportedDevices;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.Manufacturer;
import team1502.configuration.Controllers.GyroSensor;

public class App {
    public static void main(String[] args) throws Exception {
        var test = "null";
        if (args.length > 0 ) {
            test = args[0];
        } else {
            new AppTest().Test2();
            return;
        }
        
        System.out.println("=== Hello, " + test + " ===");

        try {
            switch (test) {
                case "null" : echo("App [testnumber] -- first arg is test number to run");
                case "0" : Test0(); break;
                case "1" : Test1(); break;
                case "2" : Test2(); break;
                case "3" : Test3(); break;
                default : echo(test + " [testnumber] is not a known test");
            }
        } catch (Exception ex) {
            System.out.println("Test " + test + " - Failed: " + ex.getMessage());
        }
    }
    
    public static void echo(String text) {System.out.println(text);}

    public static void Test0() {
        ShowDeviceTypes();
        ShowManufacturers();
    }
    
    public static void ShowDeviceTypes() {
        
        echo("");
        var formatter = MdFormatter.Table("CAN Device Types").Heading("Device Name","Device Id");
        for (var value : DeviceType.values()){
            formatter.AddRow(value.DeviceName, value.DeviceID);
        }
        formatter.AsTable().forEach(row -> echo(row));
    }
    
    public static void ShowManufacturers() {
        echo("");
        var formatter = MdFormatter.Table("CAN Manufacturers").Heading("Manufacturer Name","Manufacturer Id");
        for (var value : Manufacturer.values()){
            formatter.AddRow(value.ManufacturerName, value.ManufacturerID);
        }
        formatter.AsTable().forEach(row -> echo(row));
    }
    
    public static void Test1() throws Exception {
        Motor FrontLeftDriveMotor = new Motor("FLD");
        
        var CAN = new CANConfiguration();
        //CAN.Id(1, FrontLeftDriveMotor);
        //assertEquals(1, FrontLeftDriveMotor.GetCanId());
        var canInfo = FrontLeftDriveMotor.getCanInfo();
        //System.out.println(FrontLeftDriveMotor.name + "(" + FrontLeftDriveMotor.getCanId() + ") :" + canInfo.getDeviceTypeId() + "-" + canInfo.getManufacturerId());
    }
    
    public static void Test2() {
        try {
            var robot = Create1502();
            System.out.println("Robot " + robot.name + "created");
            System.out.println("Test2 - Finished");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void Test3() {
        try {
            var robot = Create1502();
            System.out.println("Robot " + robot.name + "created");
            System.out.println("Test3 - Finished");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    static Devices Basic2(Devices hw, String rioVersion) {
        return hw.
        RoboRIO("RIO", rioVersion, rio -> rio.PWM(pwm -> pwm).DIO(dio -> dio))
        .Radio("RADIO")
        .RadioPowerModule("RPM")
        .RadioBarrelJack("RBJ")
        .EthernetSwitch("ETH");
    }

    static Devices SwerveDrive(Devices hw) {
        return hw.SwerveDrive(dr -> dr
            .SwerveModule("FL", sw -> sw
                .Motor("FLD", "NEO")
                .Motor("FLT", "NEO")
                .Encoder("FLE", "CANcoder"))
            .SwerveModule("FR", sw -> sw
                .Motor("FRD", "NEO")
                .Motor("FRT", "NEO")
                .Encoder("FRE", "CANcoder"))
            .SwerveModule("RL", sw -> sw
                .Motor("RLD", "NEO")
                .Motor("RLT", "NEO")
                .Encoder("RLE", "CANcoder"))
            .SwerveModule("RR", sw -> sw
                .Motor("RRD", "NEO")
                .Motor("RRT", "NEO")
                .Encoder("RRE", "CANcoder")));
    }
    
    static Robot CreateTestBoard1502() {
        return Robot
            .Create("testboard_1502", r -> r

            .Devices(hw ->
                // SwerveDrive(hw)
                hw.DC("DC-DC")
                .Gyro("GYRO", "Pigeon2")

            )            
            .PDP("PDH", pdp -> pdp
                .Ch(0, "FLD")
                .Ch(1, "FLT")
            )
            .CAN(can -> can
                .Id(0, "FLD")
                .Id(1, "FLT")
                .Id(9, "PDH")
            )
        );
    } 

    static Devices EquipmentList(Devices hw) {
        return hw
            .MotorController("NEO", mc -> mc // neo.brushless, CANSparkMax
                .Manufacturer(Manufacturer.REVRobotics)
                .Device(SupportedDevices.MotorCANSparkMax)
                .PowerProfile(380))                
            .MotorController("NEO-550", mc -> mc // neo.brushless, CANSparkMax
                .Manufacturer(Manufacturer.REVRobotics)
                .Device(SupportedDevices.MotorCANSparkMax)
                .PowerProfile(265))
            .Accelerometer("CANcoder", cs -> cs // neo.brushless, CANSparkMax
                .Manufacturer(Manufacturer.CTRElectronics)
                .Device(SupportedDevices.CANcoder)
                .PowerProfile(265))
            .Gyro("Pigeon2", cs -> (GyroSensor)cs // neo.brushless, CANSparkMax
                .Manufacturer(Manufacturer.CTRElectronics)
                .Device(SupportedDevices.Pigeon2)
                .PowerProfile(0.4))
            .Device("RIO", 45) // +RSL 0.6 ??
            .Device("RADIO", 0.79)
            .Device("RPM", 12.0) // radio power module
            .Device("RBJ", 12.0) // radio barrel jack
            .Device("RSL", 0.6) // radio signal light
            .Device("ETH", 12.0)
            .Device("TOF", 0.02)
            .Device("Commpressor", 120.0)
            .Device("LimeLight", 60.0)
            .Device("RaspberryPi", 60.0) //? + camera
            .Device("LED-5V", 25.0) // 5V addressable LEDs - 5A max
        ;
    }

    static Robot Create1502() {
        return Robot
            .Create("1502", robot -> robot

            .Devices(hw -> hw
                .Include(() -> EquipmentList(hw))
                //.Common(() -> Basic2(hw, "2.0"))
                
                .RoboRIO("RIO", "2.0", rio -> rio
                    .PWM(pwm -> pwm
                        .Spark(0, "LED-L", "LED-5V") // left_blinkin 5V addressable LEDs
                        .Spark(1, "LED-R", "LED-5V") // right_blinkin
                    )
                )

                .Include(() -> SwerveDrive(hw))
                // WHEEL_BASE_WIDTH _LENGTH 25"x32"; DIAMETER 4"
                // GYRO_REVERSED
                // motors-reversed: all
                
                .Subsystem("ARM", s -> s
                    .Motor("R-ARM", "NEO-550")
                    .Motor("L-ARM", "NEO-550")
                    .Motor("X-ARM", "NEO-550")
                )
                
                .PH("PCM", ph -> ph
                    .Compressor("Compressor")
                    .Solenoid(15, 0, "SOL") // REVPH
                )
                
                .DC("DC-DC", dc -> dc
                    .Pi("PhotonVisionone", "10.15.02.11")
                    .Pi("PhotonVisiontwo", "10.15.02.12")
                )
                
                .MPM("RM-PDP", mpm -> mpm // REV Mini Power Module
                    .Ch(0, 10, "RRE")
                    .Ch(1, 10, "FRE")                
                )

                .MPM("LM-PDP", mpm -> mpm
                    .Ch(0, 10, ch -> ch.Gyro("GYRO", "Pigeon2") )
                    .Ch(3, 10, "FLE")
                    .Ch(4, 10, ch -> ch
                        .EthernetSwitch("ETH") // also provides POE
                            .Radio("RADIO")
                                .RadioPowerModule("RPM")
                                .RadioBarrelJack("RBJ")
                        )
                    .Ch(5, 10, "RLE")               
                )
            )
            
            // SHOW POWER DISTRUBTION HUB separately to make it easier to reference
            .PDP("PDH", pdp -> pdp
                // LEFT SIDE                                 RIGHT SIDE
                //======================================     ======================================
                .Ch(10, 30, "DC-DC")     .Ch(9, 10, "PCM")
                .Ch(11)                             .Ch(7)
                .Ch(12, 30, "RM-PDP")    .Ch(8)
                .Ch(13, 30, "LM-PDP")    .Ch(6)
                .Ch(14, 40, "X-ARM")     .Ch(5)
                .Ch(15, 40, "L-ARM")     .Ch(4, 40, "R-ARM")
                .Ch(16, 40, "RLD")       .Ch(3, 40, "FRD")
                .Ch(17, 40, "RLT")       .Ch(2, 40, "FRT")
                .Ch(18, 40, "RRD")       .Ch(1, 40, "FLD")
                .Ch(19, 40, "RRT")       .Ch(0, 40, "FLT")

                .Ch(20, 10, "RIO") // + LEDs?
                .Ch(21, 10, "RPM") // these two are
                .Ch(22, 10, "RBJ") // redundant power sources
                .Ch(22) // switchable
            )

            // SHOW CAN assignments separately to make it easier to reference
            .CAN(can -> can
                .Id(1, "RRT")       // ANGLE_BACK_RIGHT
                .Id(2, "RRD")       // DRIVE_BACK_RIGHT
                .Id(3, "RLT")
                .Id(4, "RLD")       // DRIVE_BACK_LEFT
                .Id(5, "FLT")
                .Id(6, "FLD")       // DRIVE_FRONT_LEFT
                .Id(7, "FRT")
                .Id(8, "FRD")       // DRIVE_FRONT_RIGHT
                .Id(9, "PDH")
                .Id(10, "RRE")      // BACK_RIGHT_CAN_CODER, false 91.4
                .Id(11, "RLE")      // BACK_LEFT_CAN_CODER, false, 15.0
                .Id(12, "FLE")      // FRONT_LEFT_CAN_CODER, false, 111.4
                .Id(13, "FRE")      // FRONT_RIGHT_CAN_CODER, false, 104.0
                .Id(14, "GYRO")     // Pigeon2
                .Id(16, "R-ARM")    // ARM_LEAD
                .Id(17, "L-ARM")    // ARM_FOLLOW
                .Id(18, "X-ARM")    // EXTEND
            )
        );
    } 
}
