package Variables;

public class StateEnum extends NamedEnum implements StateVariable {

    AccessModifier accessModifier;
     String initialValue;
     //uninitialised
    public StateEnum (String theEnum, String name, AccessModifier accessModifier) throws Exception {
        super(theEnum, name);
        this.accessModifier = accessModifier;

    }
    //initialised
    public StateEnum (String theEnum, String name, AccessModifier accessModifier, String initialValue) throws Exception {
        super(theEnum, name);
        this.accessModifier = accessModifier;
        this.initialValue= initialValue;
    }



}
