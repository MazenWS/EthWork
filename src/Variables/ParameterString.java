package Variables;

import Steps.Step;

public class ParameterString extends NamedString implements ParameterVariable {
DataLocation dataLocation;
    public ParameterString(String name, DataLocation dataLocation){
        super(name);
        this.dataLocation = dataLocation;
    }

    @Override
    public String write(){
        String res = super.write();
        String[] var = res.split(" ");
        res= String.join(" ",var[0], dataLocation.name().toLowerCase(),var[1]);
       return res;
    }
}
