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
    if (!methods.isEmpty()) {
        for (Method method : methods) {
            res += method.write() + "\n\n";
        }
    }

return res;
}
}
