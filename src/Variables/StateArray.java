package Variables;

import Methods.AccessModifier;

import java.util.ArrayList;
import java.util.Iterator;

public class StateArray extends NamedArray implements StateVariable{

    AccessModifier accessModifier;
    ArrayList<String> initialValue;


    //1// constructor for the dynamic storage array as a state variable
    public StateArray(Variable variable, AccessModifier accessModifier, String name){
        super(name, variable);
        this.accessModifier = accessModifier;


    }


    //2//constructor for the static size
    public StateArray(Variable variable, AccessModifier accessModifier, String name, int size){
        super(name,variable, size);
        this.accessModifier = accessModifier;

    }

    //3// static size with initial values
    public StateArray(Variable  variable, AccessModifier accessModifier, String name, int size, ArrayList<String>  initialValue){
     super(name,variable, size);
        this.accessModifier = accessModifier;
        this.initialValue= initialValue;

    }

    //4// initial values only
    public StateArray(Variable  variable, AccessModifier accessModifier, String name, ArrayList<String>  initialValue){
        super(name,variable);
        this.accessModifier = accessModifier;
        this.initialValue= initialValue;

    }
public String write(){
    String res  = super.write();
    String[] var = res.split(" ");
    res= String.join(" ",var[0], accessModifier.name().toLowerCase(),var[1]);


    if(initialValue!=null){

        res+= " = [";

        Iterator iter=initialValue.iterator();
        while (iter.hasNext()) {
            res+=iter.next();
            res+=",";

    }
        res=res.substring(0, res.length() - 1);
        res+= "]";

    }
    res+=";";
    return res;

}
    public static void main(String[] main){
       ArrayList values= new ArrayList<String>( );
       values.add ("1");
       values.add("2");
        StateArray a = new StateArray( new VariableInteger(false,64), AccessModifier.PUBLIC,"arr",values);
        System.out.println(a.write());
    }
}
