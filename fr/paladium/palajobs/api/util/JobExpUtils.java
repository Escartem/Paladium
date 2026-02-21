package fr.paladium.palajobs.api.util;

public class JobExpUtils {
  private static final Integer[] LEVELS = new Integer[] { 
      Integer.valueOf(0), 
      Integer.valueOf(22123), 
      Integer.valueOf(40390), 
      Integer.valueOf(73751), 
      Integer.valueOf(118886), 
      Integer.valueOf(176611), 
      Integer.valueOf(247616), 
      Integer.valueOf(332507), 
      Integer.valueOf(431826), 
      Integer.valueOf(546062), 
      Integer.valueOf(675669), 
      Integer.valueOf(821062), 
      Integer.valueOf(982632), 
      Integer.valueOf(1160743), 
      Integer.valueOf(1355741), 
      Integer.valueOf(1567953), 
      Integer.valueOf(1797690), 
      Integer.valueOf(2045248), 
      Integer.valueOf(2310912), 
      Integer.valueOf(2594953) };
  
  public static int getNeededXpForLvl(int lvl) {
    int sum = 0;
    int xpSoftMax = LEVELS[LEVELS.length - 1].intValue();
    for (int i = 2; i <= lvl; i++) {
      if (i <= getMaxLevel()) {
        sum += LEVELS[i - 1].intValue();
      } else {
        sum += xpSoftMax;
      } 
    } 
    return sum;
  }
  
  public static int getLevel(double experience) {
    int sum = 0;
    int xpSoftMax = LEVELS[LEVELS.length - 1].intValue();
    for (int i = 2; i < Integer.MAX_VALUE; i++) {
      int xpToAdd = (i <= getMaxLevel()) ? LEVELS[i - 1].intValue() : xpSoftMax;
      sum += xpToAdd;
      if (experience < sum)
        return i - 1; 
    } 
    return Integer.MAX_VALUE;
  }
  
  public static int getMaxLevel() {
    return LEVELS.length;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\ap\\util\JobExpUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */