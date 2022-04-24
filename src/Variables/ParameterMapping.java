package Variables;

public class ParameterMapping  extends NamedMapping implements ParameterVariable{

DataLocation dataLocation;
    public ParameterMapping (String name, Variable keyType, Variable valueType, DataLocation dataLocation){
   super(name ,keyType, valueType);

        this.dataLocation= dataLocation;
    }
}
