package GeneticAlgorithm.Utils;

import java.util.Random;

public class RandomBoolean {

    public static boolean get(double probability) {
        Random r = new Random();
        return r.nextDouble() <= probability;
    }
}
