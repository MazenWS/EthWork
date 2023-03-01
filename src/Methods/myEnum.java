package Methods;

import Contracts.TheFile;
import Lines.Line;

import java.util.ArrayList;

public class myEnum {
    String name;
   String[] members;
   int javaLine;
   public static ArrayList<String> createdEnums = new ArrayList<String>();


    public myEnum (String name,String[] members ){
        this.name= name;
        this.members=members;
        createdEnums.add(name);
    }

    public void setJavaLine(int javaLine){
        this.javaLine = javaLine;
    }

    public String write() {
        String res = "enum "+name+"{ ";
        for(String member : members){
            res += member +", ";
        }
        res = res.substring(0,res.length()-2)+" }";

        int solLine = TheFile.solidityCount;
        TheFile.lineMap.addLine(new Line(javaLine,"Enum",solLine,solLine));
        TheFile.solidityCount++;

        return res;
    }

}
