import java.util.*;
public class StateVariable extends Variable{
    DataType dataType;
    String name;
    String initialValue;
    AccessModifier accessModifier;
    boolean payable;

    public StateVariable(DataType dataType, String name, String initialValue , AccessModifier accessModifier ) throws Exception {

        if(dataType== DataType.STRUCT)
            throw new Exception("Use the Struct class!!");

        boolean payable1;
        payable1 = false;
        this.dataType=dataType;
        this.name=name;
        this.accessModifier=accessModifier;
        this.initialValue=initialValue;

        if (dataType== DataType.ADDRESS){
            Scanner isPayable = new Scanner(System.in);
            System.out.println("is the address with the name "+this.name+" a payable address? Please enter" +
                    " \"True\" if yes and \"False\" otherwise."  );

            payable1 = isPayable.nextLine().equals("True");
        }

        this.payable = payable1;

    }

    public StateVariable(DataType dataType, String name) {
        this.dataType= dataType;
        this.name= name;

    }
}
