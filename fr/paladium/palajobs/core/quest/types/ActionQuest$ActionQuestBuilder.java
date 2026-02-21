package fr.paladium.palajobs.core.quest.types;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.QuestType;

public class ActionQuestBuilder {
  private String id;
  
  private JobType job;
  
  private String name;
  
  private String description;
  
  private String icon;
  
  private QuestType type;
  
  private int quantity;
  
  private double earnedExperience;
  
  private String action;
  
  public ActionQuestBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public ActionQuestBuilder job(JobType job) {
    this.job = job;
    return this;
  }
  
  public ActionQuestBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public ActionQuestBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public ActionQuestBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public ActionQuestBuilder type(QuestType type) {
    this.type = type;
    return this;
  }
  
  public ActionQuestBuilder quantity(int quantity) {
    this.quantity = quantity;
    return this;
  }
  
  public ActionQuestBuilder earnedExperience(double earnedExperience) {
    this.earnedExperience = earnedExperience;
    return this;
  }
  
  public ActionQuestBuilder action(String action) {
    this.action = action;
    return this;
  }
  
  public ActionQuest build() {
    return new ActionQuest(this.id, this.job, this.name, this.description, this.icon, this.type, this.quantity, this.earnedExperience, this.action);
  }
  
  public String toString() {
    return "ActionQuest.ActionQuestBuilder(id=" + this.id + ", job=" + this.job + ", name=" + this.name + ", description=" + this.description + ", icon=" + this.icon + ", type=" + this.type + ", quantity=" + this.quantity + ", earnedExperience=" + this.earnedExperience + ", action=" + this.action + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\quest\types\ActionQuest$ActionQuestBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */