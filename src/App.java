//import static org.junit.jupiter.api.Assertions.assertEquals;
import test.java.AppTest;
import team1502.configuration.Robot;
import team1502.configuration.SupportedDevices;
import team1502.configuration.CAN.DeviceType;
import team1502.configuration.CAN.Manufacturer;
import team1502.configuration.Controllers.GyroSensor;
import team1502.old.CANConfiguration;
import team1502.old.Devices;
import team1502.old.Motor;

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
                // case "2" : Test2(); break;
                // case "3" : Test3(); break;
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
    
    

}
