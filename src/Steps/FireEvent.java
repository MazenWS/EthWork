package Steps;

public class FireEvent implements Step{
    String EventName;
    String[] parameters;
    int javaLine;

    //emit EventName(parameters)
    public FireEvent(String EventName, String[] parameters){
        this.EventName = EventName;
        this.parameters = parameters;
    }


    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
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
        Step p = new FireEvent("Buy",new String[] {"caller","amount"});
        System.out.println(p.write());
    }
}
