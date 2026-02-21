package fr.paladium.palarpg.module.stat.common.listener;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palarpg.PalaRPGMod;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.palarpg.module.stat.server.manager.StatsManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.living.LivingEvent;

public class StatCommonLivingListener {
  @SubscribeEvent
  public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
    if (!event.entityLiving.field_70170_p.field_72995_K && PalaRPGMod.proxy.isDungeonWorld() && event.entityLiving instanceof net.minecraft.entity.player.EntityPlayer)
      StatsManager.HEALTH.applyNaturalRegeneration(event.entityLiving); 
    RPGStatPlayerData entityData = (RPGStatPlayerData)RPGPlayer.getData(event.entity, "stats");
    if (entityData == null || (FMLCommonHandler.instance().getSide() == Side.CLIENT && !event.entityLiving.func_110124_au().equals((Minecraft.func_71410_x()).field_71439_g.func_110124_au())))
      return; 
    entityData.tick();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\common\listener\StatCommonLivingListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */