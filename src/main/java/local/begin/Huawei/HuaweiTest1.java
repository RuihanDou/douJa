package local.begin.Huawei;

public class HuaweiTest1 {

    public int numSteps(int[] nums) {
        int length = nums.length;
        int min = length;

        for(int i = 1; i < length/2; i++) {
            int pace = 0;
            for(int j = i; j < length; j += nums[j]) {
                pace++;
                if(j == length - 1) {
                    if(pace < min) {
                        min = pace;
                    }
                    break;
                }
            }
        }

        if(min == 100) {
            return -1;
        } else {
            return min;
        }
    }

    public static void main(String[] args) {

    }
}
