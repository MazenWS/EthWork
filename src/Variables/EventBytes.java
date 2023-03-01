package Variables;

public class EventBytes extends VariableBytes implements EventVariable{
    String name;
    boolean indexed;
    //1// unnamed
    public EventBytes(  boolean indexed , int length ) throws Exception {
        super(length);
        this.indexed= indexed;}


         //2// named
        public EventBytes(String name, boolean indexed , int length ) throws Exception {
            super(length);
            this.name=name;
            this.indexed= indexed;
    }

    @Override
    public String write(){
        String res = super.write();
        res += indexed? " indexed ":" ";
        return res+name;
    }

}
