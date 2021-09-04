package local.begin.dataStructureAlgorithm.dataStruct;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

/**
 * 二分搜索树 Binary Search Tree
 * 左子树所有值都小于该节点的值，右子树所有的值都大于该节点的值
 * 不包含重复的元素
 * @param <E> 可比较
 */
public class BST  <E extends Comparable<E>>{

    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 向二分搜索树添加新的元素e
    public void add(E e){
        root = add(root, e);
    }

//    // 向以node为根的二分搜索树中插入元素e，递归算法
//    private void add(Node node, E e){
//        if(e.equals(node.e)){
//            return;
//        } else if(e.compareTo(node.e) < 0 && node.left == null){
//            node.left = new Node(e);
//            size++;
//            return;
//        }else if(e.compareTo(node.e) > 0 && node.right == null){
//            node.right = new Node(e);
//            size++;
//            return;
//        }
//
//        if(e.compareTo(node.e) < 0){
//            add(node.left, e);
//        } else {
//            add(node.right, e);
//        }
//    }

    // 向以node为根的二分搜索树中插入元素e，递归算法
    // 返回插入新节点后后二分搜索树的根
    private Node add(Node node, E e){
        if (node == null) {
            size++;
            return new Node(e);
        }
        if(e.compareTo(node.e) < 0){
            node.left = add(node.left, e);
        } else if(e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    // 非递归的方式实现向二分搜索树中添加元素e
    public void addEle(E e){
        // 对二分搜索树是空的情况特殊处理
        // 此时，直接让 root 指向新的节点即可
        if(root == null){
            root = new Node(e);
            size ++;
            return;
        }
        // 用 p 来跟踪待插入节点的上一个节点
        // p 的作用相当于链表插入节点时，pre 的作用
        Node p = root;
        while (p != null){
            // 如果待插入的值小于当前 p 节点的值
            // 说明新插入的值要放在 p 的左子树
            if(e.compareTo(p.e) < 0){
                // 如果 p 的左子树为空，则在 p.left 上放入新的节点
                if(p.left == null){
                    p.left = new Node(e);
                    size++;
                    return; // 注意这里直接 return
                }
                // 否则 p = p.left
                p = p.left;
            }
            // 如果待插入的值大于当前 p 节点的值
            // 说明新插入的值要放在 p 的右子树
            else if(e.compareTo(p.e) > 0){
                // 如果 p 的右子树为空，则在 p.right 上放入新的节点
                if(p.right == null){
                    p.right = new Node(e);
                    size ++;
                    return; // 注意这里直接 return
                }
                // 否则 p = p.right
                p = p.right;
            }
            // 如果待插入的值等于当前 p 节点的值，说明二分搜索树中已经有这个值了
            // 直接 return
            else return;
        }
    }

    // 看二分搜索树中是否包含元素e
    public boolean contains(E e){
        return contains(root, e);
    }

    // 看以node为根的二分搜索树中是否包含元素e，递归算法
    private boolean contains(Node node, E e) {
        if(node == null){
            return false;
        }
        if(e.compareTo(node.e) == 0){
            return true;
        } else if(e.compareTo(node.e) < 0){
            return contains(node.left, e);
        } else { // e.compareTo(node.e) > 0
            return contains(node.right, e);
        }
    }

    // 二分搜索树的前序遍历
    public void preOrder(){
        preOrder(root);
    }

    // 前序遍历以node为根的二分搜索树，递归算法
    private void preOrder(Node node) {
        if(node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 二分搜索树的中序遍历
    // 二分搜索树的中序遍历输出从小到大的排序
    public void inOrder(){
        inOrder(root);
    }

    // 中序遍历以node为根的二分搜索树，递归算法
    private void inOrder(Node node) {
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    // 二分搜索树的后序遍历
    public void postOrder(){
        postOrder(root);
    }

    // 后序遍历以node为节点的二分搜索树，递归算法
    private void postOrder(Node node){
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    // 二分搜索树的非递归前序遍历
    public void preOrderNR(){

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);

            if(cur.right != null){
                stack.push(cur.right);
            }
            if(cur.left != null){
                stack.push(cur.left);
            }
        }

    }

    // 二分搜索树的层序遍历
    public void levelOrder(){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.e);
            if(cur.left != null){
                q.add(cur.left);
            }
            if(cur.right != null){
                q.add(cur.right);
            }
        }
    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {

        if(node == null){
            res.append(generateDepthString(depth) +"null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);

    }

    private String generateDepthString(int depth) {

        StringBuilder res = new StringBuilder();
        for(int i = 0; i < depth; i++){
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = new int[]{5,3,6,8,4,2};
        for (int num : nums){
            bst.add(num);
        }

        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////

        bst.preOrder();
        System.out.println();
        bst.preOrderNR();
        System.out.println();
        bst.inOrder();
        System.out.println();
        bst.postOrder();
        System.out.println();
        bst.levelOrder();
        System.out.println();
        System.out.println(bst);
    }

}
