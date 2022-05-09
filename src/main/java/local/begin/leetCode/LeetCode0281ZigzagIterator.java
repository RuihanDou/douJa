package local.begin.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 281. 锯齿迭代器
 * 给出两个一维的向量，请你实现一个迭代器，交替返回它们中间的元素。
 *
 * 示例:
 *
 * 输入:
 * v1 = [1,2]
 * v2 = [3,4,5,6]
 *
 * 输出: [1,3,2,4,5,6]
 *
 * 解析: 通过连续调用 next 函数直到 hasNext 函数返回 false，
 *      next 函数返回值的次序应依次为: [1,3,2,4,5,6]。
 * 拓展：假如给你 k 个一维向量呢？你的代码在这种情况下的扩展性又会如何呢?
 *
 * 拓展声明：
 *  “锯齿” 顺序对于 k > 2 的情况定义可能会有些歧义。所以，假如你觉得 “锯齿” 这个表述不妥，也可以认为这是一种 “循环”。例如：
 *
 * 输入:
 * [1,2,3]
 * [4,5,6,7]
 * [8,9]
 *
 * 输出: [1,4,8,2,5,9,3,6,7].
 */
public class LeetCode0281ZigzagIterator {

    /**
     * outer 控制 List<List<Integer>> lists 的循环遍历
     * inner 控制 List<Integer> list / v1 / v2 的循环遍历
     */
    private int inner;
    private int outer;
    private List<List<Integer>> lists;

    public LeetCode0281ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.lists = new ArrayList<>();
        this.lists.add(v1);
        this.lists.add(v2);
        this.inner = 0;
        this.outer = 0;
    }

    public void outerNext(){
        // 用于处理v1.size()==0的情况
        while (outer < lists.size() && inner >= lists.get(outer).size()){
            outer++;
        }
        if(outer == lists.size()){
            outer = 0;
            inner++;
            // outer从0开始增加，如果增加到list.size()，inner还是太大，说明已经没有元素可以遍历了，由hasNext()里的代码判断返回
            while (inner >= lists.get(outer).size()){
                outer++;
                if(outer == lists.size()){
                    break;
                }
            }
        }
    }

    public int next() {
        return lists.get(outer++).get(inner);
    }

    public boolean hasNext() {
        outerNext();
        return outer < lists.size() && inner < lists.get(outer).size();
    }

}
