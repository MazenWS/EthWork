import java.util.Hashtable;

public class Struct {
    String name;
    AccessModifier accessModifier;
    Variable[] vars;

        public Struct(String name,AccessModifier accessModifier,  Variable[] vars) {
            this.name=name;
            this.accessModifier=accessModifier;
            this.vars = vars;
        }
}
