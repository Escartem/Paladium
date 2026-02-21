package fr.paladium.palajobs.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

@Cancelable
public class OnPlayerEarnXp extends Event {
  public final EntityPlayer player;
  
  public final String jobName;
  
  public double xpearn;
  
  public void setXpearn(double xpearn) {
    this.xpearn = xpearn;
  }
  
  public OnPlayerEarnXp(EntityPlayer player, String jobName, double xpearn) {
    this.player = player;
    this.jobName = jobName;
    this.xpearn = xpearn;
  }
  
  public static OnPlayerEarnXp fireEvent(EntityPlayer player, String jobName, double xpearn) {
    OnPlayerEarnXp event = new OnPlayerEarnXp(player, jobName, xpearn);
    MinecraftForge.EVENT_BUS.post(event);
    return event;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\api\event\OnPlayerEarnXp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */