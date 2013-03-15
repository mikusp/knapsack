package GeneticAlgorithm.Strategies.Mutation;

import GeneticAlgorithm.Models.ItemCollection;
import GeneticAlgorithm.Models.Knapsack;

/**
 * General class used for mutating knapsacks.
 * Each implementation of this class have to define
 * mutate method that performs given type of mutation.
 */
public abstract class MutationStrategy {

    /**
     * Mutate knapsack with given probability of mutation.
     * @param knapsack offspring from bred knapsacks
     * @param itemsAvailable
     *
     */
    public abstract void mutate(Knapsack knapsack, ItemCollection itemsAvailable); //Czemu mamy tutaj drugi parametr itemCollection?
    //Nie możemy po prostu dopisać do Kanpsack fukcji getItemsAvaible() i przekawywać jeden parametr?
    //btw. wybacz, że tak, ale nie oganiam githuba i nie wiem gdzie sa komentarze
    //Mateusz
}
