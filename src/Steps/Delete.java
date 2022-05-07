package Steps;

public class Delete  implements Step{
    String statement;
    public Delete(String statement){
        this.statement= statement;
    }

    @Override
    public String write() throws Exception {
        return "delete "+statement+";";
    }
}
