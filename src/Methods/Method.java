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
    String modifier;


    public Method(String name, ParameterVariable[] parameters, AccessModifier accessModifier, Type accessType,
               String modifier  , ParameterVariable[] returnTypes ){
        this.name= name;
        this.parameters= parameters;
        this.accessModifier= accessModifier;
        this.accessType= accessType;
        this.returnTypes= returnTypes;
        this.modifier= modifier;

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
            res += " payable ";
        res+= modifier!=null ? modifier+" ":"";
        if(returnTypes != null && returnTypes.length != 0){
            res += "returns(";
            for (Variable ret : returnTypes) {
                res += ret.write() + ", ";
            }
            res = res.substring(0,res.length()-2)+")";
        }
        res += "{\n";
        for(Step step : steps){
            String str = step.write();
            res += str+"\n";
        }
        res += "}";

        return res;
    }
}
