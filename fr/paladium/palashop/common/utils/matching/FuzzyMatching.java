package fr.paladium.palashop.common.utils.matching;

import java.util.Arrays;

public class FuzzyMatching {
  public static final int INDEX_NOT_FOUND = -1;
  
  public static Double apply(CharSequence left, CharSequence right) {
    double defaultScalingFactor = 0.1D;
    double percentageRoundValue = 100.0D;
    if (left == null || right == null)
      throw new IllegalArgumentException("Strings must not be null"); 
    int[] mtp = matches(left, right);
    double m = mtp[0];
    if (m == 0.0D)
      return Double.valueOf(0.0D); 
    double j = (m / left.length() + m / right.length() + (m - mtp[1]) / m) / 3.0D;
    double jw = (j < 0.7D) ? j : (j + Math.min(0.1D, 1.0D / mtp[3]) * mtp[2] * (1.0D - j));
    return Double.valueOf(Math.round(jw * 100.0D) / 100.0D);
  }
  
  protected static int[] matches(CharSequence first, CharSequence second) {
    CharSequence max, min;
    if (first.length() > second.length()) {
      max = first;
      min = second;
    } else {
      max = second;
      min = first;
    } 
    int range = Math.max(max.length() / 2 - 1, 0);
    int[] matchIndexes = new int[min.length()];
    Arrays.fill(matchIndexes, -1);
    boolean[] matchFlags = new boolean[max.length()];
    int matches = 0;
    for (int mi = 0; mi < min.length(); mi++) {
      char c1 = min.charAt(mi);
      for (int xi = Math.max(mi - range, 0), xn = Math.min(mi + range + 1, max.length()); xi < xn; xi++) {
        if (!matchFlags[xi] && c1 == max.charAt(xi)) {
          matchIndexes[mi] = xi;
          matchFlags[xi] = true;
          matches++;
          break;
        } 
      } 
    } 
    char[] ms1 = new char[matches];
    char[] ms2 = new char[matches];
    int i, si;
    for (i = 0, si = 0; i < min.length(); i++) {
      if (matchIndexes[i] != -1) {
        ms1[si] = min.charAt(i);
        si++;
      } 
    } 
    for (i = 0, si = 0; i < max.length(); i++) {
      if (matchFlags[i]) {
        ms2[si] = max.charAt(i);
        si++;
      } 
    } 
    int transpositions = 0;
    for (int j = 0; j < ms1.length; j++) {
      if (ms1[j] != ms2[j])
        transpositions++; 
    } 
    int prefix = 0;
    for (int k = 0; k < min.length() && 
      first.charAt(k) == second.charAt(k); k++)
      prefix++; 
    return new int[] { matches, transpositions / 2, prefix, max.length() };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\commo\\utils\matching\FuzzyMatching.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */