package Steps;

public class FunctionStatement extends Step{
    String FunctionName;
    String[] parameters;

    //func(params)
    public FunctionStatement(String FunctionName, String[] parameters){
        this.FunctionName = FunctionName;
        this.parameters = parameters;
    }

    @Override
    public String write() {
        String res = FunctionName+"(";
        for(String param : parameters){
            res += param +", ";
        }
        res = res.substring(0,res.length()-2)+");";
        return res;
    }

    public static void main(String[] args) throws Exception {
        Step p = new FunctionStatement("sum",new String[] {"one","two"});
        System.out.println(p.write());
    }
}
