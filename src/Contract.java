import Methods.Method;
import Methods.Type;
import Steps.Step;
import Variables.*;

import java.util.ArrayList;

public class Contract {

    String contractName;
    ArrayList<Method> methods;
    ArrayList<Struct> structs;
    ArrayList<myEnum> enums;
    ArrayList<StateVariable> stateVars;

    public Contract(String contractName){
        this.contractName = contractName;
        methods = new ArrayList<Method>();
        structs = new ArrayList<Struct>();
        enums = new ArrayList<myEnum>();
        stateVars = new ArrayList<StateVariable>();
    }

    public void addMethod(Method method){
        methods.add(method);
    }

    public void addStruct(Struct struct){
        structs.add(struct);
    }

    public void addEnum(myEnum enumm){
        enums.add(enumm);
    }

    public void addStateVariable(StateVariable var){
        stateVars.add(var);
    }

    public String writeContract() throws Exception {
        String res = "contract "+contractName + " {\n\n\n";
        for(Struct struct : structs) {
            res += struct.write()+"\n\n";
        }
        res += "\n";
        for(myEnum enumm : enums){
            res += enumm.write()+"\n";
        }
        res += "\n";
        for(StateVariable state : stateVars){
            res += state.write()+"\n";
        }
        for(Method method : methods){
            res += method.write() +"\n\n";
        }

        res += "}";

        return res;
    }

    public static void main(String[] args){
        Contract c = new Contract("newContract");
        c.addStateVariable(new StateAddress("owner",true, AccessModifier.PRIVATE));
        Method m = new Method("m1",new Variable[] {new ParameterInteger("count",false,256)}, Methods.AccessModifier.PUBLIC, Type.PURE,
                new Variable[0],new Step[] {new LocalBool("flag","true")});
        c.addMethod(m);
        try {
            System.out.println(c.writeContract());
        }
        catch(Exception e ){
            e.printStackTrace();
        }
    }

}
