package GeneticAlgorithm.Strategies.Selection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import GeneticAlgorithm.Models.Knapsack;
import GeneticAlgorithm.Models.Population;

public class RankSelectionStrategy extends SelectionStrategy {

    @Override
    public List<Knapsack> select(Population population) {
        List<Knapsack> result = new ArrayList<>();
        
        int populationFitness = sum(population.getPopulationSize());
        
        Random rand = new Random();
        
        Iterator<Knapsack> knapsacks = population.getBestKnapsacks(population.getPopulationSize()).iterator();
        
        for (int i = 0; i < 2; ++i) {
            result.add(selectGenome(knapsacks, rand.nextInt(populationFitness)));
        }
        
        return result;
    }
    
    private int sum(int max) {
        int result = 0;
        
        for (int i = 1; i <= max; ++i) {
            result += i;
        }
        
        return result;
    }
    
    private Knapsack selectGenome(Iterator<Knapsack> iter, int threshold) {
        int currentSum = 0;
        
        for (int i = 1;; currentSum += i++, iter.next()) {
            if (currentSum >= threshold)
                return iter.next();
        }
    }

}
