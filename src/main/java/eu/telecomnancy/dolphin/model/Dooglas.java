package eu.telecomnancy.dolphin.model;

import com.jump.dolphin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dooglas {

    private String startPeriod;
    private String endPeriod;
    private int idSharpe;
    private RESTManager restManager;
    private List<RatioResult> ratioResults;

    public Dooglas(String startPeriod, String endPeriod, int idSharpe){
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.idSharpe = idSharpe;
        restManager = new RESTManager();
        init();
    }

    public void init(){
        Map<Integer, Ratio> locRatiosMap = restManager.getRatioMap();
        List<Integer> stockIds = restManager.getAssetList(startPeriod)
                .stream()
                .filter(parAsset -> parAsset.assetType == EnumAssetType.STOCK)
                .map(parAsset-> Integer.parseInt(parAsset.id._value))
                .collect(Collectors.toList());
        List<Ratio> ratiosToInvoke = List.of(locRatiosMap.get(idSharpe));
        Map<Integer, Map<Integer, JumpValue>> ratiosInvoked = restManager.invokeRatios(
                ratiosToInvoke,
                stockIds,
                startPeriod,
                endPeriod);
        ratioResults = new ArrayList<>();
        ratiosInvoked.forEach((s, stringNumberMap) ->
                ratioResults.add(new RatioResult(s, stringNumberMap)));
        System.out.println(ratioResults);
    }




}
