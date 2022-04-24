package Variables;

public class NamedEnum extends VariableEnum implements NamedVariable {
    String name;
    public NamedEnum( String theEnum,String name) throws Exception {
        super(theEnum);
        this.name = name;
    }
}
