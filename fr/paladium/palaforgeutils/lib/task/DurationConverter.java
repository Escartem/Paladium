package fr.paladium.palaforgeutils.lib.task;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DurationConverter {
  private static HashMap<String, Long> codes = new HashMap<>();
  
  static {
    codes.put("s", Long.valueOf(1000L));
    codes.put("sec", Long.valueOf(1000L));
    codes.put("seconds", Long.valueOf(1000L));
    codes.put("secondes", Long.valueOf(1000L));
    codes.put("m", Long.valueOf(60000L));
    codes.put("min", Long.valueOf(60000L));
    codes.put("minutes", Long.valueOf(60000L));
    codes.put("h", Long.valueOf(3600000L));
    codes.put("heures", Long.valueOf(3600000L));
    codes.put("hours", Long.valueOf(3600000L));
    codes.put("d", Long.valueOf(86400000L));
    codes.put("days", Long.valueOf(86400000L));
    codes.put("j", Long.valueOf(86400000L));
    codes.put("jours", Long.valueOf(86400000L));
    codes.put("mois", Long.valueOf(2592000000L));
    codes.put("month", Long.valueOf(2592000000L));
    codes.put("months", Long.valueOf(2592000000L));
    codes.put("y", Long.valueOf(31536000000L));
    codes.put("years", Long.valueOf(31536000000L));
    codes.put("a", Long.valueOf(31536000000L));
    codes.put("ans", Long.valueOf(31536000000L));
  }
  
  public static long fromStringToMillis(String duration) {
    if (duration.startsWith("0"))
      return 0L; 
    if (duration.matches("\\d+\\D+")) {
      Pattern pattern = Pattern.compile("\\D+$");
      Matcher matcher = pattern.matcher(duration);
      matcher.find();
      for (String code : codes.keySet()) {
        if (code.equalsIgnoreCase(matcher.group()))
          return 200L + Integer.parseInt(duration.substring(0, duration.length() - matcher.group().length())) * ((Long)codes.get(code)).longValue(); 
      } 
      return 0L;
    } 
    return 0L;
  }
  
  public static String fromMillisToString(long duration) {
    if (duration == 0L)
      return "maintenant"; 
    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    cal.setTime(new Date(duration));
    StringBuilder formatted = new StringBuilder();
    AtomicInteger currentFieldsAmount = new AtomicInteger(0);
    int years = cal.get(1) - 1970;
    addFieldToFormattedDuration(formatted, 2, currentFieldsAmount, (cal.get(1) > 1970) ? (years + " " + getPlurialWord("an", years) + " ") : "");
    int months = cal.get(2);
    addFieldToFormattedDuration(formatted, 2, currentFieldsAmount, (months > 0) ? (months + " mois ") : "");
    int days = cal.get(5) - 1;
    addFieldToFormattedDuration(formatted, 2, currentFieldsAmount, (days > 0) ? (days + " " + getPlurialWord("jour", days) + " ") : "");
    int hours = cal.get(11);
    addFieldToFormattedDuration(formatted, 2, currentFieldsAmount, (hours > 0) ? (hours + " " + getPlurialWord("heure", hours) + " ") : "");
    int minutes = cal.get(12);
    addFieldToFormattedDuration(formatted, 2, currentFieldsAmount, (minutes > 0) ? (minutes + " " + getPlurialWord("minute", minutes) + " ") : "");
    int seconds = cal.get(13);
    addFieldToFormattedDuration(formatted, 2, currentFieldsAmount, (seconds > 0) ? (seconds + " " + getPlurialWord("seconde", seconds) + " ") : "");
    return (formatted.length() > 0) ? formatted.substring(0, formatted.length() - 1) : "En cours";
  }
  
  public static String getPlurialWord(String word, int value) {
    return (value > 1) ? (word + "s") : word;
  }
  
  private static void addFieldToFormattedDuration(StringBuilder formatted, int maxFieldsAmount, AtomicInteger currentFieldsAmount, String formattedField) {
    if (formattedField.isEmpty())
      return; 
    if (currentFieldsAmount.get() < maxFieldsAmount) {
      formatted.append(formattedField);
      currentFieldsAmount.incrementAndGet();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\task\DurationConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */