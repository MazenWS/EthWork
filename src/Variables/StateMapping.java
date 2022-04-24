package Variables;

public class StateMapping extends NamedMapping implements StateVariable{
    AccessModifier accessModifier;


    public StateMapping (Variable keyType, Variable valueType,String name, AccessModifier accessModifier){
        super(name, keyType, valueType);
        this.accessModifier= accessModifier;
    }
    public String write(){
        String res = super.write();
        String[] var = res.split(" ");
        res= String.join(" ",var[0], accessModifier.name().toLowerCase(),var[1]);
        res+=";";
        return res;
    }

}
