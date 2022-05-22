package Variables;

import Contracts.TheFile;
import Lines.Line;

public class StateStruct  extends NamedStruct implements StateVariable{

    AccessModifier  accessModifier;
    String[] initialValue;
    int javaLine;

    public StateStruct(String theStruct,String name, AccessModifier  accessModifier,String[] initialValue) throws Exception {
        super(theStruct, name);
        this.accessModifier = accessModifier;
        this.initialValue= initialValue;
    }
    public StateStruct(String theStruct,String name, AccessModifier  accessModifier) throws Exception {
        super(theStruct,name);
        this.accessModifier = accessModifier;
    }

    public StateStruct(String theStruct,String name, String[] initialValue) throws Exception {
        super(theStruct, name);
        this.accessModifier =AccessModifier.INTERNAL;
        this.initialValue= initialValue;
    }
    public StateStruct(String theStruct,String name) throws Exception {
        super(theStruct,name);
        this.accessModifier = AccessModifier.INTERNAL;
    }

    public String write(){
        String res = super.write();
        String[] var = res.split(" ");
        res= String.join(" ",var[0], accessModifier.name().toLowerCase(),var[1]);
        if (initialValue!= null){
            res+= " = ";
            res+= theStruct +"( ";
            for (String s: initialValue ) {
                res+=s;
                res+= ",";

            }
            res=res.substring(0, res.length() - 1);
            res+= ");";
        }
        res+=";";
        int solLine = ++TheFile.solidityCount;
        TheFile.lineMap.addLine(new Line(javaLine,"State",solLine,solLine));
        return res;
    }

    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }
}
