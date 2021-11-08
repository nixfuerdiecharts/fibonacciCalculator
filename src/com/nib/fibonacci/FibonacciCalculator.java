package com.nib.fibonacci;

import java.math.BigInteger;

/**
 * Calculator class for nth fibonacci number with methods with different calculation modes
 *
 * @author Niko
 */
public class FibonacciCalculator {
    /**
     * Constructor
     */
    public FibonacciCalculator() {

    }

    /**
     * Entry point for fibonacci calculation
     *
     * @param nthFibNumber    nth number to calculate the corresponding fibonacci number
     * @param calculationMode Determines the mode of calculation for fibonacci numbers
     * @return Corresponding fibonacci number for the given parameter
     * @see CalculationMode
     */
    public BigInteger fibonacci(int nthFibNumber, CalculationMode calculationMode) {

        //Determine which calculation mode is chosen
        return switch (calculationMode) {
            case RECURSIVE -> fibonacciRecursive(nthFibNumber);
            case RECURSIVECACHE -> null;
            case ITERATIVE -> null;
            case ITERATIVECACHE -> null;
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
        } else if (nthFibNumber >= 2) {
            result = fibonacciRecursive(nthFibNumber - 1).add(fibonacciRecursive(nthFibNumber - 2));
        } else {
            //Given argument is illegal for fibonacci algorithm
            throw new IllegalArgumentException("Illegal Argument in recursive fibonacci calculation. Argument can only be 0 or bigger");
        }

        return result;
    }
}
