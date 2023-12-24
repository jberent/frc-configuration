package team1502.configuration.Builder;

import java.util.function.Function;

public class SwerveBuilder extends Builder {

    public SwerveBuilder(Builder parent) {
        super(parent);
    }

    public SwerveBuilder SwerveModule(String name, Function<SwerveModuleBuilder, SwerveModuleBuilder> fn) {
        var swerve = new SwerveModuleBuilder(this);
        fn.apply(swerve);
        return this;
    }

}
