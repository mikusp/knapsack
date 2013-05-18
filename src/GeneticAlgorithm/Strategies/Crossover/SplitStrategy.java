package GeneticAlgorithm.Strategies.Crossover;

import GeneticAlgorithm.Models.Knapsack;
import GeneticAlgorithm.Utils.RandomBoolean;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Random;

public class SplitStrategy extends CrossoverStrategy {

    public SplitStrategy(double probability) {
        super(probability);
    }

    /**
     * Metoda podziału genów na lewą i prawą stronę,
     * Bierzemy losową ilość genów jednego plecaka i krzyżujemy z pozostałymi genami drugiego plecaka
     * @param left
     * @param right
     */
    @Override
    public Collection<Knapsack> crossover(Knapsack left, Knapsack right) {
        boolean doCrossover = RandomBoolean.get(probability);
        Collection<Knapsack> result;

        if (doCrossover) {
            int pivotPoint = generatePivotPoint(left.getItemsAvailable().getSize());

            result = crossoverAtPoint(left, right, pivotPoint);
        }
        else {
            result = new ArrayList<>();
            result.add(left);
            result.add(right);
        }

        return result;
    }

    /**
     * Generates an int from range [1, genomeLength - 1].
     * 0 or genomeLength would not make any difference from not doing
     * crossover.
     * @param genomeLength
     * @return int in [1, genomeLength - 1] range
     *
     * TODO should be reused in MultipleSplitStrategy
     */
    private int generatePivotPoint(int genomeLength) {
        // result is in range [0, genomeLength - 2]
        assert(genomeLength >= 2);
        int result = (new Random()).nextInt(genomeLength - 1);

        // int in range [1, genomeLength - 1]
        assert(result >= 0 && result <= genomeLength - 2);
        return result + 1;
    }

    /**
     * Example: left  11111111111
     *          right 00000000000
     * Crossover at 5
     * Result
     *          off1  11111000000
     *          off2  00000111111
     * @param left
     * @param right
     * @param pivotPoint
     * @return
     */
    private Collection<Knapsack> crossoverAtPoint(Knapsack left, Knapsack right, int pivotPoint) {
//        System.out.println("Początek crossover:");
        BitSet leftGenome = left.getItemsInside();
        BitSet rightGenome = right.getItemsInside();

        BitSet leftOffspringGenome = new BitSet(left.getItemsAvailable().getSize());
        BitSet rightOffspringGenome = new BitSet(left.getItemsAvailable().getSize());

        for (int i = 0; i < left.getItemsAvailable().getSize(); ++i) {
            if (i < pivotPoint) {
                leftOffspringGenome.set(i, leftGenome.get(i));
                rightOffspringGenome.set(i, rightGenome.get(i));
            }
            else {
                leftOffspringGenome.set(i, rightGenome.get(i));
                rightOffspringGenome.set(i, leftGenome.get(i));
            }
        }

        assert(leftGenome.size() == leftOffspringGenome.size());
        assert(rightGenome.size() == rightOffspringGenome.size());

//        System.out.println("lewy before:" + left.getItemsInside().toString());
//        System.out.println("lewy offspring:" + leftOffspringGenome.toString());
//
//        System.out.println("right before:" + right.getItemsInside().toString());
//        System.out.println("right offspring:" + rightOffspringGenome.toString());

        Collection<Knapsack> result = new ArrayList<>();
        result.add(new Knapsack(leftOffspringGenome,
                left.getSizeConstraint(),
                left.getItemsAvailable()));
        result.add(new Knapsack(rightOffspringGenome,
                right.getSizeConstraint(),
                right.getItemsAvailable()));

        return result;
    }
}
