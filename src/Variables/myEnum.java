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

}
