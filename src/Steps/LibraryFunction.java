package Steps;

import Contracts.TheFile;
import Lines.Line;

public class LibraryFunction implements Step{
    String library;
    String functionName;
    String[] inputs;
    int javaLine;


    public LibraryFunction(String library, String functionName, String[] inputs){
        this.library = library;
        this.functionName = functionName;
        this.inputs = inputs;
    }

    @Override
    public String write() throws Exception {
        TheFile.lineMap.addLine(new Line(javaLine,"Step",TheFile.solidityCount,TheFile.solidityCount));
        TheFile.solidityCount++;
        String res = library+"."+functionName+"(";
        if(inputs!=null && inputs.length>0){
            res += inputs[0];
            for(int i =1;i<inputs.length;i++){
                res += ", "+inputs[i];
            }
        }
        return res + ");";
    }

    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }
}
