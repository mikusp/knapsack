package GeneticAlgorithm.Strategies.Elitism;

import GeneticAlgorithm.Models.Population;

public class SimpleElitismStrategy extends ElitismStrategy {

    private final int amount;

    public SimpleElitismStrategy(int amount) {
        this.amount = amount;
    }

    /**
     * Copies a number of best knapsacks from oldPopulation to newPopulation.
     * Knapsacks are not changed in any way.
     * @param oldPopulation
     * @param newPopulation
     */
    @Override
    public void copyElite(Population oldPopulation, Population newPopulation) {
        newPopulation.add(oldPopulation.getBestKnapsacks(amount));
    }
}
