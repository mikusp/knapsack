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
        
        Collections.sort(tournament);
        
        return tournament.subList(0, 2);
    }

}
