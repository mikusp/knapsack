/**
 * 
 */
package GeneticAlgorithm.Strategies.Mutation;

import java.util.ArrayList;
import java.util.List;

import GeneticAlgorithm.Models.Knapsack;
import GeneticAlgorithm.Utils.RandomBoolean;

/**
 * @author mgw
 *
 */
public class SingleOnlyImprovingStrategy extends MutationStrategy {

    /**
     * @param mutationProbability
     */
    public SingleOnlyImprovingStrategy(double mutationProbability) {
        super(mutationProbability);
    }

    /* (non-Javadoc)
     * @see GeneticAlgorithm.Strategies.Mutation.MutationStrategy#mutate(GeneticAlgorithm.Models.Knapsack)
     */
    @Override
    public void mutate(Knapsack knapsack) {
        boolean doMutation = RandomBoolean.get(mutationProbability);
        List<Integer> mutations = new ArrayList<>();
        
        if (doMutation) {
            double probabilityOfMutation = 1/(knapsack.getItemsAvailable().getSize());

            int fitnessBefore = knapsack.fitness();
            
            for (int i = 0; i < knapsack.getItemsAvailable().getSize(); ++i) {
                if (RandomBoolean.get(probabilityOfMutation)) {
                    knapsack.mutateItem(i);
                    mutations.add(i);
                }
            }
            
            int fitnessAfter = knapsack.fitness();
            
            if (fitnessAfter < fitnessBefore)
                for (Integer i : mutations)
                    knapsack.mutateItem(i);  
        }
    }
}
