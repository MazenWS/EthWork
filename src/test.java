import Contracts.Contract;
import Contracts.TheFile;
import Methods.*;
import Methods.AccessModifier;
import Steps.*;
import Variables.*;

public class test {

    public static void main(String[] args) {
        TheFile f= new TheFile("ErrorTest");
        Contract c = new Contract("MyContract");

        try {
            c.addStateVariable(new StateInteger("value",false,7));
            c.addEvent(new Event("ValueSet", new EventVariable[] {
                    new EventInteger("val",false,7, false)
            }));
            Method method1 = new Method("getValue",null,
                    AccessModifier.PUBLIC, Type.PURE,null,
                    new ParameterVariable[] { new ParameterInteger("",false,7)});
            method1.addSteps(new Return("value"));

            Method method2 = new Method("setValue",new ParameterVariable[] {
                    new ParameterInteger("val",false,7)
            },AccessModifier.PUBLIC,Type.NONE,null,null);
            method2.addSteps(new Assign("value","val"));
            method2.addSteps(new FireEvent("ValueSet",new String[] {"value"}));

            c.addMethod(method1);
            c.addMethod(method2);

            f.addContract(c);

            f.createContract();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
