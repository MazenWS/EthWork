package Variables;

import Contracts.TheFile;
import Lines.Line;
import Steps.Step;

import java.util.ArrayList;

public class LocalArray extends ParameterArray implements Step {
    ArrayList<String> initialValue;
    int javaLine;


    public LocalArray(String name,Variable  variable, DataLocation dataLocation, int size, ArrayList<String>  initialValue){
        super(name, variable,dataLocation, size);
        this.initialValue= initialValue;
    }

    public LocalArray( String name, Variable  variable, DataLocation dataLocation,ArrayList<String>  initialValue){
        super( name,variable, dataLocation );
        this.initialValue= initialValue;

    }
    public LocalArray(String name,Variable  variable, DataLocation dataLocation, int size ){
        super(name, variable,dataLocation, size);

    }

    public LocalArray( String name, Variable  variable, DataLocation dataLocation ){
        super( name,variable, dataLocation );


    }


    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }

    @Override
    public String write(){
        String res = super.write();
        if(initialValue != null){
            if(size > 0) {
                res += "[";
                for (String val : initialValue){
                    res += val+", ";
                }
                res += res.substring(0,res.length()-2) +"]";
            }
        }
        res+=";";
        TheFile.lineMap.addLine(new Line(javaLine,"Step",TheFile.solidityCount,TheFile.solidityCount));
        TheFile.solidityCount++;
        return res;
    }

    public static void main(String[] args){

    }

}
