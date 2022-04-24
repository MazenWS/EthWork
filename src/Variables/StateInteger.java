package Variables;

public class StateInteger extends NamedInteger implements  StateVariable {

    boolean signed;
    AccessModifier accessModifier;
    int initialValue;
    int length_powerOfTwo;
    //1// uninitialised
    public StateInteger ( String name, boolean signed, AccessModifier accessModifier, int length_powerOfTwo){
        super(name,signed, length_powerOfTwo);
        this.accessModifier= accessModifier;
    }
    //2// initialised
    public StateInteger ( String name, boolean signed, AccessModifier accessModifier, int length_powerOfTwo, int initialValue){
        super(name ,signed, length_powerOfTwo);
        this.accessModifier= accessModifier;
        this.initialValue = initialValue;
    }
}
