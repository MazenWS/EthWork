package Variables;

public class NamedArray extends VariableArray implements NamedVariable{
    String name;


    //1//dynamic size
    public NamedArray(String name,Variable variable){
        super(variable);
        this.name= name;

    }


    //2// static size
    public NamedArray(String name,Variable variable, int size){
        super(variable,size);
        this.name= name;
    }

    @Override
    public String write(){
        String res = super.write()+" ";
        return res+name;
    }

}
