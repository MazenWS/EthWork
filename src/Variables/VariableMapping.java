package Variables;
//mappings cannot be created as a local variable or event parameter
public class VariableMapping implements Variable{

    //valuetype datatypes only including address and enum
    Variable keyType;

    //All complex datatypes including  mapping array and struct
    Variables.Variable valueType;

    public VariableMapping (Variable keyType, Variable valueType){
        this.keyType= keyType;
        this.valueType= valueType;
    }

    @Override
    public String write() {
        return "mapping("+keyType.write()+"=>"+valueType.write()+")";
    }
}
