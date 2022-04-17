package Steps;

public class TransferEther extends Step{
    String toAddress;
    String value;

    //(bool success,) = address.call{value: value}("")
    public TransferEther(String toAddress, String value){
        this.toAddress = toAddress;
        this.value = value;
    }
    @Override
    public String write() {
        return "(bool success, ) = "+toAddress+".call{value: "+value+"}(\"\");";
    }

    public static void main(String[] args) throws Exception {
        Step p = new TransferEther("owner",Environment.MSG_VALUE);
        System.out.println(p.write());
    }
}
