package Variables;

import Methods.myEnum;

public class StateEnum extends NamedEnum implements StateVariable {

    AccessModifier accessModifier;
     String initialValue;
     //uninitialised
    public StateEnum (String theEnum, String name, AccessModifier accessModifier) throws Exception {
        super(theEnum, name);
        this.accessModifier = accessModifier;

    }
    //initialised
    public StateEnum (String theEnum, String name, AccessModifier accessModifier, String initialValue) throws Exception {
        super(theEnum, name);
        this.accessModifier = accessModifier;
        this.initialValue= initialValue;
    }

    public StateEnum (String theEnum, String name) throws Exception {
        super(theEnum, name);
        this.accessModifier = AccessModifier.INTERNAL;

    }
    //initialised
    public StateEnum (String theEnum, String name, String initialValue) throws Exception {
        super(theEnum, name);
        this.accessModifier = AccessModifier.INTERNAL;
        this.initialValue= initialValue;
    }

    public String write(){
        String res = super.write();
        String[] var = res.split(" ");
        res= String.join(" ",var[0], accessModifier.name().toLowerCase(),var[1]);
        if (initialValue!=null){
            res+= " = ";
            res+= initialValue;

        }
        res+=";";
        return res;
    }

    public static void main(String[] args) throws Exception {
        myEnum mmmm = new myEnum("student",new String[]{"ahmed","ali","alaa"});
        StateEnum bool = new StateEnum("student","enum1",AccessModifier.PUBLIC);
        StateEnum bool2 = new StateEnum("student","bool2",AccessModifier.PRIVATE, "student.ahmed");
        System.out.println(bool.write());
        System.out.println(bool2.write());
    }

}
