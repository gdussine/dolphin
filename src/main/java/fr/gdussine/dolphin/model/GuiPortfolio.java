package fr.gdussine.dolphin.model;

import com.jump.dolphin.JumpValue;
import com.jump.dolphin.Portfolio;
import fr.gdussine.dolphin.api.JumpService;
import fr.gdussine.dolphin.core.PortfolioBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class GuiPortfolio implements Comparable<GuiPortfolio>{

    private Portfolio portfolio;
    private Map<Integer, Integer> values;
    public double sharp;

    public GuiPortfolio(Map<Integer, Integer> values){
        this.values = values;
        jumpLoad(JumpService.getInstance());
    }

    public GuiPortfolio(Integer...args){
        Map<Integer, Integer> m = new HashMap<>();
        for(int i = 0; i<args.length/2; i++ ){
            m.put(args[i*2], args[i*2+1]);
        }
        this.values = m;
        this.jumpLoad(JumpService.getInstance());

    }

    private void jumpLoad(JumpService api){
        this.portfolio = new PortfolioBuilder().addAll(this.values).build();
        api.setPortfolio(this.portfolio);
        this.sharp = api.getSharpValue();
    }

    public double getSharp() {
        return sharp;
    }

    @Override
    public int compareTo(@NotNull GuiPortfolio guiPortfolio) {
        return Double.compare(this.sharp, guiPortfolio.sharp);
    }
}
