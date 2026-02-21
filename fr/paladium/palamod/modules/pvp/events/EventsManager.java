package fr.paladium.palamod.modules.pvp.events;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palamod.metrics.bigbrother.BigBrotherImpl;
import fr.paladium.palamod.modules.pvp.dto.PvpStat;
import fr.paladium.palamod.modules.pvp.eep.PvpEEP;
import fr.paladium.palamod.util.FastUUID;
import fr.paladium.worldguardflagplus.WorldGuardFlagPlus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EventsManager {
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onDeath(LivingDeathEvent e) {
    if (e.entityLiving.field_70170_p.field_72995_K || !(e.entityLiving instanceof EntityPlayer) || e.isCanceled())
      return; 
    EntityPlayer target = (EntityPlayer)e.entityLiving;
    if (BigBrotherImpl.instance == null || BigBrotherImpl.instance.getPlayerSession() == null)
      return; 
    BigBrotherImpl.instance.getPlayerSession().increment(FastUUID.toString((Entity)target), "deaths", 1.0D);
    if (!(e.source.func_76364_f() instanceof EntityPlayer))
      return; 
    EntityPlayer killer = (EntityPlayer)e.source.func_76364_f();
    String killerUuid = FastUUID.toString((Entity)killer);
    processKill("global", target, killer);
    String scope = getScope(killer);
    if (scope != null)
      processKill(scope, target, killer); 
    BigBrotherImpl.instance.getPlayerSession().increment(killerUuid, "kills", 1.0D);
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onDamage(LivingHurtEvent event) {
    if (event.entityLiving.field_70170_p.field_72995_K || !(event.entityLiving instanceof EntityPlayer))
      return; 
    EntityPlayer target = (EntityPlayer)event.entityLiving;
    if (!(event.source.func_76364_f() instanceof EntityPlayer) || event.isCanceled())
      return; 
    EntityPlayer attacker = (EntityPlayer)event.source.func_76364_f();
    processDamage("global", target, attacker, event.ammount);
    String scope = getScope(attacker);
    if (scope != null)
      processDamage(scope, target, attacker, event.ammount); 
  }
  
  private void processDamage(String scope, EntityPlayer target, EntityPlayer attacker, double damage) {
    PvpEEP targetProperties = PvpEEP.get((Entity)target);
    PvpEEP attackerProperties = PvpEEP.get((Entity)attacker);
    PvpStat targetGlobalStats = targetProperties.getOrCreateStat(scope);
    targetGlobalStats.incrementDamageTaken(damage);
    PvpStat attackerGlobalStats = attackerProperties.getOrCreateStat(scope);
    attackerGlobalStats.incrementDamageDealt(damage);
    long now = System.currentTimeMillis();
    long attackDiff = now - attackerGlobalStats.getLastDamageDealt();
    if (attackDiff <= 1000L) {
      attackerGlobalStats.increaseCombo();
    } else {
      attackerGlobalStats.resetCombo();
      attackerGlobalStats.increaseCombo();
    } 
    attackerGlobalStats.setLastDamageDealt(now);
  }
  
  private void processKill(String scope, EntityPlayer target, EntityPlayer killer) {
    PvpEEP targetProperties = PvpEEP.get((Entity)target);
    PvpEEP killerProperties = PvpEEP.get((Entity)killer);
    PvpStat targetGlobalStats = targetProperties.getOrCreateStat(scope);
    targetGlobalStats.incrementDeaths();
    targetGlobalStats.resetKillStreak();
    PvpStat killerGlobalStats = killerProperties.getOrCreateStat(scope);
    killerGlobalStats.incrementKills();
    killerGlobalStats.increaseKillStreak();
  }
  
  private String getScope(EntityPlayer target) {
    if (ForgeEnv.isIDE())
      return null; 
    String flag = WorldGuardUtils.getFlag(target.field_70170_p, target.field_70165_t, target.field_70163_u, target.field_70161_v, WorldGuardFlagPlus.EVENT_FACTION);
    if (flag == null)
      return null; 
    switch (flag) {
      case "TOTEM":
        return "totem";
      case "KOTH":
        return "koth";
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pvp\events\EventsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */