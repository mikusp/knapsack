package GeneticAlgorithm.Algorithm;

import GeneticAlgorithm.Models.Knapsack;
import GeneticAlgorithm.Models.Population;
import GeneticAlgorithm.Strategies.Crossover.CrossoverStrategy;
import GeneticAlgorithm.Strategies.Elitism.ElitismStrategy;
import GeneticAlgorithm.Strategies.Mutation.MutationStrategy;
import GeneticAlgorithm.Strategies.Selection.SelectionStrategy;

import java.util.Collection;
import java.util.List;

public class Algorithm {
    // TODO add state

    private final SelectionStrategy selectionStrategy;
    private final CrossoverStrategy crossoverStrategy;
    private final MutationStrategy mutationStrategy;
    private final ElitismStrategy elitismStrategy;
    private Population currentPopulation;

    /**
     * Maybe Factory will be better with this many parameters?
     * @param crossoverStrategy
     * @param selectionStrategy
     * @param mutationStrategy
     * @param elitismStrategy
     * @param initialPopulation
     */
    public Algorithm(CrossoverStrategy crossoverStrategy,
                     SelectionStrategy selectionStrategy,
                     MutationStrategy mutationStrategy,
                     ElitismStrategy elitismStrategy,
                     Population initialPopulation) {
        this.crossoverStrategy = crossoverStrategy;
        this.selectionStrategy = selectionStrategy;
        this.mutationStrategy = mutationStrategy;
        this.elitismStrategy = elitismStrategy;
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

    public Collection<String> getBestItems() {
        return currentPopulation.getBestItems();
    }

    public int getBestItemsSize(){
        return currentPopulation.getBestItemsSize();
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
        // current WIP
        System.out.println("STEP begin");

        // Step 1. Create empty population
        Population newPopulation = new Population(currentPopulation.getKnapsackSize(),
                currentPopulation.getItemCollection());

        System.out.println("--- population created");

        // Step 2. Copy elite to new population
        elitismStrategy.copyElite(currentPopulation, newPopulation);
        System.out.println("--- elite copied");

        // Step 3. Breed genomes until new population is as big as old population
        // Apparent problem: if currentPopulation size is odd, newPopulation
        // will be bigger. Similarily, if it's even and we copy elite that's size
        // is odd we have the same situation.
        // definitely TODO
        while (newPopulation.getPopulationSize() < currentPopulation.getPopulationSize()) {
            System.out.println("---- current population size:" + currentPopulation.getPopulationSize());
            System.out.println("---- new population size:    " + newPopulation.getPopulationSize());
            List<Knapsack> parents = selectionStrategy.select(currentPopulation);

            System.out.println("----- selected parents");

            Collection<Knapsack> offspring =   // getting indices is very ugly
                    crossoverStrategy.crossover(parents.get(0), parents.get(1));

            System.out.println("----- crossover done");

            for (Knapsack k : offspring)
                mutationStrategy.mutate(k);

            System.out.println("----- mutation done");

            newPopulation.add(offspring);

            newPopulation.updateSize();
        }

        currentPopulation = newPopulation;

        System.out.println("STEP end");
    }
}
