package Methods;

import Steps.Step;
import Variables.ParameterVariable;

import java.util.ArrayList;

public class Constructor {
    ParameterVariable[] parameters;
    ArrayList<Step> steps;
    boolean payable;

    public Constructor(ParameterVariable[] parameters, boolean payable){
        this.parameters = parameters;
    }

    public void addStep(Step step){
        steps.add(step);
    }

    public String write() throws Exception {
        String res = "constructor ";
        res += payable? "payable {": "{\n";
        for(Step s : steps) {
            res += s.write() + "\n";
        }
        return res + "}";
    }
}
