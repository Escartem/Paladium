package fr.paladium.palajobs.core.quest.types;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.QuestType;
import net.minecraft.entity.Entity;

public class EntityFeedQuestBuilder {
  private String id;
  
  private JobType job;
  
  private String name;
  
  private String description;
  
  private String icon;
  
  private QuestType type;
  
  private int quantity;
  
  private double earnedExperience;
  
  private Class<? extends Entity> entityType;
  
  public EntityFeedQuestBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public EntityFeedQuestBuilder job(JobType job) {
    this.job = job;
    return this;
  }
  
  public EntityFeedQuestBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public EntityFeedQuestBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public EntityFeedQuestBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public EntityFeedQuestBuilder type(QuestType type) {
    this.type = type;
    return this;
  }
  
  public EntityFeedQuestBuilder quantity(int quantity) {
    this.quantity = quantity;
    return this;
  }
  
  public EntityFeedQuestBuilder earnedExperience(double earnedExperience) {
    this.earnedExperience = earnedExperience;
    return this;
  }
  
  public EntityFeedQuestBuilder entityType(Class<? extends Entity> entityType) {
    this.entityType = entityType;
    return this;
  }
  
  public EntityFeedQuest build() {
    return new EntityFeedQuest(this.id, this.job, this.name, this.description, this.icon, this.type, this.quantity, this.earnedExperience, this.entityType);
  }
  
  public String toString() {
    return "EntityFeedQuest.EntityFeedQuestBuilder(id=" + this.id + ", job=" + this.job + ", name=" + this.name + ", description=" + this.description + ", icon=" + this.icon + ", type=" + this.type + ", quantity=" + this.quantity + ", earnedExperience=" + this.earnedExperience + ", entityType=" + this.entityType + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\quest\types\EntityFeedQuest$EntityFeedQuestBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */