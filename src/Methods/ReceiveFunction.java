package Methods;

import Steps.Step;

import java.util.ArrayList;

public class ReceiveFunction {
    ArrayList<Step> steps;

    public ReceiveFunction() {
        steps = new ArrayList<Step>();
    }

    public void addStep(Step step){
        steps.add(step);
    }

    public String write() throws Exception {
        String res = "receive() external payable {";
        if (! steps.isEmpty()) {
            res += "\n";
            for (Step s : steps) {
                String str = s.write();
                if(str.charAt(str.length()-1) != ';'){
                    str += ';';
                }
                res += str + "\n";
            }
        }
        res += "}";
        return res;
    }
}
