package Contracts;

import Lines.Line;
import Lines.LineCounter;
import Methods.*;
import Variables.StateVariable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class Contract extends TypeContract{
    ArrayList<StateVariable> stateVars;
    ArrayList<Event> events;
    ArrayList<Modifier> modifiers;
    Constructor constructor;
    ReceiveFunction receive;
    ArrayList<String> extendsContract;
    //each file has multiple libraries
    //we supposed to say it will be used for which data type but we will use * (all) for simplicity
    //import {Library1, Library3} from "./library-file.sol";
    //using Library1 for *;
    //using Lubrary2 for *
    ArrayList<String> allLibs;
    Hashtable<String, String[]> fileAndLib;
    Hashtable<String,Integer> libraryLines ;
    boolean isAbstract;



    public Contract(String contractName){
        this.contractName = contractName;
        methods = new ArrayList<Method>();
        structs = new ArrayList<Struct>();
        enums = new ArrayList<myEnum>();
        stateVars = new ArrayList<StateVariable>();
        events = new ArrayList<Event>();
        modifiers= new ArrayList<Modifier>();
        extendsContract= new ArrayList<>();
        fileAndLib=new Hashtable<>();
        allLibs= new ArrayList<>();
       libraryLines = new Hashtable<String,Integer>();
    }

    public void setAbstract(){
            isAbstract= true;
    }
    public void addAContractToExtend(String extendsContract){
            this.extendsContract.add(extendsContract);
    }

    public void addLibrary(String libraryFile,String[] libraryNames){
        fileAndLib.put(libraryFile,libraryNames);
        libraryLines.put(libraryFile,LineCounter.getLine());

    }

    public void addConstructor(Constructor constructor) throws Exception {

        if(this.constructor != null){
            throw new Exception("A Constructor is Already Added");
        }
        constructor.setJavaLine(LineCounter.getLine());
        this.constructor = constructor;
    }
    public void addModifier( Modifier modifier){
        modifier.setJavaLine(LineCounter.getLine());
        modifiers.add(modifier);
    }


    public void addStateVariable(StateVariable var){
        stateVars.add(var);
        var.setJavaLine(LineCounter.getLine());
    }

    public void addEvent(Event event){
        events.add(event);
        event.setJavaLine(LineCounter.getLine());
    }

    public void addReceiveFunction(ReceiveFunction receive) throws Exception {
        if(this.receive != null){
            throw new Exception("A receive Function is already Added");
        }
        receive.setJavaLine(LineCounter.getLine());
        this.receive = receive;
    }

    public String write() throws Exception {
        String res="";

        if (!fileAndLib.isEmpty()){
            Enumeration<String> e = fileAndLib.keys();

            while (e.hasMoreElements()) {

               String filename = e.nextElement();
               res+="import {";
                for (String lib:
                    fileAndLib.get(filename) ) {
                    allLibs.add(lib);
                    res+=lib+", ";
                }
                res = res.substring(0,res.length()-2) ;
                res+="} from \""+filename+"\" ;";
                res+="\n";
                TheFile.lineMap.addLine(new Line(libraryLines.get(filename),"library",TheFile.solidityCount,  TheFile.solidityCount));
                TheFile.solidityCount++;
        }
            for (String lib:
                    allLibs) {
                res+="using "+ lib +" *;";
                res+="\n";
                TheFile.solidityCount++;
            }
            res+="\n\n\n";
            TheFile.solidityCount+=3;
        }


         res+= "contract "+contractName ;

        if (!extendsContract.isEmpty()){
            res+= " is ";
            for (String names : extendsContract) {
                res += names+", ";
            }

            res = res.substring(0,res.length()-2) ;
        }
        res+=  " {\n\n\n";
        TheFile.solidityCount+=4;

        if(! structs.isEmpty()) {
            for (Struct struct : structs) {
                res += struct.write() + "\n\n";
                TheFile.solidityCount++;
            }
            res += "\n";
            TheFile.solidityCount++;
        }

        if(! enums.isEmpty()) {
            for (myEnum enumm : enums) {
                res += enumm.write() + "\n";
            }
            res += "\n";
            TheFile.solidityCount++;
        }

        if(!stateVars.isEmpty()) {
            for (StateVariable state : stateVars) {
                res += state.write() + "\n";
            }
            res += "\n";
            TheFile.solidityCount++;
        }

        if(! events.isEmpty()) {
            for(Event event : events){
                res += event.write() + "\n";
            }
            res += "\n";
            TheFile.solidityCount++;
        }

        if(constructor != null){
            res += constructor.write()+ "\n\n";
            TheFile.solidityCount++;
        }

        if(! modifiers.isEmpty()) {
            for(Modifier mod : modifiers) {
                res += mod.write() + "\n\n";
                TheFile.solidityCount++;
            }
        }

        if (!methods.isEmpty()) {
            for (Method method : methods) {
                res += method.write() + "\n\n";
                TheFile.solidityCount++;
            }
        }

        if(receive != null){
            res += receive.write()+"\n";
        }
        res += "}";
        return res;
    }


    }

