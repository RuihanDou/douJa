package local.begin.test;


public class StringTest {

    public static String removeBlankInCharactor(String input){
        // 只把汉字中的空格去除
        return input.replaceAll("(?<=[\\x{4e00}-\\x{9fa5}])\\s(?=[\\x{4e00}-\\x{9fa5}])", "");


    }

    public static void main(String[] args) {
        String test = "a tiger eat: a 窦 睿 翰";
        System.out.println(StringTest.removeBlankInCharactor(test));
    }


}


