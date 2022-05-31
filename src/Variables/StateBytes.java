package Variables;

import Contracts.TheFile;
import Lines.Line;

public class StateBytes extends NamedBytes implements StateVariable{

    String initialValueInHex;
    AccessModifier accessModifier;
    boolean constant;
    boolean immutable;
    int javaLine;

    //2.1// uninitialized
    public StateBytes(String name, int length, AccessModifier accessModifier ) throws Exception {
        super(name,length);
        this. accessModifier= accessModifier;
    }
    //2.2// initialized
    public StateBytes(String name, int length, AccessModifier accessModifier, String initialValueInHex) throws Exception {
        super(name,length);
        this. accessModifier= accessModifier;
        this.initialValueInHex = initialValueInHex;
    }
    //if the user did not specify an AccessModifier then it's internal
    //2.1// uninitialized
    public StateBytes(String name, int length) throws Exception {
        super(name,length);
        this. accessModifier= AccessModifier.INTERNAL;
    }
    //2.2// initialized
    public StateBytes(String name, int length, String initialValueInHex) throws Exception {
        super(name,length);
        this. accessModifier=AccessModifier.INTERNAL;
        this.initialValueInHex = initialValueInHex;
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
        if ( initialValueInHex!= null){
            res+= " = ";
            res+= initialValueInHex ;

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
