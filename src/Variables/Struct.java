package Variables;



import java.util.ArrayList;
import java.util.Hashtable;


public class Struct {
    String name;
    NamedVariable[] vars;
    static  ArrayList<String> structNames;

        public Struct(String name, NamedVariable[] vars) {
            this.name=name;
            this.vars= vars;
            structNames.add(name);



        }




        }



