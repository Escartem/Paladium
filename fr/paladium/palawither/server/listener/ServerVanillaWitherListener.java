package fr.paladium.palawither.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palawither.api.ItemsRegister;
import fr.paladium.palawither.common.network.SCPacketWitherData;
import fr.paladium.palawither.common.wither.base.IWither;
import fr.paladium.palawither.common.wither.impl.VanillaWither;
import java.util.Optional;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ServerVanillaWitherListener {
  @SubscribeEvent
  public void onVanillaWitherSpawn(EntityJoinWorldEvent event) {
    if (event.world.field_72995_K || !(event.entity instanceof EntityWither))
      return; 
    EntityWither wither = (EntityWither)event.entity;
    if (wither.func_82212_n() <= 0)
      return; 
    wither.func_70099_a(new ItemStack(ItemsRegister.VANILLA_WITHER_GEM), 1.0F);
    event.setCanceled(true);
  }
  
  @SubscribeEvent
  public void onVanillaWitherTick(LivingEvent.LivingUpdateEvent event) {
    if (event.entity.field_70170_p.field_72995_K || !(event.entity instanceof EntityWither))
      return; 
    EntityWither wither = (EntityWither)event.entity;
    Optional<VanillaWither> optionalWither = VanillaWither.getByEntity(wither);
    if (!optionalWither.isPresent())
      return; 
    SCPacketWitherData.update((IWither)optionalWither.get(), (EntityLivingBase)wither);
  }
  
  @SubscribeEvent
  public void onVanillaWitherDamage(LivingHurtEvent event) {
    if (event.entity.field_70170_p.field_72995_K || !(event.entity instanceof EntityWither) || event.source.func_76364_f() instanceof net.minecraft.entity.player.EntityPlayer)
      return; 
    EntityWither wither = (EntityWither)event.entity;
    Optional<VanillaWither> optionalWither = VanillaWither.getByEntity(wither);
    if (!optionalWither.isPresent() || !((VanillaWither)optionalWither.get()).hasUpgrade(ItemsRegister.RESISTANCE_WITHER_UPGRADE))
      return; 
    event.ammount /= 2.0F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\server\listener\ServerVanillaWitherListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */