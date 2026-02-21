package fr.paladium.palajobs.core.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtil {
  public static long now() {
    return ZonedDateTime.now(ZoneId.of("Europe/Paris")).toEpochSecond();
  }
  
  public static ZonedDateTime nowZoned() {
    return ZonedDateTime.now(ZoneId.of("Europe/Paris"));
  }
  
  public static ZonedDateTime fromlong(long l) {
    return ZonedDateTime.ofInstant(Instant.ofEpochMilli(l), ZoneId.of("Europe/Paris"));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\cor\\utils\TimeUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */