package Methods;

import Contracts.TheFile;
import Lines.Line;
import Lines.LineCounter;
import Steps.Step;
import Variables.ParameterVariable;
import Variables.Variable;

import java.util.ArrayList;

public class Method {
    String name;
    ParameterVariable[] parameters;
    AccessModifier accessModifier;
    Type accessType;
    ParameterVariable[] returnTypes;
    ArrayList<Step> steps = new ArrayList<>();
    String[] modifiers;
    boolean abstractMethod;
    int javaLine;


    public Method(String name, ParameterVariable[] parameters, AccessModifier accessModifier, Type accessType,
               String[] modifiers  , ParameterVariable[] returnTypes ){
        this.name= name;
        this.parameters= parameters;
        this.accessModifier= accessModifier;
        this.accessType= accessType;
        this.returnTypes= returnTypes;
        this.modifiers= modifiers;

    }
     public  void addSteps( Step step){
        steps.add(step);
        step.setJavaLine(LineCounter.getLine());
     }

     public void setJavaLine(int javaLine){
        this.javaLine = javaLine;
     }

     public void setAbstractMethod(){
        abstractMethod = true;
     }

    public String write() throws Exception {
        String res = "function "+name+"(";
        if(parameters != null && parameters.length != 0) {
            for (Variable param : parameters) {
                res += param.write() + ", ";
            }
            res = res.substring(0,res.length()-2);
        }
        res += ") ";
        res += accessModifier.name().toLowerCase()+" ";

        if(accessType.equals(Type.PURE))
            res += "pure ";
        else if(accessType.equals(Type.VIEW))
            res += "view ";
        else if(accessType.equals(Type.PAYABLE))
            res += "payable ";
        if( modifiers != null && modifiers.length != 0 ){
            for(String mod : modifiers) {
                res += mod + ", ";
            }
            res = res.substring(0,res.length()-2);
        }
        if(returnTypes != null && returnTypes.length != 0){
            res += " returns(";
            for (Variable ret : returnTypes) {
                res += ret.write() + ", ";
            }
            res = res.substring(0,res.length()-2)+")";
        }
        if(abstractMethod){
            res += ";";
            int solLine = TheFile.solidityCount;
            TheFile.lineMap.addLine(new Line(javaLine,"Method",solLine,solLine));
            TheFile.solidityCount++;
            return res;
        }
        res += "{\n";

        int solLine = TheFile.solidityCount;
        TheFile.lineMap.addLine(new Line(javaLine,"Method",solLine,solLine));
        TheFile.solidityCount++;

        for(Step step : steps)
            res += step.write()+"\n";
        res += "}";
        TheFile.solidityCount++;

        return res;
    }
}
