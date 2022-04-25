package Variables;

import java.util.ArrayList;

public class myEnum {
    String name;
   String[] members;
   public static ArrayList<String> createdEnums = new ArrayList<String>();


    public myEnum (String name,String[] members ){
        this.name= name;
        this.members=members;
        createdEnums.add(name);
    }

    public String write() {
        String res = "enum "+name+"{ ";
        for(String member : members){
            res += member +", ";
        }
        res = res.substring(0,res.length()-2)+" }";
        return res;
    }

}
