package fr.paladium.palarpg.module.stat.server.listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palarpg.PalaRPGMod;
import fr.paladium.palarpg.module.entity.common.entity.ProjectileDamageManager;
import fr.paladium.palarpg.module.stat.server.manager.StatsManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class StatServerLivingEventListener {
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onLivingAttackEvent(LivingAttackEvent event) {
    if (PalaRPGMod.proxy.isDungeonWorld()) {
      if ("explosion".equalsIgnoreCase(event.source.func_76355_l())) {
        event.setCanceled(true);
        return;
      } 
      if (isValidDamageSource(event.source)) {
        if (event.source.func_76352_a() && !(event.source.func_76364_f() instanceof fr.paladium.palarpg.module.entity.common.entity.projectile.RPGProjectile)) {
          event.setCanceled(true);
          return;
        } 
        event.setCanceled(StatsManager.DODGE.tryDodge(event.entityLiving));
      } 
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onLivingHurtEvent(LivingHurtEvent event) {
    if (PalaRPGMod.proxy.isDungeonWorld() && isValidDamageSource(event.source)) {
      if (event.source.func_76352_a() && !(event.source.func_76364_f() instanceof fr.paladium.palarpg.module.entity.common.entity.projectile.RPGProjectile)) {
        event.setCanceled(true);
        return;
      } 
      float damage = "player".equalsIgnoreCase(event.source.func_76355_l()) ? 0.0F : (event.ammount - 1.0F);
      Entity source = event.source.func_76364_f();
      if (event.source.func_76352_a() && ProjectileDamageManager.getProjectileDamage(source) != null) {
        damage = ProjectileDamageManager.getProjectileDamage(source).getDamage();
        source = ProjectileDamageManager.getProjectileDamage(source).getEntity();
      } 
      if (source instanceof EntityLivingBase)
        StatsManager.DAMAGE.applyDamageToEntity((EntityLivingBase)source, event.entityLiving, damage); 
      ProjectileDamageManager.removeProjectileDamage(event.source.func_76364_f());
      if (event.entityLiving instanceof EntityPlayer) {
        EntityPlayer player = (EntityPlayer)event.entityLiving;
        player.field_71071_by.func_70449_g(1.0F);
      } 
      event.ammount = -1.0F;
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onLivingHealEvent(LivingHealEvent event) {
    if (PalaRPGMod.proxy.isDungeonWorld() && event.entityLiving instanceof EntityPlayer)
      event.setCanceled(true); 
  }
  
  private boolean isValidDamageSource(DamageSource source) {
    return ("player".equalsIgnoreCase(source.func_76355_l()) || "mob".equalsIgnoreCase(source.func_76355_l()) || source.func_76352_a() || "arrow".equalsIgnoreCase(source.func_76355_l()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\server\listener\StatServerLivingEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */