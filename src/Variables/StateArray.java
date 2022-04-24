package Variables;

import Methods.AccessModifier;

import java.util.ArrayList;

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


}
