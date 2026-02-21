package fr.paladium.palarpg.module.profile.server.listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palajobs.api.event.OnPlayerLevelUp;
import fr.paladium.palarpg.PalaRPGMod;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.profile.common.playerdata.RPGProfilePlayerData;
import fr.paladium.palarpg.module.profile.common.skilltree.manager.RPGSkillTreeManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class ProfileServerPlayerEventListener {
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onPlayerJoinWorld(EntityJoinWorldEvent event) {
    if (!(event.entity instanceof net.minecraft.entity.player.EntityPlayerMP) || !PalaRPGMod.proxy.isDungeonWorld())
      return; 
    RPGSkillTreeManager.apply((EntityPlayer)event.entity);
  }
  
  @SubscribeEvent
  public void onJobLevelUp(OnPlayerLevelUp event) {
    RPGProfilePlayerData profileData = (RPGProfilePlayerData)RPGPlayer.getData((Entity)event.player, "profile");
    if (profileData == null)
      return; 
    profileData.addSkillPoints(1);
    profileData.sync();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\server\listener\ProfileServerPlayerEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */