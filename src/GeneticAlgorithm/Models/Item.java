package GeneticAlgorithm.Models;

/**
 * Class representing single item used in knapsack algorithm.
 * Each item has its size, value and (optional) name.
 */
public class Item {

    private final int size;
    private final int value;
    private final String name;

    public Item(int size, int value, String name) {
        this.size = size;
        this.value = value;
        this.name = name;
    }

    public Item(int size, int value) {
        this(size, value, "");
    }

    public int getSize() {
        return size;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
