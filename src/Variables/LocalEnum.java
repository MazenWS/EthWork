package Variables;

public class LocalEnum extends ParameterEnum implements LocalVariable{
String initialValue;

    public LocalEnum( String theEnum,String name, String initialValue) throws Exception {
        super(theEnum,name);
        this.name = name;
        this.initialValue = initialValue;
    }

}
