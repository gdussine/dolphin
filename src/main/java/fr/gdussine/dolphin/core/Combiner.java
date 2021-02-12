package fr.gdussine.dolphin.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Combiner {

    private Map<Integer, Synergie> synergies;
    private Map<Integer, List<Integer>> sortedSynergiesId;

    public Combiner(Map<Integer, Synergie> synergies){
        this.synergies = synergies;
        this.sortedSynergiesId = new HashMap<Integer, List<Integer>>();
    }

    public double simiCos(List<Integer> a, List<Integer> b){
        double ab = 0;
        double ac = 0;
        double bc = 0;
        for(int i = 0; i<a.size(); i++){
            ab += a.get(i) * b.get(i);
            ac += a.get(i) * a.get(i);
            bc += b.get(i) * b.get(i);
        }
        return ab / (Math.sqrt(ac) * Math.sqrt(bc));
    }

    public void sorter(){
        for(Integer idSujet : synergies.keySet()){

            Synergie synSujet = synergies.get(idSujet);

        }
    }

    public void info(){
    }


}
