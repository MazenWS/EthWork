package Variables;

public class EventBool extends VariableBool implements EventVariable{
    String name;
    boolean indexed;
     //1// unnamed
    public EventBool (boolean indexed){
        this.indexed = indexed;
    }
     //2// named
     public EventBool (String name,boolean indexed){
         this.indexed = indexed;
         this.name = name;
     }

    @Override
    public String write(){
        String res = super.write();
        res += indexed? " indexed ":" ";
        return res+name;
    }
}
