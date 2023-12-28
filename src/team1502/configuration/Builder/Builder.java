package team1502.configuration.Builder;

import java.util.function.Function;

import team1502.configuration.Parts.Part;

public class Builder {
    private Builder _parent;
    private Part _part;

    public Builder(Builder parent){
        _parent =  parent;
    }

    public RobotBuilder getRobotBuilder() {
        return _parent == null ? (RobotBuilder)this : _parent.getRobotBuilder();
    }

    public Part createPart(String name) {
        return _parent.createPart(name);
    }

    protected void install() {
        install(_part);
    }

    protected void install(Part part) {
        _parent.install(part);
    }

    protected void build() {

    }
    
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
    
    protected void setValue(String valueName, Object value) {
        _part.setValue(valueName, value);
    }

    public void configure(Function<Part, Part> fn)
    {
        fn.apply(_part);
    }

    public void setCanNumber(int number) {
        configure(part -> part.CanInfo(c -> c.Number(number)));
    }
}
