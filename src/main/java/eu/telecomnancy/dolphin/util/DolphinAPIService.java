package eu.telecomnancy.dolphin.util;

import com.jump.dolphin.*;
import eu.telecomnancy.dolphin.model.ODPortfolio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DolphinAPIService {

    private RESTManager restManager = new RESTManager();
    private List<Integer> stocks;

    private static DolphinAPIService instance;

    public static DolphinAPIService get(){
        if(instance == null)
            instance = new DolphinAPIService();
        return instance;
    }

    private DolphinAPIService(){
        restManager = new RESTManager();
    }

    public List<Integer> getStocks(){
        if(stocks == null)
            stocks = restManager.getAssetList(Context.get().PTF_DATE_START)
                .stream()
                .filter(parAsset -> parAsset.assetType == EnumAssetType.STOCK)
                .filter(asset -> asset.priceValue != null)
                .map(parAsset -> Integer.parseInt(parAsset.id._value))
                .collect(Collectors.toList());
        return stocks;

    }

    public List<Integer> getUnUsedStocks(Set<Integer> assets){
        return stocks.stream()
                .filter(x->!assets.contains(x))
                .collect(Collectors.toList());
    }

    public Asset getAssetByName(String name){
        return restManager.getAssetList(Context.get().PTF_DATE_START).stream()
                .filter(x->x.label._value.contains(name)).findAny().orElse(null);
    }

    public Ratio getSharpeRatio(){
        return restManager.getRatioMap().get(Context.get().API_SHARPE_ID);
    }

    public Double getRatioResult(){
        JumpValue value = restManager.invokeRatios(List.of(getSharpeRatio()), List.of(Context.get().PTF_ID), Context.get().PTF_DATE_START, Context.get().PTF_DATE_STOP)
                .get(Context.get().PTF_ID).get(Context.get().API_SHARPE_ID);
        return (Double)value.getRealValue();
    }

    public Double getRatioResult(int id){
        JumpValue value = restManager.invokeRatios(List.of(getSharpeRatio()), List.of(id), Context.get().PTF_DATE_START, Context.get().PTF_DATE_STOP)
                .get(id).get(Context.get().API_SHARPE_ID);
        return (Double)value.getRealValue();
    }


    public void pushPortfolio(ODPortfolio odPortfolio){
        Portfolio p = ODUtils.toPorftolio(odPortfolio);
        restManager.pushPortfolio(Context.get().PTF_ID, ODUtils.toPorftolio(odPortfolio));
    }



}
