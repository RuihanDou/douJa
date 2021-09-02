package local.begin.dataStructureAlgorithm.algoInterface;

public interface Sort {
    static <E extends Comparable<E>> void sort(E[] arr) {}

    static String getName() {
        return "";
    }
}
