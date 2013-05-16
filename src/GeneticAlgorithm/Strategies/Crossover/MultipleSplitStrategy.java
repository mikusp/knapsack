/**
 * 
 */
package GeneticAlgorithm.Strategies.Crossover;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Random;

import GeneticAlgorithm.Models.Knapsack;
import GeneticAlgorithm.Utils.RandomBoolean;

/**
 * @author mgw
 *
 */
public class MultipleSplitStrategy extends CrossoverStrategy {

    private final int numberOfPivotPoints;
    
    /**
     * 
     * @param probability
     * @param numberOfPivotPoints - IMPORTANT has to be smaller than number of items
     */
    public MultipleSplitStrategy(double probability, int numberOfPivotPoints) {
        super(probability);
        this.numberOfPivotPoints = numberOfPivotPoints;
    }

    /* (non-Javadoc)
     * @see GeneticAlgorithm.Strategies.Crossover.CrossoverStrategy#crossover(GeneticAlgorithm.Models.Knapsack, GeneticAlgorithm.Models.Knapsack)
     */
    @Override
    public Collection<Knapsack> crossover(Knapsack left, Knapsack right) {
        boolean doCrossover = RandomBoolean.get(probability);
        Collection<Knapsack> result;
        
        if (doCrossover) {
            int[] pivotPoints = new int[numberOfPivotPoints];
            
            populatePivotPoints(pivotPoints, left.getItemsAvailable().getSize());
            
            result = crossoverAtPoints(left, right, pivotPoints);
        }
        else {
            result = new ArrayList<>();
            result.add(left);
            result.add(right);
        }
        
        return result;
    }

    private void populatePivotPoints(int[] pivotPoints, int size) {
        for (int i = 0; i < numberOfPivotPoints; ++i) {
            pivotPoints[i] = generatePivotPoint(size);
        }
        
        Arrays.sort(pivotPoints);
    }
    
    private int generatePivotPoint(int genomeLength) {
        // result is in range [0, genomeLength - 2]
        assert(genomeLength >= 2);
        int result = (new Random()).nextInt(genomeLength - 1);

        // int in range [1, genomeLength - 1]
        assert(result >= 0 && result <= genomeLength - 2);
        return result + 1;
    }
    
    private Collection<Knapsack> crossoverAtPoints(Knapsack left, Knapsack right, int[] pivotPoints) {
        BitSet leftGenome = left.getItemsInside();
        BitSet rightGenome = right.getItemsInside();
        
        BitSet leftOffspring = new BitSet(left.getItemsAvailable().getSize());
        BitSet rightOffspring = new BitSet(right.getItemsAvailable().getSize());
        
        boolean[] mask = pointsToBooleanMask(pivotPoints, left.getItemsAvailable().getSize());
        
        for (int i = 0; i < leftGenome.length(); ++i) {
            if (mask[i]) {
                leftOffspring.set(i, leftGenome.get(i));
                rightOffspring.set(i, rightGenome.get(i));
            }
            else {
                leftOffspring.set(i, rightGenome.get(i));
                rightOffspring.set(i, leftGenome.get(i));
            }
        }
        
        Collection<Knapsack> result = new ArrayList<>();
        result.add(new Knapsack(leftOffspring, left.getSizeConstraint(), left.getItemsAvailable()));
        result.add(new Knapsack(rightOffspring, right.getSizeConstraint(), right.getItemsAvailable()));
        
        return result;
    }
    
    private boolean[] pointsToBooleanMask(int[] pivotPoints, int genomeLength) {
        boolean[] result = new boolean[genomeLength];
        
        boolean set = true;
        int currentIndex = 0;
        
        for (int i = 0; i < genomeLength; ++i) {
            result[i] = set;
            if (i >= pivotPoints[currentIndex]) {
                currentIndex++;
                set = !set;
            }
        }
        
        return result;
    }

}
