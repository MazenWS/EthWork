package Methods;

import Steps.Step;
import Variables.NamedVariable;
import Variables.Variable;

import java.util.ArrayList;

public class Modifier {
    String name;
    NamedVariable[] parameters;
    ArrayList<Step> steps = new ArrayList<>();

public Modifier (String name, NamedVariable[] parameters){
    this.name= name;
    this.parameters = parameters;
}
public void  addSteps(Step step){
    steps.add(step);

}
    public String write() throws Exception {
    String res= "modifier " + name+ "(";
    if (parameters!= null && parameters.length != 0){
        for (Variable param : parameters) {
            res += param.write() + ", ";
        }
        res = res.substring(0,res.length()-2)+"){ " +"\n";
    }
        res+="){ "+"\n";

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
