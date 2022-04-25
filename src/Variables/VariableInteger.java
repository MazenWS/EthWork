package Variables;

public class VariableInteger implements Variable{

    boolean signed;
    int length_powerOfTwo;
     public VariableInteger(   boolean signed, int length_powerOfTwo){
       this.signed = signed;
       this.length_powerOfTwo= length_powerOfTwo;
     }

    @Override
    public String write() {
        return signed? "int"+(int)Math.pow(2, length_powerOfTwo) : "uint"+ (int)Math.pow(2, length_powerOfTwo);
    }
}
