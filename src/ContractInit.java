import java.util.Hashtable;
import java.util.Set;

public class ContractInit{
    public static String createContract(IContract c) throws Exception {
        String res = "";
        res += "pragma solidity ^0.8.2; \n\n";
        res += "contract "+ c.getContractName() +" {\n\n\n";
        Hashtable<String,String> state = c.getStateVariables();
        Hashtable<String ,String > init = c.initStateVariables();
        Set<String> s = state.keySet();
        for(String si : s){
            switch(si.toLowerCase()) {
                case "string" : res += "string "+state.get(si);break;
                case "int" : res += "int " +state.get(si);break;
                case "address" : res += "address " + state.get(si);break;
                case "boolean" : res += "bool " + state.get(si);break;
                //arrays
                //hashtables => mappings
                //default: throw error unsupported Type ;
            }
            if(init.containsKey(state.get(si)))
                res += " = "+init.get(state.get(si));
            res += ";\n";
        }
        res += "\n\n";
        String[] methods = c.getMethodNames();
        for(String m : methods) {
            res += createFunction(c,m);
        }
        res += "}";
        return res;
    }

    private static String createFunction(IContract c,String methodName) throws Exception {
        boolean pure = true;
        boolean view = true;
        boolean payable = false;
        String body = "";
        Step[] steps = c.getMethodSteps(methodName);
        Object[] states = c.getStateVariables().values().toArray();
        for(Step step : steps){
            String line = "";
            if(! step.event.equals("true")){
                line += "    if("+step.event+")\n    ";
                if(line.contains(".equals")){
                    String[] sides = step.event.split(".equals");
                    if(sides[1].equals(""))sides[1] = "empty";
                    line = "    if(keccak256(abi.encodePacked(bytes("+sides[0]+"))) == keccak256(abi.encodePacked(bytes"+sides[1]+")))\n    ";
                }
            }
            line += "    "+step.action+";\n";
            if(line.contains("String"))line = line.replace("String","string memory");
            body += line;
        }
        for(Step step : steps){
            for(Object state : states) {
                if(step.event.contains((String)state)) pure = false;
                if(step.action.contains((String)state)) pure = false;
                if(step.action.contains("=")){
                    String[] sides = step.action.split("=");
                    if(sides[0].contains((String)state)) view = false;
                }
            }
        }
        String res = "";
        res += "function "+methodName+"(";
        Hashtable h = c.getMethodParameters(methodName);
        Set<String> s = h.keySet();
        for(String si : s) {
            res += writeParameter(h,si);
            res += ",";
        }
        res = res.substring(0,res.length()-1);
        res += ")";
        res += " public";
        if(payable) res += " payable";
        else if(pure) res += " pure";
        else if(view ) res += " view";
        String ret = "";
        if(! c.getMethodReturnType(methodName).equals("void"))
            ret += " returns("+c.getMethodReturnType(methodName)+") {\n";
        if(ret.contains("String")) ret = ret.replace("String","string memory");
        res += ret;
        res += body + "}";
        res += "\n\n";
        return res;
    }

    private static boolean member(Object[] states, String side) {
        for(Object state : states){
            if(side.contains((String) state))
                return true;
        }
        return false;
    }

    private static String writeParameter(Hashtable h,String si) throws Exception {
        if(si.contains("[]")){
            if(si.contains("String"))
                return "string[] memory "+h.get(si);
            if(si.toLowerCase().contains("uint"))
                return "uint[] memory "+h.get(si);
            if(si.toLowerCase().contains("int"))
                return "int[] memory "+h.get(si);
            if(si.toLowerCase().contains("address"))
                return "address[] memory "+h.get(si);
            if(si.toLowerCase().contains("boolean"))
                return "bool[] memory "+h.get(si);
            throw new Exception("Unsopported Data Type " + si);
        }
        if(si.contains("Hashtable")){
            String si2 = si.substring(10,si.length()-1);
            String[] maps = si2.split(",");
            if(maps.length != 2)
                throw new Exception("Hashtable Wrong Format "+ si);
            if(! maps[0].toLowerCase().equals("uint") && ! maps[0].toLowerCase().equals("int") && ! maps[0].toLowerCase().equals("string") && ! maps[0].toLowerCase().equals("address") && ! maps[0].toLowerCase().equals("boolean"))
                throw new Exception("Unsuopported Data Type "+maps[0]+" in "+si);
            if(! maps[1].toLowerCase().equals("uint") && ! maps[1].toLowerCase().equals("int") && ! maps[1].toLowerCase().equals("string") && ! maps[1].toLowerCase().equals("address") && ! maps[1].toLowerCase().equals("boolean"))
                throw new Exception("Unsuopported Data Type "+maps[1]+" in "+si);
            return "mapping ("+maps[0].toLowerCase()+" => "+maps[1].toLowerCase()+") memory " + h.get(si);
        }
        if(si.toLowerCase().equals("string"))
            return si.toLowerCase() + " memory "+h.get(si);
        if(si.toLowerCase().equals("uint") || si.toLowerCase().equals("int") || si.toLowerCase().equals("address") || si.toLowerCase().equals("boolean"))
            return si.toLowerCase()+" " + h.get(si);
        throw new Exception("Unsupported Data type "+ si);
    }
}
