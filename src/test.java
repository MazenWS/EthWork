import Contracts.Contract;
import Contracts.TheFile;
import Lines.LineCounter;
import Methods.*;
import Steps.*;
import Variables.*;
import Variables.AccessModifier;

public class test {




    public static void main(String[] args) {
        TheFile f= new TheFile("TwoStepOwnable");
        Contract c = new Contract("TwoStepOwnable");

        try {

            //address
            LineCounter.track(21);c.addStateVariable(new StateAddress("_owner", false, AccessModifier.PRIVATE));
            LineCounter.track(22);c.addStateVariable(new StateAddress("_newPotentialOwner", false, AccessModifier.PRIVATE));
            LineCounter.track(23);c.addEvent(new Event("OwnershipTransferred",new EventVariable[]{new EventAddress("previousOwner",true,false), new EventAddress("newOwner",true, false)}));
            LineCounter.track(24);c. addEvent(new Event("TransferInitiated", new EventVariable[]{new EventAddress("newOwner", true, false)}));
            LineCounter.track(25);c. addEvent(new Event("TransferCancelled", new EventVariable[]{new EventAddress("newPotentialOwner", true, false)}));
            Constructor newConstructor = new Constructor(null,false,Methods.AccessModifier.PUBLIC);
            LineCounter.track(27);c.addConstructor(newConstructor);
            LineCounter.track(28);newConstructor.addStep(new Assign("_owner" , "tx.origin"));
            LineCounter.track(29);newConstructor.addStep(new FireEvent("OwnershipTransferred", new String[]{"address(0)", "_owner"}));
            Method first = new Method("getOwner",null, Methods.AccessModifier.EXTERNAL, Type.VIEW,null,new ParameterVariable[]{new ParameterAddress("add1", false)});
            LineCounter.track(31);first.addSteps(new Return("_owner"));
            LineCounter.track(32);c.addMethod(first);


            Method second = new Method("getNewPotentialOwner",null, Methods.AccessModifier.EXTERNAL, Type.VIEW,null,new ParameterVariable[]{new ParameterAddress("add2", false)});
            LineCounter.track(36);second.addSteps(new Return("_newPotentialOwner"));
            LineCounter.track(37);c.addMethod(second);
            Modifier modifier = new Modifier("onlyOwner",null);
            LineCounter.track(39);modifier.addSteps(new Require(new Condition("isOwner()",RelationalOperator.EQUAL),"\"TwoStepOwnable: caller is not the owner.\""));
            LineCounter.track(40);modifier.addSteps(new CallAfter());
            LineCounter.track(41);c.addModifier(modifier);

            Method third= new Method("isOwner", null, Methods.AccessModifier.PUBLIC,Type.VIEW,null,new ParameterVariable[]{new ParameterBool("b1")});
            LineCounter.track(44);third.addSteps(new Return("msg.sender == _owner"));
            LineCounter.track(45);c.addMethod(third);

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

            //System.out.println(f.writeFile());
            f.createContract();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
