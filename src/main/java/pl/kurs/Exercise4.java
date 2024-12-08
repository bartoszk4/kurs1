package pl.kurs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Exercise4 {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/main/java/pl/kurs/liczby.txt"));

            String[] stringArray;
            int linia = 1;
            int minNumber;
            int maxNumber;

            while (scanner.hasNext()) {
                String line = scanner.nextLine().trim();
                System.out.println("Linia " + linia);
                if (line.isEmpty()) {
                    System.out.println("---------------------Pusta linia---------------------");
                    linia++;
                    System.out.println();
                    continue;
                }
                stringArray = line.split(" ");
                int[] intArray = new int[stringArray.length];

                for (int i = 0; i < stringArray.length; i++) {
                    intArray[i] = Integer.valueOf(stringArray[i]);
                }
                System.out.println("stringArray = " + Arrays.toString(stringArray));
                System.out.println("Monotoniczność : " + testIntArrayMonotonic(intArray));
                minNumber = searchMin(intArray);
                System.out.println("Najmniejsza liczba " + minNumber);
                maxNumber = searchMax(intArray);
                System.out.println("Największa liczba " + maxNumber);
                System.out.println("Najpopularniejsza liczba " + searchMostPopularNumber(intArray));

                if (containsAllNumberBetweenMinAndMax(minNumber, maxNumber, intArray)) {
                    System.out.println("Zawiera wszystkie liczby pomiędzy min i max");
                } else System.out.println("Nie zawiera wszsytkich liczb pomiędzy min i max");

                linia++;
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Błąd odczytu pliku");
        }
    }

    static boolean containsAllNumberBetweenMinAndMax(int min, int max, int[] arg) {
        if (min == max) return true;

        Set<Integer> integerSet = new HashSet<>();
        for (int i : arg) {
            integerSet.add(i);
        }
        for (int i = min; i < max; i++) {
            if (!integerSet.contains(i)) {
                return false;
            }
        }
        return true;
    }

    static String testIntArrayMonotonic(int[] arg) {
        if (arg.length == 1) return "Stały";

        boolean isRising = true;
        boolean isDecreasing = true;

        for (int i = 0; i < arg.length - 1; i++) {
            if (arg[i + 1] < arg[i]) isRising = false;
            if (arg[i + 1] > arg[i]) isDecreasing = false;
            if (!isRising && !isDecreasing) return "Niemonotoniczny";
        }

        if (isRising && isDecreasing) {
            return "Stały";
        } else if (isRising) {
            return "Rosnący";
        } else {
            return "Malejący";
        }
    }

    static int searchMin(int[] arg) {
        int minNumber = arg[0];
        for (int i = 1; i < arg.length; i++) {
            if (arg[i] < minNumber) minNumber = arg[i];
        }
        return minNumber;
    }

    static int searchMax(int[] arg) {
        int maxNumber = arg[0];
        for (int i = 1; i < arg.length; i++) {
            if (arg[i] > maxNumber) maxNumber = arg[i];
        }
        return maxNumber;
    }

    static String searchMostPopularNumber(int[] arg) {
        if (arg.length == 1) return String.valueOf(arg[0]);

        Map<Integer, Integer> numbersCounterMap = new HashMap<>();
        int mostPopularNumber = 0;
        int counterOfNumber;
        int counterOfMostPopular = 0;
        boolean noMostPopularNumber = true;

        for (int i : arg) {
            if (numbersCounterMap.containsKey(i)) {
                counterOfNumber = numbersCounterMap.get(i);
                numbersCounterMap.replace(i, ++counterOfNumber);
                if (counterOfNumber > counterOfMostPopular) {
                    counterOfMostPopular = counterOfNumber;
                    mostPopularNumber = i;
                    noMostPopularNumber = false;
                } else if (counterOfNumber == counterOfMostPopular) {
                    noMostPopularNumber = true;
                }
            } else {
                numbersCounterMap.put(i, 1);
            }
        }
        if (noMostPopularNumber) {
            return ("Brak najpopularniejszej liczby");
        } else return String.valueOf(mostPopularNumber);
    }

}
