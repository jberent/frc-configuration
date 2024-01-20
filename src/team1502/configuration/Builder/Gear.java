package team1502.configuration.Builder;

public class Gear extends Builder {
    public Gear(String stage, int drivingTeeth, int drivenTeeth) {
        super("Gear", stage, s1 -> s1
            .setValue("drivingTeeth", drivingTeeth)
            .setValue("drivenTeeth", drivenTeeth));
    }
}
