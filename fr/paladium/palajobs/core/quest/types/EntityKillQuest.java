package fr.paladium.palajobs.core.quest.types;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.QuestType;
import fr.paladium.palajobs.core.quest.AbstractQuest;
import fr.paladium.palajobs.server.managers.JobsManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class EntityKillQuest extends AbstractQuest {
  private Class<? extends Entity> entityType;
  
  public Class<? extends Entity> getEntityType() {
    return this.entityType;
  }
  
  public static EntityKillQuestBuilder builder() {
    return new EntityKillQuestBuilder();
  }
  
  public static class EntityKillQuestBuilder {
    private String id;
    
    private JobType job;
    
    private String name;
    
    private String description;
    
    private String icon;
    
    private QuestType type;
    
    private int quantity;
    
    private double earnedExperience;
    
    private Class<? extends Entity> entityType;
    
    public EntityKillQuestBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public EntityKillQuestBuilder job(JobType job) {
      this.job = job;
      return this;
    }
    
    public EntityKillQuestBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public EntityKillQuestBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public EntityKillQuestBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public EntityKillQuestBuilder type(QuestType type) {
      this.type = type;
      return this;
    }
    
    public EntityKillQuestBuilder quantity(int quantity) {
      this.quantity = quantity;
      return this;
    }
    
    public EntityKillQuestBuilder earnedExperience(double earnedExperience) {
      this.earnedExperience = earnedExperience;
      return this;
    }
    
    public EntityKillQuestBuilder entityType(Class<? extends Entity> entityType) {
      this.entityType = entityType;
      return this;
    }
    
    public EntityKillQuest build() {
      return new EntityKillQuest(this.id, this.job, this.name, this.description, this.icon, this.type, this.quantity, this.earnedExperience, this.entityType);
    }
    
    public String toString() {
      return "EntityKillQuest.EntityKillQuestBuilder(id=" + this.id + ", job=" + this.job + ", name=" + this.name + ", description=" + this.description + ", icon=" + this.icon + ", type=" + this.type + ", quantity=" + this.quantity + ", earnedExperience=" + this.earnedExperience + ", entityType=" + this.entityType + ")";
    }
  }
  
  public EntityKillQuest(String id, JobType job, String name, String description, String icon, QuestType type, int quantity, double earnedExperience, Class<? extends Entity> entityType) {
    super(id, job, name, description, icon, type, quantity, earnedExperience);
    this.entityType = entityType;
  }
  
  public static void performCheck(EntityPlayer player, Class<? extends Entity> entityType) {
    for (AbstractQuest quest : JobsManager.getInstance().getQuests(QuestType.ENTITY_KILL)) {
      if (!(quest instanceof EntityKillQuest))
        continue; 
      EntityKillQuest currentQuest = (EntityKillQuest)quest;
      if (currentQuest.getEntityType().equals(entityType))
        incrementStats(currentQuest, player, 1); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\quest\types\EntityKillQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */