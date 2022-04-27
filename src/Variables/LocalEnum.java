package Variables;

import Steps.Step;

public class LocalEnum extends ParameterEnum implements Step {
String initialValue;

    public LocalEnum( String theEnum,String name, String initialValue) throws Exception {
        super(theEnum,name);
        this.initialValue = initialValue;
    }
    public LocalEnum( String theEnum,String name) throws Exception {
        super(theEnum,name);

    }
    @Override
    public String write(){
        String res = super.write();
        if(initialValue != null) {
            res += " = " + initialValue;
        }
        res+=";";
        return res;
    }

}
