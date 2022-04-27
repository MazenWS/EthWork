package Methods;

import Steps.Step;
import Variables.ParameterVariable;

import java.util.ArrayList;

public class Constructor {
    ParameterVariable[] parameters;
    ArrayList<Step> steps = new ArrayList<Step>();
    boolean payable;

    public Constructor(ParameterVariable[] parameters, boolean payable){
        this.parameters = parameters;
    }

    public void addStep(Step step){
        steps.add(step);
    }

    public String write() throws Exception {
        String res = "constructor( ";
        if(parameters.length >0 ) {
            for (ParameterVariable param : parameters) {
                res += param.write() + ", ";
            }
            res = res.substring(0,res.length()-2) ;
        }
        res += ")";
        res += payable? "payable {": "{\n";
        for(Step s : steps) {
            String str = s.write();
            if(str.charAt(str.length()-1) != ';'){
                str += ';';
            }
            res += str + "\n";
        }
        return res + "}";
    }
}
