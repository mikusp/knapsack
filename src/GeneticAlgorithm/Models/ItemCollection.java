package GeneticAlgorithm.Models;

import java.util.ArrayList;

/**
 * Keeps a list of all available items
 */
public class ItemCollection {

    private final ArrayList<Item> items = new ArrayList<>();

    public ItemCollection() {
    }

    public void addItem(Item i) {
        items.add(i);
    }

    public Item getItem(int i) {
        return items.get(i);
    }

    public Iterable<Item> getAllItems() {
        return items;
    }
}
