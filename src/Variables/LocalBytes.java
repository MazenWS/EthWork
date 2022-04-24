package Variables;

public class LocalBytes extends ParameterBytes implements LocalVariable{
    String initialValueInHex;
    public LocalBytes (String name, int length, String initialValueInHex){
        super(name, length);
        this .initialValueInHex= initialValueInHex;

    }


}
