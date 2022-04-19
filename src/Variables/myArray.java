package Variables;

import java.util.ArrayList;

//Storage arrays can be dynamic static ay hagga no one cares
//ygh has to be static
public class myArray extends Variable {
    // variables including structs  and mappings

   Variable arrayType;
   int size;
   AccessModifier accessModifier;
   ArrayList<String> initialValue;
    String name;

    //0// when a recursive array
    //0.1//dynamic size
   public myArray(Variable variable ){
      this.arrayType= variable;


}
    // 0.2// static size
    public myArray(Variable variable, int size){
        this.arrayType= variable;

        this.size= size;

    }



    // 1.0 //generic for state variable || local variable|| function parameter
    //--> in th case of (local variable|| function parameter) it's a memory so no need to mention
    //or function parameter calldata only if read-only

    //1.0.1//dynamic size
    public myArray(Variable variable, String name){
        this.arrayType= variable;
        this.name= name;

    }
    //1.0.2// static size
     public myArray(Variable variable, String name, int size){
        this.arrayType= variable;
        this.name= name;
        this.size= size;

    }
    //1.0.3// static with initial value
    public myArray(Variable  variable, String name, int size, ArrayList<String>  initialValue){
        this.arrayType= variable;
        this.name= name;
        this.size= size;
        this.initialValue= initialValue;

    }


    //1.1//constructor for state variables

    //1.1.1// constructor for the dynamic storage array as a state variable
    public myArray(Variable variable, AccessModifier accessModifier, String name){
        this.arrayType= variable;
        this.accessModifier = accessModifier;
        this.name= name;

    }


    //1.1.2//constructor for the static size
    public myArray(Variable variable, AccessModifier accessModifier, String name, int size){
        this.arrayType= variable;
        this.accessModifier = accessModifier;
        this.size= size;
        this.name= name;
    }

    //1.1.3// static size with initial values
    public myArray(Variable  variable,AccessModifier accessModifier, String name, int size, ArrayList<String>  initialValue){
        this.arrayType= variable;
        this.accessModifier = accessModifier;
        this.name= name;
        this.size= size;
        this.initialValue= initialValue;

    }

    //1.1.4// initial values only
    public myArray(Variable  variable,AccessModifier accessModifier, String name, ArrayList<String>  initialValue){
        this.arrayType= variable;
        this.accessModifier = accessModifier;
        this.name= name;
        this.initialValue= initialValue;

    }





}

