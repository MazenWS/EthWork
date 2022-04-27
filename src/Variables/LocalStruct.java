package Variables;

import Steps.Step;

public class LocalStruct extends ParameterStruct implements Step {
   String[] initialValue;
   String initValue;


    public LocalStruct(String theStruct,String name, DataLocation  dataLocation, String[] initialValue) throws Exception {
        super(theStruct,name,dataLocation);
        this.name = name;
        this.dataLocation =dataLocation;
        this.initialValue= initialValue;
    }

    public LocalStruct(String theStruct,String name, DataLocation  dataLocation, String initialValue) throws Exception {
        super(theStruct,name,dataLocation);
        this.name = name;
        this.dataLocation =dataLocation;
        this.initValue= initialValue;
    }

    public String write() {
        String res = super.write();
        if( initValue != null){
            res += initValue;
        }
        if(initialValue != null){
            res += theStruct+"(";
            for(String input : initialValue){
                res += input+", ";
            }
            res = res.substring(0,res.length()-2)+")";
        }
        return res+";";
    }
}
