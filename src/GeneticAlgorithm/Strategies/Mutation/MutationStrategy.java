package GeneticAlgorithm.Strategies.Mutation;

import GeneticAlgorithm.Models.Knapsack;

/**
 * General class used for mutating knapsacks.
 * Each implementation of this class have to define
 * mutate method that performs given type of mutation.
 */
public abstract class MutationStrategy {

    /**
     * Mutate knapsack with given probability of mutation.
     * @param knapsack offspring from bred knapsacks
     */
    public abstract void mutate(Knapsack knapsack);
}
