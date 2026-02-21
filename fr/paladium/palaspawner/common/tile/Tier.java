package fr.paladium.palaspawner.common.tile;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public enum Tier {
  ZERO(0, 0, 0, 0, 0L, "T0"),
  ONE(50, 27, 2, 3, TimeUnit.SECONDS.toMillis(12L), "T1"),
  TWO(100, 54, 3, 5, TimeUnit.SECONDS.toMillis(15L), "T2"),
  THREE(150, 81, 5, 7, TimeUnit.SECONDS.toMillis(15L), "T3"),
  FOUR(200, 108, 6, 11, TimeUnit.SECONDS.toMillis(7L), "T4");
  
  Tier(int requiredSouls, int maxSpawners, int entityMin, int entityMax, long timeMax, String romanId) {
    this.requiredSouls = requiredSouls;
    this.maxSpawners = maxSpawners;
    this.entityMin = entityMin;
    this.entityMax = entityMax;
    this.timeMax = timeMax;
    this.romanId = romanId;
  }
  
  private static final Tier[] REVERSED_TIERS;
  
  private final int requiredSouls;
  
  private final int maxSpawners;
  
  private final int entityMin;
  
  private final int entityMax;
  
  private final long timeMax;
  
  private final String romanId;
  
  static {
    REVERSED_TIERS = new Tier[] { FOUR, THREE, TWO, ONE, ZERO };
  }
  
  public int getRequiredSouls() {
    return this.requiredSouls;
  }
  
  public int getMaxSpawners() {
    return this.maxSpawners;
  }
  
  public int getEntityMin() {
    return this.entityMin;
  }
  
  public int getEntityMax() {
    return this.entityMax;
  }
  
  public long getTimeMax() {
    return this.timeMax;
  }
  
  public String getRomanId() {
    return this.romanId;
  }
  
  public static Tier getTier(int souls) {
    for (Tier tier : REVERSED_TIERS) {
      if (souls >= tier.requiredSouls)
        return tier; 
    } 
    return ZERO;
  }
  
  public int getSpawnAmount(Random random) {
    if (random == null || this.entityMax <= this.entityMin)
      return 0; 
    return this.entityMin + random.nextInt(this.entityMax - this.entityMin);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\tile\Tier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */