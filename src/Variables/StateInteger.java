package Variables;

public class StateInteger extends NamedInteger implements  StateVariable {

    AccessModifier accessModifier;
    int initialValue;
    boolean initialised;

    //1// uninitialised
    public StateInteger ( String name, boolean signed, AccessModifier accessModifier, int length_powerOfTwo){
        super(name,signed, length_powerOfTwo);
        this.accessModifier= accessModifier;
        this.initialised= false;
    }
    //2// initialised
    public StateInteger ( String name, boolean signed, AccessModifier accessModifier, int length_powerOfTwo, int initialValue){
        super(name ,signed, length_powerOfTwo);
        this.accessModifier= accessModifier;
        this.initialValue = initialValue;
        this.initialised= true;
    }
    public String write(){
        String res = super.write();
        String[] var = res.split(" ");
        res= String.join(" ",var[0], accessModifier.name().toLowerCase(),var[1]);
        if (initialised){
            res+= " = ";
            res+= initialValue ;

        }
        res+=";";
        return res;
    }
}
