package local.begin.dataStructureAlgorithm.helper;

import java.util.Random;

public class ArrayGenerator {

    private ArrayGenerator(){}

    public static Integer[] generateOrderedArray(int n) {

        Integer[] arr = new Integer[n];
        for(int i = 0; i < n; i++){
            arr[i] = i;
        }
        return arr;
    }

    /**
     *  生成随机数组，随机数范围为[0,bound)
     * @param n
     * @param bound
     * @return
     */
    public static Integer[] generateRandomArray(int n, int bound){

        Integer[] arr = new Integer[n];
        Random rnd = new Random();
        for(int i = 0; i < n; i++){
            arr[i] = rnd.nextInt(bound);
        }
        return arr;
    }

    /**
     * 生成随机字符串数组
     * @param n 数组长度
     * @param w 字符串长度
     * @return
     */
    public static String[] generateRandomStringArray(int n, int w){

        String[] arr = new String[n];

        // 33 - 126 区间的ascii 字符可以打印
        Random rnd = new Random();
        for (int i = 0; i < n; i++){
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < w; j++){
                sb.append((char) (rnd.nextInt(94) + 33));
            }
            arr[i] = sb.toString();
        }

        return arr;
    }

}
