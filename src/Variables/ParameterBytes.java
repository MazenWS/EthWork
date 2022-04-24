package Variables;

public class ParameterBytes extends NamedBytes implements ParameterVariable {
    String name;


    public ParameterBytes(String name, int length ){
       super(name, length);

    }
}
