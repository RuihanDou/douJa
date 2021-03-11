package local.begin.Test;


import java.util.regex.Pattern;

public class StringTest {

    public static String removeBlankInCharactor(String input){

        return input.replaceAll("(?<=[\\x{4e00}-\\x{9fa5}])\\s(?=[\\x{4e00}-\\x{9fa5}])", "").strip();


    }

    public static void main(String[] args) {
        String test = "a tiger eat: a 窦 睿 翰";
        System.out.println(StringTest.removeBlankInCharactor(test));
    }


}


