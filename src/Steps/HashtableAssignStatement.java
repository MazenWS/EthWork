package Steps;

public class HashtableAssignStatement extends Step{
    String hashtableName;
    String insertTokey;
    String statement;

    //hashtable[key] = a+b
    public HashtableAssignStatement(String hashtableName, String insertTokey, String statement){
        this.hashtableName = hashtableName;
        this.insertTokey = insertTokey;
        this.statement = statement;
    }

    //hashtable[key] = sum(a,b)
    public HashtableAssignStatement(String hashtableName, String insertTokey, FunctionStatement function){
        this.hashtableName = hashtableName;
        this.insertTokey = insertTokey;
        this.statement = function.write();
    }
    @Override
    public String write() throws Exception{
        String res = hashtableName+"["+insertTokey+"] = ";
        if(statement.contains(".get")){
            String[] get = statement.split(".get");
            get[1] = get[1].substring(1,get[1].length()-1);
            statement = get[0]+"["+get[1]+"]";
        }
        res += statement +";";
        return res;
    }
}
