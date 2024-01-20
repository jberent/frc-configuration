package team1502.configuration.Builder;

import team1502.configuration.Builder.Controllers.MotorController;

public class PID extends Builder {
    private static final String NAME = "PID"; 
    public PID(double p, double i, double d) {
        super(NAME, NAME, s1 -> s1
        .setValue("p", p)
        .setValue("i", i)
        .setValue("d", d));

    }

    public PID(MotorController motorController) {
        setPart(motorController.getPart(NAME));
    }

    public double P() {return getDouble("p"); }
    public double I() {return getDouble("i"); }
    public double D() {return getDouble("d"); }
}
