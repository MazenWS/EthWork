package Steps;

public class AssignStatement extends Step{
    String assignToVar;
    String statement;

    //assignToVar = statement
    public AssignStatement(String assignToVar, String statement){
        this.assignToVar = assignToVar;
        this.statement = statement;
    }

    //assignToVar = sum(a,b)
    public AssignStatement(String assignToVar, FunctionStatement function){
        this.assignToVar = assignToVar;
        this.statement = function.write();
    }

    @Override
    public String write() throws Exception{
        String res  = assignToVar + " = ";
        if(statement.contains(".get")){
            String[] get = statement.split(".get");
            get[1] = get[1].substring(1,get[1].length()-1);
            statement = get[0]+"["+get[1]+"]";
        }
        res += statement +";";
        return res;
    }

    public static void main(String[] args) throws Exception {
        Step p = new AssignStatement("assign","a+b");
        System.out.println(p.write());
    }
}
