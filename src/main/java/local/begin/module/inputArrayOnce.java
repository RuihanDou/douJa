package local.begin.module;

import java.util.Arrays;
import java.util.Scanner;

public class inputArrayOnce {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine();
        String stringArray[] = inputString.split(" ");
        int nums[] = new int[stringArray.length];

        for (int i = 0; i < stringArray.length; i++) {
            nums[i] = Integer.parseInt(stringArray[i]);
        }

        System.out.println(Arrays.toString(nums));
    }
}

