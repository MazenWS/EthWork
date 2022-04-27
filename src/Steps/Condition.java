package Steps;

public class Condition {
    String arg1;
    String arg2;
    String argType;
    RelationalOperator sign;
    String bool;

    // a != b
    public Condition(String arg1, String arg2, String argType, RelationalOperator sign){
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.argType = argType;
        this.sign = sign;
    }

    //sum(a,b)==c
    public Condition(Function func, String arg2, String argType, RelationalOperator sign){
        arg1 = func.write();
        this.arg2 = arg2;
        this.argType = argType;
        this.sign = sign;
    }

    //sum(a,b)>mul(c,d)
    public Condition(Function func1, Function func2, String argType, RelationalOperator sign){
        arg1 = func1.write();
        arg2 = func2.write();
        this.argType = argType;
        this.sign = sign;
    }

    //EQUAL for true(if func) NOT EQUAL for false(if ! func)
    //method must return boolean
    //isEmpty()
    public Condition(Function func, RelationalOperator equalNotEqual){
        arg1 = func.write();
        sign = equalNotEqual;
    }
    public Condition(String bool, RelationalOperator equalNotEqual){
       this.bool= bool;
       sign = equalNotEqual;
    }
    public String write() throws Exception {
        if(bool!= null){
            if(sign.equals(RelationalOperator.EQUAL))
                return bool;
            if(sign.equals(RelationalOperator.NOT_EQUAL))
                return "! "+bool;
            throw new Exception("can only be if (RelationalOperator.EQUAL) or if not (RelationalOperator.NOT_EQUAL) for boolean functions");

        }
        if(arg2 == null){
            arg1 = arg1.substring(0,arg1.length()-1);
            if(sign.equals(RelationalOperator.EQUAL))
                return arg1;
            if(sign.equals(RelationalOperator.NOT_EQUAL))
                return "!"+arg1;
            throw new Exception("can only be if (RelationalOperator.EQUAL) or if not (RelationalOperator.NOT_EQUAL) for boolean functions");
        }
        if(arg1.contains(";"))
            arg1 = arg1.substring(0,arg1.length()-1);
        if(arg2.contains(";"))
            arg2 = arg2.substring(0,arg2.length()-1);
        if(arg1.contains(".get")){
            String[] get = arg1.split(".get");
            get[1] = arg1.substring(1,get[1].length()-1);
            arg1 = get[0]+"["+get[1]+"]";
        }
        if(arg2.contains(".get")){
            String[] get = arg2.split(".get");
            get[1] = arg2.substring(1,get[1].length()-1);
            arg2 = get[0]+"["+get[1]+"]";
        }
        if(argType.equals("String")){
            arg1 = "keccak256(abi.encodePacked(bytes(" + arg1 + ")))";
            arg2 = "keccak256(abi.encodePacked(bytes(" + arg2 + ")))";
        }
        if(sign.equals(RelationalOperator.EQUAL))
            return arg1 + " == " + arg2;
        if(sign.equals(RelationalOperator.NOT_EQUAL))
            return arg1 + " != " + arg2;
        if(sign.equals(RelationalOperator.GREATER_OR_EQUAL))
            return arg1 + " >= " + arg2;
        if(sign.equals(RelationalOperator.GREATER_THAN))
            return arg1 + " > "+ arg2;
        if(sign.equals(RelationalOperator.LESS_OR_EQUAL))
            return arg1 + " <= "+ arg2;
        return arg1 + " < "+arg2;

    }

    public static void main(String[] args) throws Exception {
    }

}