package team1502.configuration.Builder;

public class ControllerBuilder extends Builder {

    public ControllerBuilder(Builder parent) {
        super(parent);
        //TODO Auto-generated constructor stub
    }
    
    public ControllerBuilder CanNumber(int number) {
        setCanNumber(number);
        return this;
    }
}
