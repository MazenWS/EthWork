package Steps;

import Variables.Variable;

public class Function implements Step{
    String FunctionName;
    Variable[] parameters;
    int javaLine;

    //func(params)
    public Function(String FunctionName, Variable[] parameters){
        this.FunctionName = FunctionName;
        this.parameters = parameters;
    }


    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }

    @Override
    public String write() {
        String res = FunctionName+"(";
        for(Variable param : parameters){
            res += param.getClass() +", ";
        }
        res = res.substring(0,res.length()-2)+");";
        return res;
    }

    public static void main(String[] args) throws Exception {
    }
}
