import Methods.*;
import Variables.*;
import Variables.AccessModifier;

import java.util.ArrayList;

public class Contract {

    String contractName;
    ArrayList<Method> methods;
    ArrayList<Struct> structs;
    ArrayList<myEnum> enums;
    ArrayList<StateVariable> stateVars;
    ArrayList<Event> events;
    Constructor constructor;
    ReceiveFunction receive;

    public Contract(String contractName){
        this.contractName = contractName;
        methods = new ArrayList<Method>();
        structs = new ArrayList<Struct>();
        enums = new ArrayList<myEnum>();
        stateVars = new ArrayList<StateVariable>();
        events = new ArrayList<Event>();
    }

    public void addMethod(Method method){
        methods.add(method);
    }

    public void addConstructor(Constructor constructor) throws Exception {

        if(this.constructor != null){
            throw new Exception("A Constructor is Already Added");
        }
        this.constructor = constructor;
    }

    public void addStruct(Struct struct){
        structs.add(struct);
    }

    public void addEnum(myEnum enumm){
        enums.add(enumm);
    }

    public void addStateVariable(StateVariable var){
        stateVars.add(var);
    }

    public void addEvent(Event event){
        events.add(event);
    }

    public void addReceiveFunction(ReceiveFunction receive) throws Exception {
        if(this.receive != null){
            throw new Exception("A receive Function is already Added");
        }
        this.receive = receive;
    }

    public String writeContract() throws Exception {
        String res = "contract "+contractName + " {\n\n\n";
        if(! structs.isEmpty()) {
            for (Struct struct : structs) {
                res += struct.write() + "\n\n";
            }
            res += "\n";
        }

        if(! enums.isEmpty()) {
            for (myEnum enumm : enums) {
                res += enumm.write() + "\n";
            }
            res += "\n";
        }

        if(!stateVars.isEmpty()) {
            for (StateVariable state : stateVars) {
                res += state.write() + "\n";
            }
        }

        if(! events.isEmpty()) {
            for(Event event : events){
                res += event.write() + "\n";
            }
            res += "\n";
        }

        if(constructor != null){
            res += constructor.write()+ "\n\n";
        }

        if (!methods.isEmpty()) {
            for (Method method : methods) {
                res += method.write() + "\n\n";
            }
        }

        if(receive != null){
            res += receive.write()+"\n";
        }
        res += "}";

        return res;
    }

    public static void main(String[] args){
        Contract c = new Contract("TwoStepOwnable");

        try {

            //address
            c.addStateVariable(new StateAddress("_owner", false, AccessModifier.PRIVATE));
            c.addStateVariable(new StateAddress("_newPotentialOwner", false, AccessModifier.PRIVATE));
            c.addEvent();
            c.addEnum(new myEnum("directions", new String[]{"RIGHT", "LEFT", "UP", "Down"}));

            c.addStruct(new Struct("chair", new NamedVariable[]{

                        new NamedBool("high"),
                        new NamedAddress("add111", true),

                        new NamedMapping("mappy", new VariableInteger(true, 6), new VariableEnum("directions"))
                        , new NamedBytes("byt", 15)
                        , new NamedEnum("directions", "TheThe")
                        , new NamedString("typ"),
                        new NamedInteger("legs", false, 7)
            }));

                    c.addStruct(new Struct("owner", new NamedVariable[]{
                            new NamedInteger("age", true, 7),
                            new NamedAddress("paying", false),
                            new NamedArray("arr22", new VariableString(), 3),
                            new NamedArray("chairss", new VariableStruct("chair"))}));



            //array

            c.addStateVariable(new StateArray(new VariableMapping(new VariableAddress(false), new VariableStruct("chair")), AccessModifier.INTERNAL, "complexArray"));

            c.addStateVariable(new StateArray(new VariableInteger(false, 5), AccessModifier.PUBLIC, "initialised", new String[]{"1", "2", "3"}));
            //address
            c.addStateVariable(new StateAddress("owner", true, AccessModifier.PRIVATE));
            //bool
            c.addStateVariable(new StateBool("alive", AccessModifier.PRIVATE));
            c.addStateVariable(new StateBool("good", AccessModifier.PUBLIC, false));
            //bytes
            c.addStateVariable(new StateBytes("buyers", 2, AccessModifier.INTERNAL, "0x0AFF"));
            c.addStateVariable(new StateBytes("nonBuyers", 8, AccessModifier.PRIVATE));
            //enum
            c.addStateVariable(new StateEnum("directions", "dir2", AccessModifier.INTERNAL));
            c.addStateVariable(new StateEnum("directions", "dir1", AccessModifier.INTERNAL, "directions.UP"));

            //int
            c.addStateVariable(new StateInteger("yee", false, AccessModifier.PUBLIC, 7));
            c.addStateVariable(new StateInteger("count", true, AccessModifier.PUBLIC, 4, 55));

            //string
            c.addStateVariable(new StateString("firstName", "alia", AccessModifier.PRIVATE));
            c.addStateVariable(new StateString("LastName", AccessModifier.PRIVATE));
            //struct
            c.addStateVariable(new StateStruct("chair", "cc1", AccessModifier.INTERNAL));

                //mapping
            c.addStateVariable(new StateMapping(new VariableBool(),
                    new VariableBytes(15), "mapp1", AccessModifier.PUBLIC
            ));
            Method m = null;

                m = new Method("m1", new ParameterVariable[]{new ParameterAddress("add", false), new ParameterAddress("add11", true), new ParameterBool("right"), new ParameterString("name", DataLocation.MEMORY), new ParameterBytes("ages", 3), new ParameterInteger("height", false, 3), new ParameterInteger("weight", true, 3), new ParameterMapping("student", new VariableBytes(5), new VariableArray(new VariableArray(new VariableBool())), DataLocation.STORAGE), new ParameterStruct("chair", "mychair", DataLocation.MEMORY), new ParameterEnum("directions", "varenum")}, Methods.AccessModifier.PUBLIC, Type.PURE,
                        null);

            m.addSteps(new LocalBool("bright", "false"));
            c.addMethod(m);

                System.out.println(c.writeContract());

            }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
