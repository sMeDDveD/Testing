package Second;

import java.util.Arrays;

public class SortedArray {
    public static String delimiter = " ";

    private int[] array;
    private Result bsResult;

    public SortedArray(String s) {
        this(parseToArray(s));
    }

    public SortedArray(int[] aArray) {
        if (aArray == null) {
            throw new NullPointerException("The array was null");
        }
        if (aArray.length == 0) {
            throw new IllegalArgumentException("Size of the array should be non-zero");
        }
        if (!isSorted(aArray)) {
            throw new IllegalArgumentException("The array was not sorted");
        }

        array = aArray;
    }

    private static boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] parseToArray(String toParse) {
        if (toParse.equals("")) return null;
        String[] strings = toParse.split(delimiter);
        int[] resultArray = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            try {
                resultArray[i] = Integer.parseInt(strings[i]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Wrong array format");
            }
        }
        return resultArray;
    }

    public static int keyParse(String toParse) {
        if (toParse.isBlank() || toParse.isEmpty()) {
            throw new IllegalArgumentException("Key was null");
        }
        int answer;
        try {
            answer = Integer.parseInt(toParse);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Wrong key format");
        }
        return answer;
    }

    public Result binarySearch(int key) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = array[mid];

            if (midVal < key) {
                low = mid + 1;
            } else if (midVal > key) {
                high = mid - 1;
            } else {
                return bsResult = new Result(true, mid + 1);
            }
        }
        return bsResult = new Result();
    }

    public Result getBsResult() {
        return bsResult;
    }

    @Override
    public String toString() {
        return "array: " + Arrays.toString(array);
    }
}
