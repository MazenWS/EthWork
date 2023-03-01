package Variables;

public class EventArray extends VariableArray implements EventVariable {
    String name;
    boolean indexed;

    //unnamed-- dynamic
    public EventArray(boolean indexed, Variable variable){
        super(variable );
        this.indexed = indexed;

    }
    // named --dynamic
    public EventArray(String name,boolean indexed,  Variable variable){
        super(variable);
        this.name= name;
        this.indexed = indexed;

    }
    //unnamed static
    public EventArray(boolean indexed, int size, Variable variable){
        super(variable , size);
        this.indexed = indexed;

    }
    // named static
    public EventArray(String name,boolean indexed, int size, Variable variable){
        super(variable , size);
        this.name= name;
        this.indexed = indexed;

    }

    @Override
    public String write(){
        String res = super.write();
        res += indexed? " indexed ":" ";
        return res+name;
    }
}
