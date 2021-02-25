package local.begin.DataStructureAlgorithm.Test;

import local.begin.DataStructureAlgorithm.DataStruct.Array;
import local.begin.DataStructureAlgorithm.Struct.Student;

public class ArrayMain {

    public static void main(String[] args) {

        Array<Integer> arr = new Array<>();
        for(int i = 0; i < 10; i++){
            arr.addLast(i);
        }
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(2);
        System.out.println(arr);

        arr.removeElement(4);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);
    }

//    public static void main(String[] args) {
//
//        Array<Student> arr = new Array<>(30);
//        arr.addLast(new Student("Alice", 100));
//        arr.addLast(new Student("Bob", 66));
//        arr.addLast(new Student("Charlie", 88));
//        System.out.println(arr);
//
//    }

}
