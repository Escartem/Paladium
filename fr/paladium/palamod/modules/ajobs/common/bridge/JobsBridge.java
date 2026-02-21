package fr.paladium.palamod.modules.ajobs.common.bridge;

import fr.paladium.palajobs.api.blacklist.IBlackListedItem;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.item.ItemCustomFishingRod;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.pojo.objectives.types.CraftObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.FishingObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.SmeltObjective;
import fr.paladium.palajobs.core.quest.types.ActionQuest;
import fr.paladium.palajobs.core.quest.types.EntityFeedQuest;
import fr.paladium.palajobs.core.quest.types.FishingQuest;
import fr.paladium.palajobs.core.quest.types.FurnaceCraftQuest;
import fr.paladium.palajobs.core.quest.types.ItemCraftQuest;
import fr.paladium.palajobs.core.registry.BlocksRegistry;
import fr.paladium.palajobs.core.registry.ItemsRegistry;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.palajobs.utils.forge.player.PlayerUtils;
import fr.paladium.palamod.modules.ajobs.common.registerer.requirement.CraftRequirement;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class JobsBridge {
  public static void performEntityFeedQuest(EntityPlayer player, Class<? extends Entity> entity) {
    EntityFeedQuest.performCheck(player, entity);
  }
  
  public static void performFishingQuest(EntityPlayer player, ItemStack stack) {
    FishingQuest.performCheck(player, stack);
  }
  
  public static void performFishingObjective(EntityPlayer player, ItemStack stack) {
    FishingObjective.performCheck(player, stack);
  }
  
  public static boolean canUseCraft(EntityPlayer player, ItemStack stack, boolean message) {
    JobsPlayer data = JobsPlayer.get((Entity)player);
    if (data == null)
      return false; 
    IBlackListedItem canUse = JobsManager.getInstance().canUseCraft(player, data, stack);
    if (canUse != null) {
      if (message)
        PlayerUtils.sendMessage(player, new String[] { "§7[§aJobs§7]§r §cVous n'avez pas le niveau requis pour craft ceci." }); 
      return false;
    } 
    return true;
  }
  
  public static int getLevel(EntityPlayer player, String job) {
    JobsPlayer data = JobsPlayer.get((Entity)player);
    JobType jobType = JobType.valueOf(job);
    if (jobType == null || data == null)
      return 1; 
    return data.getLevel(jobType);
  }
  
  public static long getLastTimestamp(EntityPlayer player, String job) {
    JobType jobType = JobType.valueOf(job);
    if (jobType == null)
      return 1L; 
    JobsPlayer data = JobsPlayer.get((Entity)player);
    if (data == null || data.getJobsLastXP() == null || data.getJobsLastXP().isEmpty() || !data.getJobsLastXP().containsKey(Integer.valueOf(jobType.ordinal())) || player == null || jobType == null)
      return 1L; 
    Long value = (Long)data.getJobsLastXP().get(Integer.valueOf(jobType.ordinal()));
    return (value == null) ? 1L : value.longValue();
  }
  
  public static boolean canUse(EntityPlayer player, ItemStack stack) {
    JobsPlayer data = JobsPlayer.get((Entity)player);
    if (data == null)
      return false; 
    IBlackListedItem canUse = JobsManager.getInstance().canUse(player, data, stack, false);
    if (canUse != null)
      return false; 
    return true;
  }
  
  public static boolean canUseCraft(EntityPlayer player, ItemStack stack) {
    JobsPlayer data = JobsPlayer.get((Entity)player);
    if (data == null)
      return false; 
    IBlackListedItem canUse = JobsManager.getInstance().canUseCraft(player, data, stack);
    if (canUse != null) {
      PlayerUtils.sendMessage(player, new String[] { "§7[§aJobs§7]§r §cVous n'avez pas le niveau requis pour craft ceci." });
      return false;
    } 
    return true;
  }
  
  public static void performItemCraftRequirement(EntityPlayer player, ItemStack stack) {
    JobsPlayer data = JobsPlayer.get((Entity)player);
    if (data == null)
      return; 
    data.getRequirements(CraftRequirement.class).forEach(optional -> optional.ifPresent(()));
  }
  
  public static void performItemCraftObjective(EntityPlayer player, ItemStack stack) {
    CraftObjective.performCheck(player, stack);
  }
  
  public static void performItemCraftQuest(EntityPlayer player, ItemStack stack) {
    ItemCraftQuest.performCheck(player, stack);
  }
  
  public static void performSmeltObjective(EntityPlayer player, ItemStack stack) {
    SmeltObjective.performCheck(player, stack);
  }
  
  public static void performSmeltQuest(EntityPlayer player, ItemStack stack) {
    FurnaceCraftQuest.performCheck(player, stack, (stack.field_77994_a > 0) ? stack.field_77994_a : 1);
  }
  
  public static void performActionQuest(EntityPlayer p_77654_3_, String string, int i) {
    ActionQuest.performCheck(p_77654_3_, string, 1);
  }
  
  public static Block getColoredGrass() {
    return (Block)BlocksRegistry.COLORED_GRASS;
  }
  
  public static IIcon getPaladiumFishingRodCastIcon() {
    return ((ItemCustomFishingRod)ItemsRegistry.PALADIUM_FISHING_ROD).castIcon;
  }
  
  public static Item getPaladiumFishingRod() {
    return ItemsRegistry.PALADIUM_FISHING_ROD;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\bridge\JobsBridge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */