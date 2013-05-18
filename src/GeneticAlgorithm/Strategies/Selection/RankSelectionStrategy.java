package GeneticAlgorithm.Strategies.Selection;

import java.util.ArrayList;
import java.util.Collections;
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
        
        List<Knapsack> knapsacks = population.getKnapsacks();
        
        Collections.sort(knapsacks);
        Collections.reverse(knapsacks);
        
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
    
    private Knapsack selectGenome(List<Knapsack> iter, int threshold) {
        int currentSum = 0;
        int currentFitness = 1;
        
        for (Knapsack k : iter) {
            currentSum += currentFitness++;
            
            if (currentSum >= threshold)
                return k;
        }
        
        System.err.println("ARGH!!! result is null");
        return null;
    }

}
