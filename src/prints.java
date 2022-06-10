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
            boolean imported = false;

            while ((strCurrentLine = objReader.readLine()) != null) {

                lineCount++;
                String s = strCurrentLine;
               if( strCurrentLine  .contains("public class")){
                   if(!imported){
                       System.out.println("import Lines.LineCounter;");
                   }
                   strCurrentLine="public class NewTest {";
               }

                if(inMain && newStatement && strCurrentLine.length()!=0){
                    System.out.println("LineCounter.track("+lineCount+");");
                }

                if(inMain && s.length()!=0 && !(s.contains("try") && s.endsWith("{"))){
                    newStatement = false;
                    if((s.trim().charAt(s.trim().length() - 1)==';')){
                        newStatement = true;
                    }
                }
                if(strCurrentLine.contains("public static void main")){
                    inMain = true;
                }

                if(s.contains("import") && (s.contains("Lines.*")|| s.contains("Lines.LineCounter")))
                    imported = true;
                System.out.println(strCurrentLine);
            }

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
