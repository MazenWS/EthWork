package Variables;

import Contracts.TheFile;
import Lines.Line;

import java.util.Locale;

public class StateAddress extends NamedAddress implements StateVariable{

    String initialValue;
    AccessModifier accessModifier;
    int javaLine;


    //1// uninitialised
    public StateAddress(String name, boolean payable, AccessModifier accessModifier){
        super(name,payable);
        this.accessModifier= accessModifier;

    }
    //2// initialized
    public StateAddress(String name, boolean payable, String initialValue, AccessModifier accessModifier){
        super(name,payable);
        this. initialValue= initialValue;
        this.accessModifier= accessModifier;

    }
    //1// uninitialised
    public StateAddress(String name, boolean payable){
        super(name,payable);
        this.accessModifier= AccessModifier.INTERNAL;

    }
    //2// initialized
    public StateAddress(String name, boolean payable, String initialValue){
        super(name,payable);
        this. initialValue= initialValue;
        this.accessModifier= AccessModifier.INTERNAL;

    }
    public String write(){
        String res ="address ";
        res+= payable? "payable ": "";
        res+= accessModifier.name().toLowerCase()+" ";
        res+= name;
        if (initialValue!= null){
            res+=" = ";
            res+= payable? "payable("+ initialValue+")": initialValue;
        }
        res+=" ;";

        int solLine = TheFile.solidityCount++;
        TheFile.lineMap.addLine(new Line(javaLine,"State",solLine,solLine));
        return res;
    }


    public static void main (String [] args){

    }


    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }
}
