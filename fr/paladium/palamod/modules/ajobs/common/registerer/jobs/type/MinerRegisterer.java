package fr.paladium.palamod.modules.ajobs.common.registerer.jobs.type;

import fr.paladium.palajobs.api.reward.IReward;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.RewardType;
import fr.paladium.palajobs.core.jobs.AbstractJob;
import fr.paladium.palajobs.core.jobs.Requirement;
import fr.paladium.palajobs.core.jobs.requirement.BreakBlockRequirement;
import fr.paladium.palajobs.core.jobs.type.MinerJob;
import fr.paladium.palajobs.core.pojo.objectives.AbstractObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.BreakBlockObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.CobbleBreakerObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.EntityKillSpecialObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.SmeltObjective;
import fr.paladium.palajobs.core.pojo.rewards.Reward;
import fr.paladium.palajobs.core.registry.JobRegistry;
import fr.paladium.palajobs.core.tokens.LvlTokenRegistry;
import fr.paladium.palajobs.core.tokens.rewards.EnumLvlTokenRewardCategory;
import fr.paladium.palajobs.core.tokens.rewards.EnumLvlTokenRewardRarity;
import fr.paladium.palajobs.core.tokens.rewards.LvlTokenReward;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.ajobs.common.registerer.BlocksRegistry;
import fr.paladium.palamod.modules.ajobs.common.registerer.jobs.JobRegisterer;
import fr.paladium.palamod.modules.hunter.entites.EntityCavernousZombie;
import fr.paladium.palamod.modules.miner.PMiner;
import fr.paladium.palamod.modules.smeltery.registerer.PSRegister_Items;
import fr.paladium.palamod.modules.smeltery.utils.UpgradeHelper;
import fr.paladium.palamod.modules.world.PWorld;
import fr.paladium.palaspawner.common.registry.SpawnerItemRegistry;
import fr.paladium.palawither.api.BlocksRegister;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MinerRegisterer extends JobRegisterer {
  public MinerRegisterer(MinerJob job) {
    super((AbstractJob)job);
  }
  
  public void init() {
    JobRegistry.getInstance().setMinerJob((MinerJob)this.job);
  }
  
  public void registerObjectives() {
    BreakBlockObjective breakBlockObjective = new BreakBlockObjective(this.job.getType());
    breakBlockObjective.add(getJob().buildValue(0.5D, 0), new ItemStack(Blocks.field_150348_b));
    breakBlockObjective.add(getJob().buildValue(4.0D, 0), new ItemStack(Blocks.field_150365_q));
    breakBlockObjective.add(getJob().buildValue(6.0D, 0), new ItemStack(Blocks.field_150449_bY));
    breakBlockObjective.add(getJob().buildValue(25.0D, 2), new ItemStack(Blocks.field_150482_ag));
    breakBlockObjective.add(getJob().buildValue(110.0D, 10), new ItemStack(PWorld.FINDIUM_ORE));
    breakBlockObjective.add(getJob().buildValue(15.0D, 0), new ItemStack(Blocks.field_150450_ax));
    breakBlockObjective.add(getJob().buildValue(15.0D, 0), new ItemStack(Blocks.field_150369_x));
    breakBlockObjective.add(getJob().buildValue(75.0D, 0), new ItemStack(Blocks.field_150412_bA));
    breakBlockObjective.add(getJob().buildValue(3.0D, 0), new ItemStack(PWorld.GRANITE_BLOCK));
    breakBlockObjective.add(getJob().buildValue(3.0D, 0), new ItemStack(PWorld.DIORITE_BLOCK));
    breakBlockObjective.add(getJob().buildValue(3.0D, 0), new ItemStack(PWorld.ANDESITE_BLOCK));
    breakBlockObjective.add(getJob().buildValue(6.0D, 0), new ItemStack(Blocks.field_150343_Z));
    SmeltObjective smeltObjective = new SmeltObjective(this.job.getType());
    smeltObjective.add(getJob().buildValue(1.0D, 0), new ItemStack(Items.field_151044_h, 1, 1));
    smeltObjective.add(getJob().buildValue(0.1D, 0), new ItemStack(Items.field_151130_bT));
    smeltObjective.add(getJob().buildValue(8.0D, 2), new ItemStack(Items.field_151042_j));
    smeltObjective.add(getJob().buildValue(30.0D, 3), new ItemStack(Items.field_151043_k));
    smeltObjective.add(getJob().buildValue(35.0D, 4), new ItemStack(ItemsRegister.AMETHYST_INGOT));
    smeltObjective.add(getJob().buildValue(50.0D, 6), new ItemStack(ItemsRegister.TITANE_INGOT));
    smeltObjective.add(getJob().buildValue(150.0D, 8), new ItemStack(ItemsRegister.PALADIUM_INGOT));
    smeltObjective.add(getJob().buildValue(200.0D, 12), new ItemStack(ItemsRegister.PALADIUM_GREEN_INGOT));
    CobbleBreakerObjective cobbleBreakerObjective = new CobbleBreakerObjective(this.job.getType());
    cobbleBreakerObjective.add(getJob().buildValue(2.0D, 5), new ItemStack(ItemsRegister.PARTICLE_IRON));
    cobbleBreakerObjective.add(getJob().buildValue(4.0D, 5), new ItemStack(ItemsRegister.PARTICLE_GOLD));
    cobbleBreakerObjective.add(getJob().buildValue(8.0D, 5), new ItemStack(ItemsRegister.PARTICLE_DIAMOND));
    cobbleBreakerObjective.add(getJob().buildValue(12.0D, 5), new ItemStack(ItemsRegister.PARTICLE_AMETHYST));
    cobbleBreakerObjective.add(getJob().buildValue(16.0D, 5), new ItemStack(ItemsRegister.PARTICLE_TITANE));
    cobbleBreakerObjective.add(getJob().buildValue(20.0D, 5), new ItemStack(ItemsRegister.PARTICLE_PALADIUM));
    EntityKillSpecialObjective entityKillSpecialObjective = new EntityKillSpecialObjective(this.job.getType());
    entityKillSpecialObjective.add(getJob().buildValue(0.0D, 7), EntityCavernousZombie.class);
    this.job.addObjectives(new AbstractObjective[] { (AbstractObjective)breakBlockObjective, (AbstractObjective)smeltObjective, (AbstractObjective)cobbleBreakerObjective, (AbstractObjective)entityKillSpecialObjective });
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
    this.job.addReward(Integer.valueOf(5), new IReward[] { (IReward)Reward.builder().level(5).rewardItem(new ItemStack(ItemsRegister.AMETHYST_INGOT, 16)).type(RewardType.GIVE).build() });
    ItemStack enchantedPickaxe = new ItemStack(ItemsRegister.PALADIUM_PICKAXE);
    enchantedPickaxe.func_77966_a(Enchantment.field_77349_p, 5);
    enchantedPickaxe.func_77966_a(Enchantment.field_77347_r, 3);
    this.job.addReward(Integer.valueOf(6), new IReward[] { (IReward)Reward.builder().level(6).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 30)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(6), new IReward[] { (IReward)Reward.builder().level(6).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 30)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(6), new IReward[] { (IReward)Reward.builder().level(6).moneyAmount(Double.valueOf(3000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(6), new IReward[] { (IReward)Reward.builder().level(6).rewardItem(enchantedPickaxe).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(7), new IReward[] { (IReward)Reward.builder().level(7).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 35)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(7), new IReward[] { (IReward)Reward.builder().level(7).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 35)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(7), new IReward[] { (IReward)Reward.builder().level(7).moneyAmount(Double.valueOf(3500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(8), new IReward[] { (IReward)Reward.builder().level(8).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 40)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(8), new IReward[] { (IReward)Reward.builder().level(8).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 40)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(8), new IReward[] { (IReward)Reward.builder().level(8).moneyAmount(Double.valueOf(4000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(9), new IReward[] { (IReward)Reward.builder().level(9).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 45)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(9), new IReward[] { (IReward)Reward.builder().level(9).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 45)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(9), new IReward[] { (IReward)Reward.builder().level(9).moneyAmount(Double.valueOf(4500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(9), new IReward[] { (IReward)Reward.builder().level(9).rewardItem(new ItemStack((Item)PSRegister_Items.MODIFIER_SPEED)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(10), new IReward[] { (IReward)Reward.builder().level(10).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 50)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(10), new IReward[] { (IReward)Reward.builder().level(10).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 50)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(10), new IReward[] { (IReward)Reward.builder().level(10).moneyAmount(Double.valueOf(5000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 55)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 55)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).moneyAmount(Double.valueOf(5500.0D)).type(RewardType.MONEY_GIVE).build() });
    ItemStack enchantedHelmet = new ItemStack(ItemsRegister.PALADIUM_HELMET);
    enchantedHelmet.func_77966_a(Enchantment.field_77332_c, 4);
    enchantedHelmet.func_77966_a(Enchantment.field_77347_r, 3);
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).rewardItem(enchantedHelmet).type(RewardType.GIVE).build() });
    ItemStack enchantedChestplate = new ItemStack(ItemsRegister.PALADIUM_CHESTPLATE);
    enchantedChestplate.func_77966_a(Enchantment.field_77332_c, 4);
    enchantedChestplate.func_77966_a(Enchantment.field_77347_r, 3);
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).rewardItem(enchantedChestplate).type(RewardType.GIVE).build() });
    ItemStack enchantedLeggings = new ItemStack(ItemsRegister.PALADIUM_LEGGINGS);
    enchantedLeggings.func_77966_a(Enchantment.field_77332_c, 4);
    enchantedLeggings.func_77966_a(Enchantment.field_77347_r, 3);
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).rewardItem(enchantedLeggings).type(RewardType.GIVE).build() });
    ItemStack enchantedBoots = new ItemStack(ItemsRegister.PALADIUM_BOOTS);
    enchantedBoots.func_77966_a(Enchantment.field_77332_c, 4);
    enchantedBoots.func_77966_a(Enchantment.field_77347_r, 3);
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).rewardItem(enchantedBoots).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(12), new IReward[] { (IReward)Reward.builder().level(12).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 60)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(12), new IReward[] { (IReward)Reward.builder().level(12).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 60)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(12), new IReward[] { (IReward)Reward.builder().level(12).moneyAmount(Double.valueOf(6000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(13), new IReward[] { (IReward)Reward.builder().level(13).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 65)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(13), new IReward[] { (IReward)Reward.builder().level(13).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 65)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(13), new IReward[] { (IReward)Reward.builder().level(13).moneyAmount(Double.valueOf(6500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(13), new IReward[] { (IReward)Reward.builder().level(13).rewardItem(new ItemStack(Blocks.field_150343_Z, 64)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(14), new IReward[] { (IReward)Reward.builder().level(14).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 70)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(14), new IReward[] { (IReward)Reward.builder().level(14).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 70)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(14), new IReward[] { (IReward)Reward.builder().level(14).moneyAmount(Double.valueOf(7000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(15), new IReward[] { (IReward)Reward.builder().level(15).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 75)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(15), new IReward[] { (IReward)Reward.builder().level(15).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 75)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(15), new IReward[] { (IReward)Reward.builder().level(15).moneyAmount(Double.valueOf(7500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(16), new IReward[] { (IReward)Reward.builder().level(16).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 80)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(16), new IReward[] { (IReward)Reward.builder().level(16).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 80)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(16), new IReward[] { (IReward)Reward.builder().level(16).moneyAmount(Double.valueOf(8000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(16), new IReward[] { (IReward)Reward.builder().level(16).rewardItem(new ItemStack(Items.field_151144_bL, 1, 1)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(17), new IReward[] { (IReward)Reward.builder().level(17).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 85)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(17), new IReward[] { (IReward)Reward.builder().level(17).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 85)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(17), new IReward[] { (IReward)Reward.builder().level(17).moneyAmount(Double.valueOf(8500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(18), new IReward[] { (IReward)Reward.builder().level(18).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 90)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(18), new IReward[] { (IReward)Reward.builder().level(18).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 90)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(18), new IReward[] { (IReward)Reward.builder().level(18).moneyAmount(Double.valueOf(9000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(19), new IReward[] { (IReward)Reward.builder().level(19).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 95)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(19), new IReward[] { (IReward)Reward.builder().level(19).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 95)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(19), new IReward[] { (IReward)Reward.builder().level(19).moneyAmount(Double.valueOf(9500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(19), new IReward[] { (IReward)Reward.builder().level(19).rewardItem(new ItemStack(ItemsRegister.JOB_CANDY)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(19), new IReward[] { (IReward)Reward.builder().level(19).rewardItem(new ItemStack(BlocksRegister.WITHER_RECEPTACLE)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(20), new IReward[] { (IReward)Reward.builder().level(20).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 105)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(20), new IReward[] { (IReward)Reward.builder().level(20).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 105)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(20), new IReward[] { (IReward)Reward.builder().level(20).moneyAmount(Double.valueOf(10500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(20), new IReward[] { (IReward)Reward.builder().level(20).rewardItem(new ItemStack(ItemsRegister.ENDIUM_NUGGET)).type(RewardType.GIVE).build() });
  }
  
  public void registerBlacklistedCrafts() {
    this.job.addBlacklistedCraft(2, new ItemStack((Item)ItemsRegister.VOIDSTONE));
    this.job.addBlacklistedCraft(3, new ItemStack(ItemsRegister.AMETHYST_EXCAVATOR));
    this.job.addBlacklistedCraft(4, new ItemStack(ItemsRegister.BUILDER_WAND, 1, 0));
    this.job.addBlacklistedCraft(5, new ItemStack((Block)BlocksRegister.COBBLEBREAKER));
    this.job.addBlacklistedCraft(6, new ItemStack((Item)ItemsRegister.VOIDSTONE_MINAGE));
    this.job.addBlacklistedCraft(7, new ItemStack(ItemsRegister.MAGNET));
    this.job.addBlacklistedCraft(8, new ItemStack(ItemsRegister.MOULA_STONE));
    this.job.addBlacklistedCraft(9, new ItemStack(BlocksRegister.PALADIUM_HOPPER));
    this.job.addBlacklistedCraft(10, new ItemStack(ItemsRegister.COBBLEBREAKER_AMETHYST_UPGRADE));
    this.job.addBlacklistedCraft(11, new ItemStack(ItemsRegister.TITANE_EXCAVATOR));
    this.job.addBlacklistedCraft(12, new ItemStack(ItemsRegister.BUILDER_WAND, 1, 1));
    this.job.addBlacklistedCraft(13, new ItemStack(ItemsRegister.COBBLEBREAKER_TITANE_UPGRADE));
    this.job.addBlacklistedCraft(14, new ItemStack((Block)BlocksRegister.FORGE));
    this.job.addBlacklistedCraft(15, new ItemStack(ItemsRegister.PALADIUM_EXCAVATOR));
    this.job.addBlacklistedCraft(16, new ItemStack(ItemsRegister.COBBLEBREAKER_PALADIUM_UPGRADE));
    this.job.addBlacklistedCraft(17, new ItemStack(ItemsRegister.BUILDER_WAND, 1, 2));
    this.job.addBlacklistedCraft(17, new ItemStack((Item)SpawnerItemRegistry.UPGRADE_SPEED));
    this.job.addBlacklistedCraft(18, new ItemStack(BlocksRegister.ENDIUM_CAVE_BLOCK));
    this.job.addBlacklistedCraft(18, new ItemStack((Block)BlocksRegister.WITHERED_REINFORCED_PISTON));
    this.job.addBlacklistedCraft(19, new ItemStack(BlocksRegistry.MINER_THRONE));
    this.job.addBlacklistedCraft(20, new ItemStack(ItemsRegister.BUILDER_WAND, 1, 3));
    this.job.addBlacklistedCraft(20, new ItemStack(ItemsRegister.ENDIUM_PICKAXE));
    this.job.addBlacklistedCraft(20, new ItemStack(BlocksRegister.ENDIUM_NUGGET_ORE));
  }
  
  public void registerBlacklistedUsages() {
    this.job.addBlacklistedUsage(2, new ItemStack((Item)ItemsRegister.VOIDSTONE));
    this.job.addBlacklistedUsage(3, new ItemStack(ItemsRegister.AMETHYST_EXCAVATOR));
    this.job.addBlacklistedUsage(5, new ItemStack((Block)BlocksRegister.COBBLEBREAKER));
    this.job.addBlacklistedUsage(6, new ItemStack((Item)ItemsRegister.VOIDSTONE_MINAGE));
    this.job.addBlacklistedUsage(7, new ItemStack(ItemsRegister.MAGNET));
    this.job.addBlacklistedUsage(8, new ItemStack(ItemsRegister.MOULA_STONE));
    this.job.addBlacklistedUsage(9, new ItemStack(BlocksRegister.PALADIUM_HOPPER));
    this.job.addBlacklistedUsage(10, new ItemStack(ItemsRegister.COBBLEBREAKER_AMETHYST_UPGRADE));
    this.job.addBlacklistedUsage(11, new ItemStack(ItemsRegister.TITANE_EXCAVATOR));
    this.job.addBlacklistedUsage(13, new ItemStack(ItemsRegister.COBBLEBREAKER_TITANE_UPGRADE));
    this.job.addBlacklistedUsage(14, new ItemStack((Block)BlocksRegister.FORGE));
    this.job.addBlacklistedUsage(15, new ItemStack(ItemsRegister.PALADIUM_EXCAVATOR));
    this.job.addBlacklistedUsage(16, new ItemStack(ItemsRegister.COBBLEBREAKER_PALADIUM_UPGRADE));
    this.job.addBlacklistedUsage(16, new ItemStack(ItemsRegister.HARDNESS_POTION));
    this.job.addBlacklistedUsage(17, new ItemStack((Item)SpawnerItemRegistry.UPGRADE_SPEED));
    this.job.addBlacklistedUsage(20, new ItemStack(ItemsRegister.BUILDER_WAND, 1, 3));
    this.job.addBlacklistedUsage(20, new ItemStack(ItemsRegister.ENDIUM_PICKAXE));
    this.job.addBlacklistedUsage(20, new ItemStack(BlocksRegistry.MINER_THRONE));
  }
  
  public void registerLvlTokensRewards() {
    LvlTokenRegistry.register(new LvlTokenReward(2, "2-common-amethyst-ingot-2", "2 Amethyst Ingots", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.AMETHYST_INGOT), 2, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(2, "2-rare-amethyst-ingot-4", "4 Amethyst Ingots", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(ItemsRegister.AMETHYST_INGOT), 4, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(2, "2-legendary-amethyst-ingot-10", "10 Amethyst Ingots", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(ItemsRegister.AMETHYST_INGOT), 10, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(4, "4-common-amethyst-stick-3", "3 Amethyst Sticks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.STICK_AMETHYST), 3, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(4, "4-rare-amethyst-block-1", "1 Amethyst Block", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(BlocksRegister.BLOCK_AMETHYST), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(4, "4-legendary-amethyst-block-2", "2 Amethyst Blocks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(BlocksRegister.BLOCK_AMETHYST), 2, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(6, "6-common-golden-obsidian-spike-12", "12 Golden Obsidian Spikes", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(BlocksRegister.OBSI_SPIKE, 1, 2), 12, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(6, "6-rare-end-pearl-4", "4 End Pearls", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(Items.field_151079_bi), 4, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(6, "6-legendary-titanium-chest-1", "1 Titanium Chest", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack((Block)BlocksRegister.TITANE_CHEST), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(8, "8-common-speed-orb-1", "1 Speed Orb", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.ORB_SPEED), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(8, "8-rare-titanium-chest-1", "1 Titanium Chest", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack((Block)BlocksRegister.TITANE_CHEST), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(8, "8-legendary-paladium-ingot-10", "10 Paladium Ingots", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(ItemsRegister.PALADIUM_INGOT), 10, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(10, "10-common-xp-bottle-2", "2 XP Bottles", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(Items.field_151062_by), 2, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(10, "10-rare-xp-bottle-4", "4 XP Bottles", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(Items.field_151062_by), 4, this.job.getType()));
    ItemStack lvl50 = new ItemStack((Item)Items.field_151134_bR);
    Items.field_151134_bR.func_92115_a(lvl50, new EnchantmentData((Enchantment)PMiner.xp_bottler, 1));
    LvlTokenRegistry.register(new LvlTokenReward(10, "10-legendary-experience-bottler-1", "1 Experience Bottler", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, lvl50, 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(11, "11-common-titanium-stick-5", "5 Titanium Sticks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.STICK_TITANE), 5, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(11, "11-rare-titanium-block-5", "5 Titanium Blocks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(BlocksRegister.BLOCK_TITANE), 5, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(11, "11-legendary-palafurnace-3", "3 Palafurnaces", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack((Block)BlocksRegister.PALADIUM_FURNACE), 3, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(12, "12-common-poisoned-obsidian-16", "16 Poisoned Obsidian", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(BlocksRegister.OBSI_POISON), 16, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(12, "12-rare-findium-2", "2 Findium", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(ItemsRegister.FINDIUM), 2, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(12, "12-legendary-double-xp-potion-30min-1", "1 Double XP Potion (30 minutes)", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 0), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(13, "13-common-titanium-excavator-3", "3 Titanium Excavators", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.TITANE_EXCAVATOR), 3, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(13, "13-rare-paladium-block-5", "5 Paladium Blocks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(BlocksRegister.BLOCK_PALADIUM), 5, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(13, "13-legendary-paladium-block-12", "12 Paladium Blocks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(BlocksRegister.BLOCK_PALADIUM), 12, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(14, "14-common-titanium-cobblebreaker-upgrade-1", "1 Titanium Cobblebreaker Upgrade", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.COBBLEBREAKER_TITANE_UPGRADE), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(14, "14-rare-hardness-potion-1", "1 Hardness Potion", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(ItemsRegister.HARDNESS_POTION), 1, this.job.getType()));
    ItemStack lvl70 = new ItemStack(ItemsRegister.PALADIUM_PICKAXE);
    lvl70.func_77966_a(Enchantment.field_77349_p, 5);
    lvl70.func_77966_a(Enchantment.field_77347_r, 3);
    lvl70.func_77966_a(Enchantment.field_77348_q, 1);
    LvlTokenRegistry.register(new LvlTokenReward(14, "14-legendary-paladium-pickaxe-efficiency-5-unbreaking-3-silk-touch-1", "1 Paladium Pickaxe (Efficiency 5, Unbreaking 3, Silk Touch)", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, lvl70, 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(15, "15-common-paladium-block-5", "5 Paladium Blocks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(BlocksRegister.BLOCK_PALADIUM), 5, this.job.getType()));
    ItemStack lvl75 = new ItemStack(ItemsRegister.PALADIUM_PICKAXE);
    lvl75.func_77966_a(Enchantment.field_77349_p, 5);
    lvl75.func_77966_a(Enchantment.field_77347_r, 3);
    lvl75.func_77966_a(Enchantment.field_77348_q, 1);
    LvlTokenRegistry.register(new LvlTokenReward(15, "15-rare-paladium-pickaxe-efficiency-5-unbreaking-3-silk-touch-1", "1 Paladium Pickaxe (Efficiency 5, Unbreaking 3, Silk Touch)", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, lvl75, 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(15, "15-legendary-double-xp-potion-1h-1", "1 Double XP Potion (1 hour)", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 1), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(16, "16-common-nether-star-1", "1 Nether Star", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(Items.field_151156_bN), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(16, "16-rare-double-xp-potion-1h-1", "1 Double XP Potion (1 hour)", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 1), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(16, "16-legendary-nether-star-4", "4 Nether Stars", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(Items.field_151156_bN), 4, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(17, "17-common-paladium-block-16", "16 Paladium Blocks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(BlocksRegister.BLOCK_PALADIUM), 16, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(17, "17-rare-green-paladium-block-3", "3 Green Paladium Blocks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(BlocksRegister.BLOCK_GPALADIUM), 3, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(17, "17-legendary-unboxing-candy-1", "1 Unboxing Candy", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(ItemsRegister.JOB_CANDY), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(18, "18-rare-wither-reinforced-piston-1", "1 Wither Reinforced Piston", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack((Block)BlocksRegister.WITHERED_REINFORCED_PISTON), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(18, "18-legendary-wither-reinforced-piston-6", "6 Wither Reinforced Pistons", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack((Block)BlocksRegister.WITHERED_REINFORCED_PISTON), 6, this.job.getType()));
    ItemStack lvl19 = new ItemStack((Item)PSRegister_Items.HAMMER_PALADIUM);
    UpgradeHelper.applyUpgrade(lvl19, 1);
    UpgradeHelper.applyUpgrade(lvl19, 1);
    UpgradeHelper.applyUpgrade(lvl19, 1);
    UpgradeHelper.applyUpgrade(lvl19, 2);
    UpgradeHelper.applyUpgrade(lvl19, 2);
    UpgradeHelper.applyUpgrade(lvl19, 2);
    LvlTokenRegistry.register(new LvlTokenReward(19, "19-rare-speed-3-fortune-3-paladium-hammer-1", "1 Speed 3 Fortune 3 Paladium Hammer", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, lvl19, 1, this.job.getType()));
    ItemStack lvl95bis = new ItemStack(ItemsRegister.PALADIUM_PICKAXE);
    lvl95bis.func_77966_a(Enchantment.field_77349_p, 5);
    lvl95bis.func_77966_a(Enchantment.field_77347_r, 3);
    lvl95bis.func_77966_a(Enchantment.field_77346_s, 3);
    LvlTokenRegistry.register(new LvlTokenReward(19, "19-legendary-efficiency-5-unbreaking-3-fortune-3-mending-paladium-pickaxe-1", "1 Efficiency 5, Unbreaking 3, Fortune 3, Mending Paladium Pickaxe", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, lvl95bis, 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(20, "20-legendary-endium-fragment-4", "4 Endium Fragments", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(ItemsRegister.ENDIUM_FRAGMENT), 4, this.job.getType()));
  }
  
  public void registerRequirements() {
    this.job.addRequirement((Requirement)new BreakBlockRequirement(2, JobType.MINER, "Casser %d blocs de pierre", 8850, new ItemStack(Blocks.field_150348_b)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(3, JobType.MINER, "Casser %d blocs de pierre", 16150, new ItemStack(Blocks.field_150348_b)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(4, JobType.MINER, "Casser %d blocs de pierre", 29500, new ItemStack(Blocks.field_150348_b)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(5, JobType.MINER, "Casser %d blocs de pierre", 47550, new ItemStack(Blocks.field_150348_b)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(6, JobType.MINER, "Casser %d blocs de pierre", 70650, new ItemStack(Blocks.field_150348_b)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(7, JobType.MINER, "Casser %d blocs de pierre", 99050, new ItemStack(Blocks.field_150348_b)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(8, JobType.MINER, "Casser %d blocs de pierre", 133000, new ItemStack(Blocks.field_150348_b)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(9, JobType.MINER, "Casser %d minerais d'améthyste", 800, new ItemStack(PWorld.AMETHYST_ORE)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(10, JobType.MINER, "Casser %d minerais d'améthyste", 1050, new ItemStack(PWorld.AMETHYST_ORE)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(11, JobType.MINER, "Casser %d minerais d'améthyste", 1300, new ItemStack(PWorld.AMETHYST_ORE)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(12, JobType.MINER, "Casser %d minerais d'améthyste", 1550, new ItemStack(PWorld.AMETHYST_ORE)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(13, JobType.MINER, "Casser %d minerais de titane", 1300, new ItemStack(PWorld.TITANE_ORE)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(14, JobType.MINER, "Casser %d minerais de titane", 1550, new ItemStack(PWorld.TITANE_ORE)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(15, JobType.MINER, "Casser %d minerais de titane", 1800, new ItemStack(PWorld.TITANE_ORE)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(16, JobType.MINER, "Casser %d minerais de titane", 2100, new ItemStack(PWorld.TITANE_ORE)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(17, JobType.MINER, "Casser %d minerais de paladium", 800, new ItemStack(PWorld.PALADIUM_ORE)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(18, JobType.MINER, "Casser %d minerais de paladium", 900, new ItemStack(PWorld.PALADIUM_ORE)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(19, JobType.MINER, "Casser %d minerais de paladium", 1000, new ItemStack(PWorld.PALADIUM_ORE)));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(20, JobType.MINER, "Casser %d minerais de paladium", 1150, new ItemStack(PWorld.PALADIUM_ORE)));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\registerer\jobs\type\MinerRegisterer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */