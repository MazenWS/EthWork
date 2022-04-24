package Variables;


public class ParameterArray extends NamedArray implements ParameterVariable {
    String name;
    DataLocation dataLocation;

   //1//dynamic size
    public ParameterArray(String name,Variable variable, DataLocation dataLocation){
        super(name, variable);
        this.dataLocation= dataLocation;
    }


    //2// static size
    public ParameterArray(Variable variable, String name, int size){
        super(name,variable,size);
    }

    @Override
    public String write(){
        String res = super.write();
        String[] var = res.split(" ");
        return String.join(" ",var[0], dataLocation.name().toLowerCase(),var[1]);
    }

    public static void main(String[] main){
        ParameterArray a = new ParameterArray("arr", new VariableInteger(false,64),DataLocation.MEMORY);
        System.out.println(a.write());
    }

}


