package fr.paladium.palamod.modules.palaboss.common.events;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.chronos.server.api.async.ChronosCallback;
import fr.paladium.chronos.server.api.pojo.Planned;
import fr.paladium.chronos.server.managers.ChronosManager;
import fr.paladium.factions.api.faction.FactionEloChangeReason;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.api.player.IPlayer;
import fr.paladium.factions.core.faction.levels.FactionXpChangeReason;
import fr.paladium.factions.core.player.PlayerController;
import fr.paladium.palamod.modules.palaboss.PPalaBoss;
import fr.paladium.palamod.modules.palaboss.client.ClientProxy;
import fr.paladium.palamod.modules.palaboss.client.gui.GuiPalaBossBar;
import fr.paladium.palamod.modules.palaboss.common.eep.BossEEP;
import fr.paladium.palamod.modules.palaboss.common.eep.BossPlayerStat;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import fr.paladium.palamod.modules.ranking.RankingEvents;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.ranking.common.data.RankedPlayerData;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EventHandler {
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void onRenderBossBar(RenderGameOverlayEvent.Pre e) {
    if (e.type == RenderGameOverlayEvent.ElementType.ALL)
      new GuiPalaBossBar(Minecraft.func_71410_x()); 
    if (e.type == RenderGameOverlayEvent.ElementType.BOSSHEALTH && ClientProxy.boss != null)
      e.setCanceled(true); 
  }
  
  @SubscribeEvent
  public void onKillBoss(final LivingDeathEvent e) {
    if (e.entity instanceof EntityBossBase && !e.entity.field_70170_p.field_72995_K && PPalaBoss.serverBossData != null && PPalaBoss.serverBossData.isActive() && PPalaBoss.serverBossData.getUuid() != null) {
      EntityBossBase boss = (EntityBossBase)e.entity;
      if (boss.getLoots() != null)
        return; 
      PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> ChronosManager.getInstance().stopEvent(PPalaBoss.serverBossData.getUuid(), new ChronosCallback<Planned>() {
              public void onSuccess(Planned event) {
                Optional<Map.Entry> optional = PPalaBoss.bossDamage.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).findFirst();
                if (!optional.isPresent()) {
                  MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLe monstre §e" + PPalaBoss.serverBossData.getBossName() + " §ca succombé seul."));
                  PPalaBoss.bossDamage.clear();
                  PPalaBoss.bossTotalDamage = 0.0F;
                  return;
                } 
                Map.Entry entry = optional.get();
                IPlayer iPlayer = PlayerController.getInstance().getExistingSyncPlayer((UUID)entry.getKey());
                if (iPlayer == null) {
                  MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLe monstre §e" + PPalaBoss.serverBossData.getBossName() + " §ca succombé seul."));
                  PPalaBoss.bossDamage.clear();
                  PPalaBoss.bossTotalDamage = 0.0F;
                  return;
                } 
                PPalaBoss.bossDamage.forEach((uuid, damage) -> {
                      EntityPlayer player = e.entity.field_70170_p.func_152378_a(uuid);
                      if (player != null) {
                        float rawDamage = damage.floatValue() / PPalaBoss.bossTotalDamage;
                        int p = (int)(rawDamage * 100.0F);
                        BossEEP bossEep = BossEEP.get((Entity)player);
                        BossPlayerStat bossPlayerStat = bossEep.getBossStat(PPalaBoss.serverBossData.getBossName());
                        bossPlayerStat.addDamage(damage.floatValue());
                        if (uuid.equals(entry.getKey()))
                          bossPlayerStat.addKill(); 
                        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §bVous avez infligé §e" + p + "% §bde dégâts au boss §c" + PPalaBoss.serverBossData.getBossName()));
                      } 
                    });
                RankedPlayerData.get((Entity)iPlayer.getFactionPlayer().getPlayer().getPlayer()).incrementValue("boss");
                String factionName = iPlayer.hasFaction() ? iPlayer.getFaction().getName() : "Wilderness";
                RankingEvents.pushRankingData((EntityPlayer)iPlayer.getFactionPlayer().getPlayer().getPlayer(), factionName, "boss");
                PPalaBoss.lastWinPercentage = (int)(((Float)entry.getValue()).floatValue() / PPalaBoss.bossTotalDamage * 100.0F);
                if (!iPlayer.hasFaction() || iPlayer.getFaction() == null || iPlayer.getFaction().isDefaultFaction()) {
                  MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §bFélicitation à §e" + iPlayer.getUsername() + " §bqui a vaincu le boss §c" + PPalaBoss.serverBossData.getBossName() + " §bavec §e" + PPalaBoss.lastWinPercentage + "% §bde dégâts."));
                  PPalaBoss.bossDamage.clear();
                  PPalaBoss.bossTotalDamage = 0.0F;
                  return;
                } 
                IFaction faction = iPlayer.getFaction();
                if (faction == null) {
                  MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §bFélicitation à §e" + iPlayer.getUsername() + " §bqui a vaincu le boss §c" + PPalaBoss.serverBossData.getBossName() + " §bavec §e" + PPalaBoss.lastWinPercentage + "% §bde dégâts."));
                  PPalaBoss.bossDamage.clear();
                  PPalaBoss.bossTotalDamage = 0.0F;
                  return;
                } 
                PPalaBoss.lastWinFaction = faction.getName();
                PPalaBoss.lastWinPlayer = iPlayer.getUsername();
                PPalaBoss.serverBossData.setActive(false);
                faction.getLevelEntity().addElo(PPalaBoss.commonConfig.getElo(), FactionEloChangeReason.KILL_BOSS);
                faction.getLevelEntity().addXp(PPalaBoss.commonConfig.getXp(), FactionXpChangeReason.KILL_BOSS);
                MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §bFélicitation au guerrier §e" + iPlayer.getUsername() + " §bde la §e" + faction.getName() + " §bqui a vaincu le boss §c" + PPalaBoss.serverBossData.getBossName() + " §bavec §e" + PPalaBoss.lastWinPercentage + "% §bde dégâts."));
                PPalaBoss.bossDamage.clear();
                PPalaBoss.bossTotalDamage = 0.0F;
              }
              
              public void onFail(Planned event, Throwable error) {
                error.printStackTrace();
                PPalaBoss.bossDamage.clear();
                PPalaBoss.bossTotalDamage = 0.0F;
              }
            }), 0L);
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.LOW)
  public void onDamageBoss(LivingHurtEvent e) {
    if (e.entity instanceof EntityBossBase && !e.entity.field_70170_p.field_72995_K) {
      EntityBossBase boss = (EntityBossBase)e.entity;
      if (boss.getLoots() != null)
        return; 
      Entity sourceOfDomage = e.source.func_76364_f();
      EntityPlayer tmpDamager = null;
      if (sourceOfDomage instanceof EntityPlayer) {
        tmpDamager = (EntityPlayer)e.source.func_76364_f();
      } else if (sourceOfDomage instanceof EntityThrowable && ((EntityThrowable)sourceOfDomage).func_85052_h() instanceof EntityPlayer) {
        tmpDamager = (EntityPlayer)((EntityThrowable)sourceOfDomage).func_85052_h();
      } 
      EntityPlayer damager = tmpDamager;
      if (!boss.isMain() || !PPalaBoss.serverBossData.isActive() || PPalaBoss.serverBossData.getUuid() == null) {
        if (damager != null)
          damager.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCe monstre semble venir d'autres contrées, il ne compte donc pas pour la saison.")); 
        boss.func_70106_y();
        return;
      } 
      if (damager == null)
        return; 
      PPalaBoss.bossTotalDamage += e.ammount;
      float playerDamage = ((Float)PPalaBoss.bossDamage.getOrDefault(damager.func_110124_au(), Float.valueOf(0.0F))).floatValue();
      playerDamage += e.ammount;
      PPalaBoss.bossDamage.put(damager.func_110124_au(), Float.valueOf(playerDamage));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\events\EventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */