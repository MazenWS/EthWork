package Variables;

import Steps.Step;

//for local variable let it return parameterVar---> the uinitialised version of each
public class LocalString extends ParameterString implements Step {
    String initialValue;


    public LocalString(String name,DataLocation dataLocation, String initialValue ){
        super(name,dataLocation);
        this. initialValue= initialValue;
    }
    public LocalString(String name,DataLocation dataLocation){
        super(name,dataLocation);
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
