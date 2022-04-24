package Variables;

import java.util.ArrayList;

public class LocalArray extends ParameterArray implements LocalVariable{
    ArrayList<String> initialValue;


    public LocalArray(Variable  variable, String name, int size, DataLocation dataLocation,ArrayList<String>  initialValue){
        super( name,variable, dataLocation, size);
        this.initialValue= initialValue;

    }

    public LocalArray(Variable  variable, String name,  DataLocation dataLocation,ArrayList<String>  initialValue){
        super( name,variable, dataLocation );
        this.initialValue= initialValue;

    }

}
