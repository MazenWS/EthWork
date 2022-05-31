package Variables;

import Contracts.TheFile;
import Lines.Line;

public class StateMapping extends NamedMapping implements StateVariable{
    AccessModifier accessModifier;
    boolean constant;
    boolean immutable;
    int javaLine;


    public StateMapping (String name,Variable keyType, Variable valueType, AccessModifier accessModifier){
        super(name, keyType, valueType);
        this.accessModifier= accessModifier;
    }
    public StateMapping (String name ,Variable keyType, Variable valueType){
        super(name, keyType, valueType);
        this.accessModifier= AccessModifier.INTERNAL;
    }
    public String write(){
        String res = super.write();
        String[] var = res.split(" ");
        //res= String.join(" ",var[0], accessModifier.name().toLowerCase(),var[1]);
        res = var[0] + " "+ accessModifier.name().toLowerCase()+" ";
        if(immutable){
            res += "immutable ";
        }
        else if(constant){
            res += "constant ";
        }
        res += var[1];
        res+=";";

        int solLine = TheFile.solidityCount++;
        TheFile.lineMap.addLine(new Line(javaLine,"State",solLine,solLine));
        return res;
    }

    public void isConstant() {
        constant = true;
    }

    public void isImmutable() {
        immutable = true;
    }


    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }
}
