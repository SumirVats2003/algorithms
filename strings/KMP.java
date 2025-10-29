import java.util.ArrayList;
import java.util.List;

public class KMP {
  public static int[] fillLPS(String s) {
    int n = s.length();
    int[] lps = new int[n];
    int len = 0;
    lps[0] = 0;
    int i = 1;

    while (i < n) {
      if (s.charAt(i) == s.charAt(len)) {
        len++;
        lps[i] = len;
        i++;
      } else {
        if (len == 0) {
          lps[i] = 0;
          i++;
        } else {
          len = lps[len - 1];
        }
      }
    }

    return lps;
  }

  public static List<Integer> kmpSearch(String pattern, String text) {
    int N = text.length();
    int M = pattern.length();
    int[] lps = fillLPS(pattern);
    List<Integer> result = new ArrayList<>();

    int i = 0, j = 0;

    while (i < N) {
      if (text.charAt(i) == pattern.charAt(j)) {
        i++;
        j++;
      }
      if (j == M) {
        result.add(i - j);
        j = lps[j - 1];
      } else if (i < N && text.charAt(i) != pattern.charAt(j)) {
        if (j == 0)
          i++;
        else
          j = lps[j - 1];
      }
    }
    return result;
  }

  public static void main(String[] args) {
    String str = "ababaababaad";
    String pat = "ababa";

    List<Integer> result = kmpSearch(pat, str);
    System.out.println(result.toString());
  }
}
