package Variables;

import java.util.ArrayList;

public class myEnum extends Variable {
    String name;
    ArrayList<String> members;


    public myEnum (String name,ArrayList<String> members ){
        this.name= name;
        this.members=members;
    }

}
