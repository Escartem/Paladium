package fr.paladium.palamod.util;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.reflections.util.ClasspathHelper;

public class TimeUtils {
  public static String getDurationString(int seconds) {
    int minutes = twoDigitString(seconds / 60);
    int second = twoDigitString(seconds % 60);
    return String.format("%02d:%02d", new Object[] { Integer.valueOf(minutes), Integer.valueOf(second) });
  }
  
  public static List<ClassLoader> getCl(List<ClassLoader> pep) {
    pep.add(ClasspathHelper.contextClassLoader());
    return pep;
  }
  
  public static int twoDigitString(int number) {
    if (number == 0)
      return Integer.parseInt("00"); 
    if (number / 10 == 0)
      return Integer.parseInt("0" + number); 
    return number;
  }
  
  public static String getFullTime(String timeColor, String explainColor, long seconds) {
    int day = (int)TimeUnit.SECONDS.toDays(seconds);
    long hours = TimeUnit.SECONDS.toHours(seconds) - (day * 24);
    long minute = TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toHours(seconds) * 60L;
    StringBuilder builder = new StringBuilder();
    if (day > 0)
      builder.append(timeColor).append(day).append(explainColor).append(" jour")
        .append((day > 1) ? "s" : "").append(" "); 
    if (hours > 0L)
      builder.append(timeColor).append(hours).append(explainColor).append(" heure")
        .append((hours > 1L) ? "s" : "").append(" "); 
    if (minute > 0L)
      builder.append(timeColor).append(minute).append(explainColor).append(" minute")
        .append((minute > 1L) ? "s" : ""); 
    if (builder.toString().isEmpty())
      if (seconds > 0L) {
        builder.append(timeColor).append(seconds).append(" seconde").append((seconds > 1L) ? "s" : "");
      } else {
        builder.append(timeColor).append("prêt");
      }  
    return builder.toString();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\TimeUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */