package Methods;

import Steps.Step;
import Variables.Variable;

public class Method {
    String name;
    Variable[] parameters;
    AccessModifier accessModifier;
    Type accessType;
    Variable[] returnTypes;
    Step[] steps;


    public Method(String name, Variable[] parameters, AccessModifier accessModifier, Type accessType,
                  Variable[] returnTypes, Step[] steps){
        this.name= name;
        this.parameters= parameters;
        this.accessModifier= accessModifier;
        this.accessType= accessType;
        this.returnTypes= returnTypes;
        this.steps= steps;
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
        if(accessType.equals(Type.VIEW))
            res += " view";
        if(returnTypes != null && returnTypes.length != 0){
            res += " returns(";
            for (Variable ret : returnTypes) {
                res += ret.write() + ", ";
            }
            res = res.substring(0,res.length()-2)+")";
        }
        if(accessType.equals(Type.PAYABLE))
            res += " payable";
        res += "{\n";
        for(Step step : steps){
            res += step.write()+"\n";
        }
        res += "}";

        return res;
    }
}
