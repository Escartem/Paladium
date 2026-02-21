package fr.paladium.palamod.modules.luckyblock.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import net.minecraft.client.Minecraft;

public class TimeUtil {
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
  
  public static List<String> lati() {
    if (Minecraft.func_71410_x() == null)
      return null; 
    if (Minecraft.func_71410_x().func_147114_u() == null)
      return null; 
    if (Minecraft.func_71410_x().func_147114_u().func_147298_b() == null)
      return null; 
    try {
      return Minecraft.func_71410_x().func_147114_u().func_147298_b().channel().pipeline()
        .names();
    } catch (Exception e) {
      return null;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\TimeUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */