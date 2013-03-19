package GeneticAlgorithm.Strategies.Selection;

import GeneticAlgorithm.Models.Knapsack;
import GeneticAlgorithm.Models.Population;

import java.util.List;

public abstract class SelectionStrategy {

    /**
     *
     * @param population current population of knapsacks
     * @return two knapsacks selected for breeding
     */
    public abstract List<Knapsack> select(Population population);
}
