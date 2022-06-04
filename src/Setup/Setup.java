package Setup;

import Contracts.TheFile;
import Lines.LinesArrangment;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Setup {
    static String wallet;
    static String walletPK;
    static String dir = "C:\\EthereumDB";
    static boolean running;
    static String password;

    //1 it calls checkGeth() which checks that geth is downloaded on the pc and if not it will download it?
    //2 calls checkAccounts() ??
    //3 runNode(Network,syncMode) -->run a Node choosing a network and a syncmode
    //4 check if geth is running and if it runs it will get out of the loop to go to step 5
    //5 for each contract writescript and runit --> run and compile??
    //6

    //EDIT: Removed the input of all contracts written and add the names directly with a getter
    public static void deploy(String Network,String syncMode) throws Exception {
        checkGeth();
        checkAccounts();
        runNode(Network,syncMode);
        while(true) {
            if(checkIfGethIsRunning()==1){
                break;
            }
            TimeUnit.SECONDS.sleep(20);
        }
        for(String contract: TheFile.getContractNames()) {
            writeScript(contract);
            runScript();
        }
    }

   //checks that npm is installed and if not it installs it
    private static boolean checkNpm() throws Exception {
        ProcessBuilder builder = null;

        switch (CheckOs.CheckOS()) {
            case WINDOWS:
                builder = new ProcessBuilder("cmd.exe", "/c", "npm -v");
            break;
            //whatever we write in linux
            case LINUX:
                builder = new ProcessBuilder();break;
            case MAC:
                //whatever we write in Mac
                builder =new ProcessBuilder(); break;
        }

        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        boolean res = false;
        line = r.readLine();
        String[] version = line.split("\\.");
        if(Integer.parseInt(version[0]) >= 6)
            res=true;
        System.out.println(line);
        if(!res)
            System.out.println("Please install Node");
        return res;
    }

    private static boolean checkGeth() throws Exception {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe","/c","geth version");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        boolean res = false;
        while(true) {
            line = r.readLine();
            if(line==null)
                break;
            if(line.equals("Geth"))
                res = true;
            System.out.println(line);
        }
        if(!res) {
            downloadGeth();
            throw new Exception("Please install Geth");
        }
        else {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Path for Directory of Ethereum DB(choose any if first run) :");
            dir = sc.nextLine();
        }
        return res;
    }

    private static void downloadGeth() throws IOException, URISyntaxException {
        String url = "https://gethstore.blob.core.windows.net/builds/geth-windows-amd64-1.10.17-25c9b49f.exe";
        Desktop.getDesktop().browse(new URL(url).toURI());
    }

    private static String getCurrentDirectory() {
        return System.getProperty("user.dir");
    }

    private static void checkAccounts() throws IOException {
        String command = "geth account list --datadir "+dir;
        ArrayList<String> accounts = new ArrayList<String>();
        ProcessBuilder builder = new ProcessBuilder("cmd.exe","/c",command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        boolean res = false;
        while(true) {
            line = r.readLine();
            if(line==null)
                break;
            if(line.contains("Account")){
                res=true;
                System.out.println(line);
                accounts.add(line);
            }
        }
        if(res){
            int index = 0;
            while(true) {
                System.out.println("Please Enter Account Number from Above List (-1 if none):");
                Scanner sc = new Scanner(System.in);
                index = sc.nextInt();
                if (index < -1 || index >= accounts.size()) {
                    System.out.println("wrong input");
                }
                else
                    break;
            }
            if(index == -1){
                importAccountToNode();
                return;
            }
            String acc = accounts.get(index);
            String[] spl = acc.split("--");
            wallet = spl[spl.length-1];
        }
    }

    private static void runNode(String network, String syncmode) throws IOException {
        try {

            String command = "geth --"+network +" --syncmode \""+syncmode+"\" -http --allow-insecure-unlock --datadir "+dir;
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);

            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) break;
                System.out.println(line);
                if(line.contains("Block synchronisation started")){
                    return;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int checkIfGethIsRunning() throws IOException {
        String path = "\\\\.\\pipe\\geth.ipc";
        String command = "geth attach "+path+" --exec eth.syncing";
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) break;
            if(line.contains("Fatal: Unable to attach to remote geth"))
                return -1;
            running = true;
            if(line.equals("false"))
                return 1;
            System.out.println(line);
        }
        return 0;
    }

    private static void closeNode() throws IOException {
        String command = "taskkill \\IM geth.exe \\F";
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) break;
            System.out.println(line);
        }
    }

    private static void importAccountToNode() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Wallet private key :");
        walletPK = sc.nextLine()+"\n";
        System.out.println("Enter Account Password :");
        password = sc.nextLine();
        File pkFile = new File("pk.txt");
        FileWriter myWriter = new FileWriter("pk.txt");
        myWriter.write(walletPK+"\n");
        myWriter.close();
        File passFile = new File("pass.txt");
        FileWriter myWriter2 = new FileWriter("pass.txt");
        myWriter2.write(password+"\n");
        myWriter2.close();
        String command = "geth account import --datadir \""+ dir +"\" --password \""+passFile.getAbsolutePath()+"\" \""+pkFile.getAbsolutePath()+"\"";
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) break;
            System.out.println(line);
        }
        pkFile.delete();
        passFile.delete();
    }

    private static void compileContract(String contractName) throws IOException {
        String command = "solcjs --bin "+contractName+".sol && solcjs --abi "+contractName+".sol";
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        boolean error = false;
        int errLine = 0;
        while (true) {
            line = r.readLine();
            if (line == null) break;
            error = true;
            String[] err = line.split(":");
            if(err.length == 3){
                errLine = LinesArrangment.getJavaLine(Integer.parseInt(err[1]));
            }
            System.out.println(line);
        }
        if(error){
            File f = new File(contractName+".sol");
            f.delete();
            System.out.println("Error in JavaFile Line: "+errLine);
        }
        else{
            System.out.println("Successfully compiled");
        }
    }

    private static void writeScript(String name) throws IOException {
        if(password == null) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Account Password :");
            password = sc.nextLine();
        }
        FileReader reader = new FileReader(name+"_sol_"+name+".bin");
        int i;
        String bin = "";
        while((i=reader.read())!=-1)
            bin += (char)i;
        reader.close();
        FileReader reader2 = new FileReader(name+"_sol_"+name+".abi");
        String abi = "";
        while((i=reader2.read())!=-1)
            abi += (char)i;
        reader2.close();
        try{
            String data = "personal.unlockAccount(eth.accounts[0],\""+password+"\",null)\n" +
                    "var contractInst = eth.contract("+abi+")\n" +
                    "var publisher = contractInst.new({\n" +
                    "data: '0x"+bin+"',\n" +
                    "arguments: [\n" +
                    "      ],\n" +
                    "from: eth.accounts[0], \n" +
                    "      gas: '4700000'\n" +
                    "})\n" +
                    "console.log(publisher.transactionHash)";
            OutputStream fileOut = new FileOutputStream(new File("deploy.js"));
            fileOut.write(data.getBytes(StandardCharsets.UTF_8));
            fileOut.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static void runScript() throws IOException, InterruptedException {
        String path = "\\\\.\\pipe\\geth.ipc";
        String command = "geth attach "+path+" --exec loadScript('deploy.js')";
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = r.readLine();
        TimeUnit.SECONDS.sleep(10);
        System.out.println("TxHash : " + line);
        getContractAddress(line);
    }

    private static boolean checkSolidity() throws IOException {
        String command = "solcjs --version";
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = r.readLine();
        System.out.println(line.charAt(0));
        if(line.charAt(0) == '0')
            return true;
        else
            return false;
    }

    private static void installSolidity() throws IOException {
        String command = "npm install -g solc";
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
    }

    private static void getContractAddress(String transactionHash) throws IOException, InterruptedException {
        String command = "geth attach \\\\.\\pipe\\geth.ipc --exec eth.getTransactionReceipt('"+transactionHash+"')";
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",command);
        builder.redirectErrorStream(true);
        while(true) {
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while((line = r.readLine()) != null){
                if(line.equals("null")){
                    System.out.println("Mining...");
                    TimeUnit.SECONDS.sleep(10);
                    break;
                }
                if(line.toLowerCase().contains("contractaddress")){
                    System.out.println(line);
                    return;
                }
            }
        }
    }

    public static void createContract(String contractName, String code) throws IOException {
        File contract = new File(contractName+".sol");
        FileWriter myWriter = new FileWriter(contractName+".sol");
        myWriter.write(code);
        myWriter.close();
        if(! checkSolidity()){
            installSolidity();;
        }
        compileContract(contractName);
    }

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
    }
}
