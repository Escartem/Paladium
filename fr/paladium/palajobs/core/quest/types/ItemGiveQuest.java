package fr.paladium.palajobs.core.quest.types;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palajobs.api.quest.IQuestPlayerData;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.QuestType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.quest.AbstractQuest;
import fr.paladium.palajobs.core.quest.QuestPlayerData;
import fr.paladium.palajobs.core.utils.InventoryUtils;
import fr.paladium.palajobs.server.managers.JobsManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class ItemGiveQuest extends AbstractQuest {
  private ItemStack stack;
  
  public ItemStack getStack() {
    return this.stack;
  }
  
  public static ItemGiveQuestBuilder builder() {
    return new ItemGiveQuestBuilder();
  }
  
  public static class ItemGiveQuestBuilder {
    private String id;
    
    private JobType job;
    
    private String name;
    
    private String description;
    
    private String icon;
    
    private QuestType type;
    
    private int quantity;
    
    private double earnedExperience;
    
    private ItemStack stack;
    
    public ItemGiveQuestBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public ItemGiveQuestBuilder job(JobType job) {
      this.job = job;
      return this;
    }
    
    public ItemGiveQuestBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public ItemGiveQuestBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public ItemGiveQuestBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public ItemGiveQuestBuilder type(QuestType type) {
      this.type = type;
      return this;
    }
    
    public ItemGiveQuestBuilder quantity(int quantity) {
      this.quantity = quantity;
      return this;
    }
    
    public ItemGiveQuestBuilder earnedExperience(double earnedExperience) {
      this.earnedExperience = earnedExperience;
      return this;
    }
    
    public ItemGiveQuestBuilder stack(ItemStack stack) {
      this.stack = stack;
      return this;
    }
    
    public ItemGiveQuest build() {
      return new ItemGiveQuest(this.id, this.job, this.name, this.description, this.icon, this.type, this.quantity, this.earnedExperience, this.stack);
    }
    
    public String toString() {
      return "ItemGiveQuest.ItemGiveQuestBuilder(id=" + this.id + ", job=" + this.job + ", name=" + this.name + ", description=" + this.description + ", icon=" + this.icon + ", type=" + this.type + ", quantity=" + this.quantity + ", earnedExperience=" + this.earnedExperience + ", stack=" + this.stack + ")";
    }
  }
  
  public ItemGiveQuest(String id, JobType job, String name, String description, String icon, QuestType type, int quantity, double earnedExperience, ItemStack stack) {
    super(id, job, name, description, icon, type, quantity, earnedExperience);
    this.stack = stack;
  }
  
  public static boolean performCheck(EntityPlayer player, String questID) {
    JobsPlayer eep = JobsPlayer.get((Entity)player);
    for (AbstractQuest quest : JobsManager.getInstance().getQuests(QuestType.ITEM_GIVE)) {
      if (!(quest instanceof ItemGiveQuest))
        continue; 
      ItemGiveQuest currentQuest = (ItemGiveQuest)quest;
      int countToGive = 0;
      if (currentQuest.getId().equalsIgnoreCase(questID)) {
        QuestPlayerData idx = new QuestPlayerData(quest.getId());
        if (eep.getDailyQuests().contains(idx)) {
          IQuestPlayerData questPlayerData = eep.getDailyQuests().get(eep.getDailyQuests().indexOf(idx));
          if (questPlayerData.isCompleted())
            return false; 
          countToGive = currentQuest.getQuantity() - questPlayerData.getProgress();
        } 
        ItemStack stack = currentQuest.stack;
        int count = InventoryUtils.getItemCount(player, stack);
        if (countToGive < count)
          count = countToGive; 
        if (count <= 0) {
          (new Notification(Notification.NotificationType.ERROR, "Vous n'avez aucun " + stack.func_82833_r(), "jobs")).send((EntityPlayerMP)player);
          return false;
        } 
        int countSave = count;
        for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
          ItemStack it = player.field_71071_by.field_70462_a[i];
          if (it != null && count > 0 && 
            it.func_77969_a(stack) && ItemStack.func_77970_a(it, stack) && it.func_77960_j() == stack.func_77960_j()) {
            int v = Math.min(Math.min(64, count), it.field_77994_a);
            it.field_77994_a -= v;
            if (it.field_77994_a <= 0) {
              player.field_71071_by.func_70299_a(i, null);
              player.field_71071_by.field_70459_e = true;
            } else {
              player.field_71071_by.func_70299_a(i, it.func_77946_l());
              player.field_71071_by.field_70459_e = true;
            } 
            count -= v;
          } 
        } 
        incrementStats(currentQuest, player, countSave);
        return true;
      } 
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\quest\types\ItemGiveQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */