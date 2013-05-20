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
        List<Knapsack> result = new ArrayList<>();

        Knapsack k = getBestFromTournament(population, null);
        result.add(k);

        Knapsack l = getBestFromTournament(population, k);
        result.add(l);

        assert(result.size() == 2);

        return result;
    }

    private Knapsack getBestFromTournament(Population population, Knapsack knapsack) {
        List<Knapsack> tournament = new ArrayList<>();

        while (tournament.size() < tournamentSize) {
            Knapsack k = population.getRandomKnapsack();

            if (knapsack != null && k == knapsack)
                continue;

            if (!tournament.contains(k))
                tournament.add(k);
        }

        assert(tournament.size() == tournamentSize);

        Collections.sort(tournament);

        System.out.println("Wybrano plecak z fitness: " + tournament.get(0).fitness());

        return tournament.get(0);
    }

}
