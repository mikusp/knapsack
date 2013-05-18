/**
 * 
 */
package GeneticAlgorithm.Strategies.Crossover;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;

import GeneticAlgorithm.Models.Knapsack;
import GeneticAlgorithm.Utils.RandomBoolean;

/**
 * @author mgw
 *
 */
public class UniformStrategy extends CrossoverStrategy {

    private final double genomePreferenceProbabilty;
    
    /**
     * @param probability
     */
    public UniformStrategy(double probability, double genomePreferenceProbability) {
        super(probability);
        this.genomePreferenceProbabilty = genomePreferenceProbability;
    }

    /* (non-Javadoc)
     * @see GeneticAlgorithm.Strategies.Crossover.CrossoverStrategy#crossover(GeneticAlgorithm.Models.Knapsack, GeneticAlgorithm.Models.Knapsack)
     */
    @Override
    public Collection<Knapsack> crossover(Knapsack left, Knapsack right) {
        boolean doCrossover = RandomBoolean.get(probability);
        Collection<Knapsack> result;
        
        if (doCrossover) {
            result = realCrossover(left, right);
        }
        else {
            result = new ArrayList<>();
            result.add(left);
            result.add(right);
        }
        
        return result;
    }
    
    private Collection<Knapsack> realCrossover(Knapsack left, Knapsack right) {
        BitSet leftGenome = left.getItemsInside();
        BitSet rightGenome = right.getItemsInside();
        
        BitSet leftOffspring = new BitSet(left.getItemsAvailable().getSize());
        BitSet rightOffspring = new BitSet(right.getItemsAvailable().getSize());
        
        for (int i = 0; i < left.getItemsAvailable().getSize(); ++i) {
            if (RandomBoolean.get(genomePreferenceProbabilty)) {
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

}
