package Variables;

public class StateBytes extends NamedBytes implements StateVariable{

    String initialValueInHex;
    AccessModifier accessModifier;

    //2.1// uninitialized
    public StateBytes(String name, int length, AccessModifier accessModifier ){
        super(name,length);
        this. accessModifier= accessModifier;
    }
    //2.2// initialized
    public StateBytes(String name, int length, AccessModifier accessModifier, String initialValueInHex){
        super(name,length);
        this. accessModifier= accessModifier;
        this.initialValueInHex = initialValueInHex;
    }

}
