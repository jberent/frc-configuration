package team1502.configuration.Builders;

public class Chassis extends Builder {

    public Chassis Square(double inches) {
        Value("chassisLayout", "square");
        Value("wheelBaseWidth", inches);
        Value("wheelBaseLength", inches);
        return this;
    }

    public Translation2d moduleLocation(int moduleNumber) {

    }
}

/*
  
  public static final double WHEEL_BASE_WIDTH = Units.inchesToMeters(23.25);
  public static final double WHEEL_BASE_LENGTH = Units.inchesToMeters(23.25);


  public static final Translation2d FRONT_LEFT_MODULE = new Translation2d(WHEEL_BASE_LENGTH/2, WHEEL_BASE_WIDTH/2);
  public static final Translation2d FRONT_RIGHT_MODULE = new Translation2d(WHEEL_BASE_LENGTH/2, -WHEEL_BASE_WIDTH/2);
  public static final Translation2d BACK_LEFT_MODULE = new Translation2d(-WHEEL_BASE_LENGTH/2, WHEEL_BASE_WIDTH/2);
  public static final Translation2d BACK_RIGHT_MODULE = new Translation2d(-WHEEL_BASE_LENGTH/2, -WHEEL_BASE_WIDTH/2);


    |<-W/2->|

   1,1              1,-1                       ^
   [1]------|-------[2]   ---                  X
    :       :        :     ^                   |
    :       :        :    L/2                  |
    :       :        :     |                   |
    +-------+--------+    ---       < Y -------+
    :       :        :
    :       :        :
    :       :        :
   [3]------|-------[4]
  -1,1             -1,-1
    
  1  1, 1
  2  1,-1
  3 -1, 1
  4 -1,-1  


 */

