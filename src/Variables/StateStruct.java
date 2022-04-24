package Variables;

public class StateStruct  extends NamedStruct implements StateVariable{

    AccessModifier  accessModifier;
    String initialValue;

    public StateStruct(String theStruct,String name, AccessModifier  accessModifier,String initialValue) throws Exception {
        super(theStruct, name);
        this.accessModifier = accessModifier;
        this.initialValue= initialValue;
    }
    public StateStruct(String theStruct,String name, AccessModifier  accessModifier) throws Exception {
        super(theStruct,name);
        this.accessModifier = accessModifier;
    }
}
