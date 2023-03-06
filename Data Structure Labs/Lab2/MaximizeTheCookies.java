package Lab2;

import java.util.Scanner;

public class MaximizeTheCookies {

    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        long n = input.nextLong();
        long k = input.nextLong();
        long[] A = new long[100005];
        long[] B = new long[100005];

        //take input of both of the arrays
        for (int i = 0; i < n; i++) {
            A[i] = input.nextLong();
        }
        for (int i = 0; i < n; i++) {
            B[i] = input.nextLong();
        }

        long left = 0, right = 2000000001;
        long res = -1;

        while (left <= right) {
            //assume that mid is the ans ..
            long mid = left + (right - left) / 2;
            long s = 0;
            for (int i = 0; i < n; i++) {
                //if mid*A[i]>B[i] then add then extra amount to variable s
                if (mid * A[i] > B[i]) {
                    s = s + mid * A[i] - B[i];
                }
                //if s become more than magic ingredient amount then move out of loop
                if (s > k) {
                    break;
                }
            }
            //if s>k then check ans in range left to mid-1
            if (s > k) {
                right = mid - 1;
            }
            //if s==k then you have found the ans
            else if (s == k) {
                res = mid;
                break;
            }
            // check in range mid+1 to right
            else {
                left = mid + 1;
            }
        }
        //if s==k is not possible then print left-1 as solution
        if (res == -1) {
            res = left - 1;
        }
        System.out.println(res);
    }
}
