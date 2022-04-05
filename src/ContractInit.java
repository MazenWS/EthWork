import java.util.Hashtable;
import java.util.Set;

public class ContractInit{
    public static String createContract(IContract c) throws Exception {
        String res = "";
        res += "pragma solidity ^0.8.2; \n\n";
        res += "contract "+ c.getContractName() +" {\n\n\n";
        if (c.createObject()!=null){
            Struct myStruct =c.createObject();
            res+= "struct "+myStruct.name+" {\n";
            Hashtable<String,String> vars = myStruct.vars;
            Set<String> datatype = vars.keySet();
            for(String dtype : datatype){

                res+= writestatevariables(vars,dtype);
                res += ";\n";}
            res+="}\n";

        }
        Hashtable<String,String> state = c.getStateVariables();
        //write the initial values of the state variables...
       Hashtable<String ,String > init = c.initStateVariables();
        Set<String> datatype = state.keySet();
        for(String dtype : datatype){

           res+= writestatevariables(state,dtype);
            if(init.containsKey(state.get(dtype)))
                res += " = "+init.get(state.get(dtype));
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
        Hashtable params = c.getMethodParameters(methodName);
        Set<String> type = params.keySet();
        for(String dtype : type) {
            res += writeParameter(params,dtype);
            res += ",";
        }
        res = res.substring(0,res.length()-1);
        res += ")";
        res += " public";
        if(payable) res += " payable";
        else if(pure) res += " pure";
        else if(view ) res += " view";
        String ret = "";


            // checking if the function is returning anything
        if(!(c.getMethodReturnType(methodName)==null))
            //if two returns have the same datatype they have to have names
            //looping over the return types the user stated and adding them in the function declaration
            ret += " returns(";
        Hashtable<String,String> returns= c.getMethodReturnType(methodName);
        Set<String> returntype = returns.keySet();

            for (String  rettype:returntype
                 ) {
               ret +=writestatevariables2(returns,rettype)+" ,";
            }
            ret= ret.substring(0,ret.length()-1);
ret+=" )\n";

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
// why not a generic helper function ??
    private static String writeParameter(Hashtable params,String dtype) throws Exception {
        if(dtype.contains("[]")){
            if(dtype.contains("String"))
                return "string[] memory "+params.get(dtype);
            if(dtype.toLowerCase().contains("uint"))
                return "uint[] memory "+params.get(dtype);
            if(dtype.toLowerCase().contains("int"))
                return "int[] memory "+params.get(dtype);
            if(dtype.toLowerCase().contains("address"))
                return "address[] memory "+params.get(dtype);
            if(dtype.toLowerCase().contains("boolean"))
                return "bool[] memory "+params.get(dtype);
            throw new Exception("Unsopported Data Type " + dtype);
        }
        if(dtype.contains("Hashtable")){
            String dtype2 = dtype.substring(10,dtype.length()-1);
            String[] maps = dtype2.split(",");
            if(maps.length != 2)
                throw new Exception("Hashtable Wrong Format "+ dtype);
            if(! maps[0].toLowerCase().equals("uint") && ! maps[0].toLowerCase().equals("int") && ! maps[0].toLowerCase().equals("string") && ! maps[0].toLowerCase().equals("address") && ! maps[0].toLowerCase().equals("boolean"))
                throw new Exception("Unsuopported Data Type "+maps[0]+" in "+dtype);
            if(! maps[1].toLowerCase().equals("uint") && ! maps[1].toLowerCase().equals("int") && ! maps[1].toLowerCase().equals("string") && ! maps[1].toLowerCase().equals("address") && ! maps[1].toLowerCase().equals("boolean"))
                throw new Exception("Unsuopported Data Type "+maps[1]+" in "+dtype);
            return "mapping ("+maps[0].toLowerCase()+" => "+maps[1].toLowerCase()+") memory " + params.get(dtype);
        }
        if(dtype.toLowerCase().equals("string"))
            return dtype.toLowerCase() + " memory "+params.get(dtype);
        if(dtype.toLowerCase().equals("uint") || dtype.toLowerCase().equals("int") || dtype.toLowerCase().equals("address") || dtype.toLowerCase().equals("boolean"))
            return dtype.toLowerCase()+" " + params.get(dtype);
        throw new Exception("Unsupported Data type "+ dtype);
    }

//helper function to read the state variables hashtable
    private static String writestatevariables2(Hashtable params,String dtype) throws Exception {
        if(dtype.contains("[]")){
            if(dtype.contains("String"))
                return "string[] "+params.get(dtype);
            if(dtype.toLowerCase().contains("uint"))
                return "uint[] "+params.get(dtype);
            if(dtype.toLowerCase().contains("int"))
                return "int[] "+params.get(dtype);
            if(dtype.toLowerCase().contains("address"))
                return "address[] "+params.get(dtype);
            if(dtype.toLowerCase().contains("boolean"))
                return "bool[] "+params.get(dtype);
            throw new Exception("Unsopported Data Type " + dtype);
        }

        if(dtype.toLowerCase().equals("string"))
            return dtype.toLowerCase()+" "+params.get(dtype)  ;
        if(dtype.toLowerCase().equals("uint") || dtype.toLowerCase().equals("int") || dtype.toLowerCase().equals("address") || dtype.toLowerCase().equals("boolean"))
            return dtype.toLowerCase()+" " + params.get(dtype);
        throw new Exception("Unsupported Data type "+ dtype);
    }


    private static String writestatevariables(Hashtable params,String dtype) throws Exception {
        if(dtype.contains("[]")){
            if(dtype.contains("String"))
                return "string[] "+params.get(dtype);
            if(dtype.toLowerCase().contains("uint"))
                return "uint[] "+params.get(dtype);
            if(dtype.toLowerCase().contains("int"))
                return "int[] "+params.get(dtype);
            if(dtype.toLowerCase().contains("address"))
                return "address[] "+params.get(dtype);
            if(dtype.toLowerCase().contains("boolean"))
                return "bool[] "+params.get(dtype);
            throw new Exception("Unsopported Data Type " + dtype);
        }
        if(dtype.contains("Hashtable")){
            String dtype2 = dtype.substring(10,dtype.length()-1);
            String[] maps = dtype2.split(",");
            if(maps.length != 2)
                throw new Exception("Hashtable Wrong Format "+ dtype);
            if(! maps[0].toLowerCase().equals("uint") && ! maps[0].toLowerCase().equals("int") && ! maps[0].toLowerCase().equals("string") && ! maps[0].toLowerCase().equals("address") && ! maps[0].toLowerCase().equals("boolean"))
                throw new Exception("Unsuopported Data Type "+maps[0]+" in "+dtype);
            if(! maps[1].toLowerCase().equals("uint") && ! maps[1].toLowerCase().equals("int") && ! maps[1].toLowerCase().equals("string") && ! maps[1].toLowerCase().equals("address") && ! maps[1].toLowerCase().equals("boolean"))
                throw new Exception("Unsuopported Data Type "+maps[1]+" in "+dtype);
            return "mapping ("+maps[0].toLowerCase()+" => "+maps[1].toLowerCase()+") " + params.get(dtype)+";";
        }
        if(dtype.toLowerCase().equals("string"))
            return dtype.toLowerCase()+" "+params.get(dtype)  ;
        if(dtype.toLowerCase().equals("uint") || dtype.toLowerCase().equals("int") || dtype.toLowerCase().equals("address") || dtype.toLowerCase().equals("boolean"))
            return dtype.toLowerCase()+" " + params.get(dtype);
        throw new Exception("Unsupported Data type "+ dtype);
    }
}
