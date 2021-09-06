package local.begin.dataStructureAlgorithm.dataStruct;


import java.util.Random;

/**
 * 二叉堆是 一棵完全二叉树，缺失节点都在树的右下侧 （满二叉树是除叶子节点之外都既有左孩子又有右孩子）
 *
 * 最大堆 : 堆中某个节点的值总是不大于其父节点的值
 * ////最小堆 : 堆中某个节点的值总是不小于其父节点的值
 *
 */

public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    // 返回堆中的元素个数
    public int size(){
        return data.getSize();
    }

    // 返回一个布尔值，表示堆中是否为空
    public boolean isEmpty(){
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的的元素的父亲节点的索引
    private int parent(int index){
        if(index == 0){
            throw new IllegalArgumentException("index - 0 doesn't have parent");
        }
        return (index - 1) / 2;
    }

    // 返回完全二叉树中的数组表示中，一个索引所表示的元素的左孩子节点的索引
    public int leftChild(int index){
        return index * 2 + 1;
    }

    // 返回完全二叉树中的数组表示中，一个索引所表示的元素的右孩子节点的索引
    public int rightChild(int index){
        return index * 2 + 2;
    }

     // 向堆中添加元素
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    // 看堆中的最大元素
    public E findMax(){
        if(data.getSize() == 0){
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        }
        return data.get(0);
    }

    // 取出堆中最大元素
    public E extractMax(){
        E ret = findMax();

        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);

        return ret;
    }

    private void siftDown(int k){
        while (leftChild(k) < data.getSize()){
            int j = leftChild(k);
            if(j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0){
                j = rightChild(k);
            }
            // data[j] 是leftChild 和 rightChild 中的最大值
            if(data.get(k).compareTo(data.get(j)) >= 0){
                break;
            }
            data.swap(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {

        int n = 1000000;

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for(int i = 0 ; i < n ; i ++){
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }
        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i ++){
            arr[i] = maxHeap.extractMax();
        }

        for(int i = 1 ; i < n ; i ++){
            if(arr[i-1] < arr[i]){
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("Test MaxHeap completed.");
    }

}
