package Steps;


public class Require extends Step{
    Condition condition;
    String messageToThrow  = null;

    //require(bool statement,if false throw message)
    public Require(Condition condition, String messageToThrow){
        this.condition = condition;
        this.messageToThrow = messageToThrow;
    }

    //require(condition) if false throws error
    public Require(Condition condition){
        this.condition = condition;
    }

    @Override
    public String write() throws Exception {
        String res = "";
        res += "require("+condition.write();
        res += messageToThrow != null ? ", "+messageToThrow+");" : ");";
        return res;
    }

    public static void main(String[] args) throws Exception {
    }
}
