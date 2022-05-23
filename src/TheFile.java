import Contracts.Contract;
import Contracts.TypeContract;
import Methods.*;
import Steps.*;
import Variables.*;
import Variables.AccessModifier;

import java.util.ArrayList;

public class TheFile {
    String fileName;
    ArrayList<TypeContract> contracts;


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
        TheFile f= new TheFile("GovernorBravoInterfaces");
        Contract c = new Contract("GovernorBravoEvents");

        try {
            c.addEvent(new Event("ProposalCreated",new EventVariable[]{new EventInteger("id",false,7,false),new EventAddress("proposer",false,false), new EventArray("targets",false,new VariableAddress(false)),new EventArray("values",false,new VariableInteger(false,7)),new EventArray("signatures",false,new VariableString()),new EventArray("calldatas",false,new VariableBytes()),
        new EventInteger("startBlock",false,7,false),
        new EventInteger ("startBlock", false,7,false),
        new EventString("description",false)}));
            c.addEvent(new Event("VoteCast",new EventVariable[]{new EventAddress("voter",true,false),
            new EventInteger("proposalId",false,7,false),
            new EventInteger("support",false,3,false),
            new EventInteger("votes",false,7,false),
            new EventString("reason",false)}));
          c.addEvent(new Event("proposalCanceled",new EventVariable[]{
                  new EventInteger("id",false ,7,false)
          }));
          c.addEvent(new Event("ProposalQueued", new EventVariable[]{
                  new EventInteger("id",false,7,false),
                  new EventInteger("eta",false,7,false)
          }));

            c.addEvent(new Event("ProposalExecuted", new EventVariable[]{
                    new EventInteger("id",false,7,false)}));
            c.addEvent(new Event("VotingDelaySet", new EventVariable[]{
                    new EventInteger("oldVotingDelay",false,7,false),
                    new EventInteger("newVotingDelay",false,7,false)
            }));

            c.addEvent(new Event("VotingPeriodSet", new EventVariable[]{
                    new EventInteger("oldVotingPeriod",false,7,false),
                    new EventInteger("newVotingPeriod",false,7,false)
            }));
            c.addEvent(new Event("NewImplementation", new EventVariable[]{
                    new EventAddress("oldImplementation",false,false),
                    new EventAddress("newImplementation",false,false)
            }));

            c.addEvent(new Event("ProposalThresholdSet", new EventVariable[]{
                    new EventInteger("oldProposalThreshold",false,7,false),
                    new EventInteger("newProposalThreshold",false,7,false)
            }));

            c.addEvent(new Event("NewPendingAdmin", new EventVariable[]{
                    new EventAddress("oldPendingAdmin",false,false),
                    new EventAddress("newPendingAdmin",false,false)
            }));

            c.addEvent(new Event("NewAdmin", new EventVariable[]{
                    new EventAddress("oldAdmin",false,false),
                    new EventAddress("newAdmin",false,false)
            }));
             f.addContract(c);
            Contract c1 = new Contract("GovernorBravoDelegatorStorage");
            c1.addStateVariable(new StateAddress("admin",false,AccessModifier.PUBLIC));
            c1.addStateVariable(new StateAddress("implementation",false,AccessModifier.PUBLIC));
            c1.addStateVariable(new StateAddress("pendingAdmin",false,AccessModifier.PUBLIC));

            f.addContract(c1);
            Contract c2 = new Contract("GovernorBravoDelegateStorageV1");
            c2.addAContractToExtend("GovernorBravoDelegatorStorage");
            c2.addStateVariable(new StateInteger("votingDelay",false, 7 ,AccessModifier.PUBLIC));
            c2.addStateVariable(new StateInteger("votingPeriod",false,7,AccessModifier.PUBLIC));
            c2.addStateVariable(new StateInteger("proposalThreshold",false,7,AccessModifier.PUBLIC));
            c2.addStateVariable(new StateInteger("initialProposalId",false,7,AccessModifier.PUBLIC));
            c2.addStateVariable(new StateInteger("proposalCount",false,7,AccessModifier.PUBLIC));



            f.createContract();

            }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
