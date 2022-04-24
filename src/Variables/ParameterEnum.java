package Variables;

public class ParameterEnum extends NamedEnum implements ParameterVariable{
    String name;
    public ParameterEnum( String theEnum,String name) throws Exception {
     super(theEnum, name);
    }

}