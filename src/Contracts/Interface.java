package Contracts;

import Methods.Method;
import Methods.Struct;
import Methods.myEnum;

import java.util.ArrayList;

//Interface can not have any function with implementation.
//Functions of an interface can be only of type external.
//Interface can not have constructor.
//Interface can not have state variables.
//Interface can have enum, structs which can be accessed using interface name dot notation.
public class Interface extends TypeContract{
    ArrayList<String> extendsContract;
    public Interface(String contractName){
        this.contractName = contractName;
        methods = new ArrayList<Method>();
        structs = new ArrayList<Struct>();
        enums = new ArrayList<myEnum>();
        extendsContract = new ArrayList<String>();
    }

    public void addAContractToExtend(String extendsContract){
        this.extendsContract.add(extendsContract);
    }

    @Override
    public String write() throws Exception {
        String res = "interface "+contractName ;

        if (!extendsContract.isEmpty()){
            res+= " is ";
            for (String names : extendsContract) {
                res += names+", ";
            }

            res = res.substring(0,res.length()-2) ;
        }
        TheFile.solidityCount++;
        return res + super.write()+"\n"+"}";
    }
}
