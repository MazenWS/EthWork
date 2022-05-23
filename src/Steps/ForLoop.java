package Steps;

import Contracts.TheFile;
import Lines.Line;

public class ForLoop implements Step{
    Condition[] conditions;
    LogicalOperator[] operators;
    String initialization;
    String iterationStatement;
    Step[] body;
    int javaLine;

    public ForLoop(Condition condition,String initialization, String iterationStatement, Step[] body){
        conditions = new Condition[] {condition};
        operators = new LogicalOperator[0];
        this.initialization = initialization;
        this.iterationStatement = iterationStatement;
        this.body = body;
    }

    public ForLoop(Condition[] conditions, LogicalOperator[] operators,String initialization, String iterationStatement, Step[] body){
        this.conditions = conditions;
        this.operators = operators;
        this.initialization = initialization;
        this.iterationStatement = iterationStatement;
        this.body = body;
    }


    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }

    @Override
    public String write() throws Exception {
        int start = TheFile.solidityCount++;
        if(conditions.length != operators.length+1)
            throw new Exception("Conditions should be ONE more than Operators");
        String res = "for("+initialization+"; "+conditions[0].write();
        for(int i =1;i<conditions.length;i++){
            String op = operators[i-1].equals(LogicalOperator.OR)? " || " : " && ";
            res += op+conditions[i].write();
        }
        res += "; "+iterationStatement+") {\n";
        for(Step step : body) {
            step.setJavaLine(javaLine);
            res += step.write() + "\n";
        }
        res += "}";
        TheFile.lineMap.addLine(new Line(javaLine,"Step",start,TheFile.solidityCount++));
        return res;
    }
}
