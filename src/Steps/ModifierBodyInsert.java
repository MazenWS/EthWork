package Steps;

public class ModifierBodyInsert implements Step{


    //inserted only in modifiers where the body of function that uses modifier should be inserted
    @Override
    public String write() throws Exception {
        return "_;";
    }
}
