package fr.paladium.palawither.common.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class UniversalTimeUtils {
  private static final ZoneId ZONE_ID = ZoneId.of("Europe/Paris");
  
  public static long now() {
    return ZonedDateTime.now(ZONE_ID).toEpochSecond() * 1000L;
  }
  
  public static ZonedDateTime nowZoned() {
    return ZonedDateTime.now(ZONE_ID);
  }
  
  public static ZonedDateTime fromLong(long l) {
    Instant instant = Instant.ofEpochMilli(l);
    return ZonedDateTime.ofInstant(instant, ZONE_ID);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\commo\\utils\UniversalTimeUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */