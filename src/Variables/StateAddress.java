package Variables;

public class StateAddress extends NamedAddress implements StateVariable{

    String initialValue;
    AccessModifier accessModifier;


    //1// uninitialised
    public StateAddress(String name, boolean payable, AccessModifier accessModifier){
        super(name,payable);
        this.name = name ;
        this.accessModifier= accessModifier;

    }
    //2// initialized
    public StateAddress(String name, boolean payable, String initialValue, AccessModifier accessModifier){
        super(name,payable);
        this.name = name ;
        this. initialValue= initialValue;
        this.accessModifier= accessModifier;

    }
}
