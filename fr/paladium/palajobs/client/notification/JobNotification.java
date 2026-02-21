package fr.paladium.palajobs.client.notification;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.translate.common.texttotranslate.TTT;
import net.minecraft.entity.player.EntityPlayerMP;

public class JobNotification {
  private final Notification notification;
  
  private JobType type;
  
  private double xp;
  
  public Notification getNotification() {
    return this.notification;
  }
  
  public JobType getType() {
    return this.type;
  }
  
  public double getXp() {
    return this.xp;
  }
  
  public JobNotification(JobType type, int level, double xp, double multiplier) {
    this.notification = new Notification(Notification.NotificationType.INFO, "", "");
    this.type = type;
    this.xp = xp;
    update(type, level, 0.0D, multiplier);
  }
  
  public void update(JobType type, int level, double xp, double multiplier) {
    if (this.type != type)
      this.xp = 0.0D; 
    this.type = type;
    this.xp += xp;
    this.notification.title(TTT.format("jobs.level", new Object[0]) + " " + level);
    this.notification.description("+" + String.format("%.2f", new Object[] { Double.valueOf(this.xp) }) + "xp (+" + String.format("%.2f", new Object[] { Double.valueOf(multiplier) }) + "%)");
    this.notification.origin(TTT.format(type.getName(), new Object[0]));
    this.notification.setStart(System.currentTimeMillis());
  }
  
  @SideOnly(Side.CLIENT)
  public void send() {
    this.notification.send();
  }
  
  @SideOnly(Side.SERVER)
  public void send(EntityPlayerMP player) {
    this.notification.send(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\client\notification\JobNotification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */