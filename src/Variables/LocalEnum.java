package Variables;

import Steps.Step;

public class LocalEnum extends ParameterEnum implements Step {
String initialValue;

    public LocalEnum( String name,String theEnum, String initialValue) throws Exception {
        super(theEnum,name);
        this.initialValue = initialValue;
    }
    public LocalEnum( String name,String theEnum) throws Exception {
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
