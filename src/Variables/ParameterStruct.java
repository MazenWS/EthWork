package Variables;

public class ParameterStruct extends NamedStruct implements ParameterVariable{

    DataLocation dataLocation;
    public ParameterStruct(String theStruct,String name, DataLocation  dataLocation) throws Exception {
        super(theStruct, name);
        this.dataLocation =dataLocation;
    }
}
