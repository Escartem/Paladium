package fr.paladium.palajobs.core.quest.types;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.QuestType;
import net.minecraft.item.ItemStack;

public class FishingQuestBuilder {
  private String id;
  
  private JobType job;
  
  private String name;
  
  private String description;
  
  private String icon;
  
  private QuestType type;
  
  private int quantity;
  
  private double earnedExperience;
  
  private ItemStack fish;
  
  public FishingQuestBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public FishingQuestBuilder job(JobType job) {
    this.job = job;
    return this;
  }
  
  public FishingQuestBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public FishingQuestBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public FishingQuestBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public FishingQuestBuilder type(QuestType type) {
    this.type = type;
    return this;
  }
  
  public FishingQuestBuilder quantity(int quantity) {
    this.quantity = quantity;
    return this;
  }
  
  public FishingQuestBuilder earnedExperience(double earnedExperience) {
    this.earnedExperience = earnedExperience;
    return this;
  }
  
  public FishingQuestBuilder fish(ItemStack fish) {
    this.fish = fish;
    return this;
  }
  
  public FishingQuest build() {
    return new FishingQuest(this.id, this.job, this.name, this.description, this.icon, this.type, this.quantity, this.earnedExperience, this.fish);
  }
  
  public String toString() {
    return "FishingQuest.FishingQuestBuilder(id=" + this.id + ", job=" + this.job + ", name=" + this.name + ", description=" + this.description + ", icon=" + this.icon + ", type=" + this.type + ", quantity=" + this.quantity + ", earnedExperience=" + this.earnedExperience + ", fish=" + this.fish + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\quest\types\FishingQuest$FishingQuestBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */