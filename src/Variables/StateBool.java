package Variables;

public class StateBool extends NamedBool implements StateVariable {

    AccessModifier accessModifier;
    boolean initialValue;


    //1// uninitialised
    public StateBool(String name, AccessModifier accessModifier ){
    super(name);
        this. accessModifier = accessModifier;

    }
    //2// init
    public StateBool(String name, AccessModifier accessModifier, boolean initialValue){
      super(name);
        this. accessModifier = accessModifier;
        this.initialValue= initialValue;
    }

}
