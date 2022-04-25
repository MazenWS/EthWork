package Variables;

import Steps.Step;

public class ParameterInteger extends NamedInteger implements  ParameterVariable, Step {
    String name;
    public ParameterInteger(String name, boolean signed, int length_powerOfTwo){
        super(name,signed, length_powerOfTwo);
    }

    @Override
    public String write(){
        return super.write();
    }

}
