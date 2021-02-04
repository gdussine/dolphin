package eu.telecomnancy.dolphin.model;

import com.jump.dolphin.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class TestAllIn {

    private static final int ID_SHARPE = 12;

    public static String LABEL_PTF = "ESPRIT_PTF_2";
    public static final int ID_MYPTF = 1839;
    public static final String START_PERIOD = "2016-06-01";
    public static final String END_PERIOD = "2020-09-30";

    private static final int NB_LINES = 20;

    public static void main(String[] args) {
        new Dooglas(START_PERIOD, END_PERIOD, ID_SHARPE);



        /*

        Map<Integer, Ratio> locRatiosMap = locRESTManager.getRatioMap();
        List<Asset> locAllAssetList = locRESTManager.getAssetList(START_PERIOD);
        List<Asset> locStocksList = locAllAssetList
                .stream()
                .filter(parAsset -> parAsset.assetType == EnumAssetType.STOCK)
                .collect(Collectors.toList());

        //On applique les ratios sur la liste
        List<Ratio> locRatiosToInvoke = new ArrayList<>();
        Ratio locSharpe = locRatiosMap.get(ID_SHARPE);

        locRatiosToInvoke.add(locSharpe);

        List<Integer> locIdAssetList = locStocksList.stream()
                .map(parAsset -> Integer.parseInt(parAsset.id._value))
                .collect(Collectors.toList());

        Map<Integer, Map<Integer, JumpValue>> locRatioInvoked = locRESTManager.invokeRatios(
                locRatiosToInvoke,
                locIdAssetList,
                START_PERIOD,
                END_PERIOD);

        List<RatioResult> locRatioResultList = new ArrayList<>();

        locRatioInvoked.forEach((s, stringNumberMap) ->
                locRatioResultList.add(new RatioResult(s, stringNumberMap)));

*/


    }







}
