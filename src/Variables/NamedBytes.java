package Variables;

public class NamedBytes extends VariableBytes implements NamedVariable{
    String name;


    public NamedBytes(String name, int length ) throws Exception {
        super(length);
        this. name = name;

    }

    @Override
    public String write(){
        String res = super.write()+" ";
        return res+name;
    }
}
