package GeneticAlgorithm.Strategies.Crossover;

import GeneticAlgorithm.Models.Knapsack;

import java.util.Collection;

public abstract class CrossoverStrategy {

    protected final double probability;

    public CrossoverStrategy(double probability) {
        this.probability = probability;
    }

    /**
     * Krzyżowanie genów dwóch plecaków w reprodukcji.
     * Istnieje prawdopodobieństwo stworzenia słabszego plecaka.
     * @param left
     * @param right
     */
    public abstract Collection<Knapsack> crossover(Knapsack left, Knapsack right);
}
