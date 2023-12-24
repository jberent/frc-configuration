package team1502.Wpi;

public abstract class CANSparkMaxLowLevel {
    public enum MotorType {
        kBrushed(0),
        kBrushless(1);
        
        public final int value;

        MotorType(int value) {
            this.value = value;
        }

        public static MotorType fromId(int id) {
            for (MotorType type : values()) {
              if (type.value == id) {
                return type;
              }
            }
            return null;
        }
    }
}
