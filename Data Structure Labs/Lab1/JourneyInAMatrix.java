package Lab1;

import java.util.Scanner;

public class JourneyInAMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[][] nums = new int[t][t];
        for(int i=0; i<t; i++) {
            for(int j=0; j<t; j++) {
                nums[i][j] = scanner.nextInt();
            }
        }
        forward(t, 1, 1, 0, nums);
        backward(t, t, t, 0, nums);
        System.out.println(fSum+bSum);
    }

    static int fSum = Integer.MIN_VALUE;
    static int bSum = Integer.MIN_VALUE;

    static void forward(int t, int x, int y, int sum, int[][] nums) {
        if(x<1 || y<1 || x>t || y>t) {
            return;
        }
        if(x<y) {
            return;
        }
        sum += nums[x-1][y-1];
        if(x==t && y==t) {
            if(sum > fSum) {
                fSum = sum;
            }
        }

        forward(t, x+1, y, sum, nums);
        forward(t, x, y+1, sum, nums);
    }

    static void backward(int t, int x, int y, int sum, int[][] nums) {
        if(x<1 || y<1 || x>t || y>t) {
            return;
        }
        if(x>y) {
            return;
        }
        sum += nums[x-1][y-1];
        if(x==1 && y==1) {
            if(sum > bSum) {
                bSum = sum;
            }
        }

        backward(t, x-1, y, sum, nums);
        backward(t, x, y-1, sum, nums);
    }
}
