package Variables;

public class LocalStruct extends ParameterStruct implements LocalVariable {
   String initialValue;


    public LocalStruct(String theStruct,String name, DataLocation  dataLocation, String initialValue) throws Exception {
        super(theStruct,name,dataLocation);
        this.name = name;
        this.dataLocation =dataLocation;
        this.initialValue= initialValue;
    }
}
