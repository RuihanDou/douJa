package local.begin.jianZhiOffer;


/**
 * 面试题05. 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 10000
 */
public class Interview05 {

    public String replaceSpace(String s) {
        s = s.replace(" ", "%20");
        return s;
    }

    // 经实验，本方法更快 占用内存更小
    public String replaceSpaceV1(String s) {
        int length = s.length();
        char[] array = new char[length * 3];
        int size = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                array[size++] = '%';
                array[size++] = '2';
                array[size++] = '0';
            } else {
                array[size++] = c;
            }
        }
        String newStr = new String(array, 0, size);
        return newStr;
    }

    // 比V1略慢，比V1占用内存稍多
    public String replaceSpaceV2(String s) {
        int length = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Interview05 interview = new Interview05();
        long time0 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String res = interview.replaceSpace("We are happy.");
        }
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String res = interview.replaceSpaceV1("We are happy.");
        }
        long time2 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String res = interview.replaceSpaceV2("We are happy.");
        }
        long time3 = System.currentTimeMillis();
        String res = interview.replaceSpaceV2("We are happy.");
        System.out.println(res);
        System.out.println(time1 - time0);
        System.out.println(time2 - time1);
        System.out.println(time3 - time2);

        char[] chars = {'w','x','y','z','q'};
        String charla = new String(chars, 2, 1);
        System.out.println(charla);
    }
}
