package pl.kurs;

public class Exercise2 {
    public static void main(String[] args) {

//        int[] array = {1, 1, 1, 1, 1, 1};
//        int[] array = {-1, 1, -1, 1};
//        int[] array = {};
//        int[] array = {1};
//        int[] array = {0};
//        int[] array = {1, 3, 9, 27};
//        int[] array = {1, 2, 3, 4, 5};
//        int[] array = {1, 0, 3, 4, 5};
//        int[] array = {1, 0, 0, 0, 0};
//        int[] array = {1, 0, 0, 1, 0};
//        int[] array = {2, 3, 10, 5, 6};
//        int[] array = {0, 0, 0, 0, 0};
//        int[] array = {0, 1, 2, 3, 4};
//        int[] array = {4, 3, 2, 1, 0};
//        int[] array = {10, 5, 2, 1, 0};
        int[] array = {10, 7};

        System.out.println(getSequenceName(array));
    }

    public static String getSequenceName(int[] sequence) {

        if (checkIfArrayIsShortenOrEqualsTwo(sequence.length)) return "ARYTMETYCZNY" + '\n' + "GEOMETRYCZNY";

        boolean isArithmetic = true;
        boolean isGeometric = true;

        float r = sequence[1] - sequence[0];
        float q = 0;

        if (sequence[0] == 0) {
            if (checkIfWholeArrayIsZero(sequence)) {
                return "ARYTMETYCZNY" + '\n' + "GEOMETRYCZNY";
            } else {
                isGeometric = false;
            }
        } else {
            q = (float) sequence[1] / sequence[0];
        }

        boolean[] result = isArithmeticOrGeometric(r, q, isGeometric, sequence);
        isArithmetic = result[0];
        isGeometric = result[1];

        return getSequenceType(isArithmetic, isGeometric);
    }

    static boolean[] isArithmeticOrGeometric(float r, float q, boolean isGeometric, int[] sequence) {
        boolean arithmetic = true;
        boolean geometric = isGeometric;

        for (int i = 2; i < sequence.length; i++) {
            if (sequence[i] - sequence[i - 1] != r) arithmetic = false;
            if (geometric) {
                if (sequence[i - 1] != 0) {
                    if ((float) sequence[i] / sequence[i - 1] != q) geometric = false;
                } else {
                    if (sequence[i] != 0) geometric = false;
                }
            }
            if (!arithmetic && !geometric) break;
        }
        return new boolean[]{arithmetic, geometric};
    }

    static boolean checkIfWholeArrayIsZero(int[] sequence) {
        for (int i = 1; i < sequence.length; i++) {
            if (sequence[i] != 0) {
                return false;
            }
        }
        return true;
    }

    static boolean checkIfArrayIsShortenOrEqualsTwo(int length) {
        return length <= 2;
    }

    static String getSequenceType(boolean isArithmetic, boolean isGeometric) {
        if (isArithmetic || isGeometric) {
            if (isArithmetic) {
                String text = "ARYTMETYCZNY";
                if (isGeometric) {
                    text += '\n' + "GEOMETRYCZNY";
                }
                return text;
            } else {
                return "GEOMETRYCZNY";
            }
        }
        return "INNY";
    }

}
