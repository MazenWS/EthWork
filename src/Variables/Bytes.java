package Variables;

public class Bytes {
    String name;
    String initialValueInHex;
    int length;
    AccessModifier accessModifier;
    //0.0// as a data type when used in an array|| mapping
  public Bytes( int length ){
    this. length = length;
}
    // 1// generic
    //1.1// uninitialised
    public Bytes(String name, int length ){
        this. name = name;
         this. length = length;
    }
    //1.2// initialized --> only local || state variables
    public Bytes (String name, int length, String initialValueInHex){
        this.name= name;
        this. length = length;
        this .initialValueInHex= initialValueInHex;

    }

    //2//state variables
    //2.1// uninitialized
    public Bytes(String name, int length, AccessModifier accessModifier ){
        this. name = name;
        this. length = length;
        this. accessModifier= accessModifier;
    }
    //2.2// initialized
    public Bytes(String name, int length, AccessModifier accessModifier, String initialValueInHex){
        this. name = name;
        this. length = length;
        this. accessModifier= accessModifier;
        this.initialValueInHex = initialValueInHex;
    }

}
