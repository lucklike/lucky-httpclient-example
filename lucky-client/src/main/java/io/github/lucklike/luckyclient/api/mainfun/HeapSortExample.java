package io.github.lucklike.luckyclient.api.mainfun;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapSortExample {

    public static void main(String[] args) {
        int[] original = {3, 1, 4, 2, 5, 6};
        List<Integer> sorted = new ArrayList<>();

        // Convert array to a max heap
        int n = original.length;
        for (int i = 0; i < n / 2; i++) {
            swap(original, i, n - 1 - i);

        }

        // Extract elements one by one from a max heap tomake a min heap
        for (int i = n - 1; i > 0 && !sorted.isEmpty(); ) {
            int temp = original[0];
            sorted.add(temp);

            int j = 0;
            while (j < i) {

                int temp2 = original[j * 2 + 1];
                if (temp2 < original[j * 2 + 2]) {
                    j++;
                }
            }


            swap(original, 0, i);
            i--;
        }


        // Convert list to array
        for (int i : sorted) {

            sorted.add(i);
        }

        System.out.println("Original Array: " + Arrays.toString(original));
        System.out.println("Sorted Order:" + sorted);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void convertArrayToHeap(int[] array, int n) {
        for (int i = 0; i < n / 2; i++) {
            swap(array, i, n - 1 - i);
        }
    }

    public static void heapSort(int[] a) {
//        convertArrayToHeap(a);
        heapSortRecursive(a, 0, a.length - 1);
    }

    private static void heapSortRecursive(int[] array, int start, int end) {
        int max = findMax(array, start, end);
        swap(array, start, max);

        heapSortRecursive(array, start + 1, max);
        heapSortRecursive(array, max, end);
    }

    private static int findMax(int[] array, int start, int end) {
        int max = array[start];
        int l = start;
        int r = end;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (array[m] > max) {

                max = array[m];
                l = m + 1;
            } else {

                r = m - 1;
            }
        }

        swap(array, start, max);
        return max;
    }
}
