package Steps;

public class Assign implements Step{
    String assignToVar;
    String statement;
    int javaLine;

    //assignToVar = statement
    public Assign(String assignToVar, String statement){
        this.assignToVar = assignToVar;
        this.statement = statement;
    }

    //assignToVar = sum(a,b)
    public Assign(String assignToVar, Function function){
        this.assignToVar = assignToVar;
        this.statement = function.write();
    }

    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
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
        Step p = new Assign("assign","a+b");
        System.out.println(p.write());
    }
}
