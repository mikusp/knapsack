package GeneticAlgorithm.Strategies.Selection;

import GeneticAlgorithm.Models.Knapsack;
import GeneticAlgorithm.Models.Population;

import java.util.Collection;

public abstract class SelectionStrategy {

    /**
     * @param population current population of knapsacks
     * @return two knapsacks selected for breeding
     */
    public abstract Collection<Knapsack> select(Population population);
}
