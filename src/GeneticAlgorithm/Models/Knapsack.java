package GeneticAlgorithm.Models;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Random;

/**
 * Knapsack is an equivalent of genome in our algorithm.
 * It consists of zero or more items.
 */
public class Knapsack implements Comparable<Knapsack> {

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
        this(new BitSet(itemsAvailable.getSize()), sizeConstraint, itemsAvailable);

        Random r = new Random();
        for (int i = 0; i < itemsAvailable.getSize(); ++i) {
            if (r.nextBoolean())
                items.set(i);
        }
    }

    /**
     * Used for constructing knapsacks during crossover
     * @param items
     * @param sizeConstraint
     * @param itemsAvailable
     */
    public Knapsack(BitSet items, int sizeConstraint, ItemCollection itemsAvailable) {
        this.items = items;
        this.sizeConstraint = sizeConstraint;
        this.itemsAvailable = itemsAvailable;
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

        for (int i = 0; i < items.length(); ++i) {
            if (items.get(i)) {
                currentSize += itemsAvailable.getItem(i).getSize();

                if (currentSize <= sizeConstraint)
                    fitness += itemsAvailable.getItem(i).getValue();
            }
        }

        return fitness;
    }

    /**
     * Removes i-th item if it's present or adds it
     * if it's missing
     * @param i index of an item from ItemCollection
     */
    public void mutateItem(int i) {
        items.flip(i);
    }

    public ItemCollection getItemsAvailable() {
        return itemsAvailable;
    }

    /**
     * @return internal BitSet representing items inside a knapsack
     */
    public BitSet getItemsInside() {
        return items;
    }

    /**
     *
     * @return size limit of items in a knapsack
     */
    public int getSizeConstraint() {
        return sizeConstraint;
    }

    /**
     * Converts internal BitSet representation to String
     * @return binary string, intended for use by GUI
     */
    public String toBinaryString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < items.length(); ++i) {
            if (items.get(i))
                sb.append('1');
            else
                sb.append('0');
        }

        return sb.toString();
    }

    /**
     * Gets names of all items
     * @return names of items in a knapsack, intended for use by GUI
     */
    public Collection<String> listItems() {
        Collection<String> result = new ArrayList<String>();

        for (int i = 0; i < items.length(); ++i) {
            if (items.get(i) && itemsAvailable.getItem(i).getName().length() > 0)
                result.add(itemsAvailable.getItem(i).getName());
        }

        return result;
    }

    /**
     * Gets Knapsack size
     * @return size
     */
    public int getKnapsackSize() {
        int size = 0;
        for (int i = 0; i < items.length(); ++i) {
            if (items.get(i) && itemsAvailable.getItem(i).getName().length() > 0)
                size += itemsAvailable.getItem(i).getSize();
        }

        return size;
    }

    /**
     * Compares this object to other Knapsack by fitness value.
     * Comparison is done in a REVERSE order.
     * @param o
     * @return -1 if this is greater, 0 if equal, 1 if other is greater
     */
    @Override
    public int compareTo(Knapsack o) {
        int thisFitness = this.fitness();
        int otherFitness = o.fitness();

        return (thisFitness > otherFitness ? -1 : (thisFitness == otherFitness ? 0 : 1));
    }
}
