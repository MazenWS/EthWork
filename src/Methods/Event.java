package Methods;

import Contracts.TheFile;
import Lines.Line;
import Variables.EventVariable;

import java.util.ArrayList;

public class Event {
    String eventName;
    EventVariable[] vars;
    public static ArrayList<String> eventNames = new ArrayList<String>();
    int javaLine;

    public Event(String eventName,EventVariable[] vars){
        this.eventName = eventName;
        this.vars = vars;
        eventNames.add(eventName);
    }

    public void setJavaLine(int javaLine){
        this.javaLine = javaLine;
    }

    public String write() {
        String res = "event "+eventName+"(";
        for(EventVariable var : vars){
            res += var.write() + ", ";
        }
        res = res.substring(0,res.length()-2) + ");";

        int solLine = TheFile.solidityCount;
        TheFile.lineMap.addLine(new Line(javaLine,"Event",solLine,solLine));
        TheFile.solidityCount++;

        return res;
    }
}
