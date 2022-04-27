package Steps;

public class Return implements Step{
    String statement;


    public Return(String statement){
        this.statement= statement;
    }

    @Override
    public String write() throws Exception {
        String res = "return";
        res +=" "+ statement +";";
        return res;
    }
}
