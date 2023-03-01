package Variables;

import Contracts.TheFile;
import Lines.Line;

public class StateInteger extends NamedInteger implements  StateVariable {

    AccessModifier accessModifier;
    int initialValue;
    boolean initialised;
    boolean constant;
    boolean immutable;
    int javaLine;

    //1// uninitialised
    public StateInteger ( String name, boolean signed, int length_powerOfTwo, AccessModifier accessModifier) throws Exception {
        super(name,signed, length_powerOfTwo);
        this.accessModifier= accessModifier;
        this.initialised= false;
    }
    //2// initialised
    public StateInteger ( String name, boolean signed, int length_powerOfTwo, int initialValue, AccessModifier accessModifier) throws Exception {
        super(name ,signed, length_powerOfTwo);
        this.accessModifier= accessModifier;
        this.initialValue = initialValue;
        this.initialised= true;
    }

    //if the user did not specify an AccessModifier then it's internal
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
        //res= String.join(" ",var[0], accessModifier.name().toLowerCase(),var[1]);
        res = var[0] + " "+ accessModifier.name().toLowerCase()+" ";
        if(immutable){
            res += "immutable ";
        }
        else if(constant){
            res += "constant ";
        }
        res += var[1];
        if (initialised){
            res+= " = ";
            res+= initialValue ;

        }
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
