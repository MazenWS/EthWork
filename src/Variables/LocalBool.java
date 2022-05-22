package Variables;

import Steps.Step;

public class LocalBool extends ParameterBool implements Step {

    String initialValue;
    int javaLine;
    public LocalBool(String name, String initialValue){
        super(name);
        this. initialValue= initialValue;
    }

    public LocalBool(String name){
        super(name);
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
