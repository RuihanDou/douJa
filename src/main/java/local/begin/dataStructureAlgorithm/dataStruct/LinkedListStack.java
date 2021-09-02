package local.begin.dataStructureAlgorithm.dataStruct;

import local.begin.dataStructureAlgorithm.algoInterface.Stack;

public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;

    public  LinkedListStack(){
        list = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return list.size;
    }

    @Override
    public boolean isEmpty() {
        return list.size == 0;
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(list);
        return res.toString();
    }
}
