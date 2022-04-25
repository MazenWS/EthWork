package Variables;

import Steps.Step;

//for local variable let it return parameterVar---> the uinitialised version of each
public class LocalString extends ParameterString implements Step {
    String initialValue;

    public LocalString(String name, String initialValue){
        super(name);
        this. initialValue= initialValue;
    }

    @Override
    public String write(){
        String res = super.write();
        if(initialValue != null)
            res += " = " + initialValue;
        return res;
    }
}
