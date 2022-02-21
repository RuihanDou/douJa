package local.begin.leetCode;


/**
 * 838. 推多米诺
 * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
 *
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 *
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 *
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 *
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 *
 * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * 返回表示最终状态的字符串。
 *
 *
 * 示例 1：
 *
 * 输入：dominoes = "RR.L"
 * 输出："RR.L"
 * 解释：第一张多米诺骨牌没有给第二张施加额外的力。
 * 示例 2：
 *
 *
 * 输入：dominoes = ".L.R...LR..L.."
 * 输出："LL.RR.LLRRLL.."
 *
 *
 * 提示：
 *
 * n == dominoes.length
 * 1 <= n <= 105
 * dominoes[i] 为 'L'、'R' 或 '.'
 */
public class LeetCode0838Solution {
    /**
     * 枚举所有没有推动的骨牌
     *
     * 如果两边的骨牌同向，那么这段连续的树立骨牌会倒向同一侧
     * 如果两边的骨牌相对，那么这段骨牌会向中间倒
     * 如果两边的骨牌相反，那么这段骨牌会保持树立
     *
     * @param dominoes
     * @return
     */
    public String pushDominoes(String dominoes) {
        char[] s = dominoes.toCharArray();
        int n = s.length, i = 0;
        char left = 'L';
        // 找到一段连续的没有推动的骨牌
        while (i < n){
            int j = i;
            while (j < n && s[j] == '.'){
                j++;
            }
            char right = j < n ? s[j] : 'R';
            // 方向相同，这些树立的骨牌会倒向同一方向
            if(left == right){
                while (i < j){
                    s[i] = right;
                    i++;
                }
            }
            // 方向相对，那么从两侧向中间倒下
            else if(left == 'R' && right == 'L'){
                int k = j - 1;
                while (i < k){
                    s[i++] = 'R';
                    s[k--] = 'L';
                }
            }
            left = right;
            i = j + 1;
        }
        return new String(s);
    }

}
