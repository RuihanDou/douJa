package local.begin.LeetCode;

import local.begin.Tools.DebugTools;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU 缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 *
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 *
 *
 * 示例：
 *
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 */

public class LeetCode146Solution {

    // Last Recently Used Cache
    // 继承LinkedHashMap
//    class LRUCache extends LinkedHashMap<Integer, Integer> {
//
//        private int capacity;
//
//        public LRUCache(int capacity) {
//            super(capacity, 0.75F, true);
//            this.capacity = capacity;
//        }
//
//        public int get(int key) {
//            return super.getOrDefault(key, -1);
//        }
//
//        public void put(int key, int value) {
//            super.put(key, value);
//        }
//
//        @Override
//        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
//            return size() > capacity;
//        }
//    }

    static class LRUCache {

        private class DoublyLinkedNode{
            DoublyLinkedNode prev;
            DoublyLinkedNode next;
            int key;
            int val;

            public DoublyLinkedNode(int key, int val) {
                this.val = val;
                this.key = key;
            }
        }

        DoublyLinkedNode head;
        DoublyLinkedNode tail;
        Map<Integer, DoublyLinkedNode> map;
        int size;

        public LRUCache(int capacity) {
            head = new DoublyLinkedNode(0, 0);
            tail = new DoublyLinkedNode(0, 0);

            head.next = tail;
            tail.next = head;

            map = new HashMap<>();
            size = capacity;
        }

        public int get(int key) {
            if (map.containsKey(key)){
                DoublyLinkedNode node = map.get(key);
                delete(node);
                add(node);
                return node.val;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            if(map.containsKey(key)){
                DoublyLinkedNode cur = map.get(key);
                delete(cur);
                cur.val = value;
                add(cur);
            } else {
                DoublyLinkedNode newNode = new DoublyLinkedNode(key, value);
                add(newNode);
            }
        }

        /**
         * delete node in linkedlist and map
         * @param node
         */
        private void delete(DoublyLinkedNode node) {
            DoublyLinkedNode prev = node.prev;
            DoublyLinkedNode next = node.next;
            prev.next = node.next;
            next.prev = node.prev;
            map.remove(node.key);
            node = null;
        }

        /**
         * add value in queue, and delete the last value if necessary
         * @param newNode
         */
        private void add(DoublyLinkedNode newNode){
            insertQueue(newNode);
            map.put(newNode.key, newNode);
            if(map.size() > size) {
                DoublyLinkedNode toBeDelete = tail.prev;
                delete(tail.prev);
                map.remove(toBeDelete.key);
            }
        }

        private void insertQueue(DoublyLinkedNode node){
            DoublyLinkedNode oldFirst = head.next;
            head.next = node;
            node.prev = head;

            oldFirst.prev = node;
            node.next = oldFirst;
        }
    }

    public static void main(String[] args) {
        LRUCache lcuCache = new LRUCache(2);
        DebugTools.print(lcuCache.get(2));
        lcuCache.put(2,6);
        DebugTools.print(lcuCache.get(1));
        lcuCache.put(1,5);
        lcuCache.put(1,2);
        int ca = lcuCache.get(1);
        DebugTools.print(ca);
        DebugTools.print(lcuCache.get(2));



//        lcuCache.put(1,0);
//        lcuCache.put(2,2);
//        DebugTools.print(lcuCache.get(1));
//        lcuCache.put(3,3);
//        DebugTools.print(lcuCache.get(2));
//        lcuCache.put(4,4);
//        DebugTools.print(lcuCache.get(1));
//        DebugTools.print(lcuCache.get(3));
//        DebugTools.print(lcuCache.get(4));


    }


}
