package GeneticAlgorithm.Models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Population {

    private final int populationSize;
    private final int knapsackSize;
    private final Collection<Knapsack> knapsacks = new ArrayList<>();

    public Population(int populationSize, int knapsackSize, ItemCollection items) {
        this.populationSize = populationSize;
        this.knapsackSize = knapsackSize;
        this.createPopulation(items);
    }

    /**
     * Creates random population of knapsacks
     * @param items all available items
     */
    private void createPopulation(ItemCollection items) {
        for (int i = 0; i < populationSize; ++i) {
            knapsacks.add(new Knapsack(knapsackSize, items));
        }
    }

    /**
     * Sums fitnesses of all knapsacks in a population
     * @return population's getFitnessSum
     */
    public int getFitnessSum() {
        int fitness = 0;

        for (Knapsack knapsack : knapsacks) {
            fitness += knapsack.fitness();
        }

        return fitness;
    }

    public Iterable<Knapsack> getKnapsacks() {
        return knapsacks;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public int getMaximalFitness() {
        int maximalFitness = 0;

        for (Knapsack k : knapsacks) {
            int fitness = k.fitness();

            if (fitness > maximalFitness)
                maximalFitness = fitness;
        }

        return maximalFitness;
    }

    public double getMeanFitness() {
        return this.getFitnessSum() / this.getPopulationSize();
    }

    public int getMinimalFitness() {
        int minimalFitness = knapsacks.iterator().next().fitness();

        for (Knapsack k : knapsacks) {
            int fitness = k.fitness();

            if (fitness < minimalFitness)
                minimalFitness = fitness;
        }

        return minimalFitness;
    }

    /**
     * Finds best knapsack in a population
     * @return binary string representing the knapsack
     */
    public String getBestGenome() {
        Knapsack knapsack = null;
        int maxFitness = 0;

        for (Knapsack k : knapsacks) {
            if (k.fitness() > maxFitness)
                knapsack = k;
        }

        return knapsack.toBinaryString();
    }

    /**
     * Returns amount of best knapsacks in a population
     * @param amount
     * @return
     */
    public Collection<Knapsack> getBestKnapsacks(int amount) {
        List<Knapsack> sortedKnapsacks = new ArrayList<>(knapsacks);
        Collections.sort(sortedKnapsacks);

        return sortedKnapsacks.subList(0, amount - 1);
    }

    /**
     * Imports all knapsacks from given population
     * @param population
     */
    public void add(Collection<Knapsack> population) {
        knapsacks.addAll(population);
    }
}
