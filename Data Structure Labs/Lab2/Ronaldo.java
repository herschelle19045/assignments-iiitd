package Lab2;

import java.util.Scanner;

public class Ronaldo {
    public static boolean possible(int[] groups_size, int num_groups, int available_mics, int max_students_to_1_mic) {
        int mics_needed = 0;
        for (int i = 0; i < num_groups; i++) {
            mics_needed += (groups_size[i] / max_students_to_1_mic);
            if (groups_size[i] % max_students_to_1_mic != 0)
                mics_needed += 1;
        }
        if (mics_needed <= available_mics)
            return true;
        return false;
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner (System.in);
        int num_groups = sc.nextInt(), available_mics = sc.nextInt();
        int[] groups_size = new int[num_groups];

        for (int i = 0; i < num_groups; i++) {
            groups_size[i] = sc.nextInt();
        }

        int low = 1, high = 100000000;
        while (low < high) {
            int mid = (low + high) / 2;
            if (possible(groups_size, num_groups, available_mics, mid))
                high = mid;
            else
                low = mid + 1;
        }

        System.out.println(low);
    }
}
