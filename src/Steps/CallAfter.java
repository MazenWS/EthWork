package Steps;

public class CallAfter implements Step {
    int javaLine;
    @Override
    public String write() throws Exception {
        return "_;" ;
    }

    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }
}
