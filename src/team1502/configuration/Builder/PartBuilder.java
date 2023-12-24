package team1502.configuration.Builder;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

import team1502.configuration.Factory.Part;

// Information needed to build, maybe context
public class PartBuilder<T extends Part> {
    private Class<T> _class;
    private Function<String, T> _constructor; 
    private Function<T, Part> _buildFunction; 
    private String _name;

    public PartBuilder(String name, Class<T> genericClass, Function<T, Part> fn) {
        _name = name;
        _class = genericClass;
        _buildFunction = fn;
    }

    public PartBuilder(String name, Function<String, T> ctor, Function<T, Part> fn) {
        _name = name;
        _constructor = ctor;
        _buildFunction = fn;
    }

    public T buildPart() {  //throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        T part = null;
        if (_constructor != null) {
            part = _constructor.apply(_name);
        } else {
            try {
                part = _class.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        // build part
        part.Name(_name);
        _buildFunction.apply(part);
        // part.buildParts(part);
        // // build sub-parts
        // var parts = part.getParts();
        // parts.forEach(p -> p.buildPart());
        
        return part;
    }
}
