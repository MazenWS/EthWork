package Variables;

public class StateString extends NamedString implements StateVariable{

AccessModifier accessModifier;
String initialValue;


    public StateString(String name, AccessModifier accessmodifier){
        super(name);
        this.accessModifier= accessmodifier;
    }
    //2.2// initialised
    public StateString(String name, String initialValue, AccessModifier accessmodifier){
        super(name);
        this. initialValue= initialValue;
        this. accessModifier = accessmodifier;

    }
}

