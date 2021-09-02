package local.begin.dataStructureAlgorithm.alogo;

import local.begin.dataStructureAlgorithm.struct.Student;

public class LinearSearch {

    private LinearSearch(){}

    public static <E> int search(E[] data, E target){

        for(int i = 0; i < data.length; i++) {
            if(data[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

//        Integer[] data = {1,2,3,4,5,6,7,8,9};
//        int rst = LinearSearch.search(data ,6);
//        System.out.println(rst);

        Student[] students = {
                new Student("Alice", 100),
                new Student("Bobo", 66),
                new Student("Charles", 88)
                };
        Student student = new Student("bobo",66);
        int res = LinearSearch.search(students, student);
        System.out.println(res);

    }

}
