package Variables;

public class NamedEnum extends VariableEnum implements NamedVariable {
    String name;
    public NamedEnum( String name,String theEnum) throws Exception {
        super(theEnum);
        this.name = name;
    }

    @Override
    public String write(){
        String res = super.write()+" ";
        return res+name;
    }
}
