package Steps;

public class Delete  implements Step{
    String statement;
    int javaLine;
    public Delete(String statement){
        this.statement= statement;
    }

    @Override
    public String write() throws Exception {
        return "delete "+statement+";";
    }

    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }
}
