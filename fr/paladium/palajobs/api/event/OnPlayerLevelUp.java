package fr.paladium.palajobs.api.event;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class OnPlayerLevelUp extends Event {
  public final EntityPlayer player;
  
  public final String jobName;
  
  public final int levelBefore;
  
  public final int levelAfter;
  
  public OnPlayerLevelUp(EntityPlayer player, String jobName, int levelBefore, int levelAfter) {
    this.player = player;
    this.jobName = jobName;
    this.levelBefore = levelBefore;
    this.levelAfter = levelAfter;
  }
  
  public static OnPlayerLevelUp fireEvent(EntityPlayer player, String jobName, int levelBefore, int levelAfter) {
    OnPlayerLevelUp event = new OnPlayerLevelUp(player, jobName, levelBefore, levelAfter);
    MinecraftForge.EVENT_BUS.post(event);
    return event;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\api\event\OnPlayerLevelUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */