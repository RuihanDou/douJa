package local.begin.dataStructureAlgorithm.dataStruct;

import local.begin.dataStructureAlgorithm.algoInterface.UF;


/**
 * Quick Union 版本 并查集
 *
 * 孩子指向父亲的树（们）
 */
public class UnionFind2 implements UF {

    private int[] parent;

    public UnionFind2(int size){
        parent = new int[size];
        for(int i = 0; i < size; i++){
            parent[i] = i;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // 寻找到 p 的根节点
    // 查找过程，查找元素p所在的集合编号
    // O(h)复杂度， h 为树的高度
    private int find(int p){
        if(p < 0 || p >= parent.length){
            throw new IllegalArgumentException("p is out of bound.");
        }

        while (p != parent[p]){
            p = parent[p];
        }
        return p;
    }

    // 查看元素p和元素q是否所属一个集合
    // O(h)复杂度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // p所属的集合的根节点指向q所属集合的根节点
    // 合并元素p和元素q所属的集合
    // O(h)复杂度
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot){
            return;
        }
        parent[pRoot] = qRoot;
    }
}
