package fr.paladium.palajobs.core.quest;

import fr.paladium.palajobs.PalaJobsLogger;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.QuestType;
import fr.paladium.palajobs.core.quest.types.BlockBreakQuest;
import fr.paladium.palajobs.core.quest.types.EntityKillQuest;
import fr.paladium.palajobs.core.utils.ItemStackSerializer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class QuestRegistry {
  public static QuestRegistry instance;
  
  private List<AbstractQuest> quests = new ArrayList<>();
  
  public List<AbstractQuest> getQuests() {
    return this.quests;
  }
  
  public void registerQuests() {
    EntityKillQuest.builder()
      .id("entitykill.creeper")
      .job(JobType.HUNTER)
      .type(QuestType.ENTITY_KILL)
      .name("Attention à pas faire boom")
      .description("Tuer 5 creepers")
      .icon(Base64.getEncoder().encodeToString(ItemStackSerializer.write(new ItemStack(Items.field_151048_u)).getBytes()).toString())
      .quantity(5)
      .earnedExperience(1000.0D)
      .entityType(EntityCreeper.class)
      .build().register();
    BlockBreakQuest.builder()
      .id("blockbreak.cactus")
      .job(JobType.FARMER)
      .type(QuestType.BLOCK_BREAK)
      .name("Le monde entier est un cactus")
      .description("Casser 64 cactus")
      .icon(Base64.getEncoder().encodeToString(ItemStackSerializer.write(new ItemStack(Blocks.field_150434_aF)).getBytes()).toString())
      .quantity(64)
      .earnedExperience(750.0D)
      .stack(new ItemStack(Blocks.field_150434_aF))
      .build().register();
    BlockBreakQuest.builder()
      .id("blockbreak.melon")
      .job(JobType.FARMER)
      .type(QuestType.BLOCK_BREAK)
      .name("Steak ? Pastèque !")
      .description("Casser 32 pastèques")
      .icon(Base64.getEncoder().encodeToString(ItemStackSerializer.write(new ItemStack(Blocks.field_150440_ba)).getBytes()).toString())
      .quantity(32)
      .earnedExperience(500.0D)
      .stack(new ItemStack(Blocks.field_150440_ba))
      .build().register();
  }
  
  public Optional<AbstractQuest> getQuestById(String id) {
    return this.quests.stream().filter(quest -> quest.getId().equals(id)).findFirst();
  }
  
  public static void register(AbstractQuest quest) {
    if (instance.quests.contains(quest)) {
      PalaJobsLogger.error("The Quest with id " + quest.getId() + " is already registered.");
      return;
    } 
    instance.quests.add(quest);
  }
  
  public static QuestRegistry getInstance() {
    if (instance == null)
      instance = new QuestRegistry(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\quest\QuestRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */