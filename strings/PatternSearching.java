import java.util.ArrayList;
import java.util.List;

public class PatternSearching {
  public static List<Integer> naivePatternSearch(String text, String pattern) {
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < text.length(); i++) {
      int j;
      for (j = 0; j < pattern.length(); j++) {
        if (text.charAt(i + j) != pattern.charAt(j))
          break;
      }
      if (j == pattern.length())
        result.add(i);
    }
    return result;
  }

  public static List<Integer> distinctCharPatternNaiveSearch(String text, String pattern) {
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < text.length() - pattern.length();) {
      int j;
      for (j = 0; j < pattern.length(); j++) {
        if (text.charAt(i + j) != pattern.charAt(j))
          break;
      }
      if (j == pattern.length())
        result.add(i);
      if (j == 0)
        i++;
      else
        i = i + j;
    }
    return result;
  }

  /*
   * The Rabin Karp algorithm works on the priciple of a Rolling Hash Function
   * For a corresponding check in the text for the pattern, first calculate the
   * hash values of the two
   * if the hash values are the same, then only compare the individual characters
   *
   * Hash Functions -
   * 1. Simple Hash Function - Sum (ascii values)
   * 2. Better Hash Function - ... + ad^2 + bd + c
   */
  private static int D = 5;
  private static int Q = 100000;

  public static List<Integer> rabinKarp(String text, String pattern) {
    List<Integer> result = new ArrayList<>();

    // 1. compute rolling hash
    int h = 1;
    for (int i = 1; i < pattern.length(); i++) {
      h = (h * D) % Q;
    }

    // 2. compute the hash values of the pattern and the first batch of text
    int p = 0, t = 0;
    for (int i = 0; i < pattern.length(); i++) {
      p = (p * D + pattern.charAt(i)) % Q;
      t = (t * D + text.charAt(i)) % Q;
    }

    for (int i = 0; i < text.length() - pattern.length(); i++) {
      // 3. compare hash and characters
      if (p == t) {
        boolean flag = true;
        for (int j = 0; j < pattern.length(); j++) {
          if (pattern.charAt(j) != text.charAt(i + j)) {
            flag = false;
            break;
          }
        }
        if (flag)
          result.add(i);
      }

      // 4. compute t(i + 1) using t(i)
      if (i < text.length() - pattern.length()) {
        t = ((D * (t - text.charAt(i) * h)) + text.charAt(i + pattern.length())) % Q;
        if (t < 0)
          t = t + Q;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    String text = "I love to code";
    String pattern = "o";
    List<Integer> lst = rabinKarp(text, pattern);
    System.out.println(lst);
  }
}
