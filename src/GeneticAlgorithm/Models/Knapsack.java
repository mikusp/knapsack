package GeneticAlgorithm.Models;

import java.util.BitSet;
import java.util.Random;

/**
 * Knapsack is an equivalent of genome in our algorithm.
 * It consists of zero or more items.
 */
public class Knapsack {

    /**
     * Items currently being in a knapsack represented as a binary string.
     * For example, 011010101 means that second, third, fifth, seventh
     * and ninth items from all available items are in the knapsack.
     * This representation should make it easier to crossover and mutate
     * knapsacks.
     */
    private final BitSet items;
    private final int sizeConstraint;
    private final ItemCollection itemsAvailable;

    /**
     * Creates new Knapsack with random items inside.
     * Probability of including an item is 50%.
     * @param sizeConstraint knapsack's size limit
     * @param itemsAvailable collection of available items
     */
    public Knapsack(int sizeConstraint, ItemCollection itemsAvailable) {
        this.items = new BitSet(itemsAvailable.getSize());
        this.sizeConstraint = sizeConstraint;
        this.itemsAvailable = itemsAvailable;

        Random r = new Random();
        for (int i = 0; i < itemsAvailable.getSize(); ++i) {
            if (r.nextBoolean())
                items.set(i);
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

        for (int i = 0; i < items.size(); ++i) {
            currentSize += itemsAvailable.getItem(i).getSize();

            if (currentSize <= sizeConstraint)
                fitness += itemsAvailable.getItem(i).getValue();
        }

        return fitness;
    }

    public void mutateItem(int i) {
        items.flip(i);
    }

    public ItemCollection getItemsAvailable() {
        return itemsAvailable;
    }
}
