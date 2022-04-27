package Variables;

import Steps.Step;

public class ParameterBytes extends NamedBytes implements ParameterVariable  {


    public ParameterBytes(String name, int length ) throws Exception {

        super(name, length);
    }

    @Override
    public String write(){
        return super.write();
    }
}
