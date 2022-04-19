package Variables;

public class myInteger extends Variable{

    String name;
    boolean signed;
    AccessModifier accessModifier;
    int initialValue;
    int length_powerOfTwo;


    //0.0// as a data type when used in an array|| mapping
  public myInteger(boolean signed, int length_powerOfTwo ){
    this.signed= signed;
    this.length_powerOfTwo=length_powerOfTwo;


}
     //1// generic -->  state Variable without accesss modifier || function parameter ||  local Variable
    // 1.1// uninitialised

    public myInteger(String name, boolean signed, int length_powerOfTwo){
        this.name= name;
        this.signed= signed;
        this.length_powerOfTwo=length_powerOfTwo;
    }
    //1.2// initialised
    public myInteger(String name, boolean signed, int length_powerOfTwo, int initialValue){
        this.name= name;
        this.signed= signed;

        this.length_powerOfTwo=length_powerOfTwo;
        this.initialValue= initialValue;}


    //2// State Variables --> they are the only variables that can have access modifiers
    //2.1// uninitialised
    public myInteger(String name, boolean signed, AccessModifier accessModifier, int length_powerOfTwo){
        this.name= name;
        this.signed= signed;
        this.accessModifier= accessModifier;
        this.length_powerOfTwo=length_powerOfTwo;
    }
    //2.2// initialized
    public myInteger(String name, boolean signed, AccessModifier accessModifier, int length_powerOfTwo, int initialValue){
        this.name= name;
        this.signed= signed;
        this.accessModifier= accessModifier;
        this.length_powerOfTwo=length_powerOfTwo;
        this.initialValue= initialValue;
    }





}
