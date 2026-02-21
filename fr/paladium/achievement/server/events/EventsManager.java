package fr.paladium.achievement.server.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.achievement.core.data.ExtendedAchievementPlayerData;
import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.WalkDistanceQuestHelper;
import fr.paladium.achievement.core.pojo.types.BreakBlockAchievement;
import fr.paladium.achievement.core.pojo.types.CommandAchievement;
import fr.paladium.achievement.core.pojo.types.PlayerLevelupAchievement;
import fr.paladium.achievement.core.pojo.types.WalkDistanceAchievement;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.world.BlockEvent;

public class EventsManager {
  private final AchievementManager manager = AchievementManager.getInstance();
  
  @SubscribeEvent
  public void onConnect(PlayerEvent.PlayerLoggedInEvent event) {
    EntityPlayer player = event.player;
    boolean foundWalkAchvmt = false;
    ExtendedAchievementPlayerData eep = ExtendedAchievementPlayerData.get((Entity)player);
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(WalkDistanceAchievement.class)) {
      if (achievement instanceof WalkDistanceAchievement && (
        !eep.achievementStats.containsKey(achievement.getId()) || (eep.achievementStats.containsKey(achievement.getId()) && ((Integer)eep.achievementStats.get(achievement.getId())).intValue() < achievement.getNbToValidate()))) {
        foundWalkAchvmt = true;
        break;
      } 
    } 
    if (foundWalkAchvmt)
      this.manager.getWalkDistanceQuestData().put(player.func_110124_au(), new WalkDistanceQuestHelper(player.field_70165_t, player.field_70163_u, player.field_70161_v)); 
  }
  
  @SubscribeEvent
  public void onPlayerTick(TickEvent.PlayerTickEvent e) {
    if (e.side == Side.CLIENT || e.phase != TickEvent.Phase.END)
      return; 
    if (AchievementManager.getInstance().getWalkDistanceQuestData().containsKey(e.player.func_110124_au())) {
      WalkDistanceQuestHelper walk = (WalkDistanceQuestHelper)this.manager.getWalkDistanceQuestData().get(e.player.func_110124_au());
      double distance = e.player.func_70011_f(walk.getX(), e.player.field_70163_u, walk.getZ());
      if (distance >= 1.0D && distance < 3.0D) {
        WalkDistanceAchievement.performCheck(e.player, (int)distance);
        ((WalkDistanceQuestHelper)this.manager.getWalkDistanceQuestData().get(e.player.func_110124_au())).setX(e.player.field_70165_t);
        ((WalkDistanceQuestHelper)this.manager.getWalkDistanceQuestData().get(e.player.func_110124_au())).setY(e.player.field_70163_u);
        ((WalkDistanceQuestHelper)this.manager.getWalkDistanceQuestData().get(e.player.func_110124_au())).setZ(e.player.field_70161_v);
      } else if (distance >= 3.0D) {
        ((WalkDistanceQuestHelper)this.manager.getWalkDistanceQuestData().get(e.player.func_110124_au())).setX(e.player.field_70165_t);
        ((WalkDistanceQuestHelper)this.manager.getWalkDistanceQuestData().get(e.player.func_110124_au())).setY(e.player.field_70163_u);
        ((WalkDistanceQuestHelper)this.manager.getWalkDistanceQuestData().get(e.player.func_110124_au())).setZ(e.player.field_70161_v);
      } 
    } 
  }
  
  @SubscribeEvent
  public void onMessage(CommandEvent event) {
    if (event.sender instanceof EntityPlayer)
      CommandAchievement.performCheck(event.command.func_71517_b(), (EntityPlayer)event.sender); 
  }
  
  @SubscribeEvent
  public void breakEventPalapass(BlockEvent.HarvestDropsEvent event) {
    if (event == null || event.harvester == null || event.harvester.func_130014_f_() == null || (event.harvester.func_130014_f_()).field_72995_K)
      return; 
    BreakBlockAchievement.performCheck(event.block, event.harvester);
  }
  
  @SubscribeEvent
  public void playerLevelup(PlayerPickupXpEvent event) {
    PlayerLevelupAchievement.performCheck(event.entityPlayer.field_71068_ca, event.entityPlayer);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\server\events\EventsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */