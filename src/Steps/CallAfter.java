package Steps;

import Contracts.TheFile;
import Lines.Line;

public class CallAfter implements Step {
    int javaLine;
    @Override
    public String write() throws Exception {
        TheFile.lineMap.addLine(new Line(javaLine,"Step",TheFile.solidityCount,TheFile.solidityCount));
        TheFile.solidityCount++;
        return "_;" ;
    }

    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }
}
