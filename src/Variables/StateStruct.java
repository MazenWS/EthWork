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

    public String write(){
        String res = super.write();
        String[] var = res.split(" ");
        res= String.join(" ",var[0], accessModifier.name().toLowerCase(),var[1]);
        if (initialValue!= null){
            res+= " = ";
            res+= initialValue ;

        }
        res+=";";
        return res;
    }
}
