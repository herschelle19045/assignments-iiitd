package MidSem;

import java.util.Arrays;
import java.util.Scanner;

public class HaggaAndReunion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        int[] nums = new int[n];
        for(int i=0; i<n; i++) {
            nums[i] = scanner.nextInt();
        }
        mergeSort(nums);
        for(int i=0; i<q; i++) {
            int x = scanner.nextInt();
            System.out.println(nums[x-1]);
        }

    }

    public static void mergeSort(int[] num) {
        int l = num.length;
        if(l==1) {
            return;
        }
        int[] left = Arrays.copyOf(num, l/2);
        int[] right = Arrays.copyOfRange(num, l/2, l);

        mergeSort(left);
        mergeSort(right);
        merge(num, left, right);
    }

    public static void merge(int[] num, int[] left, int[] right) {
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
}
