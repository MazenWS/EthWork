package Variables;

public class LocalAddress extends ParameterAddress implements LocalVariable{
    public String initialValue;

    public LocalAddress (String name, boolean payable, String initialValue){
        super(name, payable);
        this. initialValue= initialValue;

    }

    @Override
    public String write(){
        String res = super.write();
        res += " = " + initialValue;
        return res;
    }


}


