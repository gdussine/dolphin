package fr.gdussine.dolphin.core;

import com.jump.dolphin.*;
import eu.telecomnancy.dolphin.util.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PortfolioBuilder {

    private String label;
    private AssetCurrency currency;
    private EnumDynAmountTypeWebModel type;
    private List<DynAmountLineContainerModel> lines;

    public PortfolioBuilder(){
        label = Context.get().PTF_NAME;
        currency = new AssetCurrency("EUR");
        type = EnumDynAmountTypeWebModel.front;
        lines = new ArrayList<DynAmountLineContainerModel>();


    }

    public PortfolioBuilder  addAsset(Asset asset, Double quantity){
        lines.add(DynAmountLineContainerModel.of(
                new DynAmountLineAssetModel(Integer.parseInt(asset.id._value), quantity)));
        return this;
    }

    public PortfolioBuilder  addAsset(Integer id, Double quantity){
        lines.add(DynAmountLineContainerModel.of(
                new DynAmountLineAssetModel(id, quantity)));
        return this;
    }

    public PortfolioBuilder addAll(Map<Integer, Integer> all){
        all.entrySet().stream()
                .forEach(x->this.addAsset(x.getKey(), Double.parseDouble(""+x.getValue())));
        return this;
    }

    public Portfolio build(){
        return new Portfolio(label, currency, type, Map.of(Context.get().PTF_DATE_START, lines)) ;
    }




}
