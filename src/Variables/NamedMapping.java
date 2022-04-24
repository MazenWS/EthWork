package Variables;

public class NamedMapping extends VariableMapping implements NamedVariable {
String name;

    public NamedMapping (String name, Variable keyType, Variable valueType ){
        super( keyType, valueType);
        this.name =name;
    }

    @Override
    public String write(){
        String res = super.write()+" ";
        return res+name;
    }
}
