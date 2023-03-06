package Lab2;

import java.util.Scanner;

public class MadhavAndStairs {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        recur(n, 0, 0);
        System.out.println(output);
    }
    static int output = 0;
    public static void recur(int number, int step, int currentStair) {

        currentStair += step;

        if(currentStair > number) {
            return;
        }

        if(currentStair == number) {
            output++;
            return;
        }

        recur(number, 1, currentStair);
        recur(number, 2, currentStair);
        recur(number, 3, currentStair);
    }
}
