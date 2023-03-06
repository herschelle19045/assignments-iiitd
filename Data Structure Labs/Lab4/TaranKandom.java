package Lab4;

import java.util.Scanner;

public class TaranKandom {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); int q = scanner.nextInt();
        long[] nums = new long[t];
        for (int i = 0; i < t; i++) {
            nums[i] = scanner.nextLong();
        }
        for (int i = 0; i < q; i++) {
            long x = scanner.nextInt();
            int index = search(nums, x);
            if(index < t && x == nums[index]) {
                System.out.println(index+1);
            }
            else {
                if(x >= nums[nums.length-1]) {
                    System.out.println(-1);
                }
                else if(x < nums[0]) {
                    System.out.println(0);
                }
                else if(x < 0) {
                    System.out.println(index);
                }
                else if(index < t && x < nums[index]) {
                    System.out.println(index);
                }
                else {
                    System.out.println(index+1);
                }
            }
        }
    }

    static int search(long[] array, long target) {
        int start = 0;
        int end = array.length;
        int middle = 0;
        while (start < end) {
            int mid = (start + end) / 2;
            if(target < array[mid]) {
                end = mid;
            }
            else if(target > array[mid]) {
                start = mid+1;
            }
            else {
                return mid;
            }
            middle = (start+end)/2;
        }
        return middle;
    }
}
