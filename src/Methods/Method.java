package Methods;

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


    public Method(String name, ParameterVariable[] parameters, AccessModifier accessModifier, Type accessType,
                  ParameterVariable[] returnTypes ){
        this.name= name;
        this.parameters= parameters;
        this.accessModifier= accessModifier;
        this.accessType= accessType;
        this.returnTypes= returnTypes;

    }
     public  void addSteps( Step step){
        steps.add(step);

     }
    public String write() throws Exception {
        String res = "function "+name+"(";
        if(parameters != null && parameters.length != 0) {
            for (Variable param : parameters) {
                res += param.write() + ", ";
            }
            res = res.substring(0,res.length()-2)+")";
        }
        switch (accessModifier){
            case INTERNAL -> {res += " internal";break;}
            case PRIVATE -> {res += " private";break;}
            case PUBLIC -> {res += " public";break;}
            case EXTERNAL -> {res += " exteral";break;}
        }
        if(accessType.equals(Type.PURE))
            res += " pure";
        else if(accessType.equals(Type.VIEW))
            res += " view";
        else if(accessType.equals(Type.PAYABLE))
            res += " payable";
        if(returnTypes != null && returnTypes.length != 0){
            res += " returns(";
            for (Variable ret : returnTypes) {
                res += ret.write() + ", ";
            }
            res = res.substring(0,res.length()-2)+")";
        }
        res += "{\n";
        for(Step step : steps){
            String str = step.write();
            if(str.charAt(str.length()-1) != ';'){
                str += ';';
            }
            res += str+"\n";
        }
        res += "}";

        return res;
    }
}
