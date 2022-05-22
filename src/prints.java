import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class prints {


    public static void main(String[] args) {

        BufferedReader objReader = null;
        try {
            String strCurrentLine;

            objReader = new BufferedReader(new FileReader(args[0]));
            boolean inMain = false;
            boolean newStatement = true;
            int printsAdded = 0;
            int lineCount = 0;

            while ((strCurrentLine = objReader.readLine()) != null) {

                lineCount++;
                if(inMain && newStatement && strCurrentLine != ""){
                    System.out.println("Lines.LineCounter.track("+(lineCount-printsAdded)+");");
                    printsAdded ++;
                    String s = strCurrentLine;
                    newStatement = (s.replaceAll("\\s+"," ").trim().charAt(s.length()-1) != ';') ? false : true;
                }

                if(strCurrentLine.contains("public static void main")){
                    inMain = true;
                }
                System.out.println(strCurrentLine);
            }

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
