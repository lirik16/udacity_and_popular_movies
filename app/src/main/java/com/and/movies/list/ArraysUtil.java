package com.and.movies.list;

public class ArraysUtil {
    /**
     * Searches the specified array of ints for the first element
     * that is equal or greater than the specified value using the
     * binary search algorithm.  The array must be sorted (as
     * by the {@link java.util.Arrays#sort(int[])} method) prior to making this call.  If it
     * is not sorted, the results are undefined.
     *
     * @param a the array to be searched
     * @param key the specified value
     * @return index of the first element that is equal or greater than the specified value,
     *         if it is contained in the array; otherwise, <tt>a.length</tt>.
     */
    public static int lowerBound(int[] a, int key) {
        return innerLowerBound(a, 0, a.length, key);
    }

    private static int innerLowerBound(int[] array, int startIndex, int count, int key) {
        int it = 0;
        int step = 0;
        while (count > 0) {
            step = count / 2;
            it = startIndex + step;
            if (array[it] < key) {
                startIndex = ++it;
                count -= step + 1;
            } else {
                count = step;
            }
        }
        return startIndex;
    }
}
