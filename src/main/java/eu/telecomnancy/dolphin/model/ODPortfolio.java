package eu.telecomnancy.dolphin.model;

import eu.telecomnancy.dolphin.util.Context;
import eu.telecomnancy.dolphin.util.DolphinAPIService;
import eu.telecomnancy.dolphin.util.ODUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Publisher;

public class ODPortfolio {

    private Map<Integer, Double> map = new HashMap<>();

    public ODPortfolio(){

    }

    public ODPortfolio(Map<Integer, Double> map) {
        this.map = map;
    }

    public Map<Integer, Double> getMap() {
        return map;
    }

    public void put(Integer id, Double qty){
        map.put(id, qty);
    }

    public void setMap(Map<Integer, Double> map) {
        this.map = map;
    }

    public static ODPortfolio random(){
        List<Integer> stocks = ODUtils.copy(DolphinAPIService.get().getStocks());
        ODPortfolio odPortfolio = new ODPortfolio();
        int nbAction = ODUtils.randint(Context.get().PTF_NB_MAX-Context.get().PTF_NB_MIN, Context.get().PTF_NB_MIN);
        for(int i = 0; i < nbAction; i++){
            double qty =1; //ODUtils.randouble(Context.get().PTF_NAV_MAX-Context.get().PTF_NAV_MIN,Context.get().PTF_NAV_MIN);
            int index = ODUtils.randint(stocks.size(), 0);
            odPortfolio.put(stocks.remove(index), qty);
        }
        return odPortfolio;
    }

    @Override
    public String toString() {
        return "ODPortfolio["+ map.size()+ "]{" +
                "map=" + map +
                '}';
    }
}
