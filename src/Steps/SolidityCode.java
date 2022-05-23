package Steps;

import Contracts.TheFile;
import Lines.Line;

public class SolidityCode implements Step{

    String code;
    int javaLine;

    public SolidityCode(String code){
        this.code = code;
    }


    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }

    @Override
    public String write() throws Exception {
        String[] lines = code.split("\n");
        TheFile.lineMap.addLine(new Line(javaLine,"Step",TheFile.solidityCount,TheFile.solidityCount+=lines.length));
        return code;
    }
}
