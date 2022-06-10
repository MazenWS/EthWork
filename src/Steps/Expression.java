package Steps;

public class Expression {


    public static String arrayValue(String arrName, String index){
        return arrName+"["+index+"]";
    }

    public static String mappingValue(String mapName, String key) {
        return mapName + "["+key+"]";
    }

    public static String structMemberValue(String varName, String member){
        return varName+"."+member;
    }

    public static String arrayLength(String arrName){
        return arrName+".length";
    }

    public static String stringLength(String varName){
        return "bytes("+varName+").length";
    }

    public static String newStruct(String structName, String[] structMembers){
        String res = structName+"(";
        if(structMembers == null || structMembers.length==0){
            return res + ")";
        }
        res += structMembers[0];
        for(int i = 1; i<structMembers.length;i++){
            res += ", "+ structMembers[i];
        }
        return res + ")";
    }

    public static String functionCall(String functionName, String[] inputs) {
        String res = functionName+"(";
        if(inputs!=null && inputs.length>0){
            res += inputs[0];
            for(int i =1;i<inputs.length;i++){
                res += ", "+inputs[i];
            }
        }
        res += ")";
        return res;
    }

    public static String libraryFunctionCall(String library, String functionName, String[] inputs) {
        String res = library+"."+functionName+"(";
        if(inputs!=null && inputs.length>0){
            res += inputs[0];
            for(int i =1;i<inputs.length;i++){
                res += ", "+inputs[i];
            }
        }
        res += ")";
        return res;
    }

    public static String arithmeticOperation(String arg1, String arg2, ArithOperator operator) throws Exception {
        if(operator.equals(ArithOperator.INCREMENT) || operator.equals(ArithOperator.DECREMENT)){
            throw new Exception("cannot use Increment or Decrement");
        }
        return "("+arg1+" "+ Arithmetic.getOp(operator)+ " "+arg2+")";
    }

    public static void main(String[] args) throws Exception {
        System.out.println(arithmeticOperation("x","y",ArithOperator.DIV));
    }

}
