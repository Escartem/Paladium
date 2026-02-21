package fr.paladium.palajobs.core.registry;

import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.palajobs.core.fishing.FishingCategory;
import fr.paladium.palajobs.core.fishing.FishingReward;
import fr.paladium.palajobs.core.fishing.FishingSection;
import fr.paladium.palajobs.core.fishing.FishingSectionType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;

public class FishingRegistry {
  private static Map<Item, Map<FishingCategory, Float>> categoriesByMap = new HashMap<>();
  
  public static Map<String, FishingCategory> categoriesByName = new HashMap<>();
  
  private static final Color COLOR_X1 = Color.decode("#5ED42A");
  
  private static final Color COLOR_X2 = Color.decode("#FF3939");
  
  private static final Color COLOR_X5 = Color.decode("#AF26EF");
  
  private static final Color COLOR_LVLUP = Color.decode("#EF9F26");
  
  public static void register() {
    List<FishingReward> rewards = new ArrayList<>();
    rewards.add(new FishingReward(65.0F, ItemsRegistry.WHALE_FISH));
    rewards.add(new FishingReward(35.0F, ItemsRegistry.KRAKEN_FISH));
    FishingCategory mythic = new FishingCategory("§dMythique", 2000L, "mythic"), category = mythic;
    FishingSection x1 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(2.0F), COLOR_X2, 5.0F);
    category.addSection(x1);
    category.addRewards(rewards);
    registerCategory((Item)Items.field_151112_aM, category, 0.0F);
    registerCategory(ItemsRegistry.PALADIUM_FISHING_ROD, category, 0.0F);
    List<FishingReward> list1 = new ArrayList<>();
    list1.add(new FishingReward(37.0F, ItemsRegistry.EXP_FISH));
    list1.add(new FishingReward(37.0F, ItemsRegistry.RED_TUNA_FISH));
    list1.add(new FishingReward(26.0F, ItemsRegistry.MOON_FISH));
    FishingCategory legendary = new FishingCategory("§cLégendaire", 2000L, "legendary"), fishingCategory1 = legendary;
    FishingSection x2 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(2.0F), COLOR_X2, 4.5F);
    FishingSection fishingSection7 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(1.0F), Color.decode("#5ED42A"), 12.0F);
    fishingCategory1.addSection(fishingSection7);
    fishingCategory1.addSection(x2);
    fishingCategory1.addSection(fishingSection7);
    fishingCategory1.addRewards(list1);
    registerCategory((Item)Items.field_151112_aM, fishingCategory1, 0.0F);
    registerCategory(ItemsRegistry.PALADIUM_FISHING_ROD, fishingCategory1, 2.55F);
    fishingCategory1 = new FishingCategory("§cLégendaire §6Boosté", 2000L, "legendary");
    FishingSection x5 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(3.0F), COLOR_X5, 2.0F);
    FishingSection fishingSection6 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(2.0F), COLOR_X2, 4.5F);
    FishingSection fishingSection11 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(1.0F), COLOR_X1, 16.0F);
    fishingCategory1.addSection(fishingSection11);
    fishingCategory1.addSection(x5);
    fishingCategory1.addSection(fishingSection6);
    fishingCategory1.addSection(fishingSection11);
    fishingCategory1.addRewards(list1);
    registerCategory((Item)Items.field_151112_aM, fishingCategory1, 0.0F);
    registerCategory(ItemsRegistry.PALADIUM_FISHING_ROD, fishingCategory1, 0.3F);
    fishingCategory1 = new FishingCategory("§cLégendaire §aLevel-Up", 2000L, "legendary");
    FishingSection levelup = new FishingSection(FishingSectionType.LEVEl_UP, mythic, COLOR_LVLUP, 1.5F);
    fishingSection6 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(2.0F), COLOR_X2, 4.5F);
    fishingSection11 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(1.0F), COLOR_X1, 16.0F);
    fishingCategory1.addSection(fishingSection11);
    fishingCategory1.addSection(levelup);
    fishingCategory1.addSection(fishingSection6);
    fishingCategory1.addSection(fishingSection11);
    fishingCategory1.addRewards(list1);
    registerCategory((Item)Items.field_151112_aM, fishingCategory1, 0.0F);
    registerCategory(ItemsRegistry.PALADIUM_FISHING_ROD, fishingCategory1, 0.15F);
    registerCategory(ItemsRegistry.ENDIUM_FISHING_ROD, fishingCategory1, 100.0F);
    List<FishingReward> list2 = new ArrayList<>();
    list2.add(new FishingReward(8.5F, new ItemStack(Items.field_151115_aP, 1, ItemFishFood.FishType.CLOWNFISH.ordinal())));
    list2.add(new FishingReward(30.0F, new ItemStack(Items.field_151115_aP, 1, ItemFishFood.FishType.PUFFERFISH.ordinal())));
    list2.add(new FishingReward(30.0F, ItemsRegistry.CARP_FISH));
    list2.add(new FishingReward(10.0F, ItemsRegistry.BASS_FISH));
    list2.add(new FishingReward(5.0F, ItemsRegistry.RAY_FISH));
    list2.add(new FishingReward(1.5F, ItemsRegistry.EXP_FISH));
    list2.add(new FishingReward(1.6F, (Item)Items.field_151031_f));
    list2.add(new FishingReward(1.6F, Items.field_151122_aG));
    list2.add(new FishingReward(1.6F, (Item)Items.field_151112_aM));
    list2.add(new FishingReward(1.6F, Items.field_151057_cb));
    list2.add(new FishingReward(1.6F, Blocks.field_150392_bi));
    list2.add(new FishingReward(1.6F, Items.field_151141_av));
    list2.add(new FishingReward(1.09F, Items.field_151054_z));
    list2.add(new FishingReward(0.11F, new ItemStack((Item)Items.field_151112_aM, 1, Items.field_151112_aM.func_77612_l() - 1)));
    list2.add(new FishingReward(0.54F, Items.field_151116_aA));
    list2.add(new FishingReward(0.54F, (Item)Items.field_151021_T));
    list2.add(new FishingReward(0.54F, Items.field_151078_bh));
    list2.add(new FishingReward(0.27F, Items.field_151055_y));
    list2.add(new FishingReward(0.27F, Items.field_151007_F));
    list2.add(new FishingReward(0.54F, Items.field_151069_bo));
    list2.add(new FishingReward(0.54F, Items.field_151103_aS));
    list2.add(new FishingReward(0.54F, Items.field_151100_aR));
    list2.add(new FishingReward(0.54F, (Block)Blocks.field_150479_bC));
    FishingCategory rare = new FishingCategory("§3Rare", 2500L, "rare"), fishingCategory2 = rare;
    fishingSection6 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(2.0F), COLOR_X2, 5.0F);
    fishingSection11 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(1.0F), Color.decode("#5ED42A"), 15.0F);
    fishingCategory2.addSection(fishingSection11);
    fishingCategory2.addSection(fishingSection6);
    fishingCategory2.addSection(fishingSection11);
    fishingCategory2.addRewards(list2);
    registerCategory((Item)Items.field_151112_aM, fishingCategory2, 5.25F);
    registerCategory(ItemsRegistry.PALADIUM_FISHING_ROD, fishingCategory2, 20.25F);
    fishingCategory2 = new FishingCategory("§3Rare §6Boosté", 2500L, "rare");
    FishingSection fishingSection5 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(5.0F), COLOR_X5, 2.25F);
    FishingSection fishingSection10 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(2.0F), COLOR_X2, 5.0F);
    FishingSection fishingSection12 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(1.0F), COLOR_X1, 18.0F);
    fishingCategory2.addSection(fishingSection12);
    fishingCategory2.addSection(fishingSection5);
    fishingCategory2.addSection(fishingSection10);
    fishingCategory2.addSection(fishingSection12);
    fishingCategory2.addRewards(list2);
    registerCategory((Item)Items.field_151112_aM, fishingCategory2, 1.05F);
    registerCategory(ItemsRegistry.PALADIUM_FISHING_ROD, fishingCategory2, 4.05F);
    fishingCategory2 = new FishingCategory("§3Rare §aLevel-Up", 2500L, "rare");
    FishingSection fishingSection4 = new FishingSection(FishingSectionType.LEVEl_UP, legendary, COLOR_LVLUP, 1.7F);
    fishingSection10 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(2.0F), COLOR_X2, 5.0F);
    fishingSection12 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(1.0F), COLOR_X1, 18.0F);
    fishingCategory2.addSection(fishingSection12);
    fishingCategory2.addSection(fishingSection4);
    fishingCategory2.addSection(fishingSection10);
    fishingCategory2.addSection(fishingSection12);
    fishingCategory2.addRewards(list2);
    registerCategory((Item)Items.field_151112_aM, fishingCategory2, 0.7F);
    registerCategory(ItemsRegistry.PALADIUM_FISHING_ROD, fishingCategory2, 2.7F);
    list2 = new ArrayList<>();
    list2.add(new FishingReward(50.0F, new ItemStack(Items.field_151115_aP, 1, ItemFishFood.FishType.COD.ordinal())));
    list2.add(new FishingReward(20.0F, new ItemStack(Items.field_151115_aP, 1, ItemFishFood.FishType.SALMON.ordinal())));
    list2.add(new FishingReward(1.5F, new ItemStack(Items.field_151115_aP, 1, ItemFishFood.FishType.CLOWNFISH.ordinal())));
    list2.add(new FishingReward(10.0F, new ItemStack(Items.field_151115_aP, 1, ItemFishFood.FishType.PUFFERFISH.ordinal())));
    list2.add(new FishingReward(3.5F, ItemsRegistry.CARP_FISH));
    list2.add(new FishingReward(0.8F, (Item)Items.field_151031_f));
    list2.add(new FishingReward(0.8F, Items.field_151122_aG));
    list2.add(new FishingReward(0.8F, (Item)Items.field_151112_aM));
    list2.add(new FishingReward(0.8F, Items.field_151057_cb));
    list2.add(new FishingReward(0.8F, Blocks.field_150392_bi));
    list2.add(new FishingReward(0.8F, Items.field_151141_av));
    list2.add(new FishingReward(1.09F, Items.field_151054_z));
    list2.add(new FishingReward(0.22F, new ItemStack((Item)Items.field_151112_aM, 1, Items.field_151112_aM.func_77612_l() - 1)));
    list2.add(new FishingReward(1.09F, Items.field_151116_aA));
    list2.add(new FishingReward(1.09F, (Item)Items.field_151021_T));
    list2.add(new FishingReward(1.09F, Items.field_151078_bh));
    list2.add(new FishingReward(0.54F, Items.field_151055_y));
    list2.add(new FishingReward(0.54F, Items.field_151007_F));
    list2.add(new FishingReward(1.09F, Items.field_151069_bo));
    list2.add(new FishingReward(1.09F, Items.field_151103_aS));
    list2.add(new FishingReward(1.09F, Items.field_151100_aR));
    list2.add(new FishingReward(1.09F, (Block)Blocks.field_150479_bC));
    fishingCategory2 = new FishingCategory("§aCommun", 3000L, "common");
    FishingSection fishingSection3 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(2.0F), COLOR_X2, 5.5F);
    FishingSection fishingSection9 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(1.0F), Color.decode("#5ED42A"), 15.0F);
    fishingCategory2.addSection(fishingSection9);
    fishingCategory2.addSection(fishingSection3);
    fishingCategory2.addSection(fishingSection9);
    fishingCategory2.addRewards(list2);
    registerCategory((Item)Items.field_151112_aM, fishingCategory2, 60.45F);
    registerCategory(ItemsRegistry.PALADIUM_FISHING_ROD, fishingCategory2, 45.5F);
    fishingCategory2 = new FishingCategory("§aCommun §6Boosté", 3000L, "common");
    FishingSection fishingSection2 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(5.0F), COLOR_X5, 2.5F);
    FishingSection fishingSection8 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(2.0F), COLOR_X2, 5.5F);
    fishingSection12 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(1.0F), COLOR_X1, 20.0F);
    fishingCategory2.addSection(fishingSection12);
    fishingCategory2.addSection(fishingSection2);
    fishingCategory2.addSection(fishingSection8);
    fishingCategory2.addSection(fishingSection12);
    fishingCategory2.addRewards(list2);
    registerCategory((Item)Items.field_151112_aM, fishingCategory2, 18.6F);
    registerCategory(ItemsRegistry.PALADIUM_FISHING_ROD, fishingCategory2, 14.0F);
    fishingCategory2 = new FishingCategory("§aCommun §aLevel-Up", 3000L, "common");
    FishingSection fishingSection1 = new FishingSection(FishingSectionType.LEVEl_UP, rare, COLOR_LVLUP, 1.9F);
    fishingSection8 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(2.0F), COLOR_X2, 5.5F);
    fishingSection12 = new FishingSection(FishingSectionType.MULTIPLIER, Float.valueOf(1.0F), COLOR_X1, 20.0F);
    fishingCategory2.addSection(fishingSection12);
    fishingCategory2.addSection(fishingSection1);
    fishingCategory2.addSection(fishingSection8);
    fishingCategory2.addSection(fishingSection12);
    fishingCategory2.addRewards(list2);
    registerCategory((Item)Items.field_151112_aM, fishingCategory2, 13.95F);
    registerCategory(ItemsRegistry.PALADIUM_FISHING_ROD, fishingCategory2, 10.5F);
  }
  
  private static void registerCategory(Item item, FishingCategory category, float percentage) {
    Map<FishingCategory, Float> map = categoriesByMap.getOrDefault(item, new HashMap<>());
    map.put(category, Float.valueOf(percentage));
    categoriesByMap.put(item, map);
    categoriesByName.put(category.getName(), category);
  }
  
  public static FishingCategory getRandomCategory(Item item, Random random) {
    if (!categoriesByMap.containsKey(item))
      return null; 
    float randomValue = random.nextFloat() * 100.0F;
    float cumulativePercentage = 0.0F;
    for (Map.Entry<FishingCategory, Float> category : (Iterable<Map.Entry<FishingCategory, Float>>)((Map)categoriesByMap.get(item)).entrySet()) {
      cumulativePercentage += ((Float)category.getValue()).floatValue();
      if (randomValue <= cumulativePercentage)
        return category.getKey(); 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\registry\FishingRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */