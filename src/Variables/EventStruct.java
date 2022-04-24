package Variables;

public class EventStruct extends VariableStruct implements EventVariable {
    String name;
    boolean indexed;
    // unnamed
    public EventStruct (String theStruct, boolean indexed) throws Exception {
        super(theStruct);
        this. indexed= indexed;
    }
    //named
    public EventStruct (String theStruct, boolean indexed,String name) throws Exception {
        super(theStruct);
        this. indexed= indexed;
    }

    @Override
    public String write(){
        String res = super.write();
        res += indexed? "indexed ":" ";
        return res+name;
    }
}
