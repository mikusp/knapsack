/**
 * 
 */
package GeneticAlgorithm.Strategies.Selection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import GeneticAlgorithm.Models.Knapsack;
import GeneticAlgorithm.Models.Population;

/**
 * @author mgw
 *
 */
public class NTournamentSelectionStrategy extends SelectionStrategy {
    
    private final int tournamentSize;

    /**
     * @param tournamentSize
     */
    public NTournamentSelectionStrategy(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    @Override
    public List<Knapsack> select(Population population) {
        List<Knapsack> tournament = new ArrayList<>();
        
        for (int i = 0; i < tournamentSize; ++i) {
            tournament.add(population.getRandomKnapsack());
        }
        
        System.out.println("Population size: " + population.getPopulationSize());
        
        System.out.println("Got tournament with:");
        
        for (Knapsack k : tournament) {
            System.out.println("Knapsack: " + k.fitness());
        }
        
        Collections.sort(tournament);
        
        System.out.println("Returning " + tournament.get(0).fitness() +
                " " + tournament.get(1).fitness());
        
        return tournament.subList(0, 2);
    }

}
