package fr.paladium.palajobs.core.quest.types;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.QuestType;
import net.minecraft.item.ItemStack;

public class BlockBreakQuestBuilder {
  private String id;
  
  private JobType job;
  
  private String name;
  
  private String description;
  
  private String icon;
  
  private QuestType type;
  
  private int quantity;
  
  private double earnedExperience;
  
  private ItemStack stack;
  
  public BlockBreakQuestBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public BlockBreakQuestBuilder job(JobType job) {
    this.job = job;
    return this;
  }
  
  public BlockBreakQuestBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public BlockBreakQuestBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public BlockBreakQuestBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public BlockBreakQuestBuilder type(QuestType type) {
    this.type = type;
    return this;
  }
  
  public BlockBreakQuestBuilder quantity(int quantity) {
    this.quantity = quantity;
    return this;
  }
  
  public BlockBreakQuestBuilder earnedExperience(double earnedExperience) {
    this.earnedExperience = earnedExperience;
    return this;
  }
  
  public BlockBreakQuestBuilder stack(ItemStack stack) {
    this.stack = stack;
    return this;
  }
  
  public BlockBreakQuest build() {
    return new BlockBreakQuest(this.id, this.job, this.name, this.description, this.icon, this.type, this.quantity, this.earnedExperience, this.stack);
  }
  
  public String toString() {
    return "BlockBreakQuest.BlockBreakQuestBuilder(id=" + this.id + ", job=" + this.job + ", name=" + this.name + ", description=" + this.description + ", icon=" + this.icon + ", type=" + this.type + ", quantity=" + this.quantity + ", earnedExperience=" + this.earnedExperience + ", stack=" + this.stack + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\quest\types\BlockBreakQuest$BlockBreakQuestBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */