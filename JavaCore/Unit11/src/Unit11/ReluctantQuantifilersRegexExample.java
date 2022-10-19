package Unit11;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReluctantQuantifilersRegexExample {
    public static void main(String[] args) {
        String text = "xxxjavaxxxxxxjadava";
        Pattern pattern = Pattern.compile(".*+va");
        Matcher matcher = pattern.matcher(text);

        System.out.println("reluctant---->" + matcher.find());
        System.out.println(text.substring(matcher.start(), matcher.end()));

        System.out.println("relucant---->" + matcher.find(matcher.end()));
        System.out.println(text.substring(matcher.start(), matcher.end()));
        System.out.println("possessive---->" + matcher.find());

    }
}
