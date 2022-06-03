package Variables;

import Contracts.TheFile;
import Lines.Line;

public class StateBool extends NamedBool implements StateVariable {

    AccessModifier accessModifier;
    String initialValue;
    boolean initialised;
    boolean constant;
    boolean immutable;
    int javaLine;


    //1// uninitialised
    public StateBool(String name, AccessModifier accessModifier ){
    super(name);
        this. accessModifier = accessModifier;
        this.initialised=false;

    }
    //2// init
    public StateBool(String name, AccessModifier accessModifier, String initialValue){
      super(name);
        this. accessModifier = accessModifier;
        this.initialValue= initialValue;
        this.initialised= true;
    }

//if the user did not specify an AccessModifier then it's internal

    //1// uninitialised
    public StateBool(String name){
        super(name);
        this. accessModifier = AccessModifier.INTERNAL;
        this.initialised=false;

    }
    //2// init
    public StateBool(String name, String initialValue){
        super(name);
        this. accessModifier = AccessModifier.INTERNAL;
        this.initialValue= initialValue;
        this.initialised= true;
    }

    public void isConstant() {
        constant = true;
    }

    public void isImmutable() {
        immutable = true;
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
             res+= initialValue;

         }
         res+=";";

        int solLine = TheFile.solidityCount++;
        TheFile.lineMap.addLine(new Line(javaLine,"State",solLine,solLine));
        return res;
    }

    public static void main(String[] args){
            StateBool bool = new StateBool("bool1",AccessModifier.PUBLIC);
            StateBool bool2 = new StateBool("bool2",AccessModifier.PRIVATE, "false");
            bool2.isImmutable();
            System.out.println(bool.write());
            System.out.println(bool2.write());
    }

    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }
}
