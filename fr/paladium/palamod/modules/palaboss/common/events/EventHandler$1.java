package fr.paladium.palamod.modules.palaboss.common.events;

import fr.paladium.chronos.server.api.async.ChronosCallback;
import fr.paladium.chronos.server.api.pojo.Planned;
import fr.paladium.factions.api.faction.FactionEloChangeReason;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.api.player.IPlayer;
import fr.paladium.factions.core.faction.levels.FactionXpChangeReason;
import fr.paladium.factions.core.player.PlayerController;
import fr.paladium.palamod.modules.palaboss.PPalaBoss;
import fr.paladium.palamod.modules.palaboss.common.eep.BossEEP;
import fr.paladium.palamod.modules.palaboss.common.eep.BossPlayerStat;
import fr.paladium.palamod.modules.ranking.RankingEvents;
import fr.paladium.ranking.common.data.RankedPlayerData;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

class null implements ChronosCallback<Planned> {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\events\EventHandler$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */