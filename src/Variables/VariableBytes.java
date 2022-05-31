package Variables;

public class VariableBytes implements Variable {
    int length;


    public VariableBytes( int length ) throws Exception {
        if ( length<1 || length>32)
            throw new Exception("this bytes length doesn't exist");
        this. length = length;
    }
    public VariableBytes(   ) throws Exception {


    }


    @Override
    public String write() {
        return length!=0?"bytes"+length:"bytes";
    }
}
