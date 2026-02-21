package fr.paladium.palamod.modules.ajobs.common.registerer.jobs.type;

import fr.paladium.palajobs.api.reward.IReward;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.RewardType;
import fr.paladium.palajobs.core.jobs.AbstractJob;
import fr.paladium.palajobs.core.jobs.Requirement;
import fr.paladium.palajobs.core.jobs.requirement.BreakBlockRequirement;
import fr.paladium.palajobs.core.jobs.type.FarmerJob;
import fr.paladium.palajobs.core.pojo.objectives.AbstractObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.BreakBlockObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.CraftObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.CrusherObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.EntityKillSpecialObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.SmeltObjective;
import fr.paladium.palajobs.core.pojo.rewards.Reward;
import fr.paladium.palajobs.core.registry.BlocksRegistry;
import fr.paladium.palajobs.core.registry.ItemsRegistry;
import fr.paladium.palajobs.core.registry.JobRegistry;
import fr.paladium.palajobs.core.tokens.LvlTokenRegistry;
import fr.paladium.palajobs.core.tokens.rewards.EnumLvlTokenRewardCategory;
import fr.paladium.palajobs.core.tokens.rewards.EnumLvlTokenRewardRarity;
import fr.paladium.palajobs.core.tokens.rewards.LvlTokenReward;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.ajobs.common.registerer.BlocksRegistry;
import fr.paladium.palamod.modules.ajobs.common.registerer.jobs.JobRegisterer;
import fr.paladium.palamod.modules.hunter.entites.EntityFarmerChicken;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FarmerRegisterer extends JobRegisterer {
  public FarmerRegisterer(FarmerJob job) {
    super((AbstractJob)job);
  }
  
  public void init() {
    JobRegistry.getInstance().setFarmerJob((FarmerJob)this.job);
  }
  
  public void registerObjectives() {
    BreakBlockObjective breakBlockObjective = new BreakBlockObjective(this.job.getType());
    breakBlockObjective.add(getJob().buildValue(1.5D, 0), new ItemStack(Items.field_151014_N, 1, 7));
    breakBlockObjective.add(getJob().buildValue(2.0D, 2), new ItemStack(Blocks.field_150469_bN, 1, 0));
    breakBlockObjective.add(getJob().buildValue(2.5D, 2), new ItemStack(Blocks.field_150459_bM, 1, 0));
    breakBlockObjective.add(getJob().buildValue(4.0D, 3), new ItemStack(Blocks.field_150440_ba));
    breakBlockObjective.add(getJob().buildValue(5.0D, 6), new ItemStack(Blocks.field_150423_aK));
    breakBlockObjective.add(getJob().buildValue(10.0D, 8), new ItemStack((Block)BlocksRegister.CROPS_EGGPLANT, 1, 0));
    breakBlockObjective.add(getJob().buildValue(20.0D, 12), new ItemStack((Block)BlocksRegister.CROPS_CHERVIL, 1, 0));
    breakBlockObjective.add(getJob().buildValue(50.0D, 16), new ItemStack((Block)BlocksRegister.CROPS_KIWANO, 1, 0));
    CraftObjective craftObjective = new CraftObjective(this.job.getType());
    craftObjective.add(getJob().buildValue(1.0D, 0), new ItemStack(Items.field_151025_P));
    craftObjective.add(getJob().buildValue(4.0D, 11), new ItemStack(Items.field_151158_bO));
    SmeltObjective smeltObjective = new SmeltObjective(this.job.getType());
    smeltObjective.add(getJob().buildValue(1.0D, 2), new ItemStack(Items.field_151168_bH));
    CrusherObjective crusherObjective = new CrusherObjective(this.job.getType());
    crusherObjective.add(getJob().buildValue(3.0D, 8), new ItemStack(ItemsRegister.AMETHYST_INGOT));
    crusherObjective.add(getJob().buildValue(4.5D, 12), new ItemStack(ItemsRegister.TITANE_INGOT));
    crusherObjective.add(getJob().buildValue(6.0D, 16), new ItemStack(ItemsRegister.PALADIUM_INGOT));
    EntityKillSpecialObjective entityKillSpecialObjective = new EntityKillSpecialObjective(this.job.getType());
    entityKillSpecialObjective.add(getJob().buildValue(0.0D, 7), EntityFarmerChicken.class);
    this.job.addObjectives(new AbstractObjective[] { (AbstractObjective)breakBlockObjective, (AbstractObjective)craftObjective, (AbstractObjective)smeltObjective, (AbstractObjective)crusherObjective, (AbstractObjective)entityKillSpecialObjective });
  }
  
  public void registerRewards() {
    this.job.addReward(Integer.valueOf(2), new IReward[] { (IReward)Reward.builder().level(2).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 10)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(2), new IReward[] { (IReward)Reward.builder().level(2).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 10)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(2), new IReward[] { (IReward)Reward.builder().level(2).moneyAmount(Double.valueOf(1000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(3), new IReward[] { (IReward)Reward.builder().level(3).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 15)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(3), new IReward[] { (IReward)Reward.builder().level(3).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 15)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(3), new IReward[] { (IReward)Reward.builder().level(3).moneyAmount(Double.valueOf(1500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(3), new IReward[] { (IReward)Reward.builder().level(3).rewardItem(new ItemStack(ItemsRegister.FINDIUM, 2)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(4), new IReward[] { (IReward)Reward.builder().level(4).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 20)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(4), new IReward[] { (IReward)Reward.builder().level(4).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 20)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(4), new IReward[] { (IReward)Reward.builder().level(4).moneyAmount(Double.valueOf(2000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(5), new IReward[] { (IReward)Reward.builder().level(5).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 25)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(5), new IReward[] { (IReward)Reward.builder().level(5).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 25)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(5), new IReward[] { (IReward)Reward.builder().level(5).moneyAmount(Double.valueOf(2500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(5), new IReward[] { (IReward)Reward.builder().level(5).rewardItem(new ItemStack(ItemsRegister.AMETHYST_INGOT, 8)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(6), new IReward[] { (IReward)Reward.builder().level(6).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 30)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(6), new IReward[] { (IReward)Reward.builder().level(6).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 30)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(6), new IReward[] { (IReward)Reward.builder().level(6).moneyAmount(Double.valueOf(3000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(6), new IReward[] { (IReward)Reward.builder().level(6).rewardItem(new ItemStack(ItemsRegister.FURNACE_UPGRADE, 2)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(7), new IReward[] { (IReward)Reward.builder().level(7).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 35)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(7), new IReward[] { (IReward)Reward.builder().level(7).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 35)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(7), new IReward[] { (IReward)Reward.builder().level(7).moneyAmount(Double.valueOf(3500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(7), new IReward[] { (IReward)Reward.builder().level(7).rewardItem(new ItemStack(ItemsRegistry.TITANE_RADIUS_HOE)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(7), new IReward[] { (IReward)Reward.builder().level(7).rewardItem(new ItemStack((Item)ItemsRegister.SEED_EGGPLANT, 64)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(8), new IReward[] { (IReward)Reward.builder().level(8).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 40)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(8), new IReward[] { (IReward)Reward.builder().level(8).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 40)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(8), new IReward[] { (IReward)Reward.builder().level(8).moneyAmount(Double.valueOf(4000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(9), new IReward[] { (IReward)Reward.builder().level(9).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 45)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(9), new IReward[] { (IReward)Reward.builder().level(9).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 45)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(9), new IReward[] { (IReward)Reward.builder().level(9).moneyAmount(Double.valueOf(4500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(9), new IReward[] { (IReward)Reward.builder().level(9).rewardItem(new ItemStack(ItemsRegister.COMPRESSED_AMETHYST)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(10), new IReward[] { (IReward)Reward.builder().level(10).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 50)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(10), new IReward[] { (IReward)Reward.builder().level(10).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 50)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(10), new IReward[] { (IReward)Reward.builder().level(10).moneyAmount(Double.valueOf(5000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 55)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 55)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).moneyAmount(Double.valueOf(5500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).rewardItem(new ItemStack((Item)ItemsRegister.TRAVEL_BOOTS)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).rewardItem(new ItemStack((Item)ItemsRegister.TRAVEL_LEGGINGS)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).rewardItem(new ItemStack((Item)ItemsRegister.TRAVEL_JUMPCHEST)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).rewardItem(new ItemStack((Item)ItemsRegister.TRAVEL_SLIMYHELMET)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(12), new IReward[] { (IReward)Reward.builder().level(12).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 60)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(12), new IReward[] { (IReward)Reward.builder().level(12).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 60)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(12), new IReward[] { (IReward)Reward.builder().level(12).moneyAmount(Double.valueOf(6000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(13), new IReward[] { (IReward)Reward.builder().level(13).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 65)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(13), new IReward[] { (IReward)Reward.builder().level(13).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 65)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(13), new IReward[] { (IReward)Reward.builder().level(13).moneyAmount(Double.valueOf(6500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(13), new IReward[] { (IReward)Reward.builder().level(13).rewardItem(new ItemStack((Item)ItemsRegister.SEED_CHERVIL, 64)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(14), new IReward[] { (IReward)Reward.builder().level(14).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 70)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(14), new IReward[] { (IReward)Reward.builder().level(14).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 70)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(14), new IReward[] { (IReward)Reward.builder().level(14).moneyAmount(Double.valueOf(7000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(15), new IReward[] { (IReward)Reward.builder().level(15).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 75)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(15), new IReward[] { (IReward)Reward.builder().level(15).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 75)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(15), new IReward[] { (IReward)Reward.builder().level(15).moneyAmount(Double.valueOf(7500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(16), new IReward[] { (IReward)Reward.builder().level(16).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 80)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(16), new IReward[] { (IReward)Reward.builder().level(16).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 80)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(16), new IReward[] { (IReward)Reward.builder().level(16).moneyAmount(Double.valueOf(8000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(16), new IReward[] { (IReward)Reward.builder().level(16).rewardItem(new ItemStack((Block)BlocksRegister.PALADIUM_CHEST)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(17), new IReward[] { (IReward)Reward.builder().level(17).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 85)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(17), new IReward[] { (IReward)Reward.builder().level(17).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 85)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(17), new IReward[] { (IReward)Reward.builder().level(17).moneyAmount(Double.valueOf(8500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(17), new IReward[] { (IReward)Reward.builder().level(17).rewardItem(new ItemStack((Item)ItemsRegister.SEED_KIWANO, 64)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(18), new IReward[] { (IReward)Reward.builder().level(18).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 90)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(18), new IReward[] { (IReward)Reward.builder().level(18).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 90)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(18), new IReward[] { (IReward)Reward.builder().level(18).moneyAmount(Double.valueOf(9000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(19), new IReward[] { (IReward)Reward.builder().level(19).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 95)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(19), new IReward[] { (IReward)Reward.builder().level(19).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 95)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(19), new IReward[] { (IReward)Reward.builder().level(19).moneyAmount(Double.valueOf(9500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(19), new IReward[] { (IReward)Reward.builder().level(19).rewardItem(new ItemStack(ItemsRegister.JOB_CANDY)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(19), new IReward[] { (IReward)Reward.builder().level(19).rewardItem(new ItemStack(ItemsRegister.PALADIUM_GREEN_INGOT, 5)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(20), new IReward[] { (IReward)Reward.builder().level(20).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 105)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(20), new IReward[] { (IReward)Reward.builder().level(20).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 105)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(20), new IReward[] { (IReward)Reward.builder().level(20).moneyAmount(Double.valueOf(10500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(20), new IReward[] { (IReward)Reward.builder().level(20).rewardItem(new ItemStack(ItemsRegister.ENDIUM_NUGGET)).type(RewardType.GIVE).build() });
  }
  
  public void registerBlacklistedCrafts() {
    this.job.addBlacklistedCraft(2, new ItemStack(ItemsRegistry.AMETHYST_RADIUS_HOE));
    this.job.addBlacklistedCraft(3, new ItemStack((Item)ItemsRegister.SEEDPLANTER_LVL1));
    this.job.addBlacklistedCraft(5, new ItemStack(ItemsRegistry.TITANE_RADIUS_HOE));
    this.job.addBlacklistedCraft(6, new ItemStack((Item)ItemsRegister.SEEDPLANTER_LVL2));
    this.job.addBlacklistedCraft(7, new ItemStack((Block)BlocksRegister.CROPS_EGGPLANT));
    this.job.addBlacklistedCraft(8, new ItemStack((Block)BlocksRegister.CRUSHER));
    int i;
    for (i = 0; i < 10; i++)
      this.job.addBlacklistedCraft(9, new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, i)); 
    this.job.addBlacklistedCraft(10, new ItemStack(BlocksRegistry.ROOT_SEEDS_BLOCK));
    for (i = 0; i < 10; i++)
      this.job.addBlacklistedCraft(11, new ItemStack((Block)BlocksRegistry.COLORED_GRASS, 1, i)); 
    this.job.addBlacklistedCraft(12, new ItemStack((Block)BlocksRegister.CROPS_CHERVIL));
    this.job.addBlacklistedCraft(13, new ItemStack(ItemsRegistry.PALADIUM_RADIUS_HOE));
    this.job.addBlacklistedCraft(14, new ItemStack((Block)BlocksRegister.Totem));
    this.job.addBlacklistedCraft(15, new ItemStack((Item)ItemsRegister.SEEDPLANTER_LVL3));
    this.job.addBlacklistedCraft(16, new ItemStack((Block)BlocksRegister.CROPS_KIWANO));
    this.job.addBlacklistedCraft(17, new ItemStack(ItemsRegistry.GREEN_PALADIUM_RADIUS_HOE));
    this.job.addBlacklistedCraft(18, new ItemStack((Item)ItemsRegister.SEEDPLANTER_LVL5));
    this.job.addBlacklistedCraft(19, new ItemStack(BlocksRegistry.FARMER_THRONE));
    this.job.addBlacklistedCraft(20, new ItemStack(ItemsRegistry.ENDIUM_RADIUS_HOE));
    this.job.addBlacklistedCraft(20, new ItemStack((Item)ItemsRegister.SEEDPLANTER_LVL4));
    this.job.addBlacklistedCraft(20, new ItemStack((Block)BlocksRegister.CROPS_ORANGEBLUE));
    this.job.addBlacklistedCraft(20, new ItemStack(ItemsRegister.ENDIUM_AXE));
    this.job.addBlacklistedCraft(20, new ItemStack(ItemsRegister.ENDIUM_CHESTPLATE));
  }
  
  public void registerBlacklistedUsages() {
    this.job.addBlacklistedUsage(2, new ItemStack(ItemsRegistry.AMETHYST_RADIUS_HOE));
    this.job.addBlacklistedUsage(3, new ItemStack((Item)ItemsRegister.SEEDPLANTER_LVL1));
    this.job.addBlacklistedUsage(5, new ItemStack(ItemsRegistry.TITANE_RADIUS_HOE));
    this.job.addBlacklistedUsage(6, new ItemStack((Item)ItemsRegister.SEEDPLANTER_LVL2));
    this.job.addBlacklistedUsage(7, new ItemStack((Block)BlocksRegister.CROPS_EGGPLANT));
    this.job.addBlacklistedUsage(8, new ItemStack((Block)BlocksRegister.CRUSHER));
    int i;
    for (i = 0; i < 10; i++)
      this.job.addBlacklistedUsage(9, new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, i)); 
    this.job.addBlacklistedUsage(10, new ItemStack(BlocksRegistry.ROOT_SEEDS_BLOCK));
    for (i = 0; i < 10; i++)
      this.job.addBlacklistedUsage(11, new ItemStack((Block)BlocksRegistry.COLORED_GRASS, 1, i)); 
    this.job.addBlacklistedUsage(12, new ItemStack((Block)BlocksRegister.CROPS_CHERVIL));
    this.job.addBlacklistedUsage(13, new ItemStack(ItemsRegistry.PALADIUM_RADIUS_HOE));
    this.job.addBlacklistedUsage(14, new ItemStack((Block)BlocksRegister.Totem));
    this.job.addBlacklistedUsage(15, new ItemStack((Item)ItemsRegister.SEEDPLANTER_LVL3));
    this.job.addBlacklistedUsage(16, new ItemStack((Block)BlocksRegister.CROPS_KIWANO));
    this.job.addBlacklistedUsage(17, new ItemStack(ItemsRegistry.GREEN_PALADIUM_RADIUS_HOE));
    this.job.addBlacklistedUsage(18, new ItemStack((Item)ItemsRegister.SEEDPLANTER_LVL5));
    this.job.addBlacklistedUsage(20, new ItemStack(ItemsRegistry.ENDIUM_RADIUS_HOE));
    this.job.addBlacklistedUsage(20, new ItemStack((Item)ItemsRegister.SEEDPLANTER_LVL4));
    this.job.addBlacklistedUsage(20, new ItemStack((Block)BlocksRegister.CROPS_ORANGEBLUE));
    this.job.addBlacklistedUsage(20, new ItemStack(ItemsRegister.ENDIUM_AXE));
    this.job.addBlacklistedUsage(20, new ItemStack(ItemsRegister.ENDIUM_CHESTPLATE));
    this.job.addBlacklistedUsage(20, new ItemStack(BlocksRegistry.FARMER_THRONE));
  }
  
  public void registerLvlTokensRewards() {
    LvlTokenRegistry.register(new LvlTokenReward(2, "2-common-bone-powder-8", "8 Bone Powder", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(Items.field_151100_aR, 1, 15), 8, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(2, "2-rare-potato-3", "3 Potatoes", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(Items.field_151174_bG), 3, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(2, "2-EPIC-fertilized-soil-20", "20 Fertilized Soil", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(Blocks.field_150458_ak), 20, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(4, "4-common-titanium-ingot-2", "2 Titanium Ingots", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.TITANE_INGOT), 2, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(4, "4-rare-titanium-block-1", "1 Titanium Block", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(BlocksRegister.BLOCK_TITANE), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(4, "4-EPIC-titanium-block-3", "3 Titanium Blocks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(BlocksRegister.BLOCK_TITANE), 3, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(6, "6-common-titanium-ingot-3", "3 Titanium Ingots", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.TITANE_INGOT), 3, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(6, "6-rare-titanium-stick-3", "3 Titanium Sticks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(ItemsRegister.STICK_TITANE), 3, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(6, "6-EPIC-compressed-titanium-1", "1 Compressed Titanium", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(ItemsRegister.COMPRESSED_TITANE), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(8, "8-common-eggplant-seeds-32", "32 Eggplant Seeds", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack((Item)ItemsRegister.SEED_EGGPLANT), 32, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(8, "8-rare-findium-1", "1 Findium", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(ItemsRegister.FINDIUM), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(8, "8-EPIC-paladium-heart-2", "2 Paladium Hearts", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(ItemsRegister.PALADIUM_CORE), 2, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(10, "10-common-paladium-hoe-1", "1 Paladium Hoe", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegistry.PALADIUM_RADIUS_HOE), 1, this.job.getType()));
    ItemStack lvl10 = new ItemStack(ItemsRegister.PALADIUM_SHOVEL);
    lvl10.func_77966_a(Enchantment.field_77349_p, 5);
    lvl10.func_77966_a(Enchantment.field_77347_r, 3);
    LvlTokenRegistry.register(new LvlTokenReward(10, "10-rare-paladium-shovel-1", "1 Paladium Shovel (Efficiency 5, Unbreaking 3)", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, lvl10, 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(10, "10-EPIC-titanium-chest-1", "1 Titanium Chest", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack((Block)BlocksRegister.TITANE_CHEST), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(11, "11-common-compressed-titanium-2", "2 Compressed Titanium", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.COMPRESSED_TITANE), 2, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(11, "11-rare-creeper-head-1", "1 Creeper Head", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(Items.field_151144_bL, 1, 4), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(11, "11-EPIC-compressed-paladium-2", "2 Compressed Paladium", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), 2, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(12, "12-common-chervil-seeds-8", "8 Chervil Seeds", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack((Item)ItemsRegister.SEED_CHERVIL), 8, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(12, "12-rare-chervil-seeds-32", "32 Chervil Seeds", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack((Item)ItemsRegister.SEED_CHERVIL), 32, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(12, "12-EPIC-double-xp-potion-30min-1", "1 Double XP Potion (30 minutes)", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 0), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(13, "13-common-titanium-seedplanter-1", "1 Titanium Seedplanter", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack((Item)ItemsRegister.SEEDPLANTER_LVL3), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(13, "13-rare-compressed-paladium-1", "1 Compressed Paladium", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(13, "13-EPIC-paladium-block-12", "12 Paladium Blocks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(BlocksRegister.BLOCK_PALADIUM), 12, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(14, "14-common-paladium-16", "16 Paladium", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.PALADIUM_INGOT), 16, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(14, "14-rare-compressed-paladium-3", "3 Compressed Paladium", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), 3, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(14, "14-EPIC-paladium-chest-1", "1 Paladium Chest", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack((Block)BlocksRegister.PALADIUM_CHEST), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(15, "15-common-paladium-ingot-green-5", "5 Green Paladium Ingots", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.PALADIUM_GREEN_INGOT), 5, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(15, "15-rare-paladium-ingot-green-16", "16 Green Paladium Ingots", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(ItemsRegister.PALADIUM_GREEN_INGOT), 16, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(15, "15-EPIC-double-xp-potion-1h-1", "1 Double XP Potion (1 hour)", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 1), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(16, "16-common-kiwano-seeds-16", "16 Kiwano Seeds", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack((Item)ItemsRegister.SEED_KIWANO), 16, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(16, "16-rare-double-xp-potion-30min-2", "2 Double XP Potions (30 minutes)", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 0), 2, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(16, "16-EPIC-kiwano-seeds-48", "48 Kiwano Seeds", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack((Item)ItemsRegister.SEED_KIWANO), 48, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(17, "17-common-paladium-seedplanter-1", "1 Paladium Seedplanter", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack((Item)ItemsRegister.SEEDPLANTER_LVL4), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(17, "17-rare-paladium-block-green-5", "5 Green Paladium Blocks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(BlocksRegister.BLOCK_GPALADIUM), 5, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(17, "17-EPIC-unwrapping-candy-1", "1 Unwrapping Candy", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(ItemsRegister.JOB_CANDY), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(18, "18-rare-paladium-mixed-coal-64", "64 Paladium Mixed Coal", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(ItemsRegister.PALAMIXEDCOAL), 64, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(18, "18-EPIC-fertility-totem-3", "3 Fertility Totems", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack((Block)BlocksRegister.Totem), 3, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(19, "19-rare-kiwano-seeds-48", "48 Kiwano Seeds", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack((Item)ItemsRegister.SEED_KIWANO), 48, this.job.getType()));
    ItemStack lvl19 = new ItemStack(ItemsRegister.PALADIUM_GREEN_BOOTS);
    lvl19.func_77966_a(Enchantment.field_77332_c, 4);
    LvlTokenRegistry.register(new LvlTokenReward(19, "19-EPIC-green-paladium-boots-1", "1 Green Paladium Boots (Protection 4, Mending)", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, lvl19, 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(20, "20-EPIC-endium-fragment-4", "4 Endium Fragments", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(ItemsRegister.ENDIUM_FRAGMENT), 4, this.job.getType()));
  }
  
  public void registerRequirements() {
    this.job.addRequirement((Requirement)new BreakBlockRequirement(2, JobType.FARMER, "Casser %d blés", 2950, new ItemStack(Items.field_151014_N, 1, 7)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(3, JobType.FARMER, "Casser %d carottes", 3200, new ItemStack(Blocks.field_150459_bM, 1, 0)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(4, JobType.FARMER, "Casser %d pastèques", 3700, new ItemStack(Blocks.field_150440_ba)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(5, JobType.FARMER, "Casser %d pastèques", 5950, new ItemStack(Blocks.field_150440_ba)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(6, JobType.FARMER, "Casser %d pastèques", 8850, new ItemStack(Blocks.field_150440_ba)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(7, JobType.FARMER, "Casser %d pastèques", 12400, new ItemStack(Blocks.field_150440_ba)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(8, JobType.FARMER, "Casser %d pastèques", 13300, new ItemStack(Blocks.field_150440_ba)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(9, JobType.FARMER, "Casser %d eggplants", 8650, new ItemStack((Block)BlocksRegister.CROPS_EGGPLANT)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(10, JobType.FARMER, "Casser %d eggplants", 10900, new ItemStack((Block)BlocksRegister.CROPS_EGGPLANT)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(11, JobType.FARMER, "Casser %d eggplants", 13500, new ItemStack((Block)BlocksRegister.CROPS_EGGPLANT)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(12, JobType.FARMER, "Casser %d eggplants", 16400, new ItemStack((Block)BlocksRegister.CROPS_EGGPLANT)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(13, JobType.FARMER, "Casser %d chervils", 9800, new ItemStack((Block)BlocksRegister.CROPS_CHERVIL)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(14, JobType.FARMER, "Casser %d chervils", 11600, new ItemStack((Block)BlocksRegister.CROPS_CHERVIL)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(15, JobType.FARMER, "Casser %d chervils", 13550, new ItemStack((Block)BlocksRegister.CROPS_CHERVIL)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(16, JobType.FARMER, "Casser %d chervils", 15650, new ItemStack((Block)BlocksRegister.CROPS_CHERVIL)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(17, JobType.FARMER, "Casser %d kiwanos", 7200, new ItemStack((Block)BlocksRegister.CROPS_KIWANO)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(18, JobType.FARMER, "Casser %d kiwanos", 8200, new ItemStack((Block)BlocksRegister.CROPS_KIWANO)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(19, JobType.FARMER, "Casser %d kiwanos", 9250, new ItemStack((Block)BlocksRegister.CROPS_KIWANO)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(20, JobType.FARMER, "Casser %d kiwanos", 10400, new ItemStack((Block)BlocksRegister.CROPS_KIWANO)));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\registerer\jobs\type\FarmerRegisterer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */