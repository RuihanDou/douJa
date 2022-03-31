package local.begin.leetCode;


import java.util.ArrayList;
import java.util.List;

/**
 * 241. 为运算表达式设计优先级
 * 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：expression = "2-1-1"
 * 输出：[0,2]
 * 解释：
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * 示例 2：
 *
 * 输入：expression = "2*3-4*5"
 * 输出：[-34,-14,-10,-10,10]
 * 解释：
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 *
 *
 * 提示：
 *
 * 1 <= expression.length <= 20
 * expression 由数字和算符 '+'、'-' 和 '*' 组成。
 * 输入表达式中的所有整数值在范围 [0, 99]
 */
public class LeetCode0241Solution {

    public List<Integer> diffWaysToCompute(String expression) {
        return divideAndConquer(expression.toCharArray());
    }

    private List<Integer> divideAndConquer(char[] expression) {

        List<Integer> res = new ArrayList<>();
        //处理一个数字的情况也就是分治划分到最底层的时候
        //isOneNum函数用来判断当前的表达式是否为一个单独的数字
        if (isOneNum(expression)){
            int num = 0;
            //将该数字从char数组转换为一个int型数值
            for (int i = 0; i < expression.length; i++){
                num = num * 10 + expression[i] - '0';
            }
            res.add(num);
            return res;
        }

        for (int i = 0; i < expression.length;i++){
            if (!Character.isDigit(expression[i])) {
                char[] left = new char[i];
                char[] right = new char[expression.length - i - 1];
                //切分左右分治所使用的表达式数组
                System.arraycopy(expression,0, left,0, i);
                System.arraycopy(expression,i + 1, right,0,expression.length - i - 1);
                //对左边的表达式在进行一次同样的操作
                List<Integer> leftList = divideAndConquer(left);
                //对右边的表达式在进行一次同样的操作
                List<Integer> rightList = divideAndConquer(right);
                //计算左右两个表达式在当前用来切分的运算符进行运算后得到的所有可能的结果
                List<Integer> tempRes = calculate(leftList, rightList, expression[i]);
                //将这些结果加入最后的列表中作为这一层分治的最终结果
                for (Integer num : tempRes){
                    res.add(num);
                }
            }
        }
        return res;
    }

    private List<Integer> calculate(List<Integer> list1, List<Integer> list2, char operator) {

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++){
            for (int j = 0; j < list2.size(); j++){
                res.add(calculateTwoNums(list1.get(i), list2.get(j), operator));
            }
        }
        return res;
    }

    private Integer calculateTwoNums(Integer num1, Integer num2, char operator) {
        switch(operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            default:
                return num1 * num2;
        }
    }

    private boolean isOneNum(char[] expression) {
        for (int i = 0; i < expression.length; i++){
            if (!Character.isDigit(expression[i]))
                return false;
        }
        return true;
    }

}
