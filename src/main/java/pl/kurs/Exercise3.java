package pl.kurs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercise3 {
    public static void main(String[] args) {
        int[] array;
//        array = superPrimesGeneratorFromRange(0, 0);
//        array = superPrimesGeneratorFromRange(0, 2);
//        array = superPrimesGeneratorFromRange(2, 2);
//        array = superPrimesGeneratorFromRange(2, 3);
//        array = superPrimesGeneratorFromRange(2, 4);
//        array = superPrimesGeneratorFromRange(5, 2);
//        array = superPrimesGeneratorFromRange(5, 5);
//        array = superPrimesGeneratorFromRange(5, 7);
//        array = superPrimesGeneratorFromRange(0, 20);
//        array = superPrimesGeneratorFromRange(5000, 9967);
        array = superPrimesGeneratorFromRange(5000, 60_000);
//        array = superPrimesGeneratorFromRange(0, 100);
//        array = superPrimesGeneratorFromRange(0, 100);
//        array = superPrimesGeneratorFromRange(0, -1);

        System.out.println(Arrays.toString(array));
        System.out.println("Liczba liczb super pierwszych w podanym przedziale = " + array.length);
    }

    public static int[] superPrimesGeneratorFromRange(int from, int to) {
        if (checkForWrongInputs(from, to)) return new int[0];

        return convertIntegerListToArray(getPrimeNumbersWithPrimeSumOfDigits(from, to));
    }

    private static List<Integer> getPrimeNumbersWithPrimeSumOfDigits(int from, int to) {
        List<Integer> primesList = new ArrayList<>();

        addNumber2And3IfTheyAreInRange(from, to, primesList);

        int[] result = setUpFirstNumbers(from);

        int number1 = result[0];
        int number2 = result[1];
        int k = result[2];

        while (number2 <= to && (number1 <= to || number2 >= from)) {
            if (number2 >= from && numberIsPrime(number2) && sumOfDigitsIsPrime(number2)) {
                primesList.add(number2);
            }
            if (number1 <= to && numberIsPrime(number1) && sumOfDigitsIsPrime(number1)) {
                primesList.add(number1);
            }
            k++;
            number1 = 6 * k + 1;
            number2 = number1 - 2;
        }
        return primesList;
    }

    private static int[] setUpFirstNumbers(int from) {
        int minNumber = Math.max(from, 5);
        int k = minNumber / 6;
        int number1 = 6 * k + 1;

        if (number1 <= minNumber) {
            k++;
            number1 = 6 * k + 1;
        }
        int number2 = number1 - 2;

        return new int[]{number1, number2, k};
    }

    static boolean sumOfDigitsIsPrime(int number) {
        int tempNumber = number;
        int digit;
        int sum = 0;
        while (tempNumber > 0) {
            digit = tempNumber % 10;
            sum += digit;
            tempNumber /= 10;
        }
        return numberIsPrime(sum);
    }

    static boolean numberIsPrime(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true;
        if (number % 2 == 0 || number % 3 == 0) return false;

        for (int i = 5; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkForWrongInputs(int from, int to) {
        if (from > to || from < 0 || to < 0) {
            System.out.println("Błędne dane wejściowe");
            return true;
        }
        return false;
    }

    private static int[] convertIntegerListToArray(List<Integer> primesList) {
        int[] primesArray = new int[primesList.size()];
        if (!primesList.isEmpty()) {
            for (int i = 0; i < primesList.size(); i++) {
                primesArray[i] = primesList.get(i);
            }
        }
        return primesArray;
    }

    private static void addNumber2And3IfTheyAreInRange(int from, int to, List<Integer> primesList) {
        if ((from <= 2) && (2 <= to)) {
            primesList.add(2);
        }
        if ((from <= 3) && (3 <= to)) {
            primesList.add(3);
        }
    }
}
