package local.begin.DataStructureAlgorithm.DataStruct;


import local.begin.DataStructureAlgorithm.Interface.Stack;

import java.util.LinkedList;
import java.util.Queue;

public class QueueStack<E> implements Stack<E> {

    private Queue<E> q;
    private E top;

    public QueueStack(){
        q = new LinkedList<>();
    }


    @Override
    public int getSize() {
        return q.size();
    }

    @Override
    public boolean isEmpty() {
        return q.isEmpty();
    }

    @Override
    public void push(E e) {
        q.add(e);
        top = e;
    }

    @Override
    public E pop() {
        // 领创建一个队列q2，将q的元素依次取出放入q2,只保留最后一个，q2接替q，q中最后一个元素返回
        Queue<E> q2 = new LinkedList<>();

        while (q.size() > 1){
            q2.add(q.remove());
        }

        E ret = q.remove();
        q = q2;
        return ret;
    }

    @Override
    public E peek() {
        E ret = pop();
        push(ret);
        return ret;
    }
}
