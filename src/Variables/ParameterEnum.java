package Variables;

import Steps.Step;

public class ParameterEnum extends NamedEnum implements ParameterVariable {
    public ParameterEnum( String theEnum,String name) throws Exception {
     super(theEnum, name);
    }

    @Override
    public String write(){
        return super.write();
    }
}