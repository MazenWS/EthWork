package Steps;

import Contracts.TheFile;
import Lines.Line;

public class WhileLoop implements Step {
    Condition[] conditions;
    LogicalOperator[] operators;
    Step[] body;
    int javaLine;

    public WhileLoop(Condition condition, Step[] body){
        conditions = new Condition[] {condition};
        operators = new LogicalOperator[0];
        this.body = body;
    }

    public WhileLoop(Condition[] conditions, LogicalOperator[] operators, Step[] body){
        this.conditions = conditions;
        this.operators = operators;
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
        String res = "while("+conditions[0].write();
        for(int i =1;i<conditions.length;i++){
            String op = operators[i-1].equals(LogicalOperator.OR)? " || " : " && ";
            res += op+conditions[i].write();
        }
        res += ") {\n";
        for(Step step : body) {
            step.setJavaLine(javaLine);
            res += step.write() + "\n";
        }
        res += "}";
        TheFile.lineMap.addLine(new Line(javaLine,"Step",start,TheFile.solidityCount++));
        return res;
    }
}
