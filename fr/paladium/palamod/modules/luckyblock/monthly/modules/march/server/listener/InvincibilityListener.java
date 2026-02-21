package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.api.PotionsRegister;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class InvincibilityListener {
  @SubscribeEvent
  public void onHurt(LivingHurtEvent event) {
    if (!(event.entity instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.entity;
    if (player.func_82165_m(PotionsRegister.STAR_INVINCIBILITY.getPotionId()))
      event.setCanceled(true); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\listener\InvincibilityListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */