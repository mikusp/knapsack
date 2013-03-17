package GeneticAlgorithm.Algorithm;

import GeneticAlgorithm.Models.Population;
import GeneticAlgorithm.Strategies.Crossover.CrossoverStrategy;
import GeneticAlgorithm.Strategies.Mutation.MutationStrategy;
import GeneticAlgorithm.Strategies.Selection.SelectionStrategy;

public class Algorithm {
    // TODO add state

    private final SelectionStrategy selectionStrategy;
    private final CrossoverStrategy crossoverStrategy;
    private final MutationStrategy mutationStrategy;
    private Population currentPopulation;

    public Algorithm(CrossoverStrategy crossoverStrategy,
                     SelectionStrategy selectionStrategy,
                     MutationStrategy mutationStrategy,
                     Population initialPopulation) {
        this.crossoverStrategy = crossoverStrategy;
        this.selectionStrategy = selectionStrategy;
        this.mutationStrategy = mutationStrategy;
        this.currentPopulation = initialPopulation;
    }

    public int getMaximalFitness() {
        return currentPopulation.getMaximalFitness();
    }

    public double getMeanFitness() {
        return currentPopulation.getMeanFitness();
    }

    public int getMinimalFitness() {
        return currentPopulation.getMinimalFitness();
    }

    public String getBestGenome() {
        return currentPopulation.getBestGenome();
    }

    /**
     * Runs a single step of an algorithm.
     * It should move currentPopulation to oldPopulation,
     * create newPopulation, run (select, crossover, mutate) in a loop
     * until newPopulation is the same size as an oldPopulation.
     * State should be changed to Stepping.
     * TODO
     */
    public void step() {

    }
}
