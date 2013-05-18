package GeneticAlgorithm.Models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Population {

    private int populationSize;
    private final int knapsackSize;
    private final ItemCollection itemCollection;
    private final Collection<Knapsack> knapsacks = new ArrayList<>();

    public Population(int populationSize, int knapsackSize, ItemCollection items) {
        this.populationSize = populationSize;
        this.knapsackSize = knapsackSize;
        this.itemCollection = items;
        this.createPopulation();
    }

    public int getKnapsackSize() {
        return knapsackSize;
    }

    public Population(int knapsackSize, ItemCollection items) {
        this.populationSize = 0;
        this.knapsackSize = knapsackSize;
        this.itemCollection = items;
    }

    public ItemCollection getItemCollection() {
        return itemCollection;
    }

    /**
     * Creates random population of knapsacks
     */
    private void createPopulation() {
        for (int i = 0; i < populationSize; ++i)
            knapsacks.add(new Knapsack(knapsackSize, itemCollection));
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

    public List<Knapsack> getKnapsacks() {
        return (List<Knapsack>)knapsacks;
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

    public Knapsack getRandomKnapsack() {
        int index = (new Random()).nextInt(populationSize);

        Iterator<Knapsack> it = knapsacks.iterator();
        for (int i = 0; it.hasNext() && i < index; it.next(), ++i) {

        }

        return it.next();
    }

    /**
     * Returns amount of best knapsacks in a population
     * @param amount
     * @return
     */
    public Collection<Knapsack> getBestKnapsacks(int amount) {
        List<Knapsack> sortedKnapsacks = new ArrayList<>(knapsacks);
        Collections.sort(sortedKnapsacks);

        return sortedKnapsacks.subList(0, amount);
    }

    /**
     * Imports all knapsacks from given population
     * @param population
     */
    public void add(Collection<Knapsack> population) {
        knapsacks.addAll(population);
    }

    public void updateSize() {
        this.populationSize = knapsacks.size();
    }

    /**
     * Ugly
     * @return
     */
    public Collection<String> getBestItems() {
        Collection<Knapsack> k = this.getBestKnapsacks(1);
        Collection<String> res = null;

        for (Knapsack ks : k)
            res = ks.listItems();

        return res;
    }

    public int getBestItemsSize()
    {
        int size=0;
        Collection<Knapsack> k = this.getBestKnapsacks(1);
        for (Knapsack ks : k)
            size+=ks.getKnapsackSize();

        return size;
    }
}
