package fr.paladium.palaforgeutils.lib.java.hash;

public class LongHash {
  public static long toLong(int msw, int lsw) {
    return (msw << 32L) + lsw - -2147483648L;
  }
  
  public static int msw(long l) {
    return (int)(l >> 32L);
  }
  
  public static int lsw(long l) {
    return (int)l + Integer.MIN_VALUE;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\java\hash\LongHash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */