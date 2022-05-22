package Variables;

import Steps.Step;

public class LocalInteger extends ParameterInteger implements Step {
    String initialValue;
    int javaLine;
    public LocalInteger(String name, boolean signed, int length_powerOfTwo, String initialValue) throws Exception {
        super(name, signed, length_powerOfTwo);
        this.initialValue= initialValue;
    }
    public LocalInteger(String name, boolean signed, int length_powerOfTwo) throws Exception {
        super(name, signed, length_powerOfTwo);

    }


    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }

    @Override
    public String write(){
        String res = super.write();
        if(initialValue != null)
            res += " = " + initialValue;
        res+=";";
        return res;
    }
}
