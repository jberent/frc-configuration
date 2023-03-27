//import static org.junit.jupiter.api.Assertions.assertEquals;

import team1502.configuration.CANConfiguration;
import team1502.configuration.Devices;
import team1502.configuration.Motor;
import team1502.configuration.Robot;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.Manufacturer;

public class App {
    public static void main(String[] args) throws Exception {
        var test = "null";
        if (args.length > 0 ) {
            test = args[0];
        }
        
        System.out.println("=== Hello, " + test + " ===");

        try {
            switch (test) {
                case "null" : echo("App [testnumber] -- first arg is test number to run");
                case "0" : Test0(); break;
                case "1" : Test1(); break;
                case "2" : Test2(); break;
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
        CAN.Id(1, FrontLeftDriveMotor);
        //assertEquals(1, FrontLeftDriveMotor.GetCanId());
        var canInfo = FrontLeftDriveMotor.GetCanInfo();
        System.out.println(FrontLeftDriveMotor.Name + "(" + FrontLeftDriveMotor.GetCanId() + ") :" + canInfo.GetDeviceTypeId() + "-" + canInfo.GetManufacturerId());
    }
    
    public static void Test2() {
        var robot = Create1502();
        System.out.println("Robot " + robot.name + "created");
        System.out.println("Test2 - Finished");
    }

     

    static Devices Basic2(Devices hw, String rioVersion) {
        return hw.
        RoboRIO("RIO", rioVersion)
        .Radio("RADIO")
        .RadioPowerModule("RPM")
        .RadioBarrelJack("RBJ")
        .EthernetSwitch("ETH");

    }

    static Devices SwerveDrive(Devices hw) {
        return hw.SwerveDrive(dr -> dr
            .SwerveModule("FL", sw -> sw
                .Motor("FLD")
                .Motor("FLT")
                .Encoder("FLE"))
            .SwerveModule("FR", sw -> sw
                .Motor("FRD")
                .Motor("FRT")
                .Encoder("FRE"))
            .SwerveModule("RL", sw -> sw
                .Motor("RLD")
                .Motor("RLT")
                .Encoder("RLE"))
            .SwerveModule("RR", sw -> sw
                .Motor("RRD")
                .Motor("RRT")
                .Encoder("RRE")));
    }
    
    static Robot CreateTestBoard1502() {
        return Robot
            .Create("testboard_1502", r -> r

            .Devices(hw ->
                SwerveDrive(hw)
                .DC("DC-DC")
                .Gyro("GYRO")

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

    static Robot Create1502() {
        return Robot
            .Create("1502", r -> r

            .Devices(hw -> hw
                .Common(() -> Basic2(hw, "2.0"))
                .Common(() -> SwerveDrive(hw))
                
                .Gyro("GYRO") // Pigeon Gyro
                
                .Subsystem("ARM", s -> s
                    .Motor("R-ARM")
                    .Motor("L-ARM")
                    .Motor("X-ARM")
                )
                
                .PH("PCM", ph -> ph
                    .Solenoid(0, "SOL")
                )
                
                .DC("DC-DC")
                .Pi("PhotonVisionone", "10.15.02.11")
                .Pi("PhotonVisiontwo", "10.15.02.12")
                
                .MPM("RM-PDP", mpm -> mpm // REV Mini Power Module
                    .Ch(0, "RRE")
                    .Ch(1, "FRE")                
                )

                .MPM("LM-PDP", mpm -> mpm
                    .Encoder(0, "GYRO")
                    .Encoder(3, "FLE")
                    .Encoder(4, "ETH")
                    .Encoder(5, "RLE")                
                )
            )
            
            .PDP("PDH", pdp -> pdp
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

                .Ch(20, 10, "RIO")
                .Ch(21, 10, "RPM")
                .Ch(22, 10, "RBJ")
                .Ch(22) // switchable
            )

            .CAN(can -> can
                .Id(1, "RRT")
                .Id(2, "RRD")
                .Id(3, "RLT")
                .Id(4, "RLD")
                .Id(5, "FLT")
                .Id(6, "FLD")
                .Id(7, "FRT")
                .Id(8, "FRD")
                .Id(9, "PDH")
                .Id(10, "RRE")
                .Id(11, "RLE")
                .Id(12, "FLE")
                .Id(13, "FRE")
                .Id(14, "GYRO")
                .Id(16, "R-ARM")
                .Id(17, "L-ARM")
                .Id(18, "X-ARM")
            )
        );
    } 
}
