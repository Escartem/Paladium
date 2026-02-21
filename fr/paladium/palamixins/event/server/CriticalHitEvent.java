package fr.paladium.palamixins.event.server;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

@Cancelable
public class CriticalHitEvent extends Event {
  private final EntityPlayerMP player;
  
  private final Entity target;
  
  private final CriticalType type;
  
  public CriticalHitEvent(EntityPlayerMP player, Entity target, CriticalType type) {
    this.player = player;
    this.target = target;
    this.type = type;
  }
  
  public EntityPlayerMP getPlayer() {
    return this.player;
  }
  
  public Entity getTarget() {
    return this.target;
  }
  
  public CriticalType getType() {
    return this.type;
  }
  
  public enum CriticalType {
    CRITICAL, ENCHANTMENT;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\event\server\CriticalHitEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */