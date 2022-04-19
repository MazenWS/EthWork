package Variables;
// take care that if it is defined payable  and you want to add an initial value
// -->  the value has to be explicitly type casted to payable
public class Address {
    String name;
    String initialValue;
    AccessModifier accessModifier;
    boolean payable;


     //0.0// as a data type when used in an array|| mapping
    public Address (boolean payable){
        this.payable= payable;
    }

    //1//generic --> State Variable|| local Variable || function parameter
    //1.1//uninitialised
    public Address(String name, boolean payable){
         this.name = name ;
         this.payable = payable;

    }
    //1.2// initialized
    public Address(String name, boolean payable, String initialValue){
        this.name = name ;
        this.payable = payable;
        this. initialValue= initialValue;

    }


    //2// State Variable
    //2.1// uninitialised
    public Address(String name, boolean payable, AccessModifier accessModifier){
        this.name = name ;
        this.payable = payable;
        this.accessModifier= accessModifier;

    }
    //1.2// initialized
    public Address(String name, boolean payable, String initialValue, AccessModifier accessModifier){
        this.name = name ;
        this.payable = payable;
        this. initialValue= initialValue;
        this.accessModifier= accessModifier;

    }
}
