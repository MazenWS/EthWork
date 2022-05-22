package Variables;

import Contracts.TheFile;
import Lines.Line;

import java.util.ArrayList;
import java.util.Iterator;

public class StateArray extends NamedArray implements StateVariable{

    Variables.AccessModifier accessModifier;
    String[] initialValue;
    int javaLine;


    //1// constructor for the dynamic storage array as a state variable
    public StateArray(Variable variable,Variables.AccessModifier accessModifier, String name){
        super(name, variable);
        this.accessModifier = accessModifier;


    }


    //2//constructor for the static size
    public StateArray(Variable variable, Variables.AccessModifier accessModifier, String name, int size){
        super(name,variable, size);
        this.accessModifier = accessModifier;

    }

    //3// static size with initial values
    public StateArray(Variable  variable, Variables.AccessModifier accessModifier, String name, int size, String[]  initialValue){
     super(name,variable, size);
        this.accessModifier = accessModifier;
        this.initialValue= initialValue;

    }

    //4// initial values only
    public StateArray(Variable  variable, Variables.AccessModifier accessModifier, String name, String[]  initialValue){
        super(name,variable);
        this.accessModifier = accessModifier;
        this.initialValue= initialValue;

    }
    //1// constructor for the dynamic storage array as a state variable
    public StateArray(Variable variable, String name){
        super(name, variable);
        this.accessModifier = AccessModifier.INTERNAL;


    }


    //2//constructor for the static size
    public StateArray(Variable variable, String name, int size){
        super(name,variable, size);
        this.accessModifier = AccessModifier.INTERNAL;

    }

    //3// static size with initial values
    public StateArray(Variable  variable ,String name, int size, String[]  initialValue){
        super(name,variable, size);
        this.accessModifier = AccessModifier.INTERNAL;
        this.initialValue= initialValue;

    }

    //4// initial values only
    public StateArray(Variable  variable,  String name, String[]  initialValue){
        super(name,variable);
        this.accessModifier = AccessModifier.INTERNAL;
        this.initialValue= initialValue;

    }
    public String write(){
        String res  = "";
        res+= arrayType.write();
        res+=size >0 ?"["+size+"]" : "[]";
        res+= " "+ accessModifier.name().toLowerCase()+" ";
        res+= name;


        if(initialValue!=null){

            res+= " = [";

            for (String value:
            initialValue ) { res+=value;
                res+=",";

        }
            res=res.substring(0, res.length() - 1);
            res+= "]";

        }
        res+=";";
        int solLine = ++TheFile.solidityCount;
        TheFile.lineMap.addLine(new Line(javaLine,"State",solLine,solLine));
        return res;

    }
    public static void main(String[] main){
       ArrayList values= new ArrayList<String>( );
       values.add ("1");
       values.add("2");
        //StateArray a = new StateArray( new VariableInteger(false,64), AccessModifier.PUBLIC,"arr",values);
        //System.out.println(a.write());
    }

    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }
}
