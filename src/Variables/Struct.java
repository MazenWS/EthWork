package Variables;

import java.util.ArrayList;


public class Struct {
    String name;
    NamedVariable[] vars;
    static  ArrayList<String> structNames;

        public Struct(String name, NamedVariable[] vars) {
            this.name=name;
            this.vars= vars;
            structNames.add(name);
        }


        public String write() {
            String res = "struct "+name+" {\n";
            for(NamedVariable var : vars ){
                res += var.write()+";\n";
            }
            res += "}";
            return res;
        }
        }



