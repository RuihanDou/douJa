package local.begin.dataStructureAlgorithm.dataStruct;

import local.begin.dataStructureAlgorithm.algoInterface.Queue;

public class LoopQueueWithoutSize<E> implements Queue<E> {
    private E[] data;
    private int front, tail;
//    private int size;

    public LoopQueueWithoutSize(int capacity){
        this.data = (E[])new Object[capacity + 1];
        front = 0;
        tail = 0;
//        size = 0;
    }

    public LoopQueueWithoutSize(){
        this(10);
    }

    public int getCapacity(){
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return tail >= front ? tail - front : tail - front + data.length;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {

        if((tail + 1) % data.length == front){
            resize(this.getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
//        tail++;
//        size++;

    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        // 由于一通操作后getSize取出的值会发生变化，所以得现在预存
        int sz = getSize();
        for(int i = 0; i < sz; i++){
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = sz;
    }

    @Override
    public E dequeue() {

        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
//        size--;

        if(getSize() <= getCapacity() / 4 && getCapacity() / 2 != 0){
            resize(getCapacity() / 2);
        }

        return ret;
    }

    @Override
    public E getFront() {

        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty.");
        }

        return data[front];
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("LoopQueue: size = %d , capacity = %d\n", getSize(), getCapacity()));
        res.append("front [");
        for(int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if((i + 1) % data.length != tail){
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LoopQueueWithoutSize<>();
        for(int i = 0; i < 10; i++){
            queue.enqueue(i);
            System.out.println(queue);
            System.out.println(queue.getSize());

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
                System.out.println(queue.getSize());
            }
        }
    }
}
