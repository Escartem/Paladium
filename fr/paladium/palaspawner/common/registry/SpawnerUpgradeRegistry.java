package fr.paladium.palaspawner.common.registry;

import fr.paladium.palaspawner.common.spawner.upgrade.SpawnerUpgrade;
import java.util.HashMap;

public class SpawnerUpgradeRegistry {
  public static final HashMap<String, SpawnerUpgrade> UPGRADES = new HashMap<>();
  
  public static SpawnerUpgrade SLIME;
  
  public static SpawnerUpgrade SPEED;
  
  public static SpawnerUpgrade LOOTING;
  
  public static SpawnerUpgrade MORE;
  
  public static void register(SpawnerUpgrade upgrade) {
    UPGRADES.put(upgrade.getId(), upgrade);
  }
  
  public static void register(SpawnerUpgrade... upgrades) {
    for (SpawnerUpgrade upgrade : upgrades)
      register(upgrade); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\registry\SpawnerUpgradeRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */