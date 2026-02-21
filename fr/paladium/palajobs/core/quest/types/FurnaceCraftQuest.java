package fr.paladium.palajobs.core.quest.types;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.QuestType;
import fr.paladium.palajobs.core.quest.AbstractQuest;
import fr.paladium.palajobs.server.managers.JobsManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class FurnaceCraftQuest extends AbstractQuest {
  private ItemStack furnaceResult;
  
  public ItemStack getFurnaceResult() {
    return this.furnaceResult;
  }
  
  public static FurnaceCraftQuestBuilder builder() {
    return new FurnaceCraftQuestBuilder();
  }
  
  public static class FurnaceCraftQuestBuilder {
    private String id;
    
    private JobType job;
    
    private String name;
    
    private String description;
    
    private String icon;
    
    private QuestType type;
    
    private int quantity;
    
    private double earnedExperience;
    
    private ItemStack furnaceResult;
    
    public FurnaceCraftQuestBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public FurnaceCraftQuestBuilder job(JobType job) {
      this.job = job;
      return this;
    }
    
    public FurnaceCraftQuestBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public FurnaceCraftQuestBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public FurnaceCraftQuestBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public FurnaceCraftQuestBuilder type(QuestType type) {
      this.type = type;
      return this;
    }
    
    public FurnaceCraftQuestBuilder quantity(int quantity) {
      this.quantity = quantity;
      return this;
    }
    
    public FurnaceCraftQuestBuilder earnedExperience(double earnedExperience) {
      this.earnedExperience = earnedExperience;
      return this;
    }
    
    public FurnaceCraftQuestBuilder furnaceResult(ItemStack furnaceResult) {
      this.furnaceResult = furnaceResult;
      return this;
    }
    
    public FurnaceCraftQuest build() {
      return new FurnaceCraftQuest(this.id, this.job, this.name, this.description, this.icon, this.type, this.quantity, this.earnedExperience, this.furnaceResult);
    }
    
    public String toString() {
      return "FurnaceCraftQuest.FurnaceCraftQuestBuilder(id=" + this.id + ", job=" + this.job + ", name=" + this.name + ", description=" + this.description + ", icon=" + this.icon + ", type=" + this.type + ", quantity=" + this.quantity + ", earnedExperience=" + this.earnedExperience + ", furnaceResult=" + this.furnaceResult + ")";
    }
  }
  
  public FurnaceCraftQuest(String id, JobType job, String name, String description, String icon, QuestType type, int quantity, double earnedExperience, ItemStack furnaceResult) {
    super(id, job, name, description, icon, type, quantity, earnedExperience);
    this.furnaceResult = furnaceResult;
  }
  
  public static void performCheck(EntityPlayer player, ItemStack smelting, int stackSize) {
    for (AbstractQuest quest : JobsManager.getInstance().getQuests(QuestType.FURNACE_CRAFT)) {
      if (quest instanceof FurnaceCraftQuest) {
        FurnaceCraftQuest furnaceCraftQuest = (FurnaceCraftQuest)quest;
        if (furnaceCraftQuest.furnaceResult.func_77969_a(smelting))
          incrementStats(furnaceCraftQuest, player, (smelting.field_77994_a > 0) ? smelting.field_77994_a : 1); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\quest\types\FurnaceCraftQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */