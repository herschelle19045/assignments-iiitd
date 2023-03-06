package Lab2;

import java.util.Scanner;

public class NarcissisticNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for(int i=0; i<t; i++) {
            long n = scanner.nextLong();
            String string = String.valueOf(n);
            long length = string.length();
            recur(n, length);
            if(n == sum) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
            sum = 0;
        }
    }
    static long sum = 0;
    public static void recur(long number, long length) {
        if(number < 1) {
            return;
        }
        long num = number%10;
        sum += Math.pow(num, length);
        recur(number/10, length);
    }
}
