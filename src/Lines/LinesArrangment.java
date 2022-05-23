package Lines;

import Lines.Line;

import java.util.ArrayList;

public class LinesArrangment {
    static ArrayList<Line> lines = new ArrayList<>();


    public static int getJavaLine(int solLine){
        for(Line line : lines) {
            if(solLine >= line.solStart && solLine <= line.solEnd)
                return line.javaLine;
        }
        return 0;
    }

    public void addLine(Line line){
        lines.add(line);
    }

}
