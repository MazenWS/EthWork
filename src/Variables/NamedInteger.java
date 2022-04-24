package Variables;

public class NamedInteger extends VariableInteger implements NamedVariable{
    String name;
    public NamedInteger(String name, boolean signed, int length_powerOfTwo){
        super(signed, length_powerOfTwo);
        this.name= name;

    }
}
