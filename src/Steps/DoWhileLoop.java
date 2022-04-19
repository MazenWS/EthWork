package Steps;

public class DoWhileLoop extends Step{
    Condition[] conditions;
    LogicalOperator[] operators;
    Step[] body;

    public DoWhileLoop(Condition condition, Step[] body){
        conditions = new Condition[] {condition};
        operators = new LogicalOperator[0];
        this.body = body;
    }

    public DoWhileLoop(Condition[] conditions, LogicalOperator[] operators, Step[] body){
        this.conditions = conditions;
        this.operators = operators;
        this.body = body;
    }


    @Override
    public String write() throws Exception {
        if(conditions.length != operators.length+1)
            throw new Exception("Conditions should be ONE more than Operators");
        String res = "do {\n";
        for(Step step : body)
            res += step.write()+"\n";
        res += "}";
        res = "while("+conditions[0].write();
        for(int i =1;i<conditions.length;i++){
            String op = operators[i-1].equals(LogicalOperator.OR)? " || " : " && ";
            res += op+conditions[i].write();
        }
        res += ");";
        return res;
    }
}
