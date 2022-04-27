import Methods.*;
import Steps.*;
import Variables.*;
import Variables.AccessModifier;

import java.util.ArrayList;

public class Contract {

    String contractName;
    ArrayList<Method> methods;
    ArrayList<Struct> structs;
    ArrayList<myEnum> enums;
    ArrayList<StateVariable> stateVars;
    ArrayList<Event> events;
    Constructor constructor;
    ReceiveFunction receive;

    public Contract(String contractName){
        this.contractName = contractName;
        methods = new ArrayList<Method>();
        structs = new ArrayList<Struct>();
        enums = new ArrayList<myEnum>();
        stateVars = new ArrayList<StateVariable>();
        events = new ArrayList<Event>();
    }

    public void addMethod(Method method){
        methods.add(method);
    }

    public void addConstructor(Constructor constructor) throws Exception {

        if(this.constructor != null){
            throw new Exception("A Constructor is Already Added");
        }
        this.constructor = constructor;
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

    public void addEvent(Event event){
        events.add(event);
    }

    public void addReceiveFunction(ReceiveFunction receive) throws Exception {
        if(this.receive != null){
            throw new Exception("A receive Function is already Added");
        }
        this.receive = receive;
    }

    public String writeContract() throws Exception {
        String res = "contract "+contractName + " {\n\n\n";
        if(! structs.isEmpty()) {
            for (Struct struct : structs) {
                res += struct.write() + "\n\n";
            }
            res += "\n";
        }

        if(! enums.isEmpty()) {
            for (myEnum enumm : enums) {
                res += enumm.write() + "\n";
            }
            res += "\n";
        }

        if(!stateVars.isEmpty()) {
            for (StateVariable state : stateVars) {
                res += state.write() + "\n";
            }
            res += "\n";
        }

        if(! events.isEmpty()) {
            for(Event event : events){
                res += event.write() + "\n";
            }
            res += "\n";
        }

        if(constructor != null){
            res += constructor.write()+ "\n\n";
        }

        if (!methods.isEmpty()) {
            for (Method method : methods) {
                res += method.write() + "\n\n";
            }
        }

        if(receive != null){
            res += receive.write()+"\n";
        }
        res += "}";

        return res;
    }

    public void createContract(){
        try {
            Setup.createContract(contractName, writeContract());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Contract c = new Contract("newContract");

        try {


                //System.out.println(c.writeContract());
            c.createContract();

            }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
