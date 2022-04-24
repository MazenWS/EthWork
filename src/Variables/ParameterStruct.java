package Variables;

public class ParameterStruct extends NamedStruct implements ParameterVariable{

    DataLocation dataLocation;
    public ParameterStruct(String theStruct,String name, DataLocation  dataLocation) throws Exception {
        super(theStruct, name);
        this.dataLocation =dataLocation;
    }

    @Override
    public String write(){
        String res = super.write();
        String[] var = res.split(" ");
        return String.join(" ",var[0], dataLocation.name().toLowerCase(),var[1]);
    }
}
