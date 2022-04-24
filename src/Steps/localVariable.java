package Steps;

import Variables.Variable;

public class localVariable  extends Step{
    Variable variable;


    public localVariable( String name ){
this.variable = new Variable() {
    @Override
    public String write() {
        return null;
    }
};

    }

    @Override
    public String write() throws Exception {
        return null;
    }
}
