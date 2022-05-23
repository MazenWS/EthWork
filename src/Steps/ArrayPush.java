package Steps;

import Contracts.TheFile;
import Lines.Line;

public class ArrayPush implements Step {
    String arrName;
    String pushValue;
    int javaLine;

    public ArrayPush(String arrName, String pushValue){
        this.arrName = arrName;
        this.pushValue = pushValue;
    }


    @Override
    public String write() throws Exception {
        TheFile.lineMap.addLine(new Line(javaLine,"Step",TheFile.solidityCount,TheFile.solidityCount));
        TheFile.solidityCount++;
        return arrName+".push("+pushValue+");";
    }

    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }
}
