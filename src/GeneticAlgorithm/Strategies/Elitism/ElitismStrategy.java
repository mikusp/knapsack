package GeneticAlgorithm.Strategies.Elitism;

import GeneticAlgorithm.Models.Population;

public abstract class ElitismStrategy {

    /**
     * Copies elite from oldPopulation to newPopulation.
     * Choosing elite is different for each implementation.
     * @param oldPopulation
     * @param newPopulation
     */
    public abstract void copyElite(Population oldPopulation, Population newPopulation);
}
