package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.data;

import java.util.concurrent.TimeUnit;

public class NightHourData {
  public static final long DURATION_MILLIS = TimeUnit.HOURS.toMillis(1L);
  
  private long expirationMillis = System.currentTimeMillis() + DURATION_MILLIS;
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\data\NightHourData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */