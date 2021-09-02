package local.begin.dataStructureAlgorithm.test;

import local.begin.dataStructureAlgorithm.dataStruct.LinkedListStack;
import local.begin.dataStructureAlgorithm.algoInterface.Stack;

public class StackMain {

    public static void main(String[] args) {
//        Stack<Integer> stack = new ArrayStack<>();
        Stack<Integer> stack = new LinkedListStack<>();
        for(int i = 0; i < 5; i++){
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }

}
