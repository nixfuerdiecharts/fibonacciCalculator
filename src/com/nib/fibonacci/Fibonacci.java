package com.nib.fibonacci;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Class to capsule console behaviour and handle user input
 */
public class Fibonacci {
    /**
     * Lower Boundary of user input
     */
    public static final int minFib = 0;
    /**
     * Upper Boundary of user input
     */
    public static final int maxFib = 5000;
    /**
     * Numbers to calculate the corresponding fibonacci numbers
     */
    private final List<Integer> numbersToCalculate;

    /**
     * Constructor for Fibonacci class
     */
    public Fibonacci() {
        this.numbersToCalculate = new ArrayList<>();
    }

    /**
     * Main method with user input handling and for given numbers output of corresponding fibonacci numbers
     *
     * @param args no args needed
     */
    public static void main(String[] args) {
        //Calculator for fibonacci numbers
        FibonacciCalculator fibonacciCalculator = new FibonacciCalculator();
        //Storage for numbers to calculate
        Fibonacci fibonacci = new Fibonacci();

        //Introduction for user
        System.out.println("Please enter the numbers to calculate the corresponding fibonacci number from");
        printRules();

        //Read numbers to calculate the corresponding fibonacci number from
        handleUserInput(fibonacci);

        //calculate all fibonacci numbers
        for (int nthFibNumber : fibonacci.getNumbersToCalculate()) {
            System.out.printf("Fibonacci number for %d is %d%n", nthFibNumber, fibonacciCalculator.calculate(nthFibNumber, CalculationMode.ITERATIVECACHE));
        }
    }

    /**
     * Capsulate the user input handling
     *
     * @param fibonacci Object to add the numbers to calculate to
     */
    public static void handleUserInput(Fibonacci fibonacci) {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        do {
            //Read user input and continue if it is no integer
            try {
                userInput = scanner.nextInt();
            } catch (InputMismatchException ime) {
                printRules();
                continue;
            }
            //Validate if user input is between Boundaries
            if (!validateInput(userInput)) {
                printRules();
            } else if (userInput != -1) {
                fibonacci.addNumberToCalculate(userInput);
            }
        } while (userInput != -1);
    }

    /**
     * Printer method to print the input boundaries
     */
    public static void printRules() {
        System.out.println("Please only enter decimal numbers between 0 and 5000");
        System.out.println("Stop with input -1");
    }

    /**
     * Input validation
     *
     * @param userInput User input
     * @return True if the user input is between the boundaries
     */
    public static boolean validateInput(int userInput) {
        return userInput >= minFib && userInput <= maxFib || userInput == -1;
    }

    /**
     * Gettern for numbers to calculate
     *
     * @return List of integer of numbers to calculate the corresponding fibonacci number
     */
    public List<Integer> getNumbersToCalculate() {
        return numbersToCalculate;
    }

    /**
     * Method to add a number to calculate to the list
     *
     * @param numberToCalculate number to calclate the corresponding fibonacci number later on
     */
    public void addNumberToCalculate(int numberToCalculate) {
        this.numbersToCalculate.add(numberToCalculate);
    }
}
