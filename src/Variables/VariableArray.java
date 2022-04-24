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

    @Override
    public String write(){
        String var = arrayType.write();
        String[] varSplit = var.split(" ");
        varSplit[0] += "[";
        varSplit[0] += size > 0?size+"]" : "]";
        return String.join(" ",varSplit);
    }

    public static void main(String[] args) {
        VariableArray a = new VariableArray(new VariableString(),2);
        System.out.println(a.write());
    }
}
