package fr.paladium.palajobs.core.quest;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.QuestType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public abstract class AbstractQuest {
  private String id;
  
  private JobType job;
  
  private String name;
  
  private String description;
  
  private String icon;
  
  private QuestType type;
  
  private int quantity;
  
  private double earnedExperience;
  
  public AbstractQuest(String id, JobType job, String name, String description, String icon, QuestType type, int quantity, double earnedExperience) {
    this.id = id;
    this.job = job;
    this.name = name;
    this.description = description;
    this.icon = icon;
    this.type = type;
    this.quantity = quantity;
    this.earnedExperience = earnedExperience;
  }
  
  public String getId() {
    return this.id;
  }
  
  public JobType getJob() {
    return this.job;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public String getIcon() {
    return this.icon;
  }
  
  public QuestType getType() {
    return this.type;
  }
  
  public int getQuantity() {
    return this.quantity;
  }
  
  public double getEarnedExperience() {
    return this.earnedExperience;
  }
  
  public void register() {
    QuestRegistry.register(this);
  }
  
  public static void incrementStats(AbstractQuest quest, EntityPlayer player, int amount) {
    if (!(player instanceof net.minecraft.entity.player.EntityPlayerMP))
      return; 
    JobsPlayer eep = JobsPlayer.get((Entity)player);
    if (eep != null)
      eep.incrementQuestsStats(quest, amount, player); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\quest\AbstractQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */