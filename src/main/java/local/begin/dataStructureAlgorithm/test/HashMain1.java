package local.begin.dataStructureAlgorithm.test;

import local.begin.dataStructureAlgorithm.struct.Student1;

import java.util.HashMap;
import java.util.HashSet;

public class HashMain1 {

    public static void main(String[] args) {

        int a = 42;
        System.out.println(((Integer)a).hashCode());

        int b = -42;
        System.out.println(((Integer)b).hashCode());

        double c = 3.1415926;
        System.out.println(((Double)c).hashCode());

        String d = "imooc";
        System.out.println(d.hashCode());

        System.out.println(Integer.MAX_VALUE + 1);
        System.out.println();

        Student1 student = new Student1(3, 2, "Bobo", "Liu");
        System.out.println(student.hashCode());

        HashSet<Student1> set = new HashSet<>();
        set.add(student);

        HashMap<Student1, Integer> scores = new HashMap<>();
        scores.put(student, 100);

        Student1 student2 = new Student1(3, 2, "BoBo", "Liu");
        System.out.println(student2.hashCode());
    }

}
