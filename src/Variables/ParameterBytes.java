package Variables;

import Steps.Step;

public class ParameterBytes extends NamedBytes implements ParameterVariable, Step {


    public ParameterBytes(String name, int length ){
       super(name, length);
    }

    @Override
    public String write(){
        return super.write();
    }
}
