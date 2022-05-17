package Variables;

import Steps.Step;

public class LocalStruct extends ParameterStruct implements Step {
   String[] initialValue;
   String initValue;

    public LocalStruct(String theStruct,String name, DataLocation  dataLocation, String[] initialValue)  {
        super(theStruct,name,dataLocation);
        this.name = name;
        this.initialValue= initialValue;
    }
    public LocalStruct(String theStruct,String name, DataLocation  dataLocation, String initValue)  {
        super(theStruct,name,dataLocation);
        this.initValue= initValue;
    }
    public LocalStruct(String theStruct,String name, DataLocation  dataLocation )  {
        super(theStruct,name,dataLocation);

    }



    public String write() {
        String res = super.write();
        if (initValue!= null){
            res+= " = "+initValue;
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
