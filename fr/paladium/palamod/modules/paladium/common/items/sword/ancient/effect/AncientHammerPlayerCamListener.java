package fr.paladium.palamod.modules.paladium.common.items.sword.ancient.effect;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.manager.AncientHammerEffectCamManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class AncientHammerPlayerCamListener {
  @SubscribeEvent
  public void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
    EntityPlayer player = event.player;
    FMLServerScheduler.getInstance().add(new Runnable[] { () -> AncientHammerEffectCamManager.stop((EntityPlayerMP)player) });
  }
  
  @SubscribeEvent
  public void onUpdate(LivingEvent.LivingUpdateEvent event) {
    if (!(event.entity instanceof EntityPlayer))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.entity;
    if (!AncientHammerEffectCamManager.isPlayerCamEffectActive(player))
      return; 
    AncientHammerEffectCamManager.AncientHammerEffectCam cam = AncientHammerEffectCamManager.getPlayerCamEffect(player);
    if (cam.isFinished())
      AncientHammerEffectCamManager.stop(player); 
  }
  
  @SubscribeEvent
  public void onDamage(LivingAttackEvent event) {
    if (!(event.entity instanceof EntityPlayer))
      return; 
    EntityPlayerMP victim = (EntityPlayerMP)event.entity;
    if (AncientHammerEffectCamManager.isPlayerCamEffectActive(victim))
      event.setCanceled(true); 
  }
  
  @SubscribeEvent
  public void onDamage(LivingHurtEvent event) {
    if (!(event.entity instanceof EntityPlayer))
      return; 
    EntityPlayerMP victim = (EntityPlayerMP)event.entity;
    if (AncientHammerEffectCamManager.isPlayerCamEffectActive(victim)) {
      event.setCanceled(true);
      event.ammount = 0.0F;
    } 
  }
  
  @SubscribeEvent
  public void onPickup(EntityItemPickupEvent event) {
    if (AncientHammerEffectCamManager.isPlayerCamEffectActive((EntityPlayerMP)event.entityPlayer))
      event.setCanceled(true); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\sword\ancient\effect\AncientHammerPlayerCamListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */