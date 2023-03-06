package Lab1;

import java.util.Scanner;

public class ProtickGoesHome {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();
        int x = input.nextInt();
        int y = input.nextInt();

        int[][] array = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = input.nextInt();
                array[i][j] = num;
            }
        }
        System.out.println(new Solution(m, n, x, y, array).recursion());
    }
}

class Solution {
    int m, n, x, y;
    int[][] array;
    int op;

    public Solution(int m, int n, int x, int y, int[][] array) {
        this.m = m;
        this.n = n;
        this.x = x;
        this.y = y;
        this.array = array;
    }

    int recursion() {
        recursion(1, 1, false);
        return op;
    }

    void recursion(int l, int r, boolean visit) {
        if (l > m || r > n) {
            return;
        }
        if (array[l - 1][r - 1] == 0) {
            return;
        }
        if (l == x && r == y) {
            visit = true;
        }
        if (l == m && r == n) {
            if (visit) {
                op++;
            }
            return;
        }

        recursion(l + 1, r, visit);
        recursion(l, r + 1, visit);
    }
}
