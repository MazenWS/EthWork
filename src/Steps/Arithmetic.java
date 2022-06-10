package Steps;

import Contracts.TheFile;
import Lines.Line;

import java.util.ArrayList;
import java.util.List;

public class Arithmetic implements Step{
    String LHS;
    ArrayList<String> args = new ArrayList<String>();
    ArrayList<ArithOperator> operators = new ArrayList<ArithOperator>();
    int javaLine;


    public Arithmetic(String LHS, String arg1, String arg2, ArithOperator operator){
        this.LHS = LHS;
        args.add(arg1);
        args.add(arg2);
        operators.add(operator);
    }

    public Arithmetic(String LHS, String arg, ArithOperator operator) throws Exception {
        if(!(operator.equals(ArithOperator.DECREMENT) || operator.equals(ArithOperator.INCREMENT))) {
            throw new Exception("Increment and Decrement are the only operators used in this Constructor!!");
        }
        this.LHS = LHS;
        args.add(arg);
        operators.add(operator);
    }

    public Arithmetic(String LHS, String[] args, ArithOperator[] operators) throws Exception {
        if(args.length == 1) {
            if (!(operators[0].equals(ArithOperator.DECREMENT) || operators[0].equals(ArithOperator.INCREMENT)))
                throw new Exception("Only Increment and Decrement can be used with 1 argument entered");
        }else if (args.length != operators.length + 1) {
            throw new Exception("Argument should be 1 more than operators");
        }
        if(operators.length > 1){
            for(ArithOperator op : operators){
                if((op.equals(ArithOperator.DECREMENT) || op.equals(ArithOperator.INCREMENT)))
                    throw new Exception("Increment and Decrement can only be used with 1 argument constructor");
            }
        }
        this.LHS = LHS;
        this.args.addAll(List.of(args));
        this.operators.addAll(List.of(operators));
    }

    @Override
    public String write() throws Exception {
//        TheFile.lineMap.addLine(new Line(javaLine,"Step",TheFile.solidityCount,TheFile.solidityCount));
//        TheFile.solidityCount++;
        if(args.size() == 1){
            return LHS +" "+ getOp(operators.get(0)) +" "+ args.get(0);
        }
        String res = LHS + " = " +args.get(0);
        for(int i=0; i<operators.size();i++){
            res += " "+getOp(operators.get(i)) + " "+args.get(i+1);
        }
        return res;
    }

    public static String getOp(ArithOperator op){
        switch (op){
            case ADD -> {return "+";}
            case DIV -> {return "/";}
            case MUL -> {return "*";}
            case SUB -> {return "-";}
            case POWER -> {return "**";}
            case MODULUS -> {return "%";}
            case DECREMENT -> {return "-=";}
            case INCREMENT -> {return "+=";}
        }
        return null;
    }

    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }

    public static void main(String[] args){
        try {
            Arithmetic ar = new Arithmetic("X",new String[]{"y"},new ArithOperator[]{ArithOperator.DIV});
            System.out.println(ar.write());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
