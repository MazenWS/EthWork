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
    public Interface(String contractName){
        this.contractName = contractName;
        methods = new ArrayList<Method>();
        structs = new ArrayList<Struct>();
        enums = new ArrayList<myEnum>();
    }
    @Override
    public String write() throws Exception {
        return super.write()+"\n"+"}";
    }
}
