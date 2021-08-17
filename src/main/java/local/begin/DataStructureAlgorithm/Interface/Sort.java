package local.begin.DataStructureAlgorithm.Interface;

public interface Sort {

    static <E extends Comparable<E>> void sort(E[] arr){}

    static String getName() {
        return "";
    }
}
