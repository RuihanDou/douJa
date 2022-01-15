package local.begin.LeetCodeArray;

/**、
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 *
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 *
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 *
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *
 *
 * 提示：
 *
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 */
public class Solution0004 {

//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int m = nums1.length, n = nums2.length, sum = m + n;
//
//        int tar = sum / 2 + 1;
//        int[] merge = new int[tar];
//        int p1 = 0, p2 = 0;
//        while (p1 + p2 < tar){
//            if(p1 >= m){
//                merge[p1 + p2] = nums2[p2];
//                p2++;
//            } else if(p2 >= n){
//                merge[p1 + p2] = nums1[p1];
//                p1++;
//            } else if(nums1[p1] <= nums2[p2]){
//                merge[p1 + p2] = nums1[p1];
//                p1++;
//            } else {
//                merge[p1 + p2] = nums2[p2];
//                p2++;
//            }
//        }
//
//        if(sum % 2 == 1){
//            return 1.0 * merge[tar - 1];
//        } else {
//            return (merge[tar - 1] + merge[tar - 2]) / 2.0;
//        }
//    }

//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int m = nums1.length, n = nums2.length, sum = m + n;
//
//        int tar = sum / 2 + 1;
//        int cur = 0, pre = 0;
//        int p1 = 0, p2 = 0;
//        while (p1 + p2 < tar){
//            pre = cur;
//            if(p1 >= m){
//                cur = nums2[p2];
//                p2++;
//            } else if(p2 >= n){
//                cur = nums1[p1];
//                p1++;
//            } else if(nums1[p1] <= nums2[p2]){
//                cur = nums1[p1];
//                p1++;
//            } else {
//                cur = nums2[p2];
//                p2++;
//            }
//        }
//
//        if(sum % 2 == 1){
//            return 1.0 * cur;
//        } else {
//            return (cur + pre) / 2.0;
//        }
//    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // 减少讨论，让 num1 长度 保持 小于等于 nums2
        if(nums1.length > nums2.length){
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;

        // 分割线 左边的元素数量 = 分割线 右边的元素数量 [(m + n) 是偶数] 或者 左边 比 右边 多一个 totalLeft = (m + n + 1) / 2 [(m + n) 是奇数]
        int totalLeft = m + (n - m + 1) / 2;

        // 循环不变体在[left, right] 里寻找恰当的分割线
        // num1里分割线 x（分割线前有x个元素） 那 num2里分割线为 totalLeft - x
        // 设 num1 里分割线 为 i num2 里分割线为j
        // num1[i - 1] <= num2[j] && num2[j - 1] <= nums1[i]
        int left = 0, right = m;
        while (left < right){
            int i = left + (right - left + 1) / 2;
            int j = totalLeft - i;

            if(nums1[i - 1] > nums2[j]){
                // 下一轮搜索的区间为[left, i - 1]
                right = i - 1;
            } else {
                // 下一轮搜索的区间为[i, right]
                left = i;
            }
        }

        int i = left;
        int j = totalLeft - i;
        int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i-1];
        int nums1rightMin = i == m ? Integer.MAX_VALUE : nums1[i];
        int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j-1];
        int nums2rightMin = j == n ? Integer.MAX_VALUE :nums2[j];

        if ((m + n) % 2 == 1){
            return Math.max(nums1LeftMax, nums2LeftMax);
        } else {
            return (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1rightMin, nums2rightMin)) / 2.0;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};

        Solution0004 solution0004 = new Solution0004();
        System.out.println(solution0004.findMedianSortedArrays(nums1, nums2));
    }

}
