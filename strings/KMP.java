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

  public static void main(String[] args) {
    String str = "abcbabca";
    int[] lps = fillLPS(str);

    for (int i : lps) {
      System.out.println(i);
    }
  }
}
