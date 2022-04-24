package Variables;

public class VariableStruct implements Variable{
    String theStruct;
    public VariableStruct (String theStruct) throws Exception {
        if(!Struct.structNames.contains(theStruct))
            throw new Exception("the struct doesn't exist");
            this.theStruct= theStruct;



    }
}
