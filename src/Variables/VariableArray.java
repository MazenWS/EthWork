package Variables;

public class VariableArray implements Variable {
Variable arrayType;
Integer size;

     //1// dynamic Size
    public VariableArray(Variable variable ){
        this.arrayType= variable;
        this.size = 0;
    }

    // 2// static size
    public VariableArray(Variable variable, int size){
        this.arrayType= variable;
        this.size= size;
    }

    @Override
    public String write(){
        String var = arrayType.write() + "[";
        return var + (size > 0 ? size + "]" : "]");
    }

    public static void main(String[] args) {
        VariableArray a = new VariableArray(new VariableString(),2);
        System.out.println(a.write());
    }
}
