import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Setup {

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

    private static boolean checkGeth() throws IOException {
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
        if(!res)
            System.out.println("Please install Geth");
        return res;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(checkNpm());
        System.out.println(checkGeth());
    }
}
