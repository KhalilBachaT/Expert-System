import java.*;
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        /***************************************** file **************************************************************/
        ArrayList<String> BFs = new ArrayList<String>();
        ArrayList<String> p;
        ArrayList<String> c;
        HashMap<String,HashMap<String,ArrayList<String>>> BRs=new HashMap<String,HashMap<String,ArrayList<String>>>();
        HashMap<String,ArrayList<String>> Primisses=new HashMap<String,ArrayList<String>>();
        HashMap<String,ArrayList<String>> Conclusions=new HashMap<String,ArrayList<String>>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "../test.txt"));
            String LINE;
            String line = reader.readLine();
            int i=1;
            while (line != null) {
                p = new ArrayList<String>();
                c = new ArrayList<String>();
                LINE=line.toUpperCase();
                String[] words = LINE.split("\\s");
                if (LINE.startsWith("BF")){
                    for (String w :words
                         ) {
                        if (!w.equals("BF={") && !w.equals(",") && !w.equals("}")) BFs.add(w);
                    }
                }
                if (LINE.startsWith("R")){
                    while (i<words.length){
                        if (words[i].equals(",ALORS")){
                            break;
                        }
                        if (!words[i].equals(":") && !words[i].equals("ET") && !words[i].equals("SI")){
                             p.add(words[i]);
                        }
                        i+=1;
                    }

                    while (i<words.length){
                        if (!words[i].equals(",ALORS") && !words[i].equals("ET")) c.add(words[i]);
                        i+=1;
                    }
                    Primisses.put(words[0],p);
                    Conclusions.put(words[0],c);
                }
                i=1;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BRs.put("p",Primisses);
        BRs.put("c",Conclusions);

        while (true){
            Scanner Keyb=new Scanner(System.in);
            System.out.print("Entrer le fait : ");
            String fait=Keyb.next();

            ArrayList<String> bfa = new ArrayList<String>();
            ArrayList<String> bfarr = new ArrayList<String>();
            for (String i:BFs
            ) {
                bfa.add(i);
                bfarr.add(i);
            }
            System.out.println(BFs);
            System.out.println(BRs);
            SYSTEM_EX se = new SYSTEM_EX();
            ArrayList<String> r =new ArrayList<String>();
            System.out.println("Chainage en avant :");
            r=se.ChainageAvant(BRs,fait,bfa);
            System.out.print("les regles utlisées");
            System.out.println(r);
            System.out.println("------------------------------------------");
            System.out.println("Chainage en arriere :");
            se.ChainageArriere(BRs,fait,bfarr);
        }
    }

}
