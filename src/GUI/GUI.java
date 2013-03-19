package GUI;

import GeneticAlgorithm.Algorithm.Algorithm;
import GeneticAlgorithm.Models.Item;
import GeneticAlgorithm.Models.ItemCollection;
import GeneticAlgorithm.Models.Population;
import GeneticAlgorithm.Strategies.Crossover.CrossoverStrategy;
import GeneticAlgorithm.Strategies.Crossover.SplitStrategy;
import GeneticAlgorithm.Strategies.Elitism.ElitismStrategy;
import GeneticAlgorithm.Strategies.Elitism.NullElitismStrategy;
import GeneticAlgorithm.Strategies.Elitism.SimpleElitismStrategy;
import GeneticAlgorithm.Strategies.Mutation.MutationStrategy;
import GeneticAlgorithm.Strategies.Mutation.SingleMutationStrategy;
import GeneticAlgorithm.Strategies.Selection.RouletteWheelSelectionStrategy;
import GeneticAlgorithm.Strategies.Selection.SelectionStrategy;

import static java.lang.System.out;

public class GUI {

    public static void main(String[] args) {
        ItemCollection i = new ItemCollection();

        i.addItem(new Item(9, 150, "map"));
        i.addItem(new Item(13, 35, "compass"));
        i.addItem(new Item(153, 200, "water"));
        i.addItem(new Item(50, 160, "sandwich"));
        i.addItem(new Item(15, 60, "glucose"));
        i.addItem(new Item(68, 45, "tin"));
        i.addItem(new Item(27, 60, "banana"));
        i.addItem(new Item(39, 40, "apple"));
        i.addItem(new Item(23, 30, "cheese"));
        i.addItem(new Item(52, 10, "beer"));
        i.addItem(new Item(11, 70, "suntan"));
        i.addItem(new Item(32, 30, "camera"));
        i.addItem(new Item(24, 15, "Tshirt"));
        i.addItem(new Item(48, 10, "trousers"));
        i.addItem(new Item(73, 40, "umbrella"));
        i.addItem(new Item(42, 70, "trousers"));
        i.addItem(new Item(43, 75, "overclothes"));
        i.addItem(new Item(22, 80, "notecase"));
        i.addItem(new Item(7, 20, "sunglasses"));
        i.addItem(new Item(18, 12, "towel"));
        i.addItem(new Item(4, 50, "socks"));
        i.addItem(new Item(30, 10, "book"));
        i.addItem(new Item(90, 1, "notebook"));
        i.addItem(new Item(200, 150, "tent"));

        ElitismStrategy elitismStrategy = new SimpleElitismStrategy(2);
        CrossoverStrategy crossoverStrategy = new SplitStrategy(0.7);
        MutationStrategy mutationStrategy = new SingleMutationStrategy();
        SelectionStrategy selectionStrategy = new RouletteWheelSelectionStrategy();

        Population initialPopulation = new Population(500, 500, i);

        Algorithm a = new Algorithm(crossoverStrategy, selectionStrategy, mutationStrategy, elitismStrategy, initialPopulation);

        for (String s : a.getBestItems())
            out.println(s);
        out.println(a.getBestGenome());
        out.println(a.getMaximalFitness());
        out.println(a.getMeanFitness());
        out.println(a.getMinimalFitness());

        for (int jj = 0; jj < 20; ++jj)
            a.step();

        for (String s : a.getBestItems())
            out.println(s);
        out.println(a.getBestGenome());
        out.println(a.getMaximalFitness());
        out.println(a.getMeanFitness());
        out.println(a.getMinimalFitness());
    }
}
