//import Steps.Step;
//import Variables.Struct;
//
//import java.util.Hashtable;
//import java.util.Set;
//
//public class ContractInit{
//    public static String createContract(IContract c) throws Exception {
//        String res = "";
//        res += "pragma solidity ^0.8.2; \n\n";
//        res += "contract "+ c.getContractName() +" {\n\n\n";
//        if (c.createObject()!=null){
//            Struct myStruct =c.createObject();
//            res+= "struct "+myStruct.name+" {\n";
//            Hashtable<String,String> vars = myStruct.vars;
//            Set<String> datatype = vars.keySet();
//            for(String dtype : datatype){
//
//                res= writeParameter(res,vars,dtype);
//                res += ";\n";}
//            res+="}\n";
//
//        }
//        Hashtable<String,String> state = c.getStateVariables();
//        if(state != null && ! state.isEmpty()) {
//            Hashtable<String, String> init = c.initStateVariables();
//            Set<String> s = state.keySet();
//            for (String si : s) {
//                res = writeParameter(res, state, si);
//                if (init.containsKey(state.get(si)))
//                    res += " = " + init.get(state.get(si));
//                res += ";\n";
//            }
//            res += "\n\n";
//        }
//        res += createConstructor(c)+"\n\n";
//        String[] methods = c.getMethodNames();
//        for(String m : methods) {
//            res += createFunction(c,m);
//        }
//        res += "}";
//        return res;
//    }
//
//    private static String createFunction(IContract c,String methodName) throws Exception {
//        boolean pure = true;
//        boolean view = true;
//        String body = "";
//        Step[] steps = c.getMethodSteps(methodName);
//        Hashtable<String, String> stateVars = c.getStateVariables();
//        body = writeBody(body,steps);
//        if(stateVars != null && !stateVars.isEmpty()) {
//            Object[] states = stateVars.values().toArray();
//            for (Step step : steps) {
//                for (Object state : states) {
////                    if (step.event.contains((String) state)) pure = false;
////                    if (step.action.contains((String) state)) pure = false;
////                    if (step.action.contains("=")) {
////                        String[] sides = step.action.split("=");
////                        if (sides[0].contains((String) state)) view = false;
////                    }
//                }
//            }
//        }
//        String res = "";
//        res += "function "+methodName+"(";
//        Hashtable h = c.getMethodParameters(methodName);
//        if(h != null && ! h.isEmpty()) {
//            Set<String> s = h.keySet();
//            for (String si : s) {
//                res = writeParameter(res, h, si);
//                res += ",";
//            }
//            res = res.substring(0, res.length() - 1);
//        }
//        res += ") ";
//        res += c.getMethodAccessModifier(methodName);
//        if(! c.payable(methodName)){
//            if (pure) res += " pure";
//            else if (view) res += " view";
//        }
//        String ret = "";
//
//
//            // checking if the function is returning anything
//        if(!(c.getMethodReturnType(methodName)==null))
//            //if two returns have the same datatype they have to have names
//            //looping over the return types the user stated and adding them in the function declaration
//            ret += " returns(";
//        Hashtable<String,String> returns= c.getMethodReturnType(methodName);
//        Set<String> returntype = returns.keySet();
//
//            for (String  rettype:returntype
//                 ) {
//               ret +=writestatevariables2(returns,rettype)+" ,";
//            }
//            ret= ret.substring(0,ret.length()-1);
//ret+=" )\n";
//
//        res += ret;
//        res += c.payable(methodName)? "payable" : "";
//        res += " {\n";
//        res += body + "}";
//        res += "\n\n";
//        return res;
//    }
//
//    private static boolean member(Object[] states, String side) {
//        for(Object state : states){
//            if(side.contains((String) state))
//                return true;
//        }
//        return false;
//    }
//
//
////helper function to read the state variables hashtable
//    private static String writestatevariables2(Hashtable params,String dtype) throws Exception {
//        if(dtype.contains("[]")){
//            if(dtype.contains("String"))
//                return "string[] "+params.get(dtype);
//            if(dtype.toLowerCase().contains("uint"))
//                return "uint[] "+params.get(dtype);
//            if(dtype.toLowerCase().contains("int"))
//                return "int[] "+params.get(dtype);
//            if(dtype.toLowerCase().contains("address"))
//                return "address[] "+params.get(dtype);
//            if(dtype.toLowerCase().contains("boolean"))
//                return "bool[] "+params.get(dtype);
//            throw new Exception("Unsopported Data Type " + dtype);
//        }
//
//        if(dtype.toLowerCase().equals("string"))
//            return dtype.toLowerCase()+" "+params.get(dtype)  ;
//        if(dtype.toLowerCase().equals("uint") || dtype.toLowerCase().equals("int") || dtype.toLowerCase().equals("address") || dtype.toLowerCase().equals("boolean"))
//            return dtype.toLowerCase()+" " + params.get(dtype);
//        throw new Exception("Unsupported Data type "+ dtype);
//    }
//
//
//
//
//
//    private static String writeParameter(String dest,Hashtable h,String si) throws Exception {
//        if(si.contains("[]")){
//            if(si.contains("String"))
//                return dest += "string[] memory "+h.get(si);
//            if(si.toLowerCase().contains("uint"))
//                return dest += "uint[] memory "+h.get(si);
//            if(si.toLowerCase().contains("int"))
//                return dest += "int[] memory "+h.get(si);
//            if(si.toLowerCase().contains("address"))
//                return dest += "address[] memory "+h.get(si);
//            if(si.toLowerCase().contains("boolean"))
//                return dest += "bool[] memory "+h.get(si);
//            throw new Exception("Unsopported Data Type " + si);
//        }
//        if(si.contains("Hashtable")){
//            String si2 = si.substring(10,si.length()-1);
//            String[] maps = si2.split(",");
//            if(maps.length != 2)
//                throw new Exception("Hashtable Wrong Format "+ si);
//            if(! maps[0].toLowerCase().equals("uint") && ! maps[0].toLowerCase().equals("int") && ! maps[0].toLowerCase().equals("string") && ! maps[0].toLowerCase().equals("address") && ! maps[0].toLowerCase().equals("boolean"))
//                throw new Exception("Unsuopported Data Type "+maps[0]+" in "+si);
//            if(! maps[1].toLowerCase().equals("uint") && ! maps[1].toLowerCase().equals("int") && ! maps[1].toLowerCase().equals("string") && ! maps[1].toLowerCase().equals("address") && ! maps[1].toLowerCase().equals("boolean"))
//                throw new Exception("Unsuopported Data Type "+maps[1]+" in "+si);
//            return dest += "Variables.mapping ("+maps[0].toLowerCase()+" => "+maps[1].toLowerCase()+") memory " + h.get(si);
//        }
//        if(si.toLowerCase().equals("string"))
//            return dest += si.toLowerCase() + " memory "+h.get(si);
//        if(si.toLowerCase().equals("boolean"))
//            return dest += "bool "+ h.get(si);
//        if(si.toLowerCase().equals("uint") || si.toLowerCase().equals("int") || si.toLowerCase().equals("address"))
//            return dest += si.toLowerCase()+" " + h.get(si);
//        throw new Exception("Unsupported Data type "+ si);
//    }
//
//    public static String createConstructor(IContract c) throws Exception {
//        if(c.createConstructor() == null)
//            return "";
//        String res = "constructor(";
//        Hashtable h = c.constructorParameters();
//        if(h != null && ! h.isEmpty()) {
//            Set<String> keys;
//            if (!h.equals(null)) {
//                keys = h.keySet();
//                for (String key : keys) {
//                    res = writeParameter(res, c.constructorParameters(), key);
//                    res += ", ";
//                }
//                res = res.substring(0, res.length() - 2);
//            }
//        }
//        res += ") ";
//        if(c.payableConstructor())
//            res += "payable ";
//        res += "{\n";
//        Step[] steps = c.createConstructor();
//        res = writeBody(res,steps);
//        res += "}";
//        return res;
//    }
//
//    private static String writeBody(String dest,Step[] steps) {
//        for (Step step : steps) {
//            String line = "";
////            if (!step.event.equals("true")) {
////                line += "    if(" + step.event + ")\n    ";
////                if (line.contains(".equals")) {
////                    String[] sides = step.event.split(".equals");
////                    if (sides[1].equals("")) sides[1] = "empty";
////                    line = "    if(keccak256(abi.encodePacked(bytes(" + sides[0] + "))) == keccak256(abi.encodePacked(bytes" + sides[1] + ")))\n    ";
////                }
////            }
////            line += "    " + step.action + ";\n";
//            if (line.contains("String")) line = line.replace("String", "string memory");
//            dest += line;
//        }
//        return dest;
//    }
//}
