package Variables;

public class StateStruct  extends NamedStruct implements StateVariable{

    AccessModifier  accessModifier;
    String[] initialValue;

    public StateStruct(String name, String theStruct,AccessModifier  accessModifier,String[] initialValue) {
        super(theStruct, name);
        this.accessModifier = accessModifier;
        this.initialValue= initialValue;
    }
    public StateStruct(String name, String theStruct,AccessModifier  accessModifier) {
        super(theStruct,name);
        this.accessModifier = accessModifier;
    }

    public StateStruct(String name,String theStruct, String[] initialValue) {
        super(theStruct, name);
        this.accessModifier =AccessModifier.INTERNAL;
        this.initialValue= initialValue;
    }
    public StateStruct( String name,String theStruct) {
        super(theStruct,name);
        this.accessModifier = AccessModifier.INTERNAL;
    }

    public String write(){
        String res = super.write();
        String[] var = res.split(" ");
        res= String.join(" ",var[0], accessModifier.name().toLowerCase(),var[1]);
        if (initialValue!= null){
            res+= " = ";
            res+= theStruct +"( ";
            for (String s: initialValue ) {
                res+=s;
                res+= ",";

            }
            res=res.substring(0, res.length() - 1);
            res+= ");";



        }
        res+=";";
        return res;
    }
}
