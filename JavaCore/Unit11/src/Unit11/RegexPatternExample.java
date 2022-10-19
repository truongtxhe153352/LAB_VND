package Unit11;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPatternExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\d+");
        String text = "1 + 2 bang 3";
        Matcher matcher = pattern.matcher(text);
//        System.out.println(matcher.find());
//        System.out.println("start = " + matcher.start() + " - end " + matcher.end());
//        System.out.println("value = " + text.substring(matcher.start(), matcher.end()));

        int start = 0;
        int end = 0;
        while (matcher.find(start)){
            start = matcher.start();
            end = matcher.end();
            System.out.println("number: " + text.substring(start, end));
            start = end;
        }
    }
}
