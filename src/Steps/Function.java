package Steps;

import Contracts.TheFile;
import Lines.Line;
import Variables.Variable;

public class Function implements Step{
    String FunctionName;
    String[] parameters;
    int javaLine;

    //func(params)
    public Function(String FunctionName, String[] inputs){
        this.FunctionName = FunctionName;
        this.parameters = inputs;
    }


    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }

    @Override
    public String write() {
        TheFile.lineMap.addLine(new Line(javaLine,"Step",TheFile.solidityCount,TheFile.solidityCount));
        TheFile.solidityCount++;
        String res = FunctionName+"(";
        for(String param : parameters){
            res += param +", ";
        }
        res = res.substring(0,res.length()-2)+");";
        return res;
    }

    public static void main(String[] args) throws Exception {
        Function f = new Function("sum",new String[] {"a","b"});
        System.out.println(Expression.mappingValue(Expression.mappingValue("_operatorApproval","owner"),
                "operator"));
    }
}
