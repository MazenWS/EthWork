package Variables;

import Steps.Step;

import java.util.ArrayList;

public class LocalArray extends ParameterArray implements Step {
    ArrayList<String> initialValue;


    public LocalArray(Variable  variable, String name,DataLocation dataLocation, int size, ArrayList<String>  initialValue){
        super(name, variable,dataLocation, size);
        this.initialValue= initialValue;
    }

    public LocalArray(Variable  variable, String name,  DataLocation dataLocation,ArrayList<String>  initialValue){
        super( name,variable, dataLocation );
        this.initialValue= initialValue;

    }
    public LocalArray(Variable  variable, String name,DataLocation dataLocation, int size ){
        super(name, variable,dataLocation, size);

    }

    public LocalArray(Variable  variable, String name,  DataLocation dataLocation ){
        super( name,variable, dataLocation );


    }
    @Override
    public String write(){
        String res = super.write();
        if(initialValue != null){
            if(size > 0) {
                res += "[";
                for (String val : initialValue){
                    res += val+", ";
                }
                res += res.substring(0,res.length()-2) +"]";
            }
        }
        res+=";";
        return res;
    }

    public static void main(String[] args){

    }

}
