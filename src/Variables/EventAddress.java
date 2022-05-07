package Variables;

public class EventAddress extends VariableAddress implements EventVariable {
    String name;
    boolean indexed;

     //1// un-named
     public EventAddress(boolean indexed, boolean payable){
        super(payable);
         this.indexed= indexed;

     }

    //2//named
    public EventAddress(String name,boolean indexed, boolean payable){
         super(payable);
        this.name= name;
        this.indexed= indexed;

    }

    @Override
    public String write() {
         String res = super.write();
         res += indexed? " indexed ":" ";
         return res+name;
    }
}
