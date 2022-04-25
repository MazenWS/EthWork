package Variables;

public class StateBool extends NamedBool implements StateVariable {

    AccessModifier accessModifier;
    boolean initialValue;
    boolean initialised;


    //1// uninitialised
    public StateBool(String name, AccessModifier accessModifier ){
    super(name);
        this. accessModifier = accessModifier;
        this.initialised=false;

    }
    //2// init
    public StateBool(String name, AccessModifier accessModifier, boolean initialValue){
      super(name);
        this. accessModifier = accessModifier;
        this.initialValue= initialValue;
        this.initialised= true;
    }
    public String write(){
             String res = super.write();
             String[] var = res.split(" ");
             res= String.join(" ",var[0], accessModifier.name().toLowerCase(),var[1]);
             if (initialised){
                 res+= " = ";
                res+= initialValue? "true": "false";

             }
             res+=";";
             return res;
    }

    public static void main(String[] args){
            StateBool bool = new StateBool("bool1",AccessModifier.PUBLIC);
            StateBool bool2 = new StateBool("bool2",AccessModifier.PRIVATE, false);
            System.out.println(bool.write());
            System.out.println(bool2.write());
    }
}
