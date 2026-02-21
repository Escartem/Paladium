package fr.paladium.palaforgeutils.lib.time;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtils {
  public static long now() {
    return ZonedDateTime.now(ZoneId.of("Europe/Paris")).toEpochSecond();
  }
  
  public static ZonedDateTime nowZoned() {
    return ZonedDateTime.now(ZoneId.of("Europe/Paris"));
  }
  
  public static ZonedDateTime fromLong(long l) {
    Instant instant = Instant.ofEpochSecond(l);
    ZonedDateTime date = ZonedDateTime.ofInstant(instant, ZoneId.of("Europe/Paris"));
    return date;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\time\TimeUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */