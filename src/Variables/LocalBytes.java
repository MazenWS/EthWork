package Variables;

import Steps.Step;

public class LocalBytes extends ParameterBytes implements Step {
    String initialValueInHex;
    int javaLine;
    public LocalBytes (String name, int length, String initialValueInHex) throws Exception {
        super(name, length);
        this .initialValueInHex= initialValueInHex;
    }
    public LocalBytes (String name, int length ) throws Exception {
        super(name, length);
    }


    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }

    @Override
    public String write(){
        String res = super.write();
        if(initialValueInHex != null) {
            res += " = " + initialValueInHex;
        }
        res+=";";
        return res;
    }
}
