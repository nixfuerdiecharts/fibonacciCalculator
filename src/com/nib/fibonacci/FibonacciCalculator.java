package com.nib.fibonacci;

import java.math.BigInteger;

/**
 * Calculator class for nth fibonacci number with methods with different calculation modes
 *
 * @author Niko
 */
public class FibonacciCalculator {
    /**
     * Memoization cache for cache based calculation modes
     */
    private final BigInteger[] cache = new BigInteger[5000];

    /**
     * Constructor
     */
    public FibonacciCalculator() {
        //Set the defined fibonacci numbers in cache
        cache[0] = BigInteger.ZERO;
        cache[1] = BigInteger.ONE;
    }

    /**
     * Entry point for fibonacci calculation
     *
     * @param nthFibNumber    nth number to calculate the corresponding fibonacci number
     * @param calculationMode Determines the mode of calculation for fibonacci numbers
     * @return Corresponding fibonacci number for the given parameter
     * @see CalculationMode
     */
    public BigInteger calculate(int nthFibNumber, CalculationMode calculationMode) {
        // Check for illegal argument
        if (nthFibNumber < 0) {
            //Given argument is illegal for fibonacci algorithm
            //Also ensure that the argument is >=0 in methods for different calculation modes
            throw new IllegalArgumentException("Illegal Argument in recursive fibonacci calculation. Argument can only be 0 or bigger");
        }

        //Determine which calculation mode is chosen
        return switch (calculationMode) {
            case RECURSIVE -> fibonacciRecursive(nthFibNumber);
            case RECURSIVECACHE -> fibonacciRecursiveWithCache(nthFibNumber);
            case ITERATIVE -> fibonacciIterative(nthFibNumber);
            case ITERATIVECACHE -> fibonacciIterativeWithCache(nthFibNumber);
        };
    }

    /**
     * Calculation for nth fibonacci number with recursion
     *
     * @param nthFibNumber nth number to calculate the corresponding fibonacci number
     * @return Corresponding fibonacci number for the given parameter
     */
    private BigInteger fibonacciRecursive(int nthFibNumber) {
        BigInteger result;

        //Determine whether the recursion abortion is reached or not
        if (nthFibNumber == 0) {
            result = BigInteger.ZERO;
        } else if (nthFibNumber == 1) {
            result = BigInteger.ONE;
        } else {
            result = fibonacciRecursive(nthFibNumber - 1).add(fibonacciRecursive(nthFibNumber - 2));
        }

        return result;
    }

    /**
     * Calculation for nth fibonacci number with recursion and cache
     *
     * @param nthFibNumber nth number to calculate the corresponding fibonacci number
     * @return Corresponding fibonacci number for the given parameter
     */
    private BigInteger fibonacciRecursiveWithCache(int nthFibNumber) {
        //Get the nth fibonacci number from cache
        BigInteger result = this.getCache()[nthFibNumber];

        //if nth fibonacci number in cache is not set yet (-->null), then calculate it
        if (result == null) {
            //Not in cache yet, so needs to be calculated

            //Determine whether the recursion abortion is reached or not
            if (nthFibNumber == 0) {
                result = BigInteger.ZERO;
            } else if (nthFibNumber == 1) {
                result = BigInteger.ONE;
            } else {
                // nth number must be bigger than 1 here
                result = fibonacciRecursiveWithCache(nthFibNumber - 1).add(fibonacciRecursive(nthFibNumber - 2));
                //Save in cache so same fibonacci number does not have to be calculated more than one time
                this.setCache(nthFibNumber, result);
            }
        } else {
            //Already in cache
            result = this.getCache()[nthFibNumber];
        }

        return result;
    }

    /**
     * Calculation for nth fibonacci number with iteration
     *
     * @param nthFibNumber nth number to calculate the corresponding fibonacci number
     * @return Corresponding fibonacci number for the given parameter
     */
    private BigInteger fibonacciIterative(int nthFibNumber) {
        //Helper Variable for n-1-th fibonacci number
        BigInteger fibNMO = BigInteger.ONE;
        //Helper Variable for n-2-th fibonacci number
        BigInteger fibNMT = BigInteger.ZERO;
        //Variable to store the corresponding fibonacci number for given parameter
        BigInteger result = fibNMT;

        // for n=[0;1] is fibonacci=n
        if (nthFibNumber <= 1) {
            result = BigInteger.valueOf(nthFibNumber);
        }

        for (int i = 2; i <= nthFibNumber; i++) {
            //calculate current fibonacci number with n-1 + n-2
            result = fibNMO.add(fibNMT);
            //set helpers for next iteration
            fibNMT = fibNMO;
            fibNMO = result;

            // set cache for iterative with cache calculation mode
            this.setCache(i, result);
        }

        return result;
    }

    /**
     * Calculation for nth fibonacci number with iteration and cache
     *
     * @param nthFibNumber nth number to calculate the corresponding fibonacci number
     * @return Corresponding fibonacci number for the given parameter
     */
    private BigInteger fibonacciIterativeWithCache(int nthFibNumber) {
        BigInteger result = this.getCache()[nthFibNumber];

        if (result == null) {
            result = fibonacciIterative(nthFibNumber);
        }

        return result;
    }

    /**
     * Setter for cache
     *
     * @param index     index of cache array equivalent to n-th fibonacci number
     * @param fibonacci corresponding fibonacci number to array index
     */
    public void setCache(int index, BigInteger fibonacci) {
        this.cache[index] = fibonacci;
    }

    /**
     * Getter for cache
     *
     * @return Cache that is saved in the FibonacciCalculator instance
     */
    public BigInteger[] getCache() {
        return cache;
    }
}
