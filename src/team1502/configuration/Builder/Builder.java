package team1502.configuration.Builder;

import java.util.function.Function;

import team1502.configuration.PowerProfile;
import team1502.configuration.CAN.CanInfo;
import team1502.configuration.Parts.Part;

public class Builder {
    public String name;
    public Function<? extends Builder, Builder> buildFunction;
    public String buildType = "Part";
    
    private IBuild _build;
    private Part _part;
    /*
     * 
     //private Builder _parent;
     
     public Builder(Part part, IBuild build, Function<Builder, Builder> fn){
         _part = part;
         _build = build;
         fn.apply(this);
        }
        
    */

    // Just a build function (for partFactory)
    public Builder(String name, Function<Builder, Builder> fn){
        this.name = name;
        buildFunction = fn;
    }

    protected Builder(String buildType) {
        this.buildType = buildType;
    }

    protected Builder(String buildType, String name, Function<? extends Builder,  Builder> fn) {
        this.buildType = buildType;
        this.name = name;
        buildFunction = fn;
    }

    protected void Fill(String buildType, String name, Function<? extends Builder,  Builder> fn) {
        this.buildType = buildType;
        this.name = name;
        buildFunction = fn;
    }

    public Builder createBuilder(Builder builder) {
        builder.Fill(buildType, name, buildFunction);
        return builder;
    }

    public Builder createBuilder() {
        return new Builder(buildType, name, buildFunction);
    }

/*
    protected Builder(String buildType, String name, IBuild build, Function<Builder, Builder> fn)
    {
        this.name = name;
        buildFunction = fn;
        this.buildType = buildType;
        _build = build;
    }

    public Builder(Builder parent){
        _parent =  parent;
    }

    public RobotBuilder getRobotBuilder() {
        return null; //_parent == null ? (RobotBuilder)this : _parent.getRobotBuilder();
    }
 * 
 public Part createPart(String name) {
     return _build.createPart(name, name);
    }

    protected void install() {
        install(_part);
    }

    protected void install(Part part) {
        _parent.install(part);
    }
    */

    public String getName() {
        return  (_part != null) ? _part.name : this.name;
    }

    public Builder Name(String newName) {
        name = newName;
        if (_part != null) {
            _part.name = newName;
        }
        return this;
    
    }   
    // public Builder install(IBuild build) {
    //     build.install(this);
    //     return this;
    // }
    
    // initial part constructor, reusable with new fn for modification
    public Builder create(IBuild build) {
        _build = build;
        _part = new Part().Name(name);
        setValue("buildType", buildType);
        build();
        return this; //_part;
    }
    
    public Builder create(IBuild build, Function<? extends Builder, Builder> fn) {
        create(build);
        apply(fn);
        return this;
    }

    public Builder create(IBuild build, String name, Function<? extends Builder, Builder> fn) {
        create(build, name);
        apply(fn);
        return this;
    }

    public Builder create(IBuild build, String name) {
        _build = build;
        _part = new Part().Name(name);
        setValue("buildType", buildType);
        build();
        return this;
    }

    public Builder build() {
        if (buildType != "Part" && buildType != _part.getValue("buildType")) {
            _part.addError(buildType + " expected");
        }
        apply(buildFunction);//onBuild(_part, buildFunction);
        //install(_part);
        return this;
    }

    protected void onBuild(Part part, Function<Builder, Builder> fn) {
        apply(fn);
    }

    // (intended) empty builder (e.g., of another subclass) to do the work
    protected <T extends Builder> Object eval(Function<T, Object> fn, Part part) {
        _part = part;
        return fn.apply((T)this);
    }

    // use another builder (of the right subclass) to apply
    protected <T extends Builder> Object evalWith(Function<T, Object> fn, T builder) {
        return builder.eval(fn, _part);
    }

    // Normal apply
    protected <T extends Builder> void apply(Function<T, Builder> fn) {
        if (fn != null) {
            fn.apply((T)this);
        }
        //return this;
    }
    
    public Builder Build(Part part) {
        _part = part;
        return build();
    }

    public Builder Build(IBuild build, Part part, Function<? extends Builder, Builder> fn) {
        _part = part;
        buildFunction = fn;
        return build();
    }
    
    public Builder Create(String partName, Function<? extends Builder, Builder> fn) {
        var builder = _build.createBuilder(partName, fn);
        return builder;
    }
    public Builder Existing(String partName, Function<? extends Builder, Builder> fn) {
        var builder = _build.modifyBuilder(partName, fn);
        return builder;
    }

    public Builder Install(String newName, Function<Builder, Builder> fn) {
        var builder = fn.apply(this);
        builder.Name(newName);
        builder.addPartTo(this);
        return this;
    }
    
    private void addPartTo(Builder builder) {
        builder.addPart(_part);
    }
    
    private void addPart(Part part) {
        _part.addPart(part);
    }
/*
 * 
    public Builder Build(String deviceId, String partName, Function<Part, Part> fn)
    {        
        _part = createPart(partName);
        _part.name= deviceId;
        fn.apply(_part);
        install(_part);
        return this;
    }

    public Builder Build(String name, Function<Part, Part> fn)
    {        
        return Build(name, name, fn);
    }    
 */
    
    public Builder Value(String valueName, Object value) {
        return setValue(valueName, value);
    }

    protected Builder setValue(String valueName, Object value) {
        _part.setValue(valueName, value);
        return this;
    }

    public void configure(Function<Part, Part> fn)
    {
        fn.apply(_part);
    }

    public Object getValue(String valueName) {
        return _part.getValue(valueName);
    }
    public Boolean getBoolean(String valueName) {
        return (Boolean)getValue(valueName);
    }
    
    public Double getDouble(String valueName) {
        return (Double)getValue(valueName);
    }

    public Double getDoubleFromInt(String valueName) {
        return ((Integer)getValue(valueName)).doubleValue();
    }

    public Part getPart(String valueName) {
        return (Part)getValue(valueName);
    }

    public Builder CanInfo(Function<CanInfo, CanInfo> fn)
    {
        _part.CanInfo(fn);
        return this;
    }

    public void setCanNumber(int number) {
        configure(part -> part.CanInfo(c -> c.Number(number)));
    }

    public Builder PowerProfile(double peakPower) {
        _part.PowerProfile(peakPower);
        return this;
    }

    public Builder PowerProfile(PowerProfile power) {
        _part.PowerProfile(power);
        return this;
    }

}
