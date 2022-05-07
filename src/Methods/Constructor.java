package Methods;

import Steps.Step;
import Variables.ParameterVariable;
import Variables.Variable;

import java.util.ArrayList;

public class Constructor {
    ParameterVariable[] parameters;
    ArrayList<Step> steps = new ArrayList<Step>();
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
        String res = "constructor( ";
        if(parameters!= null &&parameters.length >0 ) {
            for (ParameterVariable param : parameters) {
                res += param.write() + ", ";
            }
            res = res.substring(0,res.length()-2) ;
        }
        res += ")";
        res += payable? "payable {": "{\n";
        for(Step s : steps) {
            String str = s.write();
            res += str + "\n";
        }
        return res + "}";
    }
}
