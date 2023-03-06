package MidSem;

import java.util.Scanner;

public class DarenAndMidsem {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int t = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        long start = 0;
        long end = 1000000000;
        while (start != end) {
            long mid = (start+end)/2;
            long sum = 0;
            for (int value : nums) {
                sum += mid/value;
            }
            if(sum > t) {
                end = mid;
            }
            else {
                start = mid+1;
            }
        }
        System.out.println(start-1);
    }
}
