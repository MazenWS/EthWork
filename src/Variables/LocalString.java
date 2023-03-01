package Variables;

import Contracts.TheFile;
import Lines.Line;
import Steps.Step;

//for local variable let it return parameterVar---> the uinitialised version of each
public class LocalString extends ParameterString implements Step {
    String initialValue;
    int javaLine;


    public LocalString(String name,DataLocation dataLocation, String initialValue ){
        super(name,dataLocation);
        this. initialValue= initialValue;
    }
    public LocalString(String name,DataLocation dataLocation){
        super(name,dataLocation);
    }


    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }

    @Override
    public String write(){
        String res = super.write();
        if(initialValue != null)
            res += " = " + initialValue;
        res+=";";
        TheFile.lineMap.addLine(new Line(javaLine,"Step",TheFile.solidityCount,TheFile.solidityCount));
        TheFile.solidityCount++;
        return res;
    }
}
