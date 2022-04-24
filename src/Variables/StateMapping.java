package Variables;

public class StateMapping extends NamedMapping implements StateVariable{
    AccessModifier accessModifier;


    public StateMapping (Variable keyType, Variable valueType,String name, AccessModifier accessModifier){
        super(name, keyType, valueType);
        this.accessModifier= accessModifier;
    }

}
