package fr.paladium.palarpg.module.equipment.common.event;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerEquipmentSetChangedEvent extends Event {
  private final EntityPlayer player;
  
  private final Map<String, Integer> oldSetMap;
  
  private final Map<String, Integer> newSetMap;
  
  public PlayerEquipmentSetChangedEvent(EntityPlayer player, Map<String, Integer> oldSetMap, Map<String, Integer> newSetMap) {
    this.player = player;
    this.oldSetMap = oldSetMap;
    this.newSetMap = newSetMap;
  }
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public Map<String, Integer> getOldSetMap() {
    return this.oldSetMap;
  }
  
  public Map<String, Integer> getNewSetMap() {
    return this.newSetMap;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\event\PlayerEquipmentSetChangedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */