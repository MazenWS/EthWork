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
            int lineCount = 0;

            while ((strCurrentLine = objReader.readLine()) != null) {

                lineCount++;
                String s = strCurrentLine;
               if( strCurrentLine  .contains("public class test {"))
                   strCurrentLine="public class NewTest {";


//                if (strCurrentLine.contains("catch (Exception e) {") )
//                newStatement = false ;
                if(inMain && newStatement && strCurrentLine != ""  ){
                    System.out.println("LineCounter.track("+lineCount+");");
                }

                newStatement = ((s.trim().length() == 0)|| (s.trim().charAt(s.trim().length() - 1) == '{')||(s.trim().charAt(0) == '/')|| (s.trim().charAt(s.trim().length() - 1) == ';')) ? true : false;
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
