package Methods;

import Contracts.TheFile;
import Lines.Line;
import Steps.Step;
import Variables.NamedVariable;
import Variables.Variable;

import java.util.ArrayList;

public class Modifier {
    String name;
    NamedVariable[] parameters;
    ArrayList<Step> steps = new ArrayList<>();
    int javaLine;

public Modifier (String name, NamedVariable[] parameters){
    this.name= name;
    this.parameters = parameters;
}
public void  addSteps(Step step){
    steps.add(step);
}
    public void setJavaLine(int javaLine){
        this.javaLine = javaLine;
    }
    public String write() throws Exception {
    String res= "modifier " + name+ "(";
    if (parameters!= null && parameters.length != 0){
        for (Variable param : parameters) {
            res += param.write() + ", ";
        }
        res = res.substring(0,res.length()-2);
    }
        res+="){ "+"\n";

        int solLine = TheFile.solidityCount;
        TheFile.lineMap.addLine(new Line(javaLine,"Modifier",solLine,solLine));
        TheFile.solidityCount++;

        for(Step step : steps)
            res += step.write()+"\n";
        res += "}";
        TheFile.solidityCount++;
        return res;

    }


}
