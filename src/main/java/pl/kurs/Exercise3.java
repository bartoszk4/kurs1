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

        System.out.println(Arrays.toString(array));
        System.out.println("Liczba liczb super pierwszych w podanym przedziale = " + array.length);
    }

    static int[] superPrimesGeneratorFromRange(int from, int to) {
        if (from > to || from < 0 || to < 0) {
            System.out.println("Błędne dane wejściowe");
            return null;
        }

        List<Integer> primesList = new ArrayList<>();

        if ((from <= 2) && (2 <= to)) {
            primesList.add(2);
        }
        if ((from <= 3) && (3 <= to)) {
            primesList.add(3);
        }
        int minNumber = Math.max(from, 5);
        int k = minNumber / 6;
        int tempNumber1 = 6 * k + 1;

        if (tempNumber1 <= minNumber) {
            k++;
            tempNumber1 = 6 * k + 1;
        }
        int tempNumber2 = tempNumber1 - 2;

        while (tempNumber2 <= to && (tempNumber1 <= to || tempNumber2 >= from)) {
            if (tempNumber2 >= from && numberIsPrime(tempNumber2) && sumOfDigitsIsPrime(tempNumber2)) {
                primesList.add(tempNumber2);
            }
            if (tempNumber1 <= to && numberIsPrime(tempNumber1) && sumOfDigitsIsPrime(tempNumber1)) {
                primesList.add(tempNumber1);
            }
            k++;
            tempNumber1 = 6 * k + 1;
            tempNumber2 = tempNumber1 - 2;
        }

        int[] primesArray = new int[primesList.size()];

        if (!primesList.isEmpty()) {
            for (int i = 0; i < primesList.size(); i++) {
                primesArray[i] = primesList.get(i);
            }
        }
        return primesArray;
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
}
