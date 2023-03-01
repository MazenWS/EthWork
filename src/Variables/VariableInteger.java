package Variables;

public class VariableInteger implements Variable{

    boolean signed;
    int length_powerOfTwo;

     public VariableInteger(boolean signed, int length_powerOfTwo) throws Exception
     {if (length_powerOfTwo<3|| length_powerOfTwo>7){
         throw new Exception("this size of int doesn't exist!!");
     }
       this.signed = signed;
       this.length_powerOfTwo= length_powerOfTwo;
     }

    @Override
    public String write() {
        String res =  signed? "int" : "uint";
        if(length_powerOfTwo != 7){
            res += (int)Math.pow(2, length_powerOfTwo);
        }
        return res;
    }
}
