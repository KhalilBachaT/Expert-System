import sun.font.TrueTypeFont;

import java.util.*;


public class SYSTEM_EX {
    /* add class or method in this class to convert the strings from the file to a map which has the name BR */
    private ArrayList<String> Rules;

    public SYSTEM_EX() {
        Rules = new ArrayList<String>();
    }

    public ArrayList<String> getRules() {
        return Rules;
    }

    /************************************** Avant *****************************************************************/
    public ArrayList<String> ChainageAvant(HashMap<String,HashMap<String,ArrayList<String>>> BR,String F,ArrayList<String> BF){
        BF.add("-");
        for (String i:BR.get("p").keySet()
             ) {
            BR.get("p").get(i).add("-");
        }
        boolean continu = true;
        boolean continu2 =true;
        for (String i:BR.get("p").keySet()
             ) {
            if (BF.containsAll(BR.get("p").get(i))){
                continu2=true;
                break;
            }
            else continu2=false;
        }
        while (!BF.contains(F) && continu && continu2){
            for (String i: BR.get("p").keySet()
            ) {
                if (BR.get("p").get(i).contains("-")) {
                    if (BF.containsAll(BR.get("p").get(i))) {
                        Rules.add(i);
                        for (String s : BR.get("c").get(i)
                        ) {
                             if (!BF.contains(s))BF.add(s);
                        }
                        BR.get("p").get(i).set(BR.get("p").get(i).size()-1,"+");
                    }
                }
            }
            for (String i:BR.get("p").keySet()
                 ) {
                if (BR.get("p").get(i).contains("-"))   {
                    continu=true;
                }
                else {
                    continu=false;
                }
            }
        }
        BF.remove("-");
        for (String i:BR.get("p").keySet()
        ) {
            BR.get("p").get(i).remove("-");
            BR.get("p").get(i).remove("+");
        }
        if (BF.contains(F)) {
            System.out.print(F);
            System.out.print("la nouvelle base de faits");
            System.out.println(BF);
        }
        else {
            System.out.println("impossible de démontrer ce fait");
        }
        return Rules;
    }

    /************************************** Arriere *****************************************************************/
    public void ChainageArriere(HashMap<String,HashMap<String,ArrayList<String>>> BR,String F,ArrayList<String> BF){
        Rules = new ArrayList<String>();
        ArrayList<String>regle = new ArrayList<String>();
        String sommet;
        HashMap<String,ArrayList<String>> ER = new HashMap<String,ArrayList<String>>();
        Deque pile = new LinkedList<String>();
        pile.addFirst(F);
        boolean continu = true;
        boolean bool =false;
        int ex = 0;
        if (!BF.contains(F)) {
            ER.put("s",Rules);
            //change the condition !!! for the other case
            while (!pile.isEmpty()&& BR.get("p").size()>0) {
                ER.clear();
                sommet = (String) pile.getFirst();
                // Chercher les regles ou le sommet est conclucsion
                regle.clear();
                for (String i : BR.get("c").keySet()
                ) {
                    if (BR.get("c").get(i).contains(sommet)) {
                        regle.add(i);
                    }
                }
                ER.put(sommet,regle);
                // supprimer la règle depuis la base des regles
                if (ER.get(sommet).isEmpty()) {
                    ArrayList<String> s=new ArrayList<String>();
                    for (String ss:BR.get("p").keySet()
                    ) {
                        if (BR.get("p").get(ss).contains(sommet)) s.add(ss);
                    }
                    for (String i:s
                    ) {
                        BR.get("p").remove(i);
                        BR.get("c").remove(i);
                    }
                    pile.removeFirst();
                    s.clear();
                }
                else {
                    // chercher les primisses de chaque règle
                    for (String i : BR.get("p").get(ER.get(sommet).get(0))
                    ) {
                        if (BF.contains(i)) {
                            bool=true;
                        } else {
                            pile.addFirst(i);
                            bool=false; // desactiver la regle
                        }
                    }

                    if (bool){
                        Rules.add(ER.get(sommet).get(0));
                        BF.add(sommet);
                        pile.removeFirst();
                    }
                    ER.get(sommet).remove(0);
                }
            }

            if (pile.isEmpty()) {
                if (BR.get("p").size()==0) {
                    System.out.println(BR);
                    System.out.println("impossible de démontrer ce fait");
                }
                else {
                    System.out.print("la nouvelle base de faits : ");
                    System.out.println(BF);
                    System.out.print("Les nouvelles règles : ");
                    System.out.println(Rules);
                }
            }
            else {
                System.out.println("impossible de démontrer ce fait");
            }
        }
    }
}
