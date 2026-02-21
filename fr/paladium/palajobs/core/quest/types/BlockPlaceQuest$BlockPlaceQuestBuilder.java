package fr.paladium.palajobs.core.quest.types;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.QuestType;
import net.minecraft.item.ItemStack;

public class BlockPlaceQuestBuilder {
  private String id;
  
  private JobType job;
  
  private String name;
  
  private String description;
  
  private String icon;
  
  private QuestType type;
  
  private int quantity;
  
  private double earnedExperience;
  
  private ItemStack stack;
  
  public BlockPlaceQuestBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public BlockPlaceQuestBuilder job(JobType job) {
    this.job = job;
    return this;
  }
  
  public BlockPlaceQuestBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public BlockPlaceQuestBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public BlockPlaceQuestBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public BlockPlaceQuestBuilder type(QuestType type) {
    this.type = type;
    return this;
  }
  
  public BlockPlaceQuestBuilder quantity(int quantity) {
    this.quantity = quantity;
    return this;
  }
  
  public BlockPlaceQuestBuilder earnedExperience(double earnedExperience) {
    this.earnedExperience = earnedExperience;
    return this;
  }
  
  public BlockPlaceQuestBuilder stack(ItemStack stack) {
    this.stack = stack;
    return this;
  }
  
  public BlockPlaceQuest build() {
    return new BlockPlaceQuest(this.id, this.job, this.name, this.description, this.icon, this.type, this.quantity, this.earnedExperience, this.stack);
  }
  
  public String toString() {
    return "BlockPlaceQuest.BlockPlaceQuestBuilder(id=" + this.id + ", job=" + this.job + ", name=" + this.name + ", description=" + this.description + ", icon=" + this.icon + ", type=" + this.type + ", quantity=" + this.quantity + ", earnedExperience=" + this.earnedExperience + ", stack=" + this.stack + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\quest\types\BlockPlaceQuest$BlockPlaceQuestBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */