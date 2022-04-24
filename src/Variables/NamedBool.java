package Variables;

public class NamedBool extends VariableBool implements NamedVariable {
    String name;
    public NamedBool (String name){
        this.name= name;
    }

    @Override
    public String write(){
        String res = super.write()+" ";
        return res+name;
    }
}
