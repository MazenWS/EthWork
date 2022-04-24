package Variables;

public class EventEnum extends VariableEnum implements EventVariable {
    String name;
    boolean indexed;
     public EventEnum(String theEnum , String name, boolean indexed) throws Exception {
         super(theEnum);
         this.name = name;
         this. indexed = indexed;
     }
    public EventEnum(String theEnum , boolean indexed) throws Exception {
        super(theEnum);
        this. indexed = indexed;
    }

}
