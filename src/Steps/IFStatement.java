package Steps;

public class IFStatement implements Step{
    Condition[] conditions;
    LogicalOperator[] operators;
    Step[] then;
    Step[] elseBody;
    int javaLine;

    public IFStatement(Condition[] conditions,LogicalOperator[] operators, Step[] body){
        this.conditions = conditions;
        this.operators = operators;
        this.then = body;
    }

    public IFStatement(Condition condition, Step[] body){
        conditions = new Condition[] {condition};
        operators = new LogicalOperator[0];
        this.then = body;
    }

    public IFStatement(Condition condition, Step[] then, Step[] elseBody){
        conditions = new Condition[] {condition};
        operators = new LogicalOperator[0];
        this.then = then;
        this.elseBody = elseBody;
    }

    public IFStatement(Condition[] conditions,LogicalOperator[] operators, Step[] then, Step[] elseBody){
        this.conditions = conditions;
        this.operators = operators;
        this.then = then;
        this.elseBody = elseBody;
    }



    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }


    @Override
    public String write() throws Exception {
        if(conditions.length != operators.length+1)
            throw new Exception("Conditions should be ONE more than Operators");
        String res = "if("+conditions[0].write();
        for(int i =1;i<conditions.length;i++){
            String op = operators[i-1].equals(LogicalOperator.OR)? " || " : " && ";
            res += op+conditions[i].write();
        }
        res += ") {\n";
        for(Step step : then)
            res += step.write()+"\n";
        res += "}";
        if(elseBody != null){
            res += "else ";
            if(elseBody[0] instanceof IFStatement && elseBody.length == 1){
                res += elseBody[0].write();
            }
            else {
                res += "{ \n";
                for (Step step : elseBody) {
                    res += step.write() + "\n";
                }
            }
            res += "}";
        }
        return res;
    }

    public static void main(String[] args) {
    }
}
