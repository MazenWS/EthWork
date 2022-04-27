package Variables;

import Steps.Step;

public class LocalBool extends ParameterBool implements Step {

    String initialValue;
    public LocalBool(String name, String initialValue){
        super(name);
        this. initialValue= initialValue;
    }

    public LocalBool(String name){
        super(name);
    }


    @Override
    public String write(){
        String res = super.write();
        if(initialValue != null)
            res += " = " + initialValue;
        res+=";";
        return res;
    }
}
