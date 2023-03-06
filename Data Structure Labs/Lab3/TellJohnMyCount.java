package Lab3;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class TellJohnMyCount {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] myArray = new int[n];
        for(int i=0; i<n; i++) {
            myArray[i] = scanner.nextInt();
        }
        int t = scanner.nextInt();

        for(int i=0; i<t; i++) {
            int testcase = scanner.nextInt();
            int leftIndex = findLeftIndex(myArray, testcase);
            int rightIndex = findRightIndex(myArray, testcase) - 1;
            if(rightIndex < leftIndex) {
                System.out.println(-1);
            }
            else if(rightIndex==leftIndex) {
                System.out.println(1);
            }
            else {
                System.out.println(rightIndex-leftIndex+1);
            }
        }
    }

    public static int findLeftIndex(int[] array, int target) {
        int start = 0;
        int end = array.length;

        while(start < end) {
            int mid = (start + end) / 2;
            if(target < array[mid] || target == array[mid]) {
                end = mid;
            }
            else {
                start = mid+1;
            }
        }
        return start;
    }
    public static int findRightIndex(int[] array, int target) {
        int start = 0;
        int end = array.length;

        while(start < end) {
            int mid = (start + end) / 2;
            if(target < array[mid]) {
                end = mid;
            }
            else {
                start = mid+1;
            }
        }
        return start;
    }
}
