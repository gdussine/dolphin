package fr.gdussine.dolphin.core;

import com.jump.dolphin.Portfolio;
import fr.gdussine.dolphin.api.JumpService;

import java.util.ArrayList;
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
            this.sortedSynergiesId.put(idSujet,synSujet.sortedIdList());
        }
        for(Integer idSujet : synergies.keySet()){
            double maxCos = -2;
            int idMax = 0;
            for(Integer idTest : synergies.keySet()){
                double maxCosCandidat = simiCos(sortedSynergiesId.get(idSujet), sortedSynergiesId.get(idTest));
                if(maxCos < maxCosCandidat && idTest != idSujet){
                    maxCos = maxCosCandidat;
                    idMax = idTest;
                }
            }
            System.out.println("Meilleur similitude de "+idSujet+" avec "+idMax+" taux="+maxCos);
        }
    }

    public Synergie best(){
        Synergie maxSyn = null;
        for(Map.Entry<Integer, Synergie> entry : synergies.entrySet()){
            if(maxSyn == null || maxSyn.getMax().getValue() < entry.getValue().getMax().getValue()){
                maxSyn = entry.getValue();
            }

        }
        return maxSyn;
    }

    public void info(){
        generation();
    }

    public List<Integer> getNPareil(int id, int n){
        List<Integer> listId = new ArrayList<>();
        for(int idTest : synergies.keySet()){
            double maxCosCandidat = simiCos(sortedSynergiesId.get(id), sortedSynergiesId.get(idTest));
            if(listId.size() < n+1 || simiCos(sortedSynergiesId.get(id), sortedSynergiesId.get(listId.get(n))) < maxCosCandidat){
                if(listId.size()==n+1){
                    listId.remove(n);
                    listId.add(n, idTest);
                } else {
                    listId.add(idTest);
                }

                listId.sort((o1,o2)->Integer.compare(o2,o1));
            }
        }

        return listId;
    }

    public Map<Integer, Integer> merge(List<Integer> l1, List<Integer> l2){
        Map<Integer, Integer> res = new HashMap<>();
        for(int id1 : l1){
            res.put(id1,10);
        }
        for(int id2 : l2){
            res.put(id2,10);
        }
        return res;
    }

    public void generation(){
        sorter();
        Synergie synergie = best();
        Integer id1 = synergie.getMax().getKey();
        List<Integer> resId1 = getNPareil(id1,16);
        Integer id2 = synergie.getId();
        List<Integer> resId2 = getNPareil(id2,16);
        PortfolioBuilder builder = new PortfolioBuilder();
        builder.addAll(merge(resId1, resId2));
        Portfolio p = builder.build();
        JumpService.getInstance().setPortfolio(p);
        JumpService.getInstance().getPortfolio();
        System.out.println(JumpService.getInstance().getSharpValue());
        System.out.println(merge(resId1, resId2).keySet().size());

    }



}
