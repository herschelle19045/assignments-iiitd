package Lab2;

import java.util.Scanner;

public class KnightMoves {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        knightMoves(m, n, k, 1, 1, 0);

        if (flag) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }

    static boolean flag = false;

    static void knightMoves(int m, int n, int k, int x, int y,int moves) {

        if (x == m && y == n) {
            flag = true;
            return;
        }
        if (x > 8 || y > 8 || x<1 || y<1) {
            return;
        }
        if (moves >= k) {
            return;
        }

        knightMoves(m, n, k, x + 1, y + 2,moves+1);
        knightMoves(m, n, k, x + 1, y - 2,moves+1);
        knightMoves(m, n, k, x - 1, y + 2,moves+1);
        knightMoves(m, n, k, x - 1, y - 2,moves+1);
        knightMoves(m, n, k, x + 2, y + 1,moves+1);
        knightMoves(m, n, k, x + 2, y - 1,moves+1);
        knightMoves(m, n, k, x - 2, y + 1,moves+1);
        knightMoves(m, n, k, x - 2, y - 1,moves+1);
    }
}
