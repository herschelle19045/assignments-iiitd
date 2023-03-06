package Lab7;

import java.util.Arrays;
import java.util.Scanner;

public class PostOrder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] preOrder = new int[n];
        for (int i = 0; i < n; i++) {
            preOrder[i] = scanner.nextInt();
        }
        int[] inOrder = new int[n];
        for (int i = 0; i < n; i++) {
            inOrder[i] = scanner.nextInt();
        }
        printPostOrder(inOrder,preOrder);
        System.out.println("\r");
        int[] newInOrder = Arrays.copyOf(inOrder, inOrder.length);
        Arrays.sort(newInOrder);
        boolean flag = isEqual(inOrder, newInOrder);
        if(flag) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }

    static void printPostOrder(int[] inOrder, int[] preOrder) {
        printPost(inOrder, preOrder, 0, inOrder.length-1, 0, preOrder.length-1);
    }

    static void printPost(int[] inOrder, int[] preOrder, int is, int ie, int ps, int pe) {
        if(ie < is || pe < ps) {
            return;
        }
        int root = search(inOrder, is, ie, preOrder[ps]);
        printPost(inOrder, preOrder, is, root-1, ps+1, root-1-is+ps+1);
        printPost(inOrder, preOrder, root+1, ie, root-is+ps+1, pe);
        System.out.print(preOrder[ps] + " ");
    }

    static int search(int[] array, int start, int end, int data) {
        for (int i = start; i <= end ; i++) {
            if(array[i] == data) {
                return i;
            }
        }
        return -1;
    }

    static boolean isEqual(int[] array1, int[] array2) {
        for (int i = 0; i < array1.length; i++) {
            if(array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
}
