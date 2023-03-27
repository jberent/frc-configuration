package team1502.configuration;

import java.util.function.Function;

public class SwerveDrive {
    public SwerveDrive SwerveModule(String name, Function<SwerveModule, SwerveModule> fn) {
        return this;
    }

}
