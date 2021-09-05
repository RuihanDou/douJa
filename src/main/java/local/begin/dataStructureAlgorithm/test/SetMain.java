package local.begin.dataStructureAlgorithm.test;

import local.begin.dataStructureAlgorithm.dataStruct.BSTSet;
import local.begin.dataStructureAlgorithm.helper.FileOperation;

import java.util.ArrayList;
import java.util.List;

public class SetMain {

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words1 = new ArrayList<>();
        FileOperation.readFile("pride-and-prejudice.txt", words1);
        System.out.println("Total words: " + words1.size());

        BSTSet<String> set1 = new BSTSet<>();
        for(String word : words1){
            set1.add(word);
        }
        System.out.println("Total unique words: " + set1.getSize());

        System.out.println();

        System.out.println("A Tale of Two Cities");

        ArrayList<String> words2 = new ArrayList<>();
        FileOperation.readFile("a-tale-of-two-cities.txt", words2);
        System.out.println("Total words: " + words2.size());

        BSTSet<String> set2 = new BSTSet<>();
        for(String word : words2){
            set2.add(word);
        }
        System.out.println("Total unique words: " + set2.getSize());

    }

}
