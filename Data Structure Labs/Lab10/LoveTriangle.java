package Lab10;

import java.util.Scanner;

public class LoveTriangle {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] planes = new int[t+1];
        for (int i = 1; i <= t; i++) {
            planes[i] = scanner.nextInt();
        }
        boolean flag = false;
        for (int i = 1; i <= t; i++) {
            int elementAtFirstIndex = planes[i];
            int elementAtSecondIndex = planes[elementAtFirstIndex];
            if(planes[elementAtSecondIndex] == i) {
                flag = true;
                break;
            }
        }
        if(flag) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }
}
