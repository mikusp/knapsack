package GeneticAlgorithm.Models;

import java.util.ArrayList;

public class Population {

    private int populationSize;
    private int knapsackSize;
    private ArrayList<Knapsack> knapsacks;

    public Population(int populationSize, int knapsackSize, ItemCollection items) {
        this.populationSize = populationSize;
        this.knapsackSize = knapsackSize;
        this.createPopulation(populationSize, knapsackSize, items);
    }

    /**
     * Creates random population of knapsacks
     * @param populationSize
     * @param knapsackSize size limit of each knapsack
     * @param items all available items
     */
    private void createPopulation(int populationSize, int knapsackSize, ItemCollection items) {
        for (int i = 0; i < populationSize; ++i) {
            knapsacks.add(new Knapsack(knapsackSize, items));
        }
    }
}
