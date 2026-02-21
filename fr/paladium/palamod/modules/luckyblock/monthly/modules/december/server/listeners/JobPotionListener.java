package fr.paladium.palamod.modules.luckyblock.monthly.modules.december.server.listeners;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palajobs.api.event.OnPlayerPreEarnXp;
import fr.paladium.palamod.api.PotionsRegister;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class JobPotionListener {
  @SubscribeEvent
  public void onEarnExperience(OnPlayerPreEarnXp event) {
    World world = event.player.field_70170_p;
    if (world.field_72995_K)
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.player;
    if (player.func_82165_m(PotionsRegister.JOB_TEN_MULTIPLIER.getPotionId()))
      event.xpearn *= 10.0D; 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\december\server\listeners\JobPotionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */