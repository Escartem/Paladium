package fr.paladium.palarpg.module.stat.server.manager;

import fr.paladium.palarpg.module.stat.server.manager.impl.BonusLootManager;
import fr.paladium.palarpg.module.stat.server.manager.impl.DamageManager;
import fr.paladium.palarpg.module.stat.server.manager.impl.DodgeManager;
import fr.paladium.palarpg.module.stat.server.manager.impl.HealthManager;
import fr.paladium.palarpg.module.stat.server.manager.impl.MaxHealthManager;
import fr.paladium.palarpg.module.stat.server.manager.impl.ResistanceManager;
import fr.paladium.palarpg.module.stat.server.manager.impl.SpeedManager;

public final class StatsManager {
  public static final DamageManager DAMAGE = new DamageManager();
  
  public static final MaxHealthManager MAX_HEALTH = new MaxHealthManager();
  
  public static final HealthManager HEALTH = new HealthManager();
  
  public static final DodgeManager DODGE = new DodgeManager();
  
  public static final ResistanceManager RESISTANCE = new ResistanceManager();
  
  public static final BonusLootManager BONUS_LOOT = new BonusLootManager();
  
  public static final SpeedManager SPEED = new SpeedManager();
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\server\manager\StatsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */