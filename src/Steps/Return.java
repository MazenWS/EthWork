package Steps;

import Contracts.TheFile;
import Lines.Line;

public class Return implements Step{
    String statement;
    int javaLine;


    public Return(String statement){
        this.statement= statement;
    }


    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }

    @Override
    public String write() throws Exception {
        TheFile.lineMap.addLine(new Line(javaLine,"Step",TheFile.solidityCount,TheFile.solidityCount));
        TheFile.solidityCount++;
        String res = "return";
        if(statement != null)
            res +=" "+ statement +";";
        return res;
    }
}
