package Variables;

import Methods.Struct;

public class VariableStruct implements Variable{
    String theStruct;

    //removed the exception because inheritance make it impossible to know.
    public VariableStruct (String theStruct){

            this.theStruct= theStruct;
    }

    @Override
    public String write() {
        return theStruct;
    }
}
