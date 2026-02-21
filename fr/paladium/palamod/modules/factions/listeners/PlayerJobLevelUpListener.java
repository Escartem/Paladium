package fr.paladium.palamod.modules.factions.listeners;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.api.type.FactionRelationshipType;
import fr.paladium.factions.core.achievements.FactionXpAchievement;
import fr.paladium.factions.core.config.ConfigFactionDefault;
import fr.paladium.factions.core.config.ConfigManager;
import fr.paladium.factions.core.faction.FactionController;
import fr.paladium.factions.core.faction.territory.ClaimController;
import fr.paladium.factions.core.player.FactionPlayer;
import fr.paladium.factions.core.player.Player;
import fr.paladium.factions.core.player.PlayerController;
import fr.paladium.palajobs.api.event.OnPlayerLevelUp;
import fr.paladium.palamod.modules.factions.PFactions;
import fr.paladium.palamod.modules.factions.dto.leveling.FactionXpChangeReason;
import fr.paladium.palapass.common.quest.factions.FactionEnterClaimQuest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent;

public class PlayerJobLevelUpListener {
  @SubscribeEvent
  public void onPlayerEnterChunk(EntityEvent.EnteringChunk event) {
    if (!(event.entity instanceof EntityPlayer) || event.entity.field_70170_p.field_72995_K)
      return; 
    IFaction faction = FactionController.getInstance().getExistingFactionSync(ClaimController.getInstance().getClaim(event.newChunkX, event.newChunkZ).getFactionUuid(), null);
    Player player = PlayerController.getInstance().getLoadedPlayer(event.entity.func_110124_au());
    if (player == null)
      return; 
    FactionPlayer factionPlayer = player.getFactionPlayer();
    if (factionPlayer == null || factionPlayer.getFaction() == null)
      return; 
    if (factionPlayer.getFaction() != null && factionPlayer.getFaction().getRelationshipEntity() != null && faction != null)
      if (factionPlayer.getFaction().getRelationshipEntity().getRelationship(faction) != null && factionPlayer
        .getFaction().getRelationshipEntity().getRelationship(faction).getType() != null && 
        FactionRelationshipType.ENEMY.equals(factionPlayer.getFaction().getRelationshipEntity().getRelationship(faction).getType())) {
        for (ConfigFactionDefault factionConf : ConfigManager.getInstance().getBaseFactions()) {
          if (factionConf.getUuid().equals(faction.getUuid()))
            return; 
        } 
        FactionEnterClaimQuest.trigger((EntityPlayer)event.entity);
      }  
  }
  
  @SubscribeEvent
  public void onPlayerJobLevelUp(OnPlayerLevelUp event) {
    if (!(event.player instanceof EntityPlayerMP) || (event.isCancelable() && event.isCanceled()))
      return; 
    if (event.levelAfter == event.levelBefore || event.levelAfter > 100)
      return; 
    EntityPlayerMP playerMp = (EntityPlayerMP)event.player;
    try {
      long xp = 350L * (event.levelAfter * 5) / 2L;
      PFactions.instance.getImpl().addXp(playerMp.func_110124_au(), xp, FactionXpChangeReason.JOB_LEVELUP);
      FactionXpAchievement.performCheck((EntityPlayer)playerMp, (int)xp);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\factions\listeners\PlayerJobLevelUpListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */