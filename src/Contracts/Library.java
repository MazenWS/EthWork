package Contracts;


import Lines.LineCounter;
import Methods.*;
import Variables.StateVariable;

import java.util.ArrayList;

//Library functions can be called directly if they do not modify the state.
//That means pure or view functions only can be called from outside the library.
//Library can not be destroyed as it is assumed to be stateless.
//A Library cannot have state variables.
//A Library cannot inherit any element.
//A Library cannot be inherited.
//Library may define and use modifiers, but it cannot export them.
//They can’t hold ethers (so can’t have a fallback function)
//Doesn’t allow payable functions (since they can’t hold ethers)

public class Library extends TypeContract{
    ArrayList<Event> events;
    ArrayList<Modifier> modifiers;

public Library(String contractName){
    this.contractName = contractName;
    methods = new ArrayList<Method>();
    structs = new ArrayList<Struct>();
    enums = new ArrayList<myEnum>();
    events = new ArrayList<Event>();
    modifiers= new ArrayList<Modifier>();

}
    public void addModifier( Modifier modifier){
        modifiers.add(modifier);
        modifier.setJavaLine(LineCounter.getLine());
    }
    public void addEvent(Event event){
        events.add(event);
        event.setJavaLine(LineCounter.getLine());
    }
    public String write() throws Exception {
        String res = "library "+contractName ;
        res+= super.write();
        if(! events.isEmpty()) {
            for(Event event : events){
                res += event.write() + "\n";
            }
            res += "\n";
            TheFile.solidityCount++;
        }
        if(! modifiers.isEmpty()) {
            for(Modifier mod : modifiers) {
                res += mod.write() + "\n\n";
                TheFile.solidityCount++;
            }
        }
        res += "}";
        TheFile.solidityCount++;
        return res;
    }
}
