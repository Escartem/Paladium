package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingEvent;

public class JumpEventHandler {
  @SubscribeEvent
  public void onJump(LivingEvent.LivingJumpEvent event) {
    Entity entity = event.entity;
    if (!(entity instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)entity;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\server\events\JumpEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */