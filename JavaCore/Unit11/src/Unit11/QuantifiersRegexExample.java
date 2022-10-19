package Unit11;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuantifiersRegexExample {
   public static void main(String[] args) {
      // Note:
      //\?  xuất hiện 0 hoặc 1 lần
      // S không phải kí tự trống
      // + lặp lại 1 hoặc nhiều lần
      // tìm t xuất hiện 0 hoặc 1 lần

    //  Pattern pattern = Pattern.compile("t?\\S+ (\\S+)(.*)", Pattern.UNICODE_CHARACTER_CLASS);
      Pattern pattern = Pattern.compile("t{2,5}+\\S+ (\\S+)(.*)", Pattern.UNICODE_CHARACTER_CLASS);


      String text = "tớ học java";
      Matcher matcher = pattern.matcher(text);
      System.out.println(text +"---->"+matcher.matches());

      String text1 = "cậu học java";
      Matcher matcher1 = pattern.matcher(text1);
      System.out.println(text1 +"---->"+matcher1.matches());

      String text2 = "tttớ học java";
      Matcher matcher2 = pattern.matcher(text2);
      System.out.println(text2 +"---->"+matcher2.matches());
   }

}
