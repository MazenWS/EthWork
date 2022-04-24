package Variables;

import java.util.ArrayList;

public class ParameterArray extends NamedArray implements ParameterVariable {
    String name;
    DataLocation dataLocation;

   //1//dynamic size
    public ParameterArray(String name,Variable variable, DataLocation dataLocation){
        super(name, variable);
        this.dataLocation= dataLocation;
    }


    //2// static size
    public ParameterArray(Variable variable, String name, int size){
        super(name,variable,size);



    }

}


