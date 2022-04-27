package Variables;

import Steps.Step;

public class ParameterAddress extends NamedAddress  implements ParameterVariable  {

    public ParameterAddress(String name, boolean payable ){
        super(name, payable);
    }

}
