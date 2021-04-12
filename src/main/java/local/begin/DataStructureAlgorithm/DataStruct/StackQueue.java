package local.begin.DataStructureAlgorithm.DataStruct;

import local.begin.DataStructureAlgorithm.Interface.Queue;

import java.util.Stack;

public class StackQueue<E> implements Queue<E> {

    private Stack<E> stack;

    public StackQueue(){
        stack = new Stack<>();
    }

    @Override
    public int getSize() {
        return stack.size();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        Stack<E> stack2 = new Stack<>();
        while (!stack.isEmpty()){
            stack2.push(stack.pop());
        }
        stack.push(e);
        while (!stack2.isEmpty()){
            stack.push(stack2.pop());
        }

    }

    @Override
    public E dequeue() {
        return stack.pop();
    }

    @Override
    public E getFront() {
        return stack.peek();
    }
}
