package GeneticAlgorithm.Strategies.Elitism;

import GeneticAlgorithm.Models.Population;

public class NullElitismStrategy extends ElitismStrategy {

    public NullElitismStrategy() {

    }

    /**
     * Does nothing, used if elitism is not required.
     * @param oldPopulation
     * @param newPopulation
     */
    @Override
    public void copyElite(Population oldPopulation, Population newPopulation) {
        return;
    }
}
