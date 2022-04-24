package Variables;

public class LocalBool extends ParameterBool implements LocalVariable {

    boolean initialValue;
    public LocalBool(String name, boolean initialValue){
        super(name);
        this. initialValue= initialValue;
    }

    @Override
    public String write(){
        String res = super.write();
        res += " = " + initialValue;
        return res;
    }
}
