package Variables;

public class VariableArray implements Variable {
Variable arrayType;
Integer size;

     //1// dynamic Size
    public VariableArray(Variable variable ){
        this.arrayType= variable;


    }
    // 2// static size
    public VariableArray(Variable variable, int size){
        this.arrayType= variable;
        this.size= size;

    }
}
