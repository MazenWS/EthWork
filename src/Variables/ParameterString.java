package Variables;

import Steps.Step;

public class ParameterString extends NamedString implements ParameterVariable, Step {

    public ParameterString(String name){
super(name);
    }

    @Override
    public String write(){
        return super.write();
    }
}
