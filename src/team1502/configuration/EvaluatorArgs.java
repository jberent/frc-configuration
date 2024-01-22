package team1502.configuration;

import java.util.function.Function;

import team1502.configuration.Builders.Builder;

public class EvaluatorArgs {
    public EvaluatorArgs(String valueName, Function<Evaluator, Object> fn) {
        this.valueName = valueName;
        this.function = fn;
    }
    // public EvaluatorArgs(String valueName, Function<? extends Builder, Object> fn) {
    //     this.valueName = valueName;
    //     this.builderFunction = fn;
    // }
    public String valueName;
    public String partName;
    public Builder builder;
    public Function<Evaluator,Object> function;
    public Function<? extends Builder, Object> builderFunction;
}
