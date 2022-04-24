package Variables;

public class ParameterInteger extends NamedInteger implements  ParameterVariable{
    String name;
    public ParameterInteger(String name, boolean signed, int length_powerOfTwo){
        super(name,signed, length_powerOfTwo);


    }


}
