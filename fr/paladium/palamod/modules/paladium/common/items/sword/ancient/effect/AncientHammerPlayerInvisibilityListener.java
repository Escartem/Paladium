package fr.paladium.palamod.modules.paladium.common.items.sword.ancient.effect;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.data.ItemAncientHammerPlayerData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class AncientHammerPlayerInvisibilityListener {
  @SubscribeEvent
  public void onDamage(LivingAttackEvent event) {
    if (!(event.entity instanceof EntityPlayer) || event.entity.field_70170_p.field_72995_K)
      return; 
    EntityPlayerMP victim = (EntityPlayerMP)event.entity;
    if (ItemAncientHammerPlayerData.get((EntityPlayer)victim).isInvisibilityInvulnerable())
      event.setCanceled(true); 
  }
  
  @SubscribeEvent
  public void onDamage(LivingHurtEvent event) {
    if (!(event.entity instanceof EntityPlayer) || event.entity.field_70170_p.field_72995_K)
      return; 
    EntityPlayerMP victim = (EntityPlayerMP)event.entity;
    if (ItemAncientHammerPlayerData.get((EntityPlayer)victim).isInvisibilityInvulnerable()) {
      event.setCanceled(true);
      event.ammount = 0.0F;
    } 
  }
  
  @SubscribeEvent
  public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
    if (event.player.field_70170_p.field_72995_K)
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.player;
    if (ItemAncientHammerPlayerData.get((EntityPlayer)player).isInvisibilityEffect()) {
      player.func_82142_c(false);
      ItemAncientHammerPlayerData.get((EntityPlayer)player).setInvisibilityEffect(false);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\sword\ancient\effect\AncientHammerPlayerInvisibilityListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */