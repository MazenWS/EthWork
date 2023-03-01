package Steps;

import Contracts.TheFile;
import Lines.Line;

public class HashtableAssign implements Step{
    String hashtableName;
    String insertToken;
    String statement;
    int javaLine;

    //hashtable[key] = a+b
    public HashtableAssign(String hashtableName, String insertToken, String statement){
        this.hashtableName = hashtableName;
        this.insertToken = insertToken;
        this.statement = statement;
    }

    //hashtable[key] = sum(a,b)
    public HashtableAssign(String hashtableName, String insertToken, Function function){
        this.hashtableName = hashtableName;
        this.insertToken = insertToken;
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
        String res = hashtableName+"["+insertToken+"] = ";
        res += statement +";";
        return res;
    }
}
