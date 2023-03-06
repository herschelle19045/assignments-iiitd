package MidSem;

import java.util.Arrays;
import java.util.Scanner;

public class Array {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(); int b = scanner.nextInt();
        int k = scanner.nextInt(); int m = scanner.nextInt();
        int[] arrayA = new int[a];
        int[] arrayB = new int[b];
        for(int i=0; i<a; i++) {
            arrayA[i] = scanner.nextInt();
        }
        for(int i=0; i<b; i++) {
            arrayB[i] = scanner.nextInt();
        }
        mergeSortA(arrayA);
        mergeSortD(arrayB);
        if(arrayA[k-1] < arrayB[m-1]) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }
    public static void mergeSortA(int[] num) {
        int l = num.length;
        if(l==1) {
            return;
        }
        int[] left = java.util.Arrays.copyOf(num, l/2);
        int[] right = java.util.Arrays.copyOfRange(num, l/2, l);

        mergeSortA(left);
        mergeSortA(right);
        mergeA(num, left, right);
    }

    public static void mergeA(int[] num, int[] left, int[] right) {
        int l = left.length;
        int r = right.length;

        int i=0; int j=0; int k=0;
        while(i<l && j<r) {
            if(left[i] < right[j]) {
                num[k++] = left[i++];
            }
            else {
                num[k++] = right[j++];
            }
        }
        while(i<l) {
            num[k++] = left[i++];
        }
        while(j<r) {
            num[k++] = right[j++];
        }
    }
    public static void mergeD(int[] num, int[] left, int[] right) {
        int l = left.length;
        int r = right.length;

        int i=0; int j=0; int k=0;
        while(i<l && j<r) {
            if(left[i] > right[j]) {
                num[k++] = left[i++];
            }
            else {
                num[k++] = right[j++];
            }
        }
        while(i<l) {
            num[k++] = left[i++];
        }
        while(j<r) {
            num[k++] = right[j++];
        }
    }
    public static void mergeSortD(int[] num) {
        int l = num.length;
        if(l==1) {
            return;
        }
        int[] left = java.util.Arrays.copyOf(num, l/2);
        int[] right = java.util.Arrays.copyOfRange(num, l/2, l);

        mergeSortD(left);
        mergeSortD(right);
        mergeD(num, left, right);
    }
}
