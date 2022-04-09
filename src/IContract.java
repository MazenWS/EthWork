import java.util.Hashtable;

public interface IContract {
    //write the contract name
    public String getContractName();
    /* why the constructor is not an object that have steps, parameters and boolean payable?? */
    //write the steps for the constructor
    public Step[] createConstructor();
    //the parameters for the constructor
    public ParameterVar[] constructorParameters();
    //is the constructor payable or not
    public boolean payableConstructor();
    //add an address variable in the contract

    //add a struct variable in your contract
    public Struct createObject();
    public StateVariable[] getStateVariables();

    public String[] getMethodNames();
    public String getMethodAccessModifier(String strMethodName);
    public Variable[] getMethodParameters(String strMethodName );
    public boolean payable(String strMethodName);
    public  Variable[] getMethodReturnType(String strMethodName );
    public Step[] getMethodSteps(String strMethodName );
}
