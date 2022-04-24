package Variables;

public class EventInteger extends VariableInteger implements EventVariable{
    String name;
    boolean indexed;

    //1// unnamed
    public EventInteger( boolean signed ,int length_powerOfTwo , boolean indexed){
     super(signed, length_powerOfTwo);
        this.indexed= indexed;

    }
    //2// named
    public EventInteger(boolean signed ,int length_powerOfTwo ,String name, boolean indexed){
        super(signed, length_powerOfTwo);
        this.name= name;
        this.indexed= indexed;

    }
}
