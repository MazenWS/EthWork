package Methods;

import Steps.Step;

public class Method {
    String name;
    Variable[] parameters;
    AccessModifier accessModifier;
    Type accessType;
    Variable[] returnTypes;
    Step[] steps;


    public Method(String name, Variable[] parameters, AccessModifier accessModifier, Type accessType,
                  Variable[] returnTypes, Step[] steps){
        this.name= name;
        this.parameters= parameters;
        this.accessModifier= accessModifier;
        this.accessType= accessType;
        this.returnTypes= returnTypes;
        this.steps= steps;
    }
}
