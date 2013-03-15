package GeneticAlgorithm.Strategies.Crossover;

import GeneticAlgorithm.Models.Knapsack;

public abstract class CrossoverStrategy {

    /**
     * Krzyżowanie genów dwóch plecaków w reprodukcji.
     * Istnieje prawdopodobieństwo stworzenia słabszego plecaka.
     * @param knapsack
     */
    public abstract void crossover(Knapsack knapsack);
}
