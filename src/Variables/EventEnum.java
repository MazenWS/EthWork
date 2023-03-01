package Variables;

public class EventEnum extends VariableEnum implements EventVariable {
    String name;
    boolean indexed;
     public EventEnum( String name,String theEnum , boolean indexed) throws Exception {
         super(theEnum);
         this.name = name;
         this. indexed = indexed;
     }
    public EventEnum(String theEnum , boolean indexed) throws Exception {
        super(theEnum);
        this. indexed = indexed;
    }

    @Override
    public String write(){
        String res = super.write();
        res += indexed? " indexed ":" ";
        return res+name;
    }
}
