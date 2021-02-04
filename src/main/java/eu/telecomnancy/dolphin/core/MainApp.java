package eu.telecomnancy.dolphin.core;

import com.jump.dolphin.Asset;
import com.jump.dolphin.JumpValue;
import com.jump.dolphin.Portfolio;
import eu.telecomnancy.dolphin.genetic.Individual;
import eu.telecomnancy.dolphin.genetic.Population;
import eu.telecomnancy.dolphin.model.ODPortfolio;
import eu.telecomnancy.dolphin.util.Context;
import eu.telecomnancy.dolphin.util.DolphinAPIService;
import eu.telecomnancy.dolphin.util.ODUtils;

import java.util.List;
import java.util.Map;

public class MainApp {

    public static void main(String[] args) {
        Individual indiv1 = Individual.random();
        Individual indiv2 = Individual.random();
        Individual chidl = indiv1.crossOver(indiv2);
    }
}
