package Steps;


import Contracts.TheFile;
import Lines.Line;

public class Require implements Step{
    Condition condition;
    String messageToThrow  = null;
    int javaLine;

    //require(bool statement,if false throw message)
    public Require(Condition condition, String messageToThrow){
        this.condition = condition;
        this.messageToThrow = messageToThrow;
    }

    //require(condition) if false throws error
    public Require(Condition condition){
        this.condition = condition;
    }

    @Override
    public String write() throws Exception {
        TheFile.lineMap.addLine(new Line(javaLine,"Step",TheFile.solidityCount,TheFile.solidityCount));
        TheFile.solidityCount++;
        String res = "";
        res += "require("+condition.write();
        res += messageToThrow != null ? ", "+messageToThrow+");" : ");";
        return res;
    }

    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }

    public static void main(String[] args) throws Exception {
    }
}
