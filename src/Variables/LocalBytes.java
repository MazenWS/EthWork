package Variables;

public class LocalBytes extends ParameterBytes implements LocalVariable{
    String initialValueInHex;
    public LocalBytes (String name, int length, String initialValueInHex){
        super(name, length);
        this .initialValueInHex= initialValueInHex;
    }

    @Override
    public String write(){
        String res = super.write();
        res += " = " + initialValueInHex;
        return res;
    }
}
