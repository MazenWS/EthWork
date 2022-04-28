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

}
