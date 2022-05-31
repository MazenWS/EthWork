package Variables;

import Contracts.TheFile;
import Lines.Line;

public class StateMapping extends NamedMapping implements StateVariable{
    AccessModifier accessModifier;
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
        res= String.join(" ",var[0], accessModifier.name().toLowerCase(),var[1]);
        res+=";";

        int solLine = TheFile.solidityCount++;
        TheFile.lineMap.addLine(new Line(javaLine,"State",solLine,solLine));
        return res;
    }

    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }
}
