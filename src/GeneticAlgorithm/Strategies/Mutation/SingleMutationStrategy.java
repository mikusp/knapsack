package GeneticAlgorithm.Strategies.Mutation;

import GeneticAlgorithm.Models.Knapsack;
import GeneticAlgorithm.Utils.RandomBoolean;

public class SingleMutationStrategy extends MutationStrategy {

    public SingleMutationStrategy(double mutationProbability) {
        super(mutationProbability);
    }

    /**
     * Mutate each item in a knapsack with 1/n probability
     * where n is a number of all available items. This strategy
     * should result, on average, in one mutation per knapsack.
     * @param knapsack offspring from bred knapsacks
     */
    @Override
    public void mutate(Knapsack knapsack) {
        boolean doMutation = RandomBoolean.get(mutationProbability);

        if (doMutation) {
            double probabilityOfMutation = 1/(knapsack.getItemsAvailable().getSize());

            for (int i = 0; i < knapsack.getItemsAvailable().getSize(); ++i)
                if (RandomBoolean.get(probabilityOfMutation))
                    knapsack.mutateItem(i);
        }
    }
}
