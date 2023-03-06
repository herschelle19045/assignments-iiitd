package Lab1;

import java.util.Scanner;

public class Vacation {
    public static int maximum_possible_points(int[][] points, int day, int activity) {
        if (day < 0)
            return 0;
        if (activity == -1) {
            int ans = 0;
            for (int i = 0; i < 3; i++) {
                ans = Math.max(ans, points[day][i] + maximum_possible_points(points, day - 1, i));
            }
            return ans;
        }
        int poss1 = points[day][(activity+1)%3] + maximum_possible_points(points, day - 1, (activity+1)%3);
        int poss2 = points[day][(activity-1+3)%3] + maximum_possible_points(points, day - 1, (activity-1+3)%3);

        return Math.max(poss1, poss2);
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] points = new int[n][3];

        for (int i = 0; i < n; i++) {
            points[i][0] = sc.nextInt();
            points[i][1] = sc.nextInt();
            points[i][2] = sc.nextInt();
        }

        int ans = maximum_possible_points(points, n-1, -1);
        System.out.println(ans);
    }
}
