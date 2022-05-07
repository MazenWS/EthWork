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
    ArrayList<Modifier> modifiers;
    Constructor constructor;
    ReceiveFunction receive;

    public Contract(String contractName){
        this.contractName = contractName;
        methods = new ArrayList<Method>();
        structs = new ArrayList<Struct>();
        enums = new ArrayList<myEnum>();
        stateVars = new ArrayList<StateVariable>();
        events = new ArrayList<Event>();
        modifiers= new ArrayList<Modifier>();
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
    public void addModifier( Modifier modifier){
        modifiers.add(modifier);
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

        if(! modifiers.isEmpty()) {
            for(Modifier mod : modifiers) {
                res += mod.write() + "\n\n";
            }
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
        Contract c = new Contract("TwoStepOwnable");

        try {

            //address
            c.addStateVariable(new StateAddress("_owner", false, AccessModifier.PRIVATE));
            c.addStateVariable(new StateAddress("_newPotentialOwner", false, AccessModifier.PRIVATE));
            c.addEvent(new Event("OwnershipTransferred",new EventVariable[]{new EventAddress("previousOwner",true,false), new EventAddress("newOwner",true, false)}));
            c. addEvent(new Event("TransferInitiated", new EventVariable[]{new EventAddress("newOwner", true, false)}));
            c. addEvent(new Event("TransferCancelled", new EventVariable[]{new EventAddress("newPotentialOwner", true, false)}));
            Constructor newConstructor = new Constructor(null,false,Methods.AccessModifier.INTERNAL);
            c.addConstructor(newConstructor);
            newConstructor.addStep(new Assign("_owner" , "tx.origin"));
            newConstructor.addStep(new FireEvent("OwnershipTransferred", new String[]{"address(0)", "_owner"}));
            Method first = new Method("getOwner",null, Methods.AccessModifier.EXTERNAL, Type.VIEW,null,new ParameterVariable[]{new ParameterAddress("add1", false)});
            first.addSteps(new Return("_owner"));
            c.addMethod(first);
            Method second = new Method("getNewPotentialOwner",null, Methods.AccessModifier.EXTERNAL, Type.VIEW,null,new ParameterVariable[]{new ParameterAddress("add2", false)});
            second.addSteps(new Return("_newPotentialOwner"));
            c.addMethod(second);
            Modifier modifier = new Modifier("onlyOwner",null);
            modifier.addSteps(new Require(new Condition("isOwner()",RelationalOperator.EQUAL),"\"TwoStepOwnable: caller is not the owner.\""));
            modifier.addSteps(new CallAfter());
            c.addModifier(modifier);
           Method third= new Method("isOwner", null, Methods.AccessModifier.PUBLIC,Type.VIEW,null,new ParameterVariable[]{new ParameterBool("b1")});
           third.addSteps(new Return("msg.sender == _owner"));
           c.addMethod(third);
           Method fourth= new Method("transferOwnership",  new ParameterVariable[]{new ParameterAddress("newPotentialOwner", false)}, Methods.AccessModifier.PUBLIC,Type.NONE,new String[]{"onlyOwner"},null);
           fourth.addSteps(new Require(new Condition("newPotentialOwner","address(0)","address",RelationalOperator.NOT_EQUAL),"\"TwoStepOwnable: new potential owner is the zero address.\""));
           fourth.addSteps(new Assign("_newPotentialOwner","newPotentialOwner"));
           fourth.addSteps(new FireEvent("TransferInitiated", new String[]{"address(newPotentialOwner)"}));
           c.addMethod(fourth);
           Method fifth = new Method("cancelOwnershipTransfer",null, Methods.AccessModifier.PUBLIC,Type.NONE,new String[]{"onlyOwner"},null);
           fifth.addSteps(new FireEvent("TransferCancelled",new String[]{"address(_newPotentialOwner)"}));
           fifth.addSteps(new Delete("_newPotentialOwner"));
           c.addMethod(fifth);
           Method sixth = new Method("acceptOwnership",null, Methods.AccessModifier.PUBLIC,Type.NONE,null,null);
           sixth.addSteps(new Require(new Condition( Environment.MSG_SENDER,"_newPotentialOwner","address",RelationalOperator.EQUAL),"\"TwoStepOwnable: current owner must set caller as new potential owner.\""));
          sixth.addSteps(new Delete("_newPotentialOwner"));
          sixth.addSteps(new FireEvent("OwnershipTransferred",new String[]{"_owner",Environment.MSG_SENDER}));
          sixth.addSteps(new Assign("_owner",Environment.MSG_SENDER));
          c.addMethod(sixth);
          

           System.out.println(c.writeContract());
            //c.createContract();

            }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
