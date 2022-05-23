package Steps;

import Contracts.TheFile;
import Lines.Line;

public class Assign implements Step{
    String assignToVar;
    String statement;
    int javaLine;

    //assignToVar = statement
    public Assign(String assignToVar, String statement){
        this.assignToVar = assignToVar;
        this.statement = statement;
    }

    //assignToVar = sum(a,b)
    public Assign(String assignToVar, Function function){
        this.assignToVar = assignToVar;
        this.statement = function.write();
    }

    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }

    @Override
    public String write() throws Exception{
        TheFile.lineMap.addLine(new Line(javaLine,"Step",TheFile.solidityCount,TheFile.solidityCount));
        TheFile.solidityCount++;
        String res  = assignToVar + " = ";
        res += statement +";";
        return res;
    }

    public static void main(String[] args) throws Exception {
        Step p = new Assign("assign","a+b");
        System.out.println(p.write());
    }
}
