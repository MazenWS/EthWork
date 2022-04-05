import java.util.Hashtable;

public class NewContract implements IContract{
    @Override
    public String getContractName() {
        return "newContract";
    }

    @Override
    public Struct createObject() {
        Hashtable<String,String > h = new Hashtable<>();
        h.put("string","name");
        h.put("int","amount");
        Struct S = new Struct("Request" , h);
        return S;
    }

    @Override
    public Step[] createConstructor() {
        return new Step[] {new Step("true","gender = true")};
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
    public Hashtable <String,String> getMethodReturnType(String strMethodName) {
       Hashtable H1= new Hashtable<String,String>();
       H1.put("String","name");
       H1.put("int","number");
        Hashtable H2= new Hashtable<String,String>();
        H2.put("String[]","names");
        H2.put("int[]","numbers");
        if(strMethodName.equals("func1"))
            return  H1;
        else
            return H2;
    }

    @Override
    public Step[] getMethodSteps(String strMethodName) {
        if(strMethodName.equals("func1"))
            return new Step[] {new Step("place.equals(\"Nasr City\")","place = \"Seif\""), new Step("age == 5","age = 10")};
        if(strMethodName.equals("func2"))
            return new Step[] {new Step("array[0] == 10", "gender = true")};
        else return null;
    }

    public static void main(String[] args) throws Exception {
        NewContract n = new NewContract();
        System.out.println(ContractInit.createContract(n));
    }
}
