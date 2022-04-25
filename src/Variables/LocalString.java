package Variables;

import Steps.Step;

//for local variable let it return parameterVar---> the uinitialised version of each
public class LocalString extends ParameterString implements Step {
    String initialValue;
    DataLocation dataLocation;

    public LocalString(String name, String initialValue ,DataLocation dataLocation){
        super(name,dataLocation);
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
