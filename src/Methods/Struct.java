package Methods;

import Contracts.TheFile;
import Lines.Line;
import Variables.NamedVariable;

import java.util.ArrayList;


public class Struct {
    String name;
    NamedVariable[] vars;
    int javaLine;
    public static ArrayList<String> structNames= new ArrayList<>();

    public Struct(String name, NamedVariable[] vars) {
        this.name=name;
        this.vars= vars;
        structNames.add(name);
    }


    public void setJavaLine(int javaLine){
        this.javaLine = javaLine;
    }

    public String write() {
        int count =1;
        String res = "struct "+name+" {\n";
        for(NamedVariable var : vars ){
            count ++;
            res += var.write()+";\n";
        }
        res += "}";

        TheFile.lineMap.addLine(new Line(javaLine,"Struct",TheFile.solidityCount++,TheFile.solidityCount+=count+1));

        return res;
    }
        }



