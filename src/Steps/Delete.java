package Steps;

import Contracts.TheFile;
import Lines.Line;

public class Delete  implements Step{
    String statement;
    int javaLine;
    public Delete(String statement){
        this.statement= statement;
    }

    @Override
    public String write() throws Exception {
        TheFile.lineMap.addLine(new Line(javaLine,"Step",TheFile.solidityCount,TheFile.solidityCount));
        TheFile.solidityCount++;
        return "delete "+statement+";";
    }

    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }
}
