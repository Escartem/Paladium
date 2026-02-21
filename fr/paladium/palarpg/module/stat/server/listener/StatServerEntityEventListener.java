package fr.paladium.palarpg.module.stat.server.listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palarpg.PalaRPGMod;
import fr.paladium.palarpg.module.stat.server.manager.StatsManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class StatServerEntityEventListener {
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onEntityJoinWorldEvent(EntityJoinWorldEvent event) {
    if (event.entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)event.entity;
      if (PalaRPGMod.proxy.isDungeonWorld()) {
        StatsManager.MAX_HEALTH.applyMaxHealth((EntityLivingBase)player, true);
        StatsManager.SPEED.applySpeed((EntityLivingBase)player);
        return;
      } 
      StatsManager.MAX_HEALTH.resetMaxHealth((EntityLivingBase)player);
      StatsManager.SPEED.resetSpeed((EntityLivingBase)player);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\server\listener\StatServerEntityEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */