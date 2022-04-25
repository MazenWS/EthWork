package Variables;

import Steps.Step;

public class ParameterBool extends NamedBool implements ParameterVariable, Step {
    public ParameterBool (String name){
        super(name);
    }

    @Override
    public String write(){
        return super.write();
    }
}
