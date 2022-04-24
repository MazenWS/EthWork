package Variables;

public class NamedString  extends VariableString implements NamedVariable{

    String name;
    public NamedString(String name){

        this.name= name;
    }

    @Override
    public String write(){
        String res = super.write()+" ";
        return res+name;
    }
}
