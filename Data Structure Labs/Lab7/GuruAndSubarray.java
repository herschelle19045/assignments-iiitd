package Lab7;

import java.util.*;

public class GuruAndSubarray {

    public static void mergeSort(long[] array) {
        int l = array.length;
        if (l < 2) {
            return;
        }
        int mid = l / 2;
        long[] left = Arrays.copyOf(array, mid);
        long[] right = Arrays.copyOfRange(array, mid, l);
        mergeSort(left);
        mergeSort(right);
        merge(left, right, array);
    }

    public static void merge(long[] left, long[] right, long[] arr) {
        int l = left.length;
        int r = right.length;
        int i = 0; int j = 0; int k = 0;
        while (i < l && j < r) {
            if (left[i] > right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < l) {
            arr[k++] = left[i++];
        }
        while (j < r) {
            arr[k++] = right[j++];
        }
    }

    static void recursion(int[] array, int start, int end, ArrayList<Long> list) {
        long sum = 0;
        for (int j = start; j <= end; j++) {
            sum += array[j];
        }
        list.add(sum);
        if(start == end) {
            return;
        }
        recursion(array, start+1, end, list);
        recursion(array, start, end-1, list);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++) {
                nums[j] = scanner.nextInt();
            }
            ArrayList<Long> list = new ArrayList<>();
            recursion(nums, 0, n-1, list);
            long[] ans = new long[list.size()];
            for (int j = 0; j < list.size(); j++) {
                ans[j] = list.get(j);
            }
            mergeSort(ans);
            for (int j = 0; j < k; j++) {
                System.out.print(ans[j] + " ");
            }
        }
    }
}
