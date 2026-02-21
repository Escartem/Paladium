package fr.paladium.palamod.modules.luckyblock.luckystats.common.manager;

import fr.paladium.palajobs.core.registry.ItemsRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.reward.LuckyStatsReward;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.reward.LuckyStatsRewardLevel;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class LuckyStatsRewardManager {
  private static Map<LuckyType, List<LuckyStatsRewardLevel>> rewards = new HashMap<>();
  
  public static void register() {
    addReward(LuckyType.PALADIUM, 0, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.TROPHY), new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 10), new LuckyStatsReward(ItemsRegister.LEGENDARYSTONE_RANDOM), new LuckyStatsReward(ItemsRegister.SPELL_SCROLL) });
    addReward(LuckyType.PALADIUM, 1, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.TROPHY, 1, 1), new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 20), new LuckyStatsReward(ItemsRegister.BIONIC_SNOWBALL), new LuckyStatsReward(BlocksRegister.SUPER_BEACON) });
    addReward(LuckyType.PALADIUM, 2, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.TROPHY, 1, 2), new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 30), new LuckyStatsReward(ItemsRegister.TALISMAN_CALM), new LuckyStatsReward(BlocksRegister.ATM) });
    addReward(LuckyType.PALADIUM, 3, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.TROPHY, 1, 3), new LuckyStatsReward(ItemsRegister.SUPERMAN_CAPE), new LuckyStatsReward(ItemsRegister.LUCKY_SWORD) });
    addReward(LuckyType.FINDIUM, 0, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.TROPHY_FINDIUM), new LuckyStatsReward((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK, 10), new LuckyStatsReward(ItemsRegister.VISION_HELMET) });
    addReward(LuckyType.FINDIUM, 1, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.TROPHY_FINDIUM, 1, 1), new LuckyStatsReward((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK, 20), new LuckyStatsReward(ItemsRegister.FOREUSE) });
    addReward(LuckyType.FINDIUM, 2, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.TROPHY_FINDIUM, 1, 2), new LuckyStatsReward((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK, 30), new LuckyStatsReward(ItemsRegister.POWER_HELMET) });
    addReward(LuckyType.FINDIUM, 3, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.TROPHY_FINDIUM, 1, 3), new LuckyStatsReward(ItemsRegister.FAUX) });
    addReward(LuckyType.HALLOWEEN, 0, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 2), new LuckyStatsReward(ItemsRegister.HALLOWEEN_BOOTS) });
    addReward(LuckyType.HALLOWEEN, 1, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 4), new LuckyStatsReward(BlocksRegister.COLORFUL_LANTERN), new LuckyStatsReward(ItemsRegister.HALLOWEEN_HELMET) });
    addReward(LuckyType.HALLOWEEN, 2, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 4), new LuckyStatsReward((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK), new LuckyStatsReward(ItemsRegister.LEGENDARYSTONE_RANDOM), new LuckyStatsReward(ItemsRegister.HALLOWEEN_LEGGINGS) });
    addReward(LuckyType.HALLOWEEN, 3, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.TROPHYHALLOWEEN, 1), new LuckyStatsReward((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, 4), new LuckyStatsReward(ItemsRegister.SURVIVINGSTONE), new LuckyStatsReward(ItemsRegister.HALLOWEEN_CHESTPLATE) });
    addReward(LuckyType.CHRISTMAS, 0, new LuckyStatsReward[] { new LuckyStatsReward(ItemsRegister.CHRISTMAS_MULTICOLOR_GIFT, 1), new LuckyStatsReward(ItemsRegister.PRESENT), new LuckyStatsReward(ItemsRegister.CHRISTMAS_BOOTS) });
    addReward(LuckyType.CHRISTMAS, 1, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.GIFT_CHEST, 1), new LuckyStatsReward(ItemsRegister.PRESENT, 2), new LuckyStatsReward(ItemsRegister.CHRISTMAS_HELMET) });
    addReward(LuckyType.CHRISTMAS, 2, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.FIRE_PLACE, 1), new LuckyStatsReward(ItemsRegister.PRESENT, 3), new LuckyStatsReward(ItemsRegister.CHRISTMAS_LEGGINGS) });
    addReward(LuckyType.CHRISTMAS, 3, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.CHRISTMAS_WREATH, 1), new LuckyStatsReward(BlocksRegister.TROPHY_CHRISTMAS), new LuckyStatsReward(ItemsRegister.CHRISTMAS_CHESTPLATE), new LuckyStatsReward(ItemsRegister.CHRISTMAS_BOOK) });
    addReward(LuckyType.BLACK, 0, new LuckyStatsReward[] { new LuckyStatsReward(
            Item.func_150898_a(BlocksRegister.BLACK_TROPHY), 1), new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 5), new LuckyStatsReward(BlocksRegister.BLOCK_PALADIUM, 16) });
    addReward(LuckyType.BLACK, 1, new LuckyStatsReward[] { new LuckyStatsReward(
            Item.func_150898_a(BlocksRegister.BLACK_TROPHY), 1, 1), new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 5), new LuckyStatsReward(ItemsRegister.COUAC_SWORD) });
    addReward(LuckyType.BLACK, 2, new LuckyStatsReward[] { new LuckyStatsReward(
            Item.func_150898_a(BlocksRegister.BLACK_TROPHY), 1, 2), new LuckyStatsReward((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK, 10), new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 5), new LuckyStatsReward((Block)BlocksRegister.MAY_LUCKY_BLOCK, 3) });
    addReward(LuckyType.BLACK, 3, new LuckyStatsReward[] { new LuckyStatsReward(
            Item.func_150898_a(BlocksRegister.BLACK_TROPHY), 1, 3), new LuckyStatsReward((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, 5), new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 10), new LuckyStatsReward(ItemsRegister.LEGENDARYSTONE_RANDOM) });
    addReward(LuckyType.EASTER, 0, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 5), new LuckyStatsReward(BlocksRegister.MAGIC_BELL, 1), new LuckyStatsReward(ItemsRegister.RESURRECTION_STONE, 1) });
    addReward(LuckyType.EASTER, 1, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 5), new LuckyStatsReward(BlocksRegister.YELLOW_EASTER_EGG, 16), new LuckyStatsReward(ItemsRegister.EASTER_TOTEM, 1), new LuckyStatsReward(ItemsRegister.GIANT_FISH, 1), new LuckyStatsReward(ItemsRegister.GIANT_FISH, 1), new LuckyStatsReward(ItemsRegister.GIANT_FISH, 1) });
    addReward(LuckyType.EASTER, 2, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK, 5), new LuckyStatsReward((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, 1), new LuckyStatsReward(ItemsRegister.RESURRECTION_STONE, 1), new LuckyStatsReward(ItemsRegister.RESURRECTION_STONE, 1), new LuckyStatsReward(ItemsRegister.RESURRECTION_STONE, 1), new LuckyStatsReward(ItemsRegister.EASTER_EGG, 1), new LuckyStatsReward(ItemsRegister.CURSED_CHOCOLATE_EGG, 1) });
    String keyUniqueId = "63873e745b13ee37d92f79db";
    addReward(LuckyType.EASTER, 3, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.EASTER_TROPHY), new LuckyStatsReward((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, 3), new LuckyStatsReward(ItemsRegister.LEGENDARYSTONE_RANDOM), new LuckyStatsReward(BlocksRegister.EGG_OF_PLENTY, 1), new LuckyStatsReward(ItemsRegister.EASTER_TICKET, 1), new LuckyStatsReward("63873e745b13ee37d92f79db", 5) });
    addReward(LuckyType.MAY, 0, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 5), new LuckyStatsReward(BlocksRegister.TRASH, 1), new LuckyStatsReward(BlocksRegister.GOLDEN_CAGE, 1) });
    addReward(LuckyType.MAY, 1, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 5), new LuckyStatsReward(
            
            Item.func_150898_a(BlocksRegister.LILY_OF_THE_VALLEY), 2), new LuckyStatsReward(ItemsRegister.PAPER_AIRPLANE), new LuckyStatsReward(ItemsRegister.MONSTROUS_SWORD) });
    addReward(LuckyType.MAY, 2, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK, 5), new LuckyStatsReward(ItemsRegistry.ENDIUM_FISHING_ROD), new LuckyStatsReward(ItemsRegister.JOB_QUEST_POTION), new LuckyStatsReward(ItemsRegister.JOB_QUEST_POTION), new LuckyStatsReward(ItemsRegister.JOB_QUEST_POTION), new LuckyStatsReward(ItemsRegister.JOB_QUEST_POTION), new LuckyStatsReward(ItemsRegister.JOB_QUEST_POTION), new LuckyStatsReward(ItemsRegister.VISION_HELMET) });
    addReward(LuckyType.MAY, 3, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.MAY_TROPHY), new LuckyStatsReward(ItemsRegister.MAY_TICKET_BLACKSMITH), new LuckyStatsReward((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, 3), new LuckyStatsReward(ItemsRegister.LEGENDARYSTONE_RANDOM), new LuckyStatsReward(BlocksRegister.FEVE_ARTY, 1) });
    addReward(LuckyType.JUNE, 0, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 5) });
    addReward(LuckyType.JUNE, 1, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 5), new LuckyStatsReward(ItemsRegister.PARALYZING_RADIO) });
    addReward(LuckyType.JUNE, 2, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK, 5), new LuckyStatsReward(ItemsRegister.SOUND_DETECTOR), new LuckyStatsReward(ItemsRegister.FOGHORN) });
    addReward(LuckyType.JUNE, 3, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.JUNE_TROPHY), new LuckyStatsReward(ItemsRegister.JUNE_TICKET), new LuckyStatsReward((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, 3), new LuckyStatsReward(ItemsRegister.LEGENDARYSTONE_RANDOM) });
  }
  
  public static void addReward(LuckyType type, int index, LuckyStatsReward... values) {
    LuckyStatsRewardLevel level = new LuckyStatsRewardLevel(index);
    for (LuckyStatsReward reward : values)
      level.add(reward); 
    List<LuckyStatsRewardLevel> levels = rewards.getOrDefault(type, new ArrayList<>());
    levels.add(level);
    rewards.put(type, levels);
  }
  
  public static Map<LuckyType, List<LuckyStatsRewardLevel>> getRewards() {
    return rewards;
  }
  
  public static LuckyStatsRewardLevel getRewards(LuckyType type, int level) {
    if (!rewards.containsKey(type))
      return null; 
    for (LuckyStatsRewardLevel reward : rewards.get(type)) {
      if (reward.getLevel() != level)
        continue; 
      return reward;
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckystats\common\manager\LuckyStatsRewardManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */