package fr.gdussine.dolphin.api;

import com.jump.dolphin.*;
import eu.telecomnancy.dolphin.util.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JumpService {

    private static JumpService instance;
    private RESTManager restManager;
    private List<Asset> assets;
    private List<Integer> assetsId;
    private Ratio sharp;
    private AssetCurrencyManager currencyManager;
    private Map<String, Double> changeRateMap;

    private JumpService(){
        this.restManager = new RESTManager();
        this.load();
    }

    public static JumpService getInstance(){
        if(instance == null)
            instance = new JumpService();
        return JumpService.instance;
    }

    public void load(){
        this.assets = restManager.getAssetList(Context.get().PTF_DATE_START)
                .stream()
                .filter(parAsset -> parAsset.assetType == EnumAssetType.STOCK)
                .filter(parAsset -> parAsset.priceValue != null)
                .collect(Collectors.toList());
        this.assetsId = assets.stream().map(x->Integer.parseInt(x.id._value))
                .collect(Collectors.toList());
        this.sharp = restManager.getRatioMap().get(Context.get().API_SHARPE_ID);
        this.currencyManager = new AssetCurrencyManager(Context.get().PTF_DATE_START, restManager);
        this.changeRateMap = currencyManager.changeRateMap;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public List<Integer> getAssetsId() {
        return assetsId;
    }

    public Ratio getSharp() {
        return sharp;
    }

    public Portfolio getPortfolio(){
        return restManager.getPortfolio(Context.get().PTF_ID);
    }

    public void setPortfolio(Portfolio portfolio){
        restManager.pushPortfolio(Context.get().PTF_ID, portfolio);
    }


    public Asset getAsset(int id) {
        return restManager.getAsset(id, Context.get().PTF_DATE_START);
    }

    public Double getMontant(Asset asset){
        MonetaryNumber value = asset.monetaryPrice;
        return  (value._amount * changeRateMap.get(value._currency));
    }

    public Map<Integer, Double> getListAssetSharpValue(List<Integer> assetIds){
        Map<Integer, Map<Integer, JumpValue>> mapRatio = restManager.invokeRatios(
                List.of(this.getSharp()), assetIds, Context.get().PTF_DATE_START, Context.get().PTF_DATE_STOP);
        Map<Integer, Double> result = new HashMap<Integer, Double>();
        for(Map.Entry<Integer, Map<Integer, JumpValue>> entryMapRatio : mapRatio.entrySet()){
            result.put(entryMapRatio.getKey(),Double.parseDouble(
                    entryMapRatio.getValue().get(Context.get().API_SHARPE_ID)
                            ._value.replace(',','.')));
        }
        return result;
    }

    public Map<Integer, Double> getAllSharpValue(){
        return this.getListAssetSharpValue(this.getAssetsId());
    }

    public Double getAssetSharpValue(int id){
        double v = Double.parseDouble(restManager
                .invokeRatios(List.of(this.getSharp()), List.of(id), Context.get().PTF_DATE_START, Context.get().PTF_DATE_STOP)
                .get(id)
                .get(Context.get().API_SHARPE_ID)._value.replace(',','.'));
        return v;
    }

    public AssetCurrencyManager getACM(){
        return new AssetCurrencyManager(Context.get().PTF_DATE_START, restManager);
    }

    public Double getSharpValue(){
       return getAssetSharpValue(Context.get().PTF_ID);
    }


    public void getPorfolio() {
    }
}
