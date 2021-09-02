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

    // 大于target的最小值索引
    public static <E extends Comparable<E>> int upper(E[] data, E target){

        int l = 0, r = data.length;

        // 在data[l, r]中寻找解
        while(l < r){

            int mid = l + (r - l) / 2;
            if(data[mid].compareTo(target) <= 0){
                l = mid + 1;
            } else { // data[mid].compareTo(target) > 0
                r = mid;
            }

        }
        return l;
    }

    // 小于target的最大值索引
    public static <E extends Comparable<E>> int lower(E[] data, E target){

        int l = -1, r = data.length - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            // 如果data[mid] < target, mid
            if(data[mid].compareTo(target) < 0) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    // > target， 返回最小值索引
    // == target，返回最大索引
    public static <E extends Comparable<E>> int upperCeil(E[] data, E target){
        int u = upper(data, target);
        if(u - 1 >= 0 && data[u - 1].compareTo(target) == 0) {
            return u - 1;
        }
        return u;
    }

    // < target， 返回最大值索引
    // == target， 返回最小索引
    public static <E extends Comparable<E>> int lowerFloor(E[] data, E target){
        int l = lower(data, target);
        if(l + 1 < data.length && data[l + 1].compareTo(target) == 0){
            return l + 1;
        }
        return l;
    }

    // >=target 的最小索引
    public static <E extends Comparable<E>> int lowerCeil(E[] data, E target){

        int l = 0, r = data.length;

        // 在data[l, r]中寻找解
        while(l < r){
            int mid = l + (r - l) / 2;
            //如果 data[mid] < target, 寻找 >= 的最小索引，data[mid]肯定不包含了
            if(data[mid].compareTo(target) < 0){
                l = mid + 1;
            } else { // data[mid] >= target, mid索引上的很可能是要返回的值
                r = mid;
            }
        }
        return l;
    }

    // <=target 的最大索引
    public static <E extends Comparable<E>> int upperFloor(E[] data, E target){

        int l = -1, r = data.length - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            // 如果 data[mid] <= target 那么 mid 为可能值
            if(data[mid].compareTo(target) <= 0){
                l = mid;
            } else { // 如果 data[mid] > target mid 肯定超过搜索右界
                r = mid - 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {

        Integer[] arr = {1,1,3,3,5,5};
        for (int i = 0; i <= 6; i++){
            System.out.print(BinarySearch.upperFloor(arr, i) + " ");
        }
        System.out.println();
    }

}
