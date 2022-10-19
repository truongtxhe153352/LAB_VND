package Unit11;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroupRegexExample {
    public static void main(String[] args) {
        // \\w So khớp bất kỳ ký tự chữ nào (chữ cái và chữ số), viết tắt của [a-zA-Z0-9]
        // Lặp lại 1 hoặc nhiều lần
        // S So khớp với bất kỳ ký tự không phải ký tự trống, viết tắt của [^\s]
        Pattern patten = Pattern.compile("(\\w+) (\\S+)(.*)", Pattern.UNICODE_CHARACTER_CLASS);
        String text = "tôi học java o dai hoc";
        Matcher matcher = patten.matcher(text);
        System.out.println(matcher.matches());
        System.out.println(matcher.group(0));
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));

    }
}
