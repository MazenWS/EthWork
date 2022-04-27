package Methods;

import Steps.Step;
import Variables.ParameterVariable;
import Variables.Variable;

import java.util.ArrayList;

public class Constructor {
    ParameterVariable[] parameters;
    ArrayList<Step> steps;
    boolean payable;
    AccessModifier accessModifier;

    public Constructor(ParameterVariable[] parameters, boolean payable, AccessModifier accessModifier){
        this.payable= payable;
        this.parameters = parameters;
        this.accessModifier= accessModifier;
    }

    public void addStep(Step step){
        steps.add(step);
    }

    public String write() throws Exception {

        String res = "constructor ";
        res+="(" ;
        if(parameters != null && parameters.length != 0) {
            for (Variable param : parameters) {
                res += param.write() + ", ";
            }
            res = res.substring(0,res.length()-2)+") ";
        }
        res+=") ";
        res+= accessModifier.name().toLowerCase() +" ";
        res += payable? "payable {": "{\n";
        for(Step s : steps) {
            String str = s.write();
            res += str + "\n";
        }
        return res + "}";
    }
}
