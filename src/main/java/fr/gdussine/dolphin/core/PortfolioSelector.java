package fr.gdussine.dolphin.core;

import com.jump.dolphin.Asset;
import com.jump.dolphin.Portfolio;
import fr.gdussine.dolphin.model.GuiPortfolio;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PortfolioSelector {

    private final Asset a1;
    private final Asset a2;
    private final Asset a3;

    public PortfolioSelector(Asset a1, Asset a2, Asset a3){
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
    }

    public Portfolio best(){
        GuiPortfolio p = new GuiPortfolio(
                Integer.parseInt(a1.id._value),1,
                Integer.parseInt(a2.id._value), 1,
                Integer.parseInt(a3.id._value), 1);
        GuiPortfolio pMax = p;
        p.sharp = 0;
        System.out.println("Initialement : " + pMax.getSharp());
        for(int p1 = 0; p1<11; p1++){
            for(int p2 = 0;p2 <11-p1; p2++){
                p = new GuiPortfolio(
                        Integer.parseInt(a1.id._value),p1,
                        Integer.parseInt(a2.id._value), p2,
                        Integer.parseInt(a3.id._value), 10-p1-p2);
                if(pMax.compareTo(p) <= 0){
                    System.out.println(p1+", "+ p2 + ", "+ (10-p1-p2) + " -> " + p.sharp);
                    pMax = p;
                }
            }
        }
        File f = new File("train/"+a1.id._value+"-"+a2.id._value+"-"+a3.id._value+".txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Maintenant : " + pMax.getSharp());
        return null;
    }



}
