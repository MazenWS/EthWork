package Variables;

import Contracts.TheFile;
import Lines.Line;

public class StateInteger extends NamedInteger implements  StateVariable {

    AccessModifier accessModifier;
    int initialValue;
    boolean initialised;
    int javaLine;

    //1// uninitialised
    public StateInteger ( String name, boolean signed, AccessModifier accessModifier, int length_powerOfTwo) throws Exception {
        super(name,signed, length_powerOfTwo);
        this.accessModifier= accessModifier;
        this.initialised= false;
    }
    //2// initialised
    public StateInteger ( String name, boolean signed, AccessModifier accessModifier, int length_powerOfTwo, int initialValue) throws Exception {
        super(name ,signed, length_powerOfTwo);
        this.accessModifier= accessModifier;
        this.initialValue = initialValue;
        this.initialised= true;
    }

    //1// uninitialised
    public StateInteger ( String name, boolean signed, int length_powerOfTwo) throws Exception {
        super(name,signed, length_powerOfTwo);
        this.accessModifier= AccessModifier.INTERNAL;
        this.initialised= false;
    }
    //2// initialised
    public StateInteger ( String name, boolean signed, int length_powerOfTwo, int initialValue) throws Exception {
        super(name ,signed, length_powerOfTwo);
        this.accessModifier= AccessModifier.INTERNAL;
        this.initialValue = initialValue;
        this.initialised= true;
    }
    public String write(){
        String res = super.write();
        String[] var = res.split(" ");
        res= String.join(" ",var[0], accessModifier.name().toLowerCase(),var[1]);
        if (initialised){
            res+= " = ";
            res+= initialValue ;

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
