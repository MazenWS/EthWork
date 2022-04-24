package Variables;

public class NamedAddress extends VariableAddress implements NamedVariable{
    String name;

    public NamedAddress(String name , boolean payable){
        super(payable);
        this.name = name;
    }

    @Override
    public String write(){
        String res = super.write()+" ";
        return res+name;
    }
}
