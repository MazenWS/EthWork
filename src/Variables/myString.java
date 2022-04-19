package Variables;

public class myString extends Variable{
    String name;
    String initialValue;
    AccessModifier accessmodifier;

//0.0// as a data type when used in an array|| mapping
    public myString(){

    }
    //1//generic --> State Variable|| function parameter|| local Variable
    //1.1// uninitialised
    public myString(String name){
        this.name= name;
    }
    //1.2// initialised
    public myString(String name, String initialValue){
        this.name= name;
        this. initialValue= initialValue;

    }
    //2// State Variable -->defined with an access modifier
    //2.1// uninitialised
    public myString(String name, AccessModifier accessmodifier){
        this.name= name;
        this.accessmodifier= accessmodifier;
    }
    //2.2// initialised
    public myString(String name, String initialValue, AccessModifier accessmodifier){
        this.name= name;
        this. initialValue= initialValue;
        this. accessmodifier = accessmodifier;

    }
}
