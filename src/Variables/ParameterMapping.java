package Variables;

import Steps.Step;

public class ParameterMapping  extends NamedMapping implements ParameterVariable{

DataLocation dataLocation;
    public ParameterMapping (String name, Variable keyType, Variable valueType, DataLocation dataLocation){
        super(name ,keyType, valueType);
        this.dataLocation= dataLocation;
    }

    @Override
    public String write(){
        String res = super.write();
        int lastSpace = res.lastIndexOf(' ');
        String type = res.substring(0,lastSpace);
        String name = res.substring(lastSpace+1);
        return String.join(" ",type, dataLocation.name().toLowerCase(),name);
    }

    public static void main(String[] args){
        ParameterMapping p = new ParameterMapping("map",new VariableAddress(false),new VariableMapping(new VariableString(),new VariableBool()),DataLocation.STORAGE);
        System.out.println(p.write());
    }
}
