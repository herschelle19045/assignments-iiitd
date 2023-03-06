package Lab8;

import java.util.Arrays;
import java.util.Scanner;

public class MaximizeGrains {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        mergeSort(array);
        int sum = 0;
        if(k < n) {
            for (int i = 0; i < k; i++) {
                sum += array[i];
            }
        }
        if(k > n) {
            for (int i = 0; i < n; i++) {
                sum += array[i];
            }
            int remaining = k-n;
            for (int i = 0; i < remaining; i++) {
                sum += array[i] / 2;
            }
        }
        System.out.println(sum);

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
            if (left[i] >= right[j]) {
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
