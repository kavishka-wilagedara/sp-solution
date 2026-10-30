import java.util.Arrays;

public class Main {

    // Problem 01
    public static double sumCalculationByForLoop(double[] numbers){

        double sum = 0;

        for(int i = 0; i < numbers.length; i++){

            sum = numbers[i] + sum;

        }
        return sum;
    }


    public static double sumCalculationByWhileLoop(double[] numbers){

        double sum = 0;
        int i = 0;

        while (i < numbers.length) {
            sum += numbers[i];
            i++;
        }
        return sum;
    }

    public static double sumCalculationByRecursion(double[] numbers, int index) {

        if (index == numbers.length) {
            return 0;
        }

        return numbers[index] + sumCalculationByRecursion(numbers, index + 1);
    }

    // Problem 02
    public static String[] combineTwoArray(String[] array1, String[] array2) {

        String[] combinedArray = new String[array1.length + array2.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < array1.length && j < array2.length) {

            combinedArray[k++] = array1[i++];
            combinedArray[k++] = array2[j++];
        }

        while (i < array1.length) {
            combinedArray[k++] = array1[i++];
        }

        while (j < array2.length) {
            combinedArray[k++] = array2[j++];
        }

        return combinedArray;
    }

    // Problem 03
    public static void printFibonacci() {

        long first = 0;
        long second = 1;

        for (int i = 0; i < 100; i++) {

            System.out.print(first + " ");

            long next = first + second;

            first = second;
            second = next;
        }

        System.out.println();
    }

    // Problem 04
    public static String largestNumber(int[] numbers) {

        String[] arr = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

        if (arr[0].equals("0")) {
            return "0";
        }

        StringBuilder result = new StringBuilder();

        for (String num : arr) {
            result.append(num);
        }

        return result.toString();
    }

    // Problem 05
    public static void findCombinations() {
        int[] numbers = {1,2,3,4,5,6,7,8,9};

        StringBuilder numberArray = new StringBuilder();

        for (int i = 0; i < numbers.length; i++) {
            numberArray.append(numbers[i]);
        }

        String keyword = numberArray.toString();

        findExpressions(keyword, 0, 0, "");
    }

    public static void findExpressions(
            String numbers,
            int index,
            long sum,
            String expression) {

        // Check all digits are used
        if (index == numbers.length()) {

            if (sum == 100) {
                System.out.println(expression + " = " + sum);
            }

            return;
        }

        // All possible numbers by concatenation
        for (int i = index; i < numbers.length(); i++) {

            String current = numbers.substring(index, i + 1);

            long number = Long.parseLong(current);

            if (index == 0) {

                findExpressions(
                        numbers,
                        i + 1,
                        number,
                        current
                );

            } else {

                // Add operation
                findExpressions(
                        numbers,
                        i + 1,
                        sum + number,
                        expression + "+" + current
                );

                // Subtract operation
                findExpressions(
                        numbers,
                        i + 1,
                        sum - number,
                        expression + "-" + current
                );
            }
        }
    }
    public static void main(String[] args) {

        double [] numbers= {1,5.5,6};
        int [] numbersArray = {50, 2 , 1, 9};
        String[] array1 = {"a", "b", "c"};
        String[] array2 = {"1", "2", "3", "4", "5"};

        System.out.println("-----------------------Problem 01----------------------");
        System.out.println("Sum of for-loop: " + sumCalculationByForLoop(numbers));
        System.out.println("Sum of while-loop: " + sumCalculationByWhileLoop(numbers));
        System.out.println("Sum of recursion: " + sumCalculationByRecursion(numbers, 0));

        System.out.println("-----------------------Problem 02----------------------");
        System.out.println("Combined array: "+ Arrays.toString(combineTwoArray(array1, array2)));

        System.out.println("-----------------------Problem 03----------------------");
        printFibonacci();
        System.out.println("-----------------------Problem 04----------------------");
        System.out.println("Formatted array: " +largestNumber(numbersArray));

        System.out.println("-----------------------Problem 05----------------------");
        System.out.println("Expressions equal to 100:");
        findCombinations();
    }
}