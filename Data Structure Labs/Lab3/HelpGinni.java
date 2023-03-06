package Lab3;

import java.util.Scanner;

public class HelpGinni {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            long k = scanner.nextLong();
            long time = scanner.nextLong();
            if(time/k <= 10) {
                System.out.println(linear(k, time));
            }
            else {
                long output = binary(k, time);
                while (output * k * Math.log10(output) > time) {
                    output --;
                }
                System.out.println(output);
            }
        }
    }

    static long binary(long k, long time) {
        long start = 0;
        long end = Long.MAX_VALUE;
        long output = 0;
        while (start < end) {
            long mid = (start+end)/2;
            double ans = (k * mid * Math.log10(mid));
            if(ans<0) ans = Long.MAX_VALUE;
            if(ans < time) {
                start = mid+1;
            }
            if(ans > time) {
                end = mid;
            }
            if(ans == time) {
                output = mid;
                break;
            }
            output = mid;
        }
        return output;
    }

    static long linear(long k, long time) {
        long x = 1;
        double ans = 0;
        while (ans < time) {
            x++;
            ans = k * x * Math.log10(x);
        }
        return x-1;
    }
}
