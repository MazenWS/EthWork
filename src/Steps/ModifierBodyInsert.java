package Steps;

public class ModifierBodyInsert implements Step{
    int javaLine;


    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }


    //inserted only in modifiers where the body of function that uses modifier should be inserted
    @Override
    public String write() throws Exception {
        return "_;";
    }
}
