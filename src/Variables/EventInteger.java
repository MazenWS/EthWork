package Variables;

public class EventInteger extends VariableInteger implements EventVariable{
    String name;
    boolean indexed;

    //1// unnamed
    public EventInteger( boolean signed ,int length_powerOfTwo , boolean indexed) throws Exception {
     super(signed, length_powerOfTwo);
        this.indexed= indexed;

    }
    //2// named
    public EventInteger(String name,boolean signed ,int length_powerOfTwo , boolean indexed) throws Exception {
        super(signed, length_powerOfTwo);
        this.name= name;
        this.indexed= indexed;

    }

    @Override
    public String write(){
        String res = super.write();
        res += indexed? " indexed ":" ";
        return res+name;
    }
}
