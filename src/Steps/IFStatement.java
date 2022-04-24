package Steps;

public class IFStatement extends Step{
    Condition[] conditions;
    LogicalOperator[] operators;
    Step[] body;

    public IFStatement(Condition[] conditions,LogicalOperator[] operators, Step[] body){
        this.conditions = conditions;
        this.operators = operators;
        this.body = body;
    }

    public IFStatement(Condition condition, Step[] body){
        conditions = new Condition[] {condition};
        operators = new LogicalOperator[0];
        this.body = body;
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
        for(Step step : body)
            res += step.write()+"\n";
        res += "}";
        return res;
    }

    public static void main(String[] args) throws Exception {
        Condition c = new Condition("a","\"b\"","String", RelationalOperator.NOT_EQUAL);
        Step s = new Function("sum",new Variable[] {});
        Step t = new Function("mul", new Variable[]{});
        Condition o = new Condition((Function)s,(Function) t,"uint", RelationalOperator.GREATER_OR_EQUAL);
        Condition[] cond = new Condition[] {c,o};
        LogicalOperator[] log = new LogicalOperator[] {LogicalOperator.AND};
        Step p = new Require(o);
        Step e = new TransferEther("owner",Environment.MSG_VALUE);
        Step[] steps = new Step[] {p,s,e,t};
        Step step = new IFStatement(cond,log,steps);
        System.out.println(step.write());
    }
}
