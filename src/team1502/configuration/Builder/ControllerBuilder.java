package team1502.configuration.Builder;

public class ControllerBuilder extends Builder {

    public ControllerBuilder(Builder parent) {
        super(parent);
        //TODO Auto-generated constructor stub
    }
    
    public ControllerBuilder CanNumber(int id) {
        _part.canInfo(c->c.Number(id));
        return this;
    }
}
