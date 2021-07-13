package local.begin.Huawei;

import com.google.common.collect.Lists;

import java.util.List;

public class Han7 {

    public static List<Integer> getOrder(List<Integer> input) {
        int length = input.size();
        int sum = 0;
        for (Integer i : input) {
            sum += i;
        }
        List<Integer> res = Lists.newArrayList();
        for(int s = 0; s < length; s++) {
            res.add(0);
        }
        int i = 0;
        int j = 1;
        while (i < sum) {
            if(j % 7 == 0 || String.valueOf(j).contains("7")) {
                int num = (j - 1) % length;
                res.set(num,res.get(num) + 1);
                i++;
            }
            j++;
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> input = Lists.newArrayList(0,1,0);
        System.out.println("输入："+input);
        System.out.println("输出："+getOrder(input));
    }

}
