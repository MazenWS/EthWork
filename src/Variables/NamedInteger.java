package Variables;

public class NamedInteger extends VariableInteger implements NamedVariable{
    String name;
    public NamedInteger(String name, boolean signed, int length_powerOfTwo) throws Exception {
        super(signed, length_powerOfTwo);
        this.name= name;

    }

    @Override
    public String write(){
        String res = super.write()+" ";
        return res+name;
    }
}
