package Variables;

import Contracts.TheFile;
import Lines.Line;
import Steps.Step;

public class LocalStruct extends ParameterStruct implements Step {
   String[] initialValue;
   String initValue;
   int javaLine;

    public LocalStruct(String theStruct,String name, DataLocation  dataLocation, String[] initialValue)  {
        super(theStruct,name,dataLocation);
        this.name = name;
        this.initialValue= initialValue;
    }
    public LocalStruct(String theStruct,String name, DataLocation  dataLocation, String initValue)  {
        super(theStruct,name,dataLocation);
        this.initValue= initValue;
    }
    public LocalStruct(String theStruct,String name, DataLocation  dataLocation )  {
        super(theStruct,name,dataLocation);

    }


    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }

    public String write() {
        String res = super.write();
        if (initValue!= null){
            res+= " = "+initValue;
        }
        if(initialValue != null){
            res += theStruct+"(";
            for(String input : initialValue){
                res += input+", ";
            }
            res = res.substring(0,res.length()-2)+")";

        }
        TheFile.lineMap.addLine(new Line(javaLine,"Step",TheFile.solidityCount,TheFile.solidityCount));
        TheFile.solidityCount++;
        return res+";";
    }
}
