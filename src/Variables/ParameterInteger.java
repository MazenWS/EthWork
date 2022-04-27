package Variables;

import Steps.Step;

public class ParameterInteger extends NamedInteger implements  ParameterVariable {

    public ParameterInteger(String name, boolean signed, int length_powerOfTwo) throws Exception {
        super(name,signed, length_powerOfTwo);
    }

    @Override
    public String write(){
        return super.write();
    }

}
