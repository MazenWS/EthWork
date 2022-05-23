package Variables;

import Contracts.TheFile;
import Lines.Line;
import Steps.Step;

public class LocalEnum extends ParameterEnum implements Step {
String initialValue;
int javaLine;

    public LocalEnum( String theEnum,String name, String initialValue) throws Exception {
        super(theEnum,name);
        this.initialValue = initialValue;
    }
    public LocalEnum( String theEnum,String name) throws Exception {
        super(theEnum,name);

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
