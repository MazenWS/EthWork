package Steps;

public class FireEventStatement extends Step{
    String EventName;
    String[] parameters;

    //emit EventName(parameters)
    public FireEventStatement(String EventName, String[] parameters){
        this.EventName = EventName;
        this.parameters = parameters;
    }

    @Override
    public String write() {
        String res = "emit "+EventName+"(";
        for(String param : parameters){
            res += param + ", ";
        }
        res = res.substring(0,res.length()-2) + ");";
        return res;
    }

    public static void main(String[] args) throws Exception {
        Step p = new FireEventStatement("Buy",new String[] {"caller","amount"});
        System.out.println(p.write());
    }
}
