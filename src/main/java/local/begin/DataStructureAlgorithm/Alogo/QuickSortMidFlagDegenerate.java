package local.begin.DataStructureAlgorithm.Alogo;

/**
 * 本类探索，如果快排 partition 每次取标定点都是数组区间的中点会造成什么样的算法退化
 * 需要更改 jvm 默认设定 为-Xss128m
 *
 * 当本partition方法中取标定点都为区间最左边的元素时，可想而知从左向右递增的有序数组的排序情况，该排序会退化为O(n^2)
 *
 * 本partition方法与QuickSortProto中类似，但取区间中间位置标定点作为Flag（与l位置交换）而非取随机标定点
 *
 * 理论上存在可以使得  取区间中间位置标定点作为Flag    的快速排序退化为O(n^2)的算法
 *
 * 本类中 generateSpecialArray(n) 方法为生成退化数组的方法
 *
 */
public class QuickSortMidFlagDegenerate {

    private QuickSortMidFlagDegenerate(){}

    private static <E> void swap(E[] arr, int i, int j) {
        E tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static Integer[] generateSpecialArray(int n){

        // 开空间
        Integer[] arr = new Integer[n];

        // 生成 arr[0...n-1] 的测试用例，其中最小值是 0
        generateSpecialArray(arr, 0, arr.length - 1, 0);

        return arr;
    }

    private static void generateSpecialArray(Integer[] arr, int l, int r, int value){
        // 递归到底的情况 ：如果 l > r, 处理区间为空的情况，直接返回
        if(l > r){
            return;
        }

        // 1、把最小值放到中间
        int mid = (l + r) / 2;
        arr[mid] = value;

        // 2、模拟 partition 过程，把中间元素和最左边元素交换位置
        swap(arr, l, mid);

        // 3、处理除了最左边元素之外，剩下的 n-1 个元素：
        // 所以， 处理区间变成了 arr[l+1  ...   r]. 同时，最小值 + 1
        generateSpecialArray(arr, l + 1, r, value + 1);

        // 4、处理号之后，还要把中间元素和最左边元素交换回来。
        swap(arr, l, mid);
    }

}
