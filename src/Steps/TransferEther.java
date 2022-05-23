package Steps;

import Contracts.TheFile;
import Lines.Line;

public class TransferEther implements Step{
    String toAddress;
    String value;
    int javaLine;

    //(bool success,) = address.call{value: value}("")
    public TransferEther(String toAddress, String value){
        this.toAddress = toAddress;
        this.value = value;
    }

    @Override
    public void setJavaLine(int javaLine) {
        this.javaLine = javaLine;
    }

    @Override
    public String write() {
        TheFile.lineMap.addLine(new Line(javaLine,"Step",TheFile.solidityCount,TheFile.solidityCount));
        TheFile.solidityCount++;
        return "(bool success, ) = "+toAddress+".call{value: "+value+"}(\"\");";
    }

    public static void main(String[] args) throws Exception {
        Step p = new TransferEther("owner",Environment.MSG_VALUE);
        System.out.println(p.write());
    }
}
