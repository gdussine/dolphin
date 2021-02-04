package eu.telecomnancy.dolphin.genetic;

import java.util.Arrays;

public class GeneticAlgo {

    /* GA parameters */
    private static final double uniformRate = 0.5;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;


    private static final double eliteRate = 0.2;
    private static final double otherRate = 0.05;
    private static final double mutationRate = 0.05;

    public static Population evolve(Population population){

        int nbElite = (int)(eliteRate * population.size());
        int nbOther = (int)(otherRate * population.size());
        int nbMutate = (int)(mutationRate* population.size());
        Individual[] elits = population.getMoreCompetents(nbElite);
        Individual[] others = population.getRandoms(nbOther);
        Individual[] parents = new Individual[nbElite+nbOther];
        Individual best = population.getMoreCompetent();
        for(int i = 0; i<parents.length; i++){
            if(i < elits.length)
                parents[i] = elits[i];
            else{
                parents[i] = others[i-elits.length];
            }
        }
        Population pop = new Population(population.size(), parents);

        pop.crossOver(best);
        for(int i =0; i<nbMutate; i++) {
            pop.mutate();
        }
        return pop;
    }




    /* Public methods */

    // Evolve a population
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size(), false);

        // Keep our best individual
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getMoreCompetent());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < pop.size(); i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = indiv1.crossOver( indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            newPopulation.getIndividual(i).mutate();
        }

        return newPopulation;
    }

    // Select individuals for crossover
    private static Individual tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        Individual fittest = tournament.getMoreCompetent();
        return fittest;
    }
}
