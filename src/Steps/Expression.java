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

    public static void main(String[] args){
        System.out.println(newStruct("player",new String[] {"2", "Cf", "captain"}));
    }

}
