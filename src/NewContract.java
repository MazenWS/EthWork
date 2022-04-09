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
        Variable var1= new Variable(DataType.BOOLEAN, "isTrue");
        Variable var2 = new Variable(DataType.BYTES,"Character");
        Struct S = new Struct("Request" ,AccessModifier.PRIVATE,new Variable[]{var1,var2});
        return S;
    }

    @Override
    public Step[] createConstructor() {
        return new Step[] {new Step("true","gender = true")};
    }

    @Override
    public ParameterVar[] constructorParameters() {
        ParameterVar  var1 = (ParameterVar) new Variable(DataType.ADDRESS,"myAddress");

        return new ParameterVar[]{var1};
    }

    @Override
    public boolean payableConstructor() {
        return true;
    }

    @Override
    public  StateVariable[] getStateVariables() throws Exception {
        StateVariable var1 = new StateVariable(DataType.ADDRESS,"myADDRESS","0x00000000000000000",AccessModifier.PUBLIC);
        return new StateVariable[]{var1};
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
    public ParameterVar[] getMethodParameters(String strMethodName) {
        ParameterVar  var1 = (ParameterVar) new Variable(DataType.ADDRESS,"myAddress");

        return new ParameterVar[]{var1};
    }

    @Override
    public boolean payable(String strMethodName) {
        if(strMethodName.equals("func1"))
            return true;
        return false;
    }

    @Override
    public ParameterVar[] getMethodReturnType(String strMethodName) {
        ParameterVar  var1 = (ParameterVar) new Variable(DataType.ADDRESS,"myAddress");

        return new ParameterVar[]{var1};
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
