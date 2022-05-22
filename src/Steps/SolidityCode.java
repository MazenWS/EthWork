package Steps;

public class SolidityCode implements Step{

    String code;
    int javaLine;

    public SolidityCode(String code){
        this.code = code;
    }


    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }

    @Override
    public String write() throws Exception {
        return code;
    }
}
