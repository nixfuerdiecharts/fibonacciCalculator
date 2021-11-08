import com.nib.fibonacci.CalculationMode;
import com.nib.fibonacci.FibonacciCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class FibonacciCalculatorTest {
    // Boundaries for nth fib numbers
    static final int minFib = 0;
    static final int maxFib = 5000;
    static final int fibAmount = 523;
    //Instance of fibonacci calculator
    FibonacciCalculator fibonacciCalculator;

    @BeforeEach
    void init() {
        fibonacciCalculator = new FibonacciCalculator();
    }

    @Test
    @Timeout(30)
    void fibonacciRecursiveTest() {
        //Variable for random number between Boundaries
        int randomNumber;
        //Variable to store current fibonacci number
        BigInteger fibonacci;
        //Variable for execution time measurements
        long executionTime = 0;

        for (int i = 0; i < fibAmount; i++) {
            randomNumber = ThreadLocalRandom.current().nextInt(minFib, maxFib);
            long start = System.currentTimeMillis();
            fibonacci = fibonacciCalculator.fibonacci(randomNumber, CalculationMode.RECURSIVE);
            long end = System.currentTimeMillis();
            executionTime += end - start;
            System.out.printf("Fibonacci number for %d is %d%n", randomNumber, fibonacci);
        }
        System.out.printf("Execution time for %d n-th fibonacci number calculations was %d ms", fibAmount, executionTime);
    }

    @Test
    @Timeout(30)
    void fibonacciRecursiveCacheTest() {
        //Variable for random number between Boundaries
        int randomNumber;
        //Variable to store current fibonacci number
        BigInteger fibonacci;
        //Variable for execution time measurements
        long executionTime = 0;
        for (int i = 0; i < fibAmount; i++) {
            randomNumber = ThreadLocalRandom.current().nextInt(minFib, maxFib);
            long start = System.currentTimeMillis();
            fibonacci = fibonacciCalculator.fibonacci(randomNumber, CalculationMode.RECURSIVECACHE);
            long end = System.currentTimeMillis();
            executionTime += end - start;
            System.out.printf("Fibonacci number for %d is %d%n", randomNumber, fibonacci);
        }
        System.out.printf("Execution time for %d n-th fibonacci number calculations was %d ms", fibAmount, executionTime);
    }

}
