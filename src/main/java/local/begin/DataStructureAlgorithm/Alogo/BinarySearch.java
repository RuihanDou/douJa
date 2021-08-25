package local.begin.DataStructureAlgorithm.Alogo;

public class BinarySearch {

    private BinarySearch(){}

    // 递归实现二分查找法
    public static <E extends Comparable<E>> int searchR(E[] data, E target){
        return searchR(data, 0, data.length - 1, target);
    }

    public static <E extends Comparable<E>> int searchR(E[] data, int l, int r, E target){

        if(l > r) return -1;

        int mid = l + (r - l) / 2;

        if(data[mid].compareTo(target) == 0){
            return mid;
        } else if(data[mid].compareTo(target) < 0){
            return searchR(data, mid + 1, r, target);
        } else { // data[mid].compareTo(target) > 0
            return searchR(data, l, mid - 1, target);
        }
    }

    // 非递归实现二分查找法
    public static <E extends Comparable<E>> int search(E[] data, E target) {
        int l = 0, r = data.length - 1;

        while (l <= r){
            int mid = l + (r - l) / 2;
            if(data[mid].equals(target)){
                return mid;
            } else if (data[mid].compareTo(target) < 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return -1;
    }
}
