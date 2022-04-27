package Variables;


import Steps.Step;

public class ParameterArray extends NamedArray implements ParameterVariable  {
    DataLocation dataLocation;

   //1//dynamic size
    public ParameterArray(String name,Variable variable, DataLocation dataLocation){
        super(name, variable);
        this.dataLocation= dataLocation;
    }


    //2// static size
    public ParameterArray(String name,Variable variable, DataLocation dataLocation, int size){
        super(name,variable,size);
        this.dataLocation = dataLocation;
    }

    @Override
    public String write(){
        String res = super.write();
        int lastSpace = res.lastIndexOf(' ');
        String type = res.substring(0,lastSpace);
        String name = res.substring(lastSpace+1);
        return String.join(" ",type, dataLocation.name().toLowerCase(),name);
    }

    public static void main(String[] main){
        ParameterArray a = null;
        try {
            a = new ParameterArray("arr", new VariableInteger(false,3), DataLocation.MEMORY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(a.write());
    }

}


