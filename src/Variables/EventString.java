package Variables;

public class EventString extends VariableString implements EventVariable  {
    boolean indexed;
    String name;

    //1// unnamed
    public EventString( boolean indexed){

        this.indexed= indexed;

    }
    //2// named
    public EventString(String name, boolean indexed){
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
