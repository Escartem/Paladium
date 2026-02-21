package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.config;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;

public class SeptemberConfig {
  private static final DoubleLocation DEFAULT_LOCATION = new DoubleLocation(0.0D, 100.0D, 0.0D);
  
  public DoubleLocation getHolidayIslandLocation() {
    return this.holidayIslandLocation;
  }
  
  private final DoubleLocation holidayIslandLocation = new DoubleLocation(0.0D, 100.0D, 0.0D);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\config\SeptemberConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */