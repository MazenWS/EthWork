package Variables;

//They cannot be used as parameters or return parameters of contract functions that are publicly visible.
//These restrictions are also true for arrays and structs that contain mappings.
public class mapping extends Variable {
    //valuetype datatypes only including address and enum
    Variable keyType;
    //All complex datatypes including  mapping array and struct
    Variable valueType;
    String name;
    AccessModifier accessModifier;
    String keyTypeEnum;
    String valueTypeEnum;

    //0.0//
    public mapping (Variable keyType, Variable valueType){
        this.keyType= keyType;
        this.valueType= valueType;
    }






    //1.1//
    public mapping (Variable keyType, Variable valueType,String name){
        this.keyType= keyType;
        this.valueType= valueType;
        this.name = name;
    }
    //2.1// State Variable
    public mapping (Variable keyType, Variable valueType,String name, AccessModifier accessModifier){
        this.keyType= keyType;
        this.valueType= valueType;
        this.name = name;
        this.accessModifier= accessModifier;
    }
    //




}
