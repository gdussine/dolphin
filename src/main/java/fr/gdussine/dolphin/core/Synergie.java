package fr.gdussine.dolphin.core;

import com.jump.dolphin.Portfolio;
import fr.gdussine.dolphin.api.JumpService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Synergie {

    private  Map<Integer, Double> mapRatio;


    private Map<Integer, Double> synergie;
    private int id;

    public Synergie(Integer id, Map<Integer, Double> mapRatio){
        this.id = id;
        this.synergie = new HashMap<Integer, Double>();
        this.mapRatio = mapRatio;
    }

    public Map<Integer, Double> convertToMap(){
        return synergie;
    }

    public Double get(Integer id){
        return synergie.get(id);
    }

    public void loadMap(List<Integer> assetsIds, Map<Integer, Synergie> synergies){

        if(synergies.get(id)!=null){
            System.out.println("Valeur déjà connue");
            this.synergie = synergies.get(id).synergie;
            return;
        }

        JumpService api = JumpService.getInstance();
        for(Integer assetId : assetsIds){
            if(synergies.get(assetId) != null){
                synergie.put(assetId, synergies.get(assetId).get(id));
            } else{
                Portfolio p = new PortfolioBuilder()
                        .addAsset(id, 1.0)
                        .addAsset(assetId, 1.0)
                        .build();
                api.setPortfolio(p);
                double mean = (mapRatio.get(id) + mapRatio.get(assetId))/2.0;
                double sharp = api.getSharpValue();
                synergie.put(assetId, sharp/mean);
            }

        }
    }

    public List<Integer> sortedIdList(){
        return synergie.entrySet()
                .stream()
                .sorted((o1,o2)->Double.compare(o2.getValue(),o1.getValue()))
                .map(x->x.getKey())
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        double moyenne = 0;
        double max = -1000;
        int max_id = 0;
        double min = 1000;
        int min_id = 0;
        for(Map.Entry<Integer, Double> entry : synergie.entrySet()){
            moyenne += entry.getValue();
            if(max < entry.getValue()){
                max = entry.getValue();
                max_id = entry.getKey();
            }
            if(min > entry.getValue()){
                min = entry.getValue();
                min_id = entry.getKey();
            }
        }
        return "Synergie("+id+") : moy="+moyenne+" min("+min_id+")="+min+" min("+max_id+")="+max;
    }
}
