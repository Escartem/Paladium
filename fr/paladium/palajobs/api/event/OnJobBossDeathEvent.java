package fr.paladium.palajobs.api.event;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palajobs.core.entity.boss.AEntityJobBoss;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;

public class OnJobBossDeathEvent extends Event {
  private final AEntityJobBoss entity;
  
  private final Map<UUID, Float> damagers;
  
  private final float totalDamage;
  
  private final EntityPlayer winner;
  
  public AEntityJobBoss getEntity() {
    return this.entity;
  }
  
  public Map<UUID, Float> getDamagers() {
    return this.damagers;
  }
  
  public float getTotalDamage() {
    return this.totalDamage;
  }
  
  public EntityPlayer getWinner() {
    return this.winner;
  }
  
  public OnJobBossDeathEvent(AEntityJobBoss entity, Map<UUID, Float> damagers, float totalDamage, EntityPlayer winner) {
    this.entity = entity;
    this.damagers = damagers;
    this.totalDamage = totalDamage;
    this.winner = winner;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\api\event\OnJobBossDeathEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */