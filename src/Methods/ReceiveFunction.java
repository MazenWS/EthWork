package Methods;

import Contracts.TheFile;
import Lines.Line;
import Steps.Step;

import java.util.ArrayList;

public class ReceiveFunction {
    ArrayList<Step> steps;
    int javaLine;

    public ReceiveFunction() {
        steps = new ArrayList<Step>();
    }

    public void addStep(Step step){
        steps.add(step);
    }

    public void setJavaLine(int javaLine){
        this.javaLine = javaLine;
    }

    public String write() throws Exception {
        String res = "receive() external payable {";

        int solLine = TheFile.solidityCount;
        TheFile.lineMap.addLine(new Line(javaLine,"ReceiveFunction",solLine,solLine));
        TheFile.solidityCount++;

        if (! steps.isEmpty()) {
            res += "\n";
            for (Step s : steps) {
                String str = s.write();
                res += str + "\n";
            }
        }
        res += "}";
        TheFile.solidityCount++;
        return res;
    }
}
