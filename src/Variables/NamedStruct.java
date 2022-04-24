package Variables;

public class NamedStruct extends VariableStruct implements NamedVariable{
    String name ;

    public NamedStruct(String theStruct,String name ) throws Exception {
        super(theStruct);
        this.name = name;

    }
}
