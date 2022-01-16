package local.begin.LeetCodeArray;

import java.util.*;


public class Solution0500 {

    private static Map<Character, Integer> map = new HashMap<>();
    static {
        map.put('q', 1);
        map.put('w', 1);
        map.put('e', 1);
        map.put('r', 1);
        map.put('t', 1);
        map.put('y', 1);
        map.put('u', 1);
        map.put('i', 1);
        map.put('o', 1);
        map.put('p', 1);

        map.put('a', 2);
        map.put('s', 2);
        map.put('d', 2);
        map.put('f', 2);
        map.put('g', 2);
        map.put('h', 2);
        map.put('j', 2);
        map.put('k', 2);
        map.put('l', 2);

        map.put('z', 3);
        map.put('x', 3);
        map.put('c', 3);
        map.put('v', 3);
        map.put('b', 3);
        map.put('n', 3);
        map.put('m', 3);
    }

    public String[] findWords(String[] words) {

        List<String> rst = new ArrayList<>();
        if(words.length < 1){
            return new String[0];
        }
        for (String word : words) {
            String word1 = word.toLowerCase(Locale.ROOT);
            int len = word1.length();
            int group = map.get(word1.charAt(0));
            boolean same = true;
            for(int i = 1; i < len; i++){
                if(map.get(word1.charAt(i)) != group){
                    same = false;
                    break;
                }
            }
            if(same){
                rst.add(word);
            }
        }
        int size = rst.size();
        String[] res = new String[size];
        for(int j = 0; j < size; j++){
            res[j] = rst.get(j);
        }
        return res;
    }

    public static void main(String[] args) {
        String[] words = {"Hello","Alaska","Dad","Peace"};
        Solution0500 solution = new Solution0500();
        System.out.println(Arrays.toString(solution.findWords(words)));
    }

}
