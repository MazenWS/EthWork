package Variables;

import Contracts.TheFile;
import Lines.Line;
import Steps.Step;

public class LocalAddress extends ParameterAddress implements Step, ParameterVariable {
    public String initialValue;
    int javaLine;

    public LocalAddress (String name, boolean payable, String initialValue){
        super(name, payable);
        this. initialValue= initialValue;

    }
    public LocalAddress (String name, boolean payable){
        super(name, payable);


    }

    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }

    @Override
    public String write(){
        String res = super.write();
        if(initialValue != null) {
            res += " = " + initialValue;
        }
        res+=";";
        TheFile.lineMap.addLine(new Line(javaLine,"Step",TheFile.solidityCount,TheFile.solidityCount));
        TheFile.solidityCount++;
        return res;
    }


}


