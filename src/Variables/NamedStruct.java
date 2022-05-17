package Variables;

public class NamedStruct extends VariableStruct implements NamedVariable{
    String name ;

    public NamedStruct(String name ,String theStruct) {
        super(theStruct);
        this.name = name;
    }
    @Override
    public String write(){
        String res = super.write()+" ";
        return res+name;
    }
}
