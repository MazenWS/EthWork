package Variables;

public class VariableBytes implements Variable {
    int length;


    public VariableBytes( int length ){
        this. length = length;
    }


    @Override
    public String write() {
        return "bytes"+length;
    }
}
