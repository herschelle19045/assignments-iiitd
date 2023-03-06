package Lab1;

import java.util.Scanner;

public class Converting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();
            System.out.println(converting(x, y, z));
        }
    }

    static int converting(int a, int b, int c) {
        if(c == 1 && a < b) {
            return -1;
        }
        if(a == b) {
            return 0;
        }
        if(a-b == 1) {
            return 1;
        }
        if(a-b == 2) {
            return 1;
        }
        if(a > b) {
            return 1+converting(a-2, b, c);
        }
        return 1+converting(a*c, b, c);
    }

    /* Alternate solution
    public static int convert(int a, int b, int c) {
        if (a >= b)
            return (a - b + 1) / 2;
        else if (b % c == 0)
            return 1 + convert(a, b / c, c);
        else {
            int d = c - b % c;
            return (d + 1) / 2 + convert(a, b + d, c);
        }
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner (System.in);
        int testcases = sc.nextInt();

        for (int i = 0; i < testcases; i++) {
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
            int ans = convert(a, b, c);
            System.out.println(ans);
        }
    }
     */
}
