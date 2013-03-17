package GeneticAlgorithm.Models;

import java.util.ArrayList;

public class Population {

    private int populationSize;
    private int knapsackSize;
    private ArrayList<Knapsack> knapsacks;

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
     * @return population's fitness
     */
    public int fitness() {
        int fitness = 0;

        for (Knapsack knapsack : knapsacks) {
            fitness += knapsack.fitness();
        }

        return fitness;
    }

    public Iterable<Knapsack> getKnapsacks() {
        return knapsacks;
    }
}
