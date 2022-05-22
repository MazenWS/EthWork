package Contracts;

import Lines.LinesArrangment;
import Methods.*;
import Steps.*;
import Variables.*;
import Variables.AccessModifier;
import Setup.Setup;

import java.util.ArrayList;

public class TheFile {
    String fileName;
    ArrayList<TypeContract> contracts;
    public static LinesArrangment lineMap;
    public static int solidityCount;


    public TheFile(String fileName){
        this.fileName= fileName;
        contracts = new ArrayList<>();
    }


    public void addContract(TypeContract contract){
        contracts.add(contract);
    }


    public String writeFile() throws Exception {
        String res = "";
        if(! contracts.isEmpty()) {
            for (TypeContract contract : contracts) {
                res += contract.write() + "\n\n";
            }

        }
        return res;
            }

    public void createContract(){
        try {
            Setup.createContract(fileName, writeFile());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        TheFile f= new TheFile("TwoStepOwnable");
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
          f.addContract(c);

           System.out.println(f.writeFile());
            //c.createContract();

            }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
