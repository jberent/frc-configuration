package team1502.configuration;

public class ConfigurationItem {

    public String Name;
    public String Category;

    public ConfigurationItem() {}
    public ConfigurationItem(String name) {this.Name = name;}

    // public ConfigurationItem(String name) {
    //     this.Name = name;
    // }

    /*
     * 
     //ConfigurationItem FrontRightModule = new SwerveModule
     Motor FrontLeftDriveMotor = new Motor();
    Motor FrontLeftTurnMotor = new Motor();
    CANCoder FrontLeftEncoder = new CANCoder();
    
    Motor FrontRightDriveMotor = new Motor();
    Motor FrontRightTurnMotor = new Motor();
    CANCoder FrontRightEncoder = new CANCoder();
    
    Motor BackLeftDriveMotor = new Motor();
    Motor BackLeftTurnMotor = new Motor();
    CANCoder BackLeftEncoder = new CANCoder();
    
    Motor BackRightDriveMotor = new Motor();
    Motor BackRightTurnMotor = new Motor();
    CANCoder BackRightEncoder = new CANCoder();
    
    SwerveModule FrontLeftSwerveModule = new SwerveModule(FrontLeftDriveMotor, FrontLeftTurnMotor, FrontLeftEncoder);
    SwerveModule FrontRightSwerveModule = new SwerveModule(FrontRightDriveMotor, FrontRightTurnMotor, FrontRightEncoder);
    SwerveModule BackLeftSwerveModule = new SwerveModule(BackLeftDriveMotor, BackLeftTurnMotor, BackLeftEncoder);
    SwerveModule BackRightSwerveModule = new SwerveModule(BackRightDriveMotor, BackRightTurnMotor, BackRightEncoder);
    
    void CreateConfiguration() {
        try {
            CreateCAN();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public CAN CreateCAN() {
        var CAN = new CAN()
            .Id(1,FrontRightDriveMotor)
            .Id(2,FrontRightTurnMotor)
            .Id(3,FrontRightEncoder)
            .Id(4,FrontLeftDriveMotor)
            .Id(5,FrontLeftTurnMotor)
            .Id(6,FrontLeftEncoder)
            .Id(7,BackRightDriveMotor)
            .Id(8,BackRightTurnMotor)
            .Id(9,BackRightEncoder)
            .Id(10,BackLeftDriveMotor)
            .Id(11,BackLeftTurnMotor)
            .Id(12,BackLeftEncoder)
            ;
            
            return CAN;
        }
        */
        
}
