package Variables;

public class NamedBytes extends VariableBytes implements NamedVariable{
    String name;


    public NamedBytes(String name, int length ){
        super(length);
        this. name = name;

    }
}
