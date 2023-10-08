package team1502.configuration;

import java.util.function.Function;

public class PDP {
    
    public PDP Ch(Integer channel, String name) {
        return this;
    }
    public PDP Ch(Integer channel, int fuze, String name) {
        return this;
    }
    public PDP Ch(Integer channel) { // empty
        return this;
    }
    public PDP Module(String module, String ... sub) {
        return this;
    }
    public PDP Module(String module, Function<PDP,PDP> fn) {
        return this;
    }
}
