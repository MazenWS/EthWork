package Variables;

public class VariableInteger implements Variable{

    boolean signed;
    int length_powerOfTwo;
     public VariableInteger(   boolean signed, int length_powerOfTwo){
       this.signed = signed;
       this.length_powerOfTwo= length_powerOfTwo;
     }
}
