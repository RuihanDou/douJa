package local.begin.dataStructureAlgorithm.dataStruct;

import local.begin.dataStructureAlgorithm.algoInterface.Merger;

/**
 * 线段树 （或者 区间树） 用于动态的数据，基于区间的统计查询
 *
 * 虽然 线段树严格意义上 不是完全二叉树，但是，它是平衡二叉树，所以可以使用数组来构建树
 *
 * @param <E>
 */
public class SegmentTree<E> {

    // data 中存储的是线段树的全局线段区间
    private E[] data;
    // tree 中存储线段树
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){

        this.merger = merger;

        data = (E[]) new Object[arr.length];

        for (int i = 0; i < arr.length; i++){
            data[i] = arr[i];
        }

        tree = (E[]) new Object[4 * arr.length];
        // 最开始的构建是在  从tree[0]开始的[0, data.length - 1]区间的线段树
        buildSegmentTree(0, 0, data.length - 1);

    }

    // 因为使用 tree 数组构建树
    // 在tree[treeIndex]的位置创建表示区间[l ... r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r){
        // 如果构建到叶子节点
        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }
        // 构建左右子树，确定左右子树(的根节点)，在数组 tree 中的位置
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // 获取data中的区间范围
        int mid = l + (r - l) / 2;
        // 创建左右子树
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length){
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子的节点的索引
    private int leftChild(int index){
        return 2 * index + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子的节点的索引
    private int rightChild(int index){
        return 2 * index + 2;
    }

    // 返回区间[queryL, queryR]的值
    public E query(int queryL, int queryR){
        if(queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR){
            throw new IllegalArgumentException("Index is illegal");
        }

        return query(0, 0, data.length - 1, queryL, queryR);

    }

    // 在以treeIndex为根的线段树中[l ... r]的范围里， 搜索区间[queryL, queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR){
        if(l == queryL && r == queryR){
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if(queryL >= mid + 1){
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if(queryR <= mid){
            return query(leftTreeIndex, l, mid, queryL, queryR);
        } else {
            E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
            return merger.merge(leftResult, rightResult);
        }
    }

    // 将index位置的值更新为e, 本线段树不支持区间更新
    public void set(int index, E e){
        if(index < 0 || index >= data.length){
            throw new IllegalArgumentException("Index is illegal.");
        }

        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e){
        if(l == r){
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if(index >= mid + 1){
            set(rightTreeIndex, mid + 1, r, index, e);
        } else {
            set(leftTreeIndex, l, mid, index, e);
        }
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i = 0; i < tree.length; i++){
            if(tree[i] != null){
                res.append(tree[i]);
            } else {
                res.append("null");
            }
            if(i != tree.length - 1){
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }
}
