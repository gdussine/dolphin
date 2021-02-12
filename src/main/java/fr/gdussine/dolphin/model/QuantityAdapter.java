package fr.gdussine.dolphin.model;

import com.jump.dolphin.*;
import eu.telecomnancy.dolphin.util.Context;
import fr.gdussine.dolphin.api.JumpService;
import fr.gdussine.dolphin.core.PortfolioBuilder;

import java.util.Map;

public class QuantityAdapter {


    private final Map<Integer, Integer> res;

    public QuantityAdapter(Map<Integer, Integer> res){
        this.res = res;
    }

    public void process(){
        double unisomme = 30000;
        for(Map.Entry<Integer, Integer> entry : res.entrySet()){
           Asset asset = JumpService.getInstance().getAsset(entry.getKey());
           double montantAction = JumpService.getInstance().getMontant(asset);
           int qty = (int) (unisomme / montantAction);
           entry.setValue(qty);
        }
        PortfolioBuilder builder = new PortfolioBuilder();
        builder.addAll(res);
        Portfolio p = builder.build();
        JumpService.getInstance().setPortfolio(p);
        double val = JumpService.getInstance().getSharpValue();
        System.out.println(val);
        System.out.println(res);
    }



}
