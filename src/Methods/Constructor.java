package Methods;

import Contracts.TheFile;
import Lines.Line;
import Steps.Step;
import Variables.ParameterVariable;
import Variables.Variable;

import java.util.ArrayList;
import java.util.Locale;

public class Constructor {
    ParameterVariable[] parameters;
    ArrayList<Step> steps = new ArrayList<Step>();
    boolean payable;
    AccessModifier accessModifier;
    int javaLine;

    public Constructor(ParameterVariable[] parameters, boolean payable, AccessModifier accessModifier){
        this.payable= payable;
        this.parameters = parameters;
        this.accessModifier= accessModifier;
    }

    public void addStep(Step step){
        steps.add(step);
    }

    public void setJavaLine(int javaLine){
        this.javaLine = javaLine;
    }

    public String write() throws Exception {
        String res = "constructor( ";
        if(parameters!= null &&parameters.length >0 ) {
            for (ParameterVariable param : parameters) {
                res += param.write() + ", ";
            }
            res = res.substring(0,res.length()-2) ;
        }
        res += ")";
        res += payable? "payable ":" ";
        if(! accessModifier.name().toLowerCase().equals("public"))
            res+=accessModifier.name().toLowerCase()+" ";
        res+="{"+"\n";

        int solLine = TheFile.solidityCount;
        TheFile.lineMap.addLine(new Line(javaLine,"Constructor",solLine,solLine));
        TheFile.solidityCount++;

        for(Step s : steps) {
            String str = s.write();
            res += str + "\n";
        }
        TheFile.solidityCount++;
        return res + "}";
    }
}
