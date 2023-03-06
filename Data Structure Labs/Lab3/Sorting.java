package Lab3;

import java.util.Arrays;
import java.util.Scanner;

public class Sorting {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] myArray = new int[n];
        for (int i = 0; i < n; i++) {
            myArray[i] = scanner.nextInt();
        }
        mergeSort(myArray);
        for (int item : myArray) {
            System.out.print(item + " ");
        }
    }

    public static void mergeSort(int[] array) {
        int l = array.length;
        if (l < 2) {
            return;
        }
        int mid = l / 2;
        int[] left = Arrays.copyOf(array, mid);
        int[] right = Arrays.copyOfRange(array, mid, l);
        mergeSort(left);
        mergeSort(right);
        merge(left, right, array);
    }

    public static void merge(int[] left, int[] right, int[] arr) {
        int l = left.length;
        int r = right.length;
        int i = 0; int j = 0; int k = 0;
        while (i < l && j < r) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < l) {
            arr[k] = left[i];
            k++; i++;
        }
        while (j < r) {
            arr[k] = right[j];
            k++; j++;

        }
    }
}
