package Steps;

public class ForLoop extends Step{
    Condition[] conditions;
    LogicalOperator[] operators;
    String initialization;
    String iterationStatement;
    Step[] body;

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
    public String write() throws Exception {
        if(conditions.length != operators.length+1)
            throw new Exception("Conditions should be ONE more than Operators");
        String res = "for("+initialization+"; "+conditions[0].write();
        for(int i =1;i<conditions.length;i++){
            String op = operators[i-1].equals(LogicalOperator.OR)? " || " : " && ";
            res += op+conditions[i].write();
        }
        res += "; "+iterationStatement+") {\n";
        for(Step step : body)
            res += step.write()+"\n";
        res += "}";
        return res;
    }
}
