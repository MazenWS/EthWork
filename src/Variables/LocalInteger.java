package Variables;

public class LocalInteger extends ParameterInteger implements LocalVariable {
    int initialValue;
    public LocalInteger(String name, boolean signed, int length_powerOfTwo, int initialValue){
        super(name, signed, length_powerOfTwo);
        this.initialValue= initialValue;
    }

    @Override
    public String write(){
        String res = super.write();
        res += " = " + initialValue;
        return res;
    }
}
