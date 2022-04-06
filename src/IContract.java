import Steps.Step;

import java.util.Hashtable;

public interface IContract {
    public String getContractName();
    public Struct createObject();
    public Step[] createConstructor();
    public Hashtable constructorParameters();
    public boolean payableConstructor();
    public Hashtable<String,String> getStateVariables();
    public Hashtable<String,String> initStateVariables();
    public String[] getMethodNames();
    public String getMethodAccessModifier(String strMethodName);
    public Hashtable<String,String> getMethodParameters(String strMethodName );
    public boolean payable(String strMethodName);
    public String getMethodReturnType(String strMethodName );
    public Step[] getMethodSteps(String strMethodName );
}
