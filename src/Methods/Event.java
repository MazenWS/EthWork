package Methods;

import Variables.EventVariable;

import java.util.ArrayList;

public class Event {
    String eventName;
    EventVariable[] vars;
    public static ArrayList<String> eventNames = new ArrayList<String>();

    public Event(String eventName,EventVariable[] vars){
        this.eventName = eventName;
        this.vars = vars;
        eventNames.add(eventName);
    }

    public String write() {
        String res = "event "+eventName+"(";
        for(EventVariable var : vars){
            res += var.write() + ", ";
        }
        res = res.substring(0,res.length()-2) + ");";
        return res;
    }
}
