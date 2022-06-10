package Contracts;

import Lines.LineCounter;
import Methods.*;

import java.util.ArrayList;

public  abstract class TypeContract {
    String contractName;
    ArrayList<Method> methods;
    ArrayList<Struct> structs;
    ArrayList<myEnum> enums ;

    public void addMethod(Method method){
        methods.add(method);
        method.setJavaLine(LineCounter.getLine());
    }

    public void addStruct(Struct struct){
        structs.add(struct);
        struct.setJavaLine(LineCounter.getLine());
    }
    public void addEnum(myEnum enumm){
        enums.add(enumm);
        enumm.setJavaLine(LineCounter.getLine());
    }

public  String write() throws Exception{

    String res =  " {\n\n\n";
    TheFile.solidityCount+=4;
    if(! structs.isEmpty()) {
        for (Struct struct : structs) {
            res += struct.write() + "\n\n";
            TheFile.solidityCount++;
        }
        res += "\n";
        TheFile.solidityCount++;
    }

    if(! enums.isEmpty()) {
        for (myEnum enumm : enums) {
            res += enumm.write() + "\n";
        }
        res += "\n";
        TheFile.solidityCount++;
    }
    if (!methods.isEmpty()) {
        for (Method method : methods) {
            res += method.write() + "\n\n";
            TheFile.solidityCount++;
        }
    }

    return res;
    }
}
