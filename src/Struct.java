import java.util.Hashtable;

public class Struct {
        String name;
        Hashtable<String, String> vars;

        public Struct(String name, Hashtable<String, String> vars) {
            this.name = name;
            this.vars = vars;
        }
}
