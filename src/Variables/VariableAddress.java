package Variables;
// take care that if it is defined payable  and you want to add an initial value
// -->  the value has to be explicitly type casted to payable
public class VariableAddress implements Variable {

    boolean payable;


     //0.0// as a data type when used in an array|| mapping
    public VariableAddress(boolean payable){
        this.payable= payable;
    }

    @Override
    public String write(){
        String res = "address";
        res += payable? " payable":"";

        return res;
    }





}
