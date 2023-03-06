package MidSem;

import java.util.Scanner;

public class DeliveryBoySavit {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] nums = new int[t];
        for(int i=0; i<t; i++) {
            nums[i] = scanner.nextInt();
        }
        int x = scanner.nextInt();
        recur(x, 0, 0, nums, 0);
        System.out.println(ans);

    }
    static int ans = 0;
    static void recur(int target, int step, int sum, int[] nums, int var) {
        sum += step;
        if(sum > target) {
            return;
        }
        if(target == sum) {
            ans++;
            return;
        }
        for(int i=var; i<nums.length; i++) {
            recur(target, nums[i], sum, nums, i);
        }
    }
}
