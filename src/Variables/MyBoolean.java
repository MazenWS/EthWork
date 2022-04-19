package Variables;

public class MyBoolean {
    String name;
    boolean initialValue;
    AccessModifier accessmodifier;

    //0.0// as a data type when used in an array|| mapping
    public MyBoolean(){

    }
    //1//generic --> State Variable|| function parameter|| local Variable
    //1.1// uninitialised
    public MyBoolean(String name){
        this.name= name;
    }
    //1.2// initialised
    public MyBoolean(String name, boolean initialValue){
        this.name= name;
        this. initialValue= initialValue;

    }
    //2// State Variable -->defined with an access modifier
   //2.1// uninitialised
    public MyBoolean(String name, AccessModifier accessmodifier){
        this.name= name;
        this.accessmodifier= accessmodifier;
    }
    //2.2// initialised
    public MyBoolean(String name, boolean initialValue, AccessModifier accessmodifier){
        this.name= name;
        this. initialValue= initialValue;
        this. accessmodifier = accessmodifier;

    }


}
