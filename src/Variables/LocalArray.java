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

    @Override
    public String write(){
        String res = super.write();
        if(initialValue != null){
            if(size != null) {
                res += "[";
                for (String val : initialValue){
                    res += val+", ";
                }
                res += res.substring(0,res.length()-2) +"]";
            }
        }
        return res;
    }

    public static void main(String[] args){

    }

}
