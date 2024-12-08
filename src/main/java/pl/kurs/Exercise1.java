package pl.kurs;

import java.io.Serializable;

public class Exercise1 implements Serializable {

    public static void main(String[] args) {

        int[] array = {1,2,3,4,5,6};
//        int[] array = {6,5,4,3,2,1};
//        int[] array = {0};
//        int[] array = {1,2};
//        int[] array = {};
//        int[] array = {1, 1, 1, 1};
//        int[] array = {1,2,4};

        System.out.println(isArithmetic(array));
    }

    static boolean isArithmetic(int[] sequence) {
        if (sequence.length <= 2) return true;

        int r = sequence[1] - sequence[0];

        for (int i = 2; i < sequence.length; i++) {
            if (sequence[i] - sequence[i - 1] != r) return false;
        }
        return true;
    }
}
