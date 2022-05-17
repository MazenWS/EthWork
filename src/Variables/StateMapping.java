package Variables;

public class StateMapping extends NamedMapping implements StateVariable{
    AccessModifier accessModifier;


    public StateMapping (String name,Variable keyType, Variable valueType, AccessModifier accessModifier){
        super(name, keyType, valueType);
        this.accessModifier= accessModifier;
    }
    public StateMapping (String name ,Variable keyType, Variable valueType){
        super(name, keyType, valueType);
        this.accessModifier= AccessModifier.INTERNAL;
    }
    public String write(){
        String res = super.write();
        String[] var = res.split(" ");
        res= String.join(" ",var[0], accessModifier.name().toLowerCase(),var[1]);
        res+=";";
        return res;
    }

}
