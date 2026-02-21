package fr.paladium.palamod.modules.achievements.tasks;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.server.managers.AchievementManager;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.core.faction.territory.FactionTerritoryType;
import fr.paladium.factions.core.player.FactionPlayer;
import fr.paladium.factions.core.player.Player;
import fr.paladium.factions.core.player.PlayerController;
import fr.paladium.palaforgeutils.lib.player.PlayerUtils;
import fr.paladium.palaforgeutils.lib.task.ATask;
import fr.paladium.palaforgeutils.lib.task.Schedule;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.achievements.network.data.AchievementExtraPlayer;
import fr.paladium.palamod.modules.achievements.types.ArmorSetEquipAchievement;
import fr.paladium.palamod.modules.achievements.types.EquipBaclavaHelmetAchievement;
import fr.paladium.palamod.modules.achievements.types.faction.FactionClaimAchievement;
import fr.paladium.palamod.modules.achievements.types.faction.FactionReachLevelAchievement;
import fr.paladium.palamod.modules.achievements.types.faction.OwnFactionTownAchievement;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

@Schedule(every = "60s", async = true)
public class AchievementTask extends ATask {
  public AchievementTask() {
    super("achvmt-check-task");
    start();
  }
  
  public void run() {
    try {
      PlayerUtils.getOnlinePlayers().forEach(player -> {
            ArmorSetEquipAchievement.performCheck((EntityPlayer)player);
            performFactionAchievements(player);
            performBaclavaHelmetAchievement(player);
          });
    } catch (Exception exception) {}
  }
  
  private void performBaclavaHelmetAchievement(EntityPlayerMP playerMP) {
    ItemStack helmet = playerMP.field_71071_by.field_70460_b[3];
    if (helmet == null || helmet.func_77973_b() == null || helmet.func_77973_b() != ItemsRegister.BALACLAVA_HELMET)
      return; 
    AchievementExtraPlayer eep = AchievementExtraPlayer.get((EntityPlayer)playerMP);
    if (eep == null || !eep.updateAchievement(60000L))
      return; 
    performCheck((Class)EquipBaclavaHelmetAchievement.class, (EntityPlayer)playerMP, 1);
  }
  
  private void performFactionAchievements(EntityPlayerMP playerMP) {
    Player player = PlayerController.getInstance().getLoadedPlayer(playerMP);
    if (player == null)
      return; 
    FactionPlayer factionPlayer = player.getFactionPlayer();
    if (factionPlayer == null || factionPlayer.getFaction() == null)
      return; 
    IFaction faction = factionPlayer.getFaction();
    if (faction.getTownEntity() != null)
      performCheck((Class)OwnFactionTownAchievement.class, (EntityPlayer)playerMP, 1); 
    if (faction.getClaimEntity() != null && !faction.getClaimEntity().getClaims(FactionTerritoryType.CLASSIC).isEmpty())
      performCheck((Class)FactionClaimAchievement.class, (EntityPlayer)playerMP, 1); 
    int level = faction.getLevelEntity().getLevel();
    int elo = faction.getLevelEntity().getElo().getRealtimeElo();
    FactionReachLevelAchievement.performCheck((EntityPlayer)playerMP, level);
    FactionReachLevelAchievement.performCheck((EntityPlayer)playerMP, elo);
  }
  
  public static void performCheck(Class<? extends Achievement> clazz, EntityPlayer player, int quantity) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(clazz))
      Achievement.incrementStats(achievement, player, quantity); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\tasks\AchievementTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */