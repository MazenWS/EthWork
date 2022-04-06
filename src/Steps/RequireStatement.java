package Steps;


public class RequireStatement extends Step{
    Condition condition;
    String messageToThrow  = null;

    //require(bool statement,if false throw message)
    public RequireStatement(Condition condition, String messageToThrow){
        this.condition = condition;
        this.messageToThrow = messageToThrow;
    }

    //require(condition) if false throws error
    public RequireStatement(Condition condition){
        this.condition = condition;
    }

    @Override
    public String write() throws Exception {
        String res = "";
        res += "require("+condition.write();
        res += messageToThrow != null ? ", "+messageToThrow+");" : ");";
        return res;
    }

    public static void main(String[] args) throws Exception {
        Step s = new FunctionStatement("sum",new String[] {"1","2"});
        Step t = new FunctionStatement("mul", new String[] {"3","4"});
        Condition o = new Condition((FunctionStatement)s,(FunctionStatement) t,"uint", RelationalOperator.GREATER_OR_EQUAL);
        Step p = new RequireStatement(o);
        System.out.println(p.write());
    }
}
