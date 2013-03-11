package GeneticAlgorithm.Models;

import java.util.ArrayList;
import java.util.Random;

/**
 * Knapsack is an equivalent of genome in our algorithm.
 * It consists of zero or more items.
 */
public class Knapsack {

    private final ArrayList<Item> items = new ArrayList<>();
    private int sizeConstraint;

    /**
     * Creates new Knapsack with random items inside.
     * Probability of including an item is 50%.
     * @param itemsAvailable collection of available items
     */
    public Knapsack(int sizeConstraint, ItemCollection itemsAvailable) {
        this.sizeConstraint = sizeConstraint;

        Random r = new Random();
        for (Item i : itemsAvailable.getAllItems()) {
            if (r.nextBoolean())
                items.add(i);
        }
    }

    /**
     * Computes fitness of a knapsack.
     * Value of an item is added to overall fitness only if
     * the item 'fits' in the knapsack, that is (size of previous
     * items + size of the item) <= size limit.
     * @return computed fitness
     */
    public int fitness() {
        int fitness = 0;
        int currentSize = 0;

        for (Item i : items) {
            currentSize += i.getSize();

            if (currentSize <= sizeConstraint)
                fitness += i.getValue();
        }

        return fitness;
    }
}
