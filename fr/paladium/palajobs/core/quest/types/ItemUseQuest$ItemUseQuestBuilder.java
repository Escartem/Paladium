package fr.paladium.palajobs.core.quest.types;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.QuestType;
import net.minecraft.item.ItemStack;

public class ItemUseQuestBuilder {
  private String id;
  
  private JobType job;
  
  private String name;
  
  private String description;
  
  private String icon;
  
  private QuestType type;
  
  private int quantity;
  
  private double earnedExperience;
  
  private ItemStack stack;
  
  public ItemUseQuestBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public ItemUseQuestBuilder job(JobType job) {
    this.job = job;
    return this;
  }
  
  public ItemUseQuestBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public ItemUseQuestBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public ItemUseQuestBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public ItemUseQuestBuilder type(QuestType type) {
    this.type = type;
    return this;
  }
  
  public ItemUseQuestBuilder quantity(int quantity) {
    this.quantity = quantity;
    return this;
  }
  
  public ItemUseQuestBuilder earnedExperience(double earnedExperience) {
    this.earnedExperience = earnedExperience;
    return this;
  }
  
  public ItemUseQuestBuilder stack(ItemStack stack) {
    this.stack = stack;
    return this;
  }
  
  public ItemUseQuest build() {
    return new ItemUseQuest(this.id, this.job, this.name, this.description, this.icon, this.type, this.quantity, this.earnedExperience, this.stack);
  }
  
  public String toString() {
    return "ItemUseQuest.ItemUseQuestBuilder(id=" + this.id + ", job=" + this.job + ", name=" + this.name + ", description=" + this.description + ", icon=" + this.icon + ", type=" + this.type + ", quantity=" + this.quantity + ", earnedExperience=" + this.earnedExperience + ", stack=" + this.stack + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\quest\types\ItemUseQuest$ItemUseQuestBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */