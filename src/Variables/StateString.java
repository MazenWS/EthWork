package Variables;

import Contracts.TheFile;
import Lines.Line;

public class StateString extends NamedString implements StateVariable{

AccessModifier accessModifier;
String initialValue;
int javaLine;


    public StateString(String name, AccessModifier accessmodifier){
        super(name);
        this.accessModifier= accessmodifier;
    }
    //2.2// initialised
    public StateString(String name, String initialValue, AccessModifier accessmodifier){
        super(name);
        this. initialValue= initialValue;
        this. accessModifier = accessmodifier;

    }

    public StateString(String name ){
        super(name);
        this.accessModifier= AccessModifier.INTERNAL;
    }
    //2.2// initialised
    public StateString(String name, String initialValue ){
        super(name);
        this. initialValue= initialValue;
        this. accessModifier = AccessModifier.INTERNAL;

    }

    public String write(){
        String res = super.write();
        String[] var = res.split(" ");
        res= String.join(" ",var[0], accessModifier.name().toLowerCase(),var[1]);
        if (initialValue!= null){
            res+= " = ";
            res+= " \""+initialValue+" \"" ;

        }
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

