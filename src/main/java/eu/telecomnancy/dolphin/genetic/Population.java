package eu.telecomnancy.dolphin.genetic;

import eu.telecomnancy.dolphin.util.ODUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Population {

    Individual[] individuals;

    public Population(int populationSize, boolean initialise) {
        individuals = new Individual[populationSize];
        // Initialise population
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < size(); i++) {
                Individual newIndividual = Individual.random();
                saveIndividual(i, newIndividual);
            }
        }
    }
    public Population(int populationSize, Individual[] individuals) {
        this.individuals = new Individual[populationSize];
        int i = 0;
        for(i = 0;i<individuals.length; i++){
            this.individuals[i] = individuals[i];
        }
        for(int j = i; j < populationSize; j++){
            this.individuals[j] = null;
        }
    }


    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public Individual getMoreCompetent() {
        Individual moreCompetent = individuals[0];
        for (int i = 0; i < size(); i++) {
            if (moreCompetent.getSkill() <= getIndividual(i).getSkill()) {
                moreCompetent = getIndividual(i);
            }
        }
        return moreCompetent;
    }

    public void checkIndividualNull(){
        for(int i = 0; i < this.individuals.length ;i++){
            if (this.individuals[i] == null)
                System.out.println(" -> ERREUR NULL id : "+i+" "+individuals[i]);
        }
    }

    public Individual[] getMoreCompetents(int n){
        Individual [] sorted = Arrays.copyOf(individuals, individuals.length);
        Arrays.sort(sorted, new Comparator<Individual>() {
            @Override
            public int compare(Individual t1, Individual t2) {
                return Double.compare(t2.getSkill(),t1.getSkill());
            }
        });
        return Arrays.copyOfRange(sorted, 0, n);
    }

    public Individual[] getRandoms(int n){
        Individual[] res = new Individual[n];
        for(int i=0; i<res.length; i++){
            int rand = ODUtils.randint(individuals.length, 0);
            res[i] =getIndividual(rand);
        }
        return res;
    }

    public void crossOver(Individual best){
        int i = individuals.length;
        while(i >0 && individuals[i-1] == null){
            i--;
        }
        for(int j = i; j < individuals.length; j++){
            int a = ODUtils.randint(i, 0);
            saveIndividual(j,getIndividual(a).crossOver(best));
        }
    }

    public void mutate(){
        individuals[ODUtils.randint(individuals.length, 0)] = Individual.random();
    }

    public int size() {
        return individuals.length;
    }

    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }
}
