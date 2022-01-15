package local.begin.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LeetCode0170TwoSum {
    private HashMap<Integer, Integer> num_counts;


    public LeetCode0170TwoSum() {
        this.num_counts = new HashMap<>();
    }


    public void add(int number) {
        if (this.num_counts.containsKey(number)){
            this.num_counts.replace(number, this.num_counts.get(number) + 1);
        }
        else{
            this.num_counts.put(number, 1);
        }
    }


    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry : this.num_counts.entrySet()) {
            int complement = value - entry.getKey();
            if (complement != entry.getKey()) {
                if (this.num_counts.containsKey(complement)){
                    return true;
                }
            } else {
                if (entry.getValue() > 1){
                    return true;
                }
            }
        }
        return false;
    }


}
