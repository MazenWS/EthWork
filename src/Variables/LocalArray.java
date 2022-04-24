package Variables;

import java.util.ArrayList;

public class LocalArray extends ParameterArray implements LocalVariable{
    ArrayList<String> initialValue;


    public LocalArray(Variable  variable, String name, int size, ArrayList<String>  initialValue){
        super(variable, name, size);
        this.initialValue= initialValue;

    }

}
