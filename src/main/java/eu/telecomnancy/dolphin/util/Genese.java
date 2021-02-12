package eu.telecomnancy.dolphin.util;

import eu.telecomnancy.dolphin.genetic.GeneticAlgo;
import eu.telecomnancy.dolphin.genetic.Population;

public class Genese {
    public static void main(String[] args) {
        Context.get().log();

        // Set a candidate solution
        int value_ref = 4;

        int gene_max = 5;

        // Create an initial population
        Population myPop = new Population(30, true);

        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        while (myPop.getMoreCompetent().getSkill() < value_ref && generationCount < gene_max) {
            myPop.checkIndividualNull();
            generationCount++;
            System.out.println("Generation: " + generationCount + " competence: " + myPop.getMoreCompetent().getSkill());
            myPop = GeneticAlgo.evolve(myPop);

        }
        System.out.println("Solution found: " + myPop.getMoreCompetent().getSkill());
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(myPop.getMoreCompetent());
        DolphinAPIService.get().pushPortfolio(myPop.getMoreCompetent().getOdPortfolio());

    }
}
