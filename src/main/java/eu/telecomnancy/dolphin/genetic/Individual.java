package eu.telecomnancy.dolphin.genetic;

import com.jump.dolphin.Portfolio;
import eu.telecomnancy.dolphin.model.ODPortfolio;
import eu.telecomnancy.dolphin.util.DolphinAPIService;
import eu.telecomnancy.dolphin.util.ODUtils;

import javax.sound.sampled.Port;
import java.util.*;
import java.util.concurrent.Flow;
import java.util.stream.Collectors;

public class Individual {

    private ODPortfolio odPortfolio;
    private Double skill;

    public Individual(ODPortfolio odPortfolio) {
        this.setOdPortfolio(odPortfolio);
    }

    public void setOdPortfolio(ODPortfolio odPortfolio){
        this.odPortfolio = odPortfolio;
        DolphinAPIService.get().pushPortfolio(odPortfolio);
        this.skill = DolphinAPIService.get().getRatioResult();
    }

    public Double getSkill() {
        return skill;
    }

    public Individual crossOver(Individual partner){
        Map<Integer, Double> mapP1 = this.getOdPortfolio().getMap();
        Map<Integer, Double> mapP2 = partner.getOdPortfolio().getMap();
        Map<Integer, Double> mapChild = new HashMap<>();
        Set<Integer> ids = new HashSet<>(mapP1.keySet());
        int[] distribP1 = ODUtils.distribPresqueAlea(mapP1.size());
        int[] distribP2 = ODUtils.distribPresqueAlea(mapP2.size());
        int indD1 = 0;
        int indD2 = 0;
        ids.addAll(mapP2.keySet());
        for(Integer id : ids){
            Double chromP1 = mapP1.get(id)==null?0:mapP1.get(id);
            Double chromP2 = mapP2.get(id)==null?0:mapP2.get(id);
            int localD1 =distribP1.length>indD1?distribP1[indD1]:0;
            int localD2 =distribP2.length>indD2?distribP2[indD2]:0;
            Double chromChild = 0.0;
            if(localD1 + localD2 != 0)
                chromChild = (chromP1 * localD1 +  chromP2 * localD2)/(localD1+ localD2);
            if(chromChild!=0) mapChild.put(id, chromChild);
            indD1 += chromP1==0?0:1;
            indD2 += chromP2==0?0:1;
        }
        return new Individual(new ODPortfolio(mapChild));
    }

    public void mutate(){

        List<Integer> ids = this.getOdPortfolio().getMap().keySet().stream().collect(Collectors.toList());
        List<Integer> unUsedStocks = DolphinAPIService.get().getUnUsedStocks(this.getOdPortfolio().getMap().keySet());
        int newId = ODUtils.randint(unUsedStocks.size(),0);
        int oldId = ODUtils.randint(ids.size(), 0);
        this.getOdPortfolio().getMap().remove(oldId);
        this.getOdPortfolio().getMap().put(newId, 1.0);


    }

    public ODPortfolio getOdPortfolio() {
        return odPortfolio;
    }

    public static Individual random(){
        return new Individual(ODPortfolio.random());
    }

    @Override
    public String toString() {
        return "Individual{" +
                "odPortfolio=" + odPortfolio +
                '}';
    }
}
