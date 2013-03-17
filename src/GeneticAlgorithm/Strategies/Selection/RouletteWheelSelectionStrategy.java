package GeneticAlgorithm.Strategies.Selection;

import GeneticAlgorithm.Models.Knapsack;
import GeneticAlgorithm.Models.Population;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class RouletteWheelSelectionStrategy extends SelectionStrategy {

    @Override
    public Collection<Knapsack> select(Population population) {
        // TODO getFitnessSum of a population will be computed many times
        // for a single population - easy optimization opportunity
        int populationFitness = population.getFitnessSum();

        Collection<Knapsack> result = new ArrayList<>();

        Random rand = new Random();

        int fitnessThreshold = rand.nextInt(populationFitness);
        result.add(selectGenome(population, fitnessThreshold));

        fitnessThreshold = rand.nextInt(populationFitness);
        result.add(selectGenome(population, fitnessThreshold));

        return result;
    }

    private Knapsack selectGenome(Population population, int fitnessThreshold) {
        int currentFitnessSum = 0;
        for (Knapsack k : population.getKnapsacks()) {
            currentFitnessSum += k.fitness();

            if (currentFitnessSum >= fitnessThreshold)
                return k;
        }

        // Should never happen
        return null;
    }
}
