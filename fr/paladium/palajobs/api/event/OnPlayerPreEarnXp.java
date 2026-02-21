package fr.paladium.palajobs.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.utils.MultiplierUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

@Cancelable
public class OnPlayerPreEarnXp extends Event {
  public final EntityPlayer player;
  
  public final JobType jobType;
  
  public final ObjectiveType objectiveType;
  
  public final double multiplier;
  
  public final double finalXp;
  
  public double xpearn;
  
  public OnPlayerPreEarnXp(EntityPlayer player, JobType jobType, ObjectiveType objectiveType, double multiplier, double finalXp, double xpearn) {
    this.player = player;
    this.jobType = jobType;
    this.objectiveType = objectiveType;
    this.multiplier = multiplier;
    this.finalXp = finalXp;
    this.xpearn = xpearn;
  }
  
  public static OnPlayerPreEarnXp fireEvent(EntityPlayer player, JobType jobType, ObjectiveType objectiveType, double xpearn, double multiplier) {
    OnPlayerPreEarnXp event = new OnPlayerPreEarnXp(player, jobType, objectiveType, multiplier, MultiplierUtils.getExperienceFromMultiplier(xpearn, Double.valueOf(multiplier)), xpearn);
    MinecraftForge.EVENT_BUS.post(event);
    return event;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\api\event\OnPlayerPreEarnXp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */