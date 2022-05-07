package Steps;

public class ArrayPush implements Step {
    String arrName;
    String pushValue;

    public ArrayPush(String arrName, String pushValue){
        this.arrName = arrName;
        this.pushValue = pushValue;
    }


    @Override
    public String write() throws Exception {
        return arrName+".push("+pushValue+");";
    }
}
