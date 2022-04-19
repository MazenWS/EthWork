package Variables;



import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class Struct extends Variable {
    myString name;
    Variable[] vars;
    Hashtable<String,String> Enums;


        public Struct(myString name, Variable[] vars) {
            this.name=name;
            this.vars= vars;

        }
        public void addEnum (myEnum Enum, String member, String name){
            Enums.put(member, name);


        }


}
