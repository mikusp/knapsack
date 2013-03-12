package GeneticAlgorithm.Strategies.Mutation;

import GeneticAlgorithm.Models.ItemCollection;
import GeneticAlgorithm.Models.Knapsack;
import GeneticAlgorithm.Utils.RandomBoolean;

public class SingleMutation extends MutationStrategy {

    /**
     * Mutate each item in a knapsack with 1/n probability
     * where n is a number of all available items. This strategy
     * should result, on average, in one mutation per knapsack.
     * @param knapsack offspring from bred knapsacks
     * @param itemsAvailable
     */
    @Override
    public void mutate(Knapsack knapsack, ItemCollection itemsAvailable) {
        double probabilityOfMutation = 1/(itemsAvailable.getSize());

        for (int i = 0; i < itemsAvailable.getSize(); ++i)
            if (RandomBoolean.get(probabilityOfMutation))
                knapsack.mutateItem(i);
    }
}
