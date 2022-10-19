package Unit11;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GreedyQuantifiersRegexExample {
    public static void main(String[] args) {
        String text = "xxxjavaxxxxxxjada ";
        Pattern pattern = Pattern.compile(".*va", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(text);
        System.out.println("greedy--->" + matcher.find());
        System.out.println(text.substring(matcher.start(), matcher.end()));

        System.out.println("greedy--->" + matcher.find(matcher.end()-1));


    }
}
