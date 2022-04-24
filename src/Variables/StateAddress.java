package Variables;

import java.util.Locale;

public class StateAddress extends NamedAddress implements StateVariable{

    String initialValue;
    AccessModifier accessModifier;


    //1// uninitialised
    public StateAddress(String name, boolean payable, AccessModifier accessModifier){
        super(name,payable);
        this.name = name ;
        this.accessModifier= accessModifier;

    }
    //2// initialized
    public StateAddress(String name, boolean payable, String initialValue, AccessModifier accessModifier){
        super(name,payable);
        this.name = name ;
        this. initialValue= initialValue;
        this.accessModifier= accessModifier;

    }
    public String write(){
        String res ="address ";
        res+= payable? "payable ": "";
        res+= accessModifier.name().toLowerCase(Locale.ROOT);
        res+= name;
        if (initialValue!= null){
            res+=" = ";
            res+= payable? "payable("+ initialValue+")": initialValue;
        }
        res+=" ;";


return res;
    }


    public static void main (String [] args){

    }


}
