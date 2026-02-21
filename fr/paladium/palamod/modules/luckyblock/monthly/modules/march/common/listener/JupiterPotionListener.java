package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.api.PotionsRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;

public class JupiterPotionListener {
  @SubscribeEvent
  public void onPlayerInteract(LivingEvent.LivingUpdateEvent event) {
    if (!(event.entity instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)event.entity;
    if (player.func_82165_m(PotionsRegister.JUPITER_GRAVITY.getPotionId())) {
      if (player.field_70122_E) {
        player.field_70747_aH = 0.0F;
        player.field_70181_x = -0.9D;
      } 
      if (player.field_70181_x > 0.0D) {
        player.field_70181_x = -0.2D;
        player.field_70143_R = 0.0F;
      } 
    } else {
      player.field_70747_aH = 0.02F;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\listener\JupiterPotionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */