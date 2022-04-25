package Variables;

import Steps.Step;

public class LocalAddress extends ParameterAddress implements Step, ParameterVariable {
    public String initialValue;

    public LocalAddress (String name, boolean payable, String initialValue){
        super(name, payable);
        this. initialValue= initialValue;

    }

    @Override
    public String write(){
        String res = super.write();
        if(initialValue != null) {
            res += " = " + initialValue;
        }
        return res;
    }


}


