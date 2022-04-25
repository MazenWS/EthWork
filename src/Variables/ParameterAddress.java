package Variables;

import Steps.Step;

public class ParameterAddress extends NamedAddress  implements ParameterVariable, Step {

    public ParameterAddress(String name, boolean payable ){
        super(name, payable);
    }

}
