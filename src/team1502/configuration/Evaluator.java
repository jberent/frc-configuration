package team1502.configuration;

import java.util.HashMap;
import java.util.function.Function;

import team1502.configuration.Builder.Builder;
import team1502.configuration.Builder.RobotBuilder;
import team1502.configuration.Builder.Controllers.MotorController;
import team1502.configuration.Builder.Motor;

public class Evaluator {
    private HashMap<String, EvaluatorArgs> _valueMap = new HashMap<>(); 
    //private HashMap<String, Function<Evaluator,Object>> _valueMap = new HashMap<>(); 
    private RobotBuilder _configuration;
    public EvaluatorArgs args;
    
    public String partName() {
        return args.partName;
    }
    public Evaluator(RobotBuilder configuration) {
        _configuration = configuration;
    }

    private Object Eval(EvaluatorArgs args) {
        this.args = args;
        var result = Eval(args.function);
        this.args = null;
        return result;
    }
    // Ad hoc evaluate
    public Object Eval(Function<Evaluator,Object> fn) {
        return fn.apply(this);
    }
    // Define
    public Evaluator Eval(String valueName, Function<Evaluator,Object> fn) {
        _valueMap.put(valueName, new EvaluatorArgs(valueName, fn));
        return this;
    }

    // Execute storedeval
    public Object getValue(String valueName, String partName) {
        var args = _valueMap.get(valueName);
        args.partName = partName;
        return Eval(args);
    }

    public Object getValue(String valueName) {
        return Eval(_valueMap.get(valueName));
    }
    // public Evaluator Value(String valueName, String partName, Function<? extends Builder, Object> fn) {
    //     _valueMap.put(valueName, s -> getValue(partName, fn));   
    //     return this;
    // }  

    private Builder getInstalled(String partName) {
        return _configuration.getInstalled(partName);
    }

    private <T extends Builder> Object getValue(String partName, T builder, Function<T, Object> fn) {
        var susBuilder = getInstalled(partName);
        return susBuilder.evalWith(fn, builder);
    
    }
    private <T extends Builder> Object getValue(String partName, Function<T, Object> fn) {
        var builder = getInstalled(partName);
        return fn.apply((T)builder);
    }

    public Object Part(String partName, Function<Builder, Object> fn) {
        return getValue(partName, new Builder(), fn);   
    }
    public Object Motor(String partName, Function<Motor, Object> fn) {
        return getValue(partName, new Motor(), fn);   
    }
    public Object MotorController(String partName, Function<MotorController, Object> fn) {
        return getValue(partName, new MotorController(), fn);   
    }

}
