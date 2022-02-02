package local.begin.leetCode;

import local.begin.dataStructureAlgorithm.dataStruct.LinkedList;

public class LeetCode0707MyLinkedList {

    private class ListNode707{
        int val;
        ListNode707 next;
        ListNode707(int val){
            this.val = val;
        }
    }

    private int size;
    ListNode707 head;
    public LeetCode0707MyLinkedList(){
        size = 0;
        head = new ListNode707(0);
    }

    public int get(int index) {
        if(index < 0 || index >= size){
            return -1;
        }
        ListNode707 cur = head;
        for(int i = 0; i <= index; i++){
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if(index > size){
            return;
        }
        
        if(index < 0){
            index = 0;
        }
        ListNode707 pre = head;
        for(int i = 0; i < index; i++){
            pre = pre.next;
        }
        
        ListNode707 toAdd = new ListNode707(val);
        toAdd.next = pre.next;
        pre.next = toAdd;
        
        size++;
    }

    public void deleteAtIndex(int index) {
        
        if(index < 0 || index >= size){
            return;
        }
        
        ListNode707 pre = head;
        for(int i = 0; i < index; i++){
            pre = pre.next;
        }
        
        ListNode707 toDel = pre.next;
        pre.next = pre.next.next;
        toDel = null;
        
        size--;
    }



}
