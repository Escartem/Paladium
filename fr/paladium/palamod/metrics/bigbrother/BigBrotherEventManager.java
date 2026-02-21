package fr.paladium.palamod.metrics.bigbrother;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palajobs.api.event.OnPlayerEarnXp;
import fr.paladium.palajobs.api.event.OnPlayerLevelUp;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.world.BlockEvent;

public class BigBrotherEventManager {
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onPlace(BlockEvent.PlaceEvent e) {
    if (e.world.field_72995_K || e.isCanceled())
      return; 
    EntityPlayerMP player = (EntityPlayerMP)e.player;
    BigBrotherImpl.instance.getPlayerSession().increment(FastUUID.toString((Entity)player), "blockPlaced", 1.0D);
  }
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onBreak(BlockEvent.BreakEvent e) {
    if (e.world.field_72995_K || e.isCanceled())
      return; 
    EntityPlayerMP player = (EntityPlayerMP)e.getPlayer();
    BigBrotherImpl.instance.getPlayerSession().increment(FastUUID.toString((Entity)player), "blockBroke", 1.0D);
  }
  
  @SubscribeEvent
  public void onPlayerEarnJobLevel(OnPlayerLevelUp e) {
    EntityPlayer player = e.player;
    int earnLevel = e.levelAfter - e.levelBefore;
    BigBrotherImpl.instance.getPlayerSession().increment(FastUUID.toString((Entity)player), "jobEarnLevel", earnLevel);
    BigBrotherImpl.instance.getPlayerSession().increment(FastUUID.toString((Entity)player), "jobEarnLevel_" + e.jobName, earnLevel);
  }
  
  @SubscribeEvent
  public void onPlayerEarnXp(OnPlayerEarnXp e) {
    EntityPlayer player = e.player;
    BigBrotherImpl.instance.getPlayerSession().increment(FastUUID.toString((Entity)player), "jobEarnXp", e.xpearn);
    BigBrotherImpl.instance.getPlayerSession().increment(FastUUID.toString((Entity)player), "jobEarnXp_" + e.jobName, e.xpearn);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\metrics\bigbrother\BigBrotherEventManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */