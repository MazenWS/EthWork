import Steps.Step;

import java.util.Hashtable;

public class NewContract implements IContract{
    @Override
    public String getContractName() {
        return "newContract";
    }

    @Override
    public Struct createObject() {
        return null;
    }

    @Override
    public Step[] createConstructor() {
        //return new Step[] {new Step("true","gender = true")};
        return null;
    }

    @Override
    public Hashtable constructorParameters() {
        Hashtable<String,String > h = new Hashtable<>();
        h.put("address","owner");
        return h;
    }

    @Override
    public boolean payableConstructor() {
        return true;
    }

    @Override
    public Hashtable<String,String> getStateVariables() {
        Hashtable<String,String > h = new Hashtable<>();
        h.put("string","name");
        h.put("boolean","gender");
        return h;
    }

    @Override
    public Hashtable<String, String> initStateVariables() {
        Hashtable<String,String > h = new Hashtable<>();
        h.put("name","Mazen");
        return h;
    }

    @Override
    public String[] getMethodNames() {
        return new String[] {"func1","func2"};
    }

    @Override
    public String getMethodAccessModifier(String strMethodName) {
        if(strMethodName.equals("func1"))
            return "internal";
        return "private";
    }

    @Override
    public Hashtable<String,String> getMethodParameters(String strMethodName) {
        Hashtable<String,String > h = new Hashtable<>();
        if(strMethodName.equals("func1")) {
            h.put("String", "place");
            h.put("int", "age");
        }
        if(strMethodName.equals("func2")){
            h.put("Hashtable<address,String>","balance");
            h.put("uint[]","array");
        }
        return h;
    }

    @Override
    public boolean payable(String strMethodName) {
        if(strMethodName.equals("func1"))
            return true;
        return false;
    }

    @Override
    public String getMethodReturnType(String strMethodName) {
        if(strMethodName.equals("func1"))
            return "String";
        else
            return "boolean";
    }

    @Override
    public Step[] getMethodSteps(String strMethodName) {
        return null;
    }

    public static void main(String[] args) throws Exception {
        NewContract n = new NewContract();
        System.out.println(ContractInit.createContract(n));
    }
}
