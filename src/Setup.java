import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Setup {
    static String wallet;
    static String dir = "C:\\EthereumDB";
    static boolean running;

    private static boolean checkNpm() throws IOException {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe","/c","npm -v");
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

    private static boolean checkGeth() throws IOException, URISyntaxException {
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
            System.out.println("Please install Geth");
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

    private static void runNode() throws IOException {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Wallet public Address :");
            wallet = sc.nextLine();
            System.out.println("Enter Account Password :");
            String pass = sc.nextLine();
            File myObj = new File("pass.txt");
            FileWriter myWriter = new FileWriter("pass.txt");
            myWriter.write(pass+"\n");
            myWriter.close();
            String command = "geth --rinkeby --syncmode light -http --allow-insecure-unlock --datadir "+dir+" --unlock "+wallet+ " --password "+myObj;
            command = command.replace("light", "\"light\"");
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
                    myObj.delete();
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
        String command = "geth account import --datadir --password passwordFile <privateKeyFile>";
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

    private static void createProject(String path) throws IOException, InterruptedException {
        String command = "cd "+path+" && mkdir App";
        path += "\\App";
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
        p.waitFor();
        installTruffle(path);
    }

    private static void installTruffle(String path) throws IOException, InterruptedException {
        String command = "cd "+path+" && npm install truffle";
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
        p.waitFor();
        truffleInit(path);
    }

    private static void truffleInit(String path) throws IOException, InterruptedException {
        String command = "cd "+path+" && truffle init";
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

    private static void createContract(String name, String path) throws IOException, InterruptedException {
        String command = "cd "+path+" && truffle create contract "+name;
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

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        //checkIfGethIsRunning();
        //createProject(getCurrentDirectory());
        //createContract("C1","D:\\Projects Sem 7\\EthWork\\App");
        //downloadGeth();
        System.out.println(checkIfGethIsRunning());
    }
}
