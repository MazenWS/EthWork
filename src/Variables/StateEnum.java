package Variables;

import Contracts.TheFile;
import Lines.Line;
import Methods.myEnum;

public class StateEnum extends NamedEnum implements StateVariable {

    AccessModifier accessModifier;
     String initialValue;
    boolean constant;
    boolean immutable;
     int javaLine;
     //uninitialised
    public StateEnum ( String name,String theEnum, AccessModifier accessModifier) throws Exception {
        super(theEnum, name);
        this.accessModifier = accessModifier;

    }
    //initialised
    public StateEnum ( String name,String theEnum, AccessModifier accessModifier, String initialValue) throws Exception {
        super(theEnum, name);
        this.accessModifier = accessModifier;
        this.initialValue= initialValue;
    }
    //if the user did not specify an AccessModifier then it's internal

    public StateEnum ( String name,String theEnum) throws Exception {
        super(theEnum, name);
        this.accessModifier = AccessModifier.INTERNAL;

    }
    //initialised
    public StateEnum ( String name,String theEnum, String initialValue) throws Exception {
        super(theEnum, name);
        this.accessModifier = AccessModifier.INTERNAL;
        this.initialValue= initialValue;
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
        if (initialValue!=null){
            res+= " = ";
            res+= initialValue;

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


    public static void main(String[] args) throws Exception {
        myEnum mmmm = new myEnum("student",new String[]{"ahmed","ali","alaa"});
        StateEnum bool = new StateEnum("student","enum1",AccessModifier.PUBLIC);
        StateEnum bool2 = new StateEnum("student","bool2",AccessModifier.PRIVATE, "student.ahmed");
        System.out.println(bool.write());
        System.out.println(bool2.write());
    }

    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }
}
