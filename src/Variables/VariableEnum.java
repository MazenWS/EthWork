package Variables;

public class VariableEnum implements Variable{
    String theEnum;
    public VariableEnum(String theEnum) throws Exception {
        if(!myEnum.createdEnums.contains(theEnum)){
            throw new Exception("this enum does not exist");
        }
        this.theEnum = theEnum;

    }
}
