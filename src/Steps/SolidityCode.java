package Steps;

public class SolidityCode implements Step{

    String code;

    public SolidityCode(String code){
        this.code = code;
    }

    @Override
    public String write() throws Exception {
        return code;
    }
}
