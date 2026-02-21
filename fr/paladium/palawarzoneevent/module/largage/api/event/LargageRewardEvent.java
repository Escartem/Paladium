package fr.paladium.palawarzoneevent.module.largage.api.event;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayerMP;

public class LargageRewardEvent extends Event {
  private final EntityPlayerMP player;
  
  public EntityPlayerMP getPlayer() {
    return this.player;
  }
  
  public LargageRewardEvent(EntityPlayerMP player) {
    this.player = player;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\largage\api\event\LargageRewardEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */