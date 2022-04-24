package Variables;
//for local variable let it return parameterVar---> the uinitialised version of each
public class LocalString extends ParameterString implements LocalVariable{
    String initialValue;




    public LocalString(String name, String initialValue){
        super(name);
        this. initialValue= initialValue;

    }
}
