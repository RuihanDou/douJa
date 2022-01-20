package local.begin.leetCode;


import java.util.ArrayList;
import java.util.List;

/**
 *93. 复原 IP 地址
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你不能重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 *
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 *
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * 示例 4：
 *
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * 示例 5：
 *
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 20
 * s 仅由数字组成
 */
public class LeetCode0093Solution {

    private static final int SEG_COUNT = 4;
    List<String> ans = new ArrayList<>();
    int[] segments = new int[SEG_COUNT];

    public List<String> restoreIpAddresses(String s) {
        segments = new int[SEG_COUNT];
        dfs(s, 0, 0);
        return ans;
    }

    private void dfs(String s, int segId, int segStart) {

        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么添加该答案
        if(segId == SEG_COUNT){
            if(segStart == s.length()){
                StringBuffer ipAddr = new StringBuffer();
                for(int i = 0; i < SEG_COUNT; i++){
                    ipAddr.append(segments[i]);
                    if(i != SEG_COUNT - 1){
                        ipAddr.append('.');
                    }
                }
                ans.add(ipAddr.toString());
            }
            return;
        }

        // 如果还没有找到 4 段 IP地址就遍历完了字符串，提前回溯
        if(segStart == s.length()){
            return;
        }

        // 由于不能有前导零，如果当前数字为0，那么这一段IP地址只能为0
        if(s.charAt(segStart) == '0'){
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1);
        }

        // 一般情况， 枚举每一种情况并递归
        int addr = 0;
        for(int segEnd = segStart; segEnd < s.length(); segEnd++){
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if(addr > 0 && addr <= 255) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                break;
            }
        }

    }

    public static void main(String[] args) {
        LeetCode0093Solution solution = new LeetCode0093Solution();
        List<String> rst = solution.restoreIpAddresses("25525511135");
        System.out.println(rst);
    }

}
