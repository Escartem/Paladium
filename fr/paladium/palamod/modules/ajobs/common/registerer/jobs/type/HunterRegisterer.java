package fr.paladium.palamod.modules.ajobs.common.registerer.jobs.type;

import fr.paladium.mount.core.blocks.BlocksRegister;
import fr.paladium.mount.core.items.ItemsRegister;
import fr.paladium.palajobs.api.reward.IReward;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.RewardType;
import fr.paladium.palajobs.core.jobs.AbstractJob;
import fr.paladium.palajobs.core.jobs.Requirement;
import fr.paladium.palajobs.core.jobs.requirement.EntityKillRequirement;
import fr.paladium.palajobs.core.jobs.requirement.FishingRequirement;
import fr.paladium.palajobs.core.jobs.type.HunterJob;
import fr.paladium.palajobs.core.pojo.objectives.AbstractObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.EntityKillObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.EntityKillSpecialObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.FishingObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.SmeltObjective;
import fr.paladium.palajobs.core.pojo.rewards.Reward;
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
import fr.paladium.palamod.modules.back2future.ModItems;
import fr.paladium.palamod.modules.back2future.entities.EntityNewSnowGolem;
import fr.paladium.palamod.modules.back2future.entities.EntityRabbit;
import fr.paladium.palamod.modules.hunter.PHunter;
import fr.paladium.palamod.modules.hunter.entites.EntityCrab;
import fr.paladium.palamod.modules.hunter.entites.EntityDolphin;
import fr.paladium.palamod.modules.hunter.entites.EntityElephant;
import fr.paladium.palamod.modules.hunter.entites.EntityGoat;
import fr.paladium.palamod.modules.hunter.entites.EntityJellyFish;
import fr.paladium.palamod.modules.hunter.entites.EntityMegaCreeper;
import fr.paladium.palamod.modules.hunter.entites.EntityPanda;
import fr.paladium.palamod.modules.hunter.entites.EntityParrot;
import fr.paladium.palamod.modules.hunter.entites.EntitySnail;
import fr.paladium.palamod.modules.hunter.entites.EntitySnake;
import fr.paladium.palamod.modules.hunter.entites.EntityTurtle;
import fr.paladium.palamod.modules.pillage.registerer.PPRegisterer;
import fr.paladium.palaspawner.common.registry.SpawnerBlockRegistry;
import fr.paladium.palaspawner.common.registry.SpawnerItemRegistry;
import fr.paladium.pet.common.registry.impl.PetBlockRegistry;
import fr.welsymc.guardiangolem.common.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;

public class HunterRegisterer extends JobRegisterer {
  public HunterRegisterer(HunterJob job) {
    super((AbstractJob)job);
  }
  
  public void init() {
    JobRegistry.getInstance().setHunterJob((HunterJob)this.job);
  }
  
  public void registerObjectives() {
    EntityKillObjective entityKillObjective = new EntityKillObjective(this.job.getType());
    entityKillObjective.add(getJob().buildValue(14.0D, 0), EntityCow.class);
    entityKillObjective.add(getJob().buildValue(14.0D, 0), EntitySheep.class);
    entityKillObjective.add(getJob().buildValue(14.0D, 0), EntityChicken.class);
    entityKillObjective.add(getJob().buildValue(10.0D, 0), EntitySquid.class);
    entityKillObjective.add(getJob().buildValue(14.0D, 0), EntityRabbit.class);
    entityKillObjective.add(getJob().buildValue(14.0D, 0), EntityPig.class);
    entityKillObjective.add(getJob().buildValue(14.0D, 0), EntityHorse.class);
    SmeltObjective smeltObjective = new SmeltObjective(this.job.getType());
    smeltObjective.add(getJob().buildValue(15.0D, 0), new ItemStack(Items.field_151101_aQ, 1, ItemFishFood.FishType.COD.ordinal()));
    smeltObjective.add(getJob().buildValue(15.0D, 0), new ItemStack(Items.field_151101_aQ, 1, ItemFishFood.FishType.SALMON.ordinal()));
    smeltObjective.add(getJob().buildValue(10.0D, 0), new ItemStack(Items.field_151157_am, 1, 0));
    smeltObjective.add(getJob().buildValue(10.0D, 0), new ItemStack(Items.field_151083_be, 1, 0));
    smeltObjective.add(getJob().buildValue(10.0D, 0), new ItemStack(Items.field_151077_bg, 1, 0));
    smeltObjective.add(getJob().buildValue(10.0D, 0), new ItemStack(ModItems.cooked_mutton, 1, 0));
    entityKillObjective.add(getJob().buildValue(8.0D, 13), EntitySpider.class);
    entityKillObjective.add(getJob().buildValue(15.0D, 7), EntityZombie.class);
    entityKillObjective.add(getJob().buildValue(40.0D, 0), EntityCreeper.class);
    entityKillObjective.add(getJob().buildValue(20.0D, 11), EntitySkeleton.class);
    entityKillObjective.add(getJob().buildValue(25.0D, 14), EntityBlaze.class);
    entityKillObjective.add(getJob().buildValue(35.0D, 14), EntityWitch.class);
    entityKillObjective.add(getJob().buildValue(15.0D, 18), EntityCaveSpider.class);
    entityKillObjective.add(getJob().buildValue(1.0D, 0), EntityNewSnowGolem.class);
    FishingObjective fishingObjective = new FishingObjective(this.job.getType());
    fishingObjective.add(getJob().buildValue(25.0D, 0), new ItemStack(Items.field_151115_aP, 1, ItemFishFood.FishType.COD.ordinal()));
    fishingObjective.add(getJob().buildValue(35.0D, 0), new ItemStack(Items.field_151115_aP, 1, ItemFishFood.FishType.SALMON.ordinal()));
    fishingObjective.add(getJob().buildValue(200.0D, 0), new ItemStack(Items.field_151115_aP, 1, ItemFishFood.FishType.CLOWNFISH.ordinal()));
    fishingObjective.add(getJob().buildValue(75.0D, 0), new ItemStack(Items.field_151115_aP, 1, ItemFishFood.FishType.PUFFERFISH.ordinal()));
    fishingObjective.add(getJob().buildValue(150.0D, 2), new ItemStack(ItemsRegistry.CARP_FISH));
    fishingObjective.add(getJob().buildValue(225.0D, 2), new ItemStack(ItemsRegistry.BASS_FISH));
    fishingObjective.add(getJob().buildValue(300.0D, 2), new ItemStack(ItemsRegistry.RAY_FISH));
    fishingObjective.add(getJob().buildValue(450.0D, 2), new ItemStack(ItemsRegistry.RED_TUNA_FISH));
    fishingObjective.add(getJob().buildValue(550.0D, 2), new ItemStack(ItemsRegistry.MOON_FISH));
    fishingObjective.add(getJob().buildValue(750.0D, 3), new ItemStack(ItemsRegistry.EXP_FISH));
    fishingObjective.add(getJob().buildValue(10000.0D, 4), new ItemStack(ItemsRegistry.WHALE_FISH));
    fishingObjective.add(getJob().buildValue(15000.0D, 4), new ItemStack(ItemsRegistry.KRAKEN_FISH));
    entityKillObjective.add(getJob().buildValue(20.0D, 0), EntityGoat.class);
    entityKillObjective.add(getJob().buildValue(1000.0D, 0), EntityWither.class);
    entityKillObjective.add(getJob().buildValue(40.0D, 8), EntityTurtle.class);
    entityKillObjective.add(getJob().buildValue(60.0D, 9), EntityPanda.class);
    entityKillObjective.add(getJob().buildValue(35.0D, 5), EntityDolphin.class);
    entityKillObjective.add(getJob().buildValue(30.0D, 3), EntityParrot.class);
    entityKillObjective.add(getJob().buildValue(80.0D, 12), EntityCrab.class);
    entityKillObjective.add(getJob().buildValue(25.0D, 2), EntitySnail.class);
    entityKillObjective.add(getJob().buildValue(15.0D, 8), EntityElephant.class);
    entityKillObjective.add(getJob().buildValue(120.0D, 15), EntitySnake.class);
    entityKillObjective.add(getJob().buildValue(150.0D, 18), EntityJellyFish.class);
    EntityKillSpecialObjective entityKillSpecialObjective = new EntityKillSpecialObjective(this.job.getType());
    entityKillSpecialObjective.add(getJob().buildValue(0.0D, 7), EntityMegaCreeper.class);
    entityKillSpecialObjective.add(getJob().buildValue(0.0D, 11), EntityElephant.class);
    this.job.addObjectives(new AbstractObjective[] { (AbstractObjective)entityKillObjective, (AbstractObjective)fishingObjective, (AbstractObjective)smeltObjective, (AbstractObjective)entityKillSpecialObjective });
  }
  
  public void registerRewards() {
    this.job.addReward(Integer.valueOf(2), new IReward[] { (IReward)Reward.builder().level(2).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 10)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(2), new IReward[] { (IReward)Reward.builder().level(2).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 10)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(2), new IReward[] { (IReward)Reward.builder().level(2).moneyAmount(Double.valueOf(1000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(3), new IReward[] { (IReward)Reward.builder().level(3).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 15)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(3), new IReward[] { (IReward)Reward.builder().level(3).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 15)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(3), new IReward[] { (IReward)Reward.builder().level(3).moneyAmount(Double.valueOf(1500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(3), new IReward[] { (IReward)Reward.builder().level(3).rewardItem(new ItemStack(ItemsRegister.TITANE_INGOT, 5)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(4), new IReward[] { (IReward)Reward.builder().level(4).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 20)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(4), new IReward[] { (IReward)Reward.builder().level(4).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 20)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(4), new IReward[] { (IReward)Reward.builder().level(4).moneyAmount(Double.valueOf(2000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(5), new IReward[] { (IReward)Reward.builder().level(5).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 25)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(5), new IReward[] { (IReward)Reward.builder().level(5).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 25)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(5), new IReward[] { (IReward)Reward.builder().level(5).moneyAmount(Double.valueOf(2500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(5), new IReward[] { (IReward)Reward.builder().level(5).rewardItem(new ItemStack(ItemsRegister.DIAMOND_STRING, 5)).type(RewardType.GIVE).build() });
    ItemStack enchantedSword = new ItemStack(ItemsRegister.PALADIUM_SWORD);
    enchantedSword.func_77966_a(Enchantment.field_77339_k, 5);
    enchantedSword.func_77966_a(Enchantment.field_77347_r, 3);
    this.job.addReward(Integer.valueOf(6), new IReward[] { (IReward)Reward.builder().level(6).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 30)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(6), new IReward[] { (IReward)Reward.builder().level(6).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 30)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(6), new IReward[] { (IReward)Reward.builder().level(6).moneyAmount(Double.valueOf(3000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(6), new IReward[] { (IReward)Reward.builder().level(6).rewardItem(enchantedSword).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(7), new IReward[] { (IReward)Reward.builder().level(7).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 35)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(7), new IReward[] { (IReward)Reward.builder().level(7).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 35)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(7), new IReward[] { (IReward)Reward.builder().level(7).moneyAmount(Double.valueOf(3500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(8), new IReward[] { (IReward)Reward.builder().level(8).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 40)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(8), new IReward[] { (IReward)Reward.builder().level(8).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 40)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(8), new IReward[] { (IReward)Reward.builder().level(8).moneyAmount(Double.valueOf(4000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(8), new IReward[] { (IReward)Reward.builder().level(8).rewardItem(new ItemStack(ItemsRegister.AMETHYST_INGOT, 16)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(9), new IReward[] { (IReward)Reward.builder().level(9).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 45)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(9), new IReward[] { (IReward)Reward.builder().level(9).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 45)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(9), new IReward[] { (IReward)Reward.builder().level(9).moneyAmount(Double.valueOf(4500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(10), new IReward[] { (IReward)Reward.builder().level(10).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 50)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(10), new IReward[] { (IReward)Reward.builder().level(10).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 50)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(10), new IReward[] { (IReward)Reward.builder().level(10).moneyAmount(Double.valueOf(5000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(10), new IReward[] { (IReward)Reward.builder().level(10).rewardItem(new ItemStack(ItemsRegister.BOWUPGRADE_EMPTY, 2)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 55)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 55)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).moneyAmount(Double.valueOf(5500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(12), new IReward[] { (IReward)Reward.builder().level(12).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 60)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(12), new IReward[] { (IReward)Reward.builder().level(12).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 60)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(12), new IReward[] { (IReward)Reward.builder().level(12).moneyAmount(Double.valueOf(6000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(12), new IReward[] { (IReward)Reward.builder().level(12).rewardItem(new ItemStack(Items.field_151072_bj, 16)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(13), new IReward[] { (IReward)Reward.builder().level(13).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 65)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(13), new IReward[] { (IReward)Reward.builder().level(13).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 65)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(13), new IReward[] { (IReward)Reward.builder().level(13).moneyAmount(Double.valueOf(6500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(14), new IReward[] { (IReward)Reward.builder().level(14).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 70)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(14), new IReward[] { (IReward)Reward.builder().level(14).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 70)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(14), new IReward[] { (IReward)Reward.builder().level(14).moneyAmount(Double.valueOf(7000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(15), new IReward[] { (IReward)Reward.builder().level(15).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 75)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(15), new IReward[] { (IReward)Reward.builder().level(15).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 75)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(15), new IReward[] { (IReward)Reward.builder().level(15).moneyAmount(Double.valueOf(7500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(16), new IReward[] { (IReward)Reward.builder().level(16).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 80)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(16), new IReward[] { (IReward)Reward.builder().level(16).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 80)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(16), new IReward[] { (IReward)Reward.builder().level(16).moneyAmount(Double.valueOf(8000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(16), new IReward[] { (IReward)Reward.builder().level(16).rewardItem(new ItemStack(ItemsRegister.TUSK)).type(RewardType.GIVE).build() });
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
    this.job.addReward(Integer.valueOf(19), new IReward[] { (IReward)Reward.builder().level(19).rewardItem(new ItemStack(Items.field_151144_bL, 1, 5)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(20), new IReward[] { (IReward)Reward.builder().level(20).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 105)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(20), new IReward[] { (IReward)Reward.builder().level(20).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 105)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(20), new IReward[] { (IReward)Reward.builder().level(20).moneyAmount(Double.valueOf(10500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(20), new IReward[] { (IReward)Reward.builder().level(20).rewardItem(new ItemStack(ItemsRegister.ENDIUM_NUGGET)).type(RewardType.GIVE).build() });
  }
  
  public void registerBlacklistedCrafts() {
    this.job.addBlacklistedCraft(2, new ItemStack(ItemsRegistry.BAMBOO_BOAT));
    this.job.addBlacklistedCraft(3, new ItemStack((Block)PetBlockRegistry.PET_CAGE));
    this.job.addBlacklistedCraft(4, new ItemStack(ItemsRegistry.PALADIUM_FISHING_ROD));
    this.job.addBlacklistedCraft(5, new ItemStack((Item)SpawnerItemRegistry.CAVERN_HAMMER));
    this.job.addBlacklistedCraft(6, new ItemStack(SpawnerBlockRegistry.EMPTY_MOB_SPAWNER));
    this.job.addBlacklistedCraft(7, new ItemStack(ItemInit.MINI_GOLEM));
    this.job.addBlacklistedCraft(8, new ItemStack((Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_ONE));
    this.job.addBlacklistedCraft(9, new ItemStack((Item)ItemsRegister.INFERNAL_KNOCKER));
    this.job.addBlacklistedCraft(10, new ItemStack(ItemsRegister.SPAWNER_FINDER));
    this.job.addBlacklistedCraft(12, new ItemStack((Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_TWO));
    this.job.addBlacklistedCraft(13, new ItemStack(ItemsRegister.CAPTURE_SWORD));
    this.job.addBlacklistedCraft(14, new ItemStack(ItemsRegister.CAPTURE_STONE));
    this.job.addBlacklistedCraft(15, new ItemStack((Item)SpawnerItemRegistry.UPGRADE_LOOTING));
    this.job.addBlacklistedCraft(16, new ItemStack((Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_THREE));
    this.job.addBlacklistedCraft(17, new ItemStack(ItemsRegister.PALADIUM_KEY));
    this.job.addBlacklistedCraft(18, new ItemStack(ItemsRegister.ENDIUM_NAMETAG));
    this.job.addBlacklistedCraft(19, new ItemStack(BlocksRegistry.HUNTER_THRONE));
    this.job.addBlacklistedCraft(20, new ItemStack((Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_FOUR));
    this.job.addBlacklistedCraft(20, new ItemStack(ItemsRegister.HUNTER_COMPASS, 1, 3));
    this.job.addBlacklistedCraft(20, new ItemStack(ItemsRegister.ENDIUM_HELMET));
    this.job.addBlacklistedCraft(20, new ItemStack(ItemsRegister.ENDIUM_SWORD));
    this.job.addBlacklistedCraft(20, new ItemStack(BlocksRegister.ENDIUM_TOTEM));
    this.job.addBlacklistedCraft(20, new ItemStack(BlocksRegister.SACRIFICE_HOTEL));
    this.job.addBlacklistedCraft(20, new ItemStack(BlocksRegister.PERLINPINPIN_POWER));
    this.job.addBlacklistedCraft(20, new ItemStack(ItemsRegister.WITHER_DUST));
  }
  
  public void registerBlacklistedUsages() {
    this.job.addBlacklistedUsage(2, new ItemStack(ItemsRegistry.BAMBOO_BOAT));
    this.job.addBlacklistedUsage(3, new ItemStack((Block)PetBlockRegistry.PET_CAGE));
    this.job.addBlacklistedUsage(4, new ItemStack(ItemsRegistry.PALADIUM_FISHING_ROD));
    this.job.addBlacklistedUsage(5, new ItemStack((Item)SpawnerItemRegistry.CAVERN_HAMMER));
    this.job.addBlacklistedUsage(6, new ItemStack(SpawnerBlockRegistry.EMPTY_MOB_SPAWNER));
    this.job.addBlacklistedUsage(7, new ItemStack(ItemInit.MINI_GOLEM));
    this.job.addBlacklistedUsage(13, new ItemStack(ItemsRegister.CAPTURE_SWORD));
    this.job.addBlacklistedUsage(14, new ItemStack(ItemsRegister.CAPTURE_STONE));
    this.job.addBlacklistedUsage(15, new ItemStack((Item)SpawnerItemRegistry.UPGRADE_LOOTING));
    this.job.addBlacklistedUsage(17, new ItemStack(ItemsRegister.PALADIUM_KEY));
    this.job.addBlacklistedUsage(20, new ItemStack(ItemsRegister.HUNTER_COMPASS, 1, 3));
    this.job.addBlacklistedUsage(20, new ItemStack(ItemsRegister.ENDIUM_HELMET));
    this.job.addBlacklistedUsage(20, new ItemStack(ItemsRegister.ENDIUM_SWORD));
    this.job.addBlacklistedUsage(20, new ItemStack(BlocksRegister.ENDIUM_TOTEM));
    this.job.addBlacklistedUsage(20, new ItemStack(BlocksRegister.SACRIFICE_HOTEL));
    this.job.addBlacklistedUsage(20, new ItemStack(BlocksRegister.PERLINPINPIN_POWER));
    this.job.addBlacklistedUsage(20, new ItemStack(BlocksRegistry.HUNTER_THRONE));
  }
  
  public void registerLvlTokensRewards() {
    LvlTokenRegistry.register(new LvlTokenReward(2, "2-common-fishing-rod-1", "1 Fishing Rod", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack((Item)Items.field_151112_aM), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(2, "2-rare-raw-fish-8", "8 Raw Fish", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(Items.field_151115_aP), 8, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(2, "2-EPIC-raw-salmon-8", "8 Raw Salmon", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(Items.field_151115_aP, 1, 1), 8, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(4, "4-common-paladium-ingot-1", "1 Paladium Ingot", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.PALADIUM_INGOT), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(4, "4-rare-paladium-stick-2", "2 Paladium Sticks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(ItemsRegister.STICK_PALADIUM), 2, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(4, "4-EPIC-paladium-block-1", "1 Paladium Block", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(BlocksRegister.BLOCK_PALADIUM), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(6, "6-common-paladium-ingot-8", "8 Paladium Ingots", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.PALADIUM_INGOT), 8, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(6, "6-rare-paladium-block-2", "2 Paladium Blocks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(BlocksRegister.BLOCK_PALADIUM), 2, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(6, "6-EPIC-paladium-block-5", "5 Paladium Blocks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(BlocksRegister.BLOCK_PALADIUM), 5, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(8, "8-common-ghast-tear-8", "8 Ghast Tears", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(Items.field_151073_bk), 8, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(8, "8-rare-paladium-ingot-12", "12 Paladium Ingots", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(ItemsRegister.PALADIUM_INGOT), 12, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(8, "8-EPIC-findium-2", "2 Findiums", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(ItemsRegister.FINDIUM), 2, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(10, "10-common-diamond-string-3", "3 Diamond Strings", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.DIAMOND_STRING), 3, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(10, "10-rare-paladium-bow-1", "1 Paladium Bow", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack((Item)ItemsRegister.PALADIUM_BOW), 1, this.job.getType()));
    ItemStack lvl10 = new ItemStack((Item)Items.field_151134_bR);
    Items.field_151134_bR.func_92115_a(lvl10, new EnchantmentData(Enchantment.field_77345_t, 4));
    LvlTokenRegistry.register(new LvlTokenReward(10, "10-EPIC-power-4-2", "2 Power 4", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, lvl10, 2, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(11, "11-common-empty-bow-modifier-1", "1 Empty Bow Modifier", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.BOWUPGRADE_EMPTY), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(11, "11-rare-paladium-key-8", "8 Paladium Keys", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(ItemsRegister.PALADIUM_KEY), 8, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(11, "11-EPIC-paladium-compressed-2", "2 Paladium Compressed", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), 2, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(12, "12-common-capture-stone-1", "1 Capture Stone", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.CAPTURE_STONE), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(12, "12-rare-paladium-compressed-2", "2 Paladium Compressed", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), 2, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(12, "12-EPIC-double-xp-potion-30min-1", "1 Double XP Potion (30 minutes)", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 0), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(13, "13-common-enclosure-foundation-3", "3 Enclosure Foundations", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(BlocksRegister.BARN_FOUNDATION), 3, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(13, "13-rare-enclosure-manager-1", "1 Enclosure Manager", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(BlocksRegister.BARN_CONTROLLER), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(13, "13-EPIC-enclosure-barrier-6", "6 Enclosure Barriers", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(BlocksRegister.BARN_FENCE), 6, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(14, "14-common-skeleton-skull-3", "3 Skeleton Skulls", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(Blocks.field_150465_bP), 3, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(14, "14-rare-wither-skull-3", "3 Wither Skull Fragments", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(ItemsRegister.WITHER_SKULLFRAGMENT), 3, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(14, "14-EPIC-wither-saddle-1", "1 Wither Saddle", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(PPRegisterer.PPItems.WITHER_SADDLE), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(15, "15-common-elephant-defense-1", "1 Elephant Defense", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.TUSK), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(15, "15-rare-titanium-horn-1", "1 Titanium Horn", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(ItemsRegister.TITANE_HORN), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(15, "15-EPIC-double-xp-potion-1h-1", "1 Double XP Potion (1 hour)", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 1), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(16, "16-common-elephant-defense-2", "2 Elephant Defenses", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.TUSK), 2, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(16, "16-rare-double-xp-potion-1h-1", "1 Double XP Potion (1 hour)", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 1), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(16, "16-EPIC-paladium-horn-1", "1 Paladium Horn", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 1), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(17, "17-common-paladium-key-3", "16 Paladium Keys", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.PALADIUM_KEY), 16, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(17, "17-rare-green-paladium-block-6", "6 Green Paladium Blocks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(BlocksRegister.BLOCK_GPALADIUM), 6, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(17, "17-EPIC-unboxing-candy-1", "1 Unboxing Candy", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(ItemsRegister.JOB_CANDY), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(18, "18-rare-findium-8", "8 Findiums", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(ItemsRegister.FINDIUM), 8, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(18, "18-EPIC-findium-block-2", "2 Findium Blocks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(BlocksRegister.BLOCK_FINDIUM), 2, this.job.getType()));
    ItemStack lvl19 = new ItemStack((Item)Items.field_151134_bR);
    Items.field_151134_bR.func_92115_a(lvl19, new EnchantmentData((Enchantment)PHunter.overwhelmed, 2));
    LvlTokenRegistry.register(new LvlTokenReward(19, "19-rare-overwhelmed-ii-1", "1 Overwhelmed II", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, lvl19, 1, this.job.getType()));
    ItemStack lvl19bis = new ItemStack(ItemsRegister.PALADIUM_SWORD);
    lvl19bis.func_77966_a(Enchantment.field_77338_j, 5);
    LvlTokenRegistry.register(new LvlTokenReward(19, "19-EPIC-mending-paladium-sword-1", "1 Mending Paladium Sharpness 5 Sword", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, lvl19bis, 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(20, "20-EPIC-endium-fragment-4", "4 Endium Fragments", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(ItemsRegister.ENDIUM_FRAGMENT), 4, this.job.getType()));
  }
  
  public void registerRequirements() {
    this.job.addRequirement((Requirement)new EntityKillRequirement(2, JobType.HUNTER, "Tuer %d animaux", 450, new Class[] { EntityCow.class, EntitySheep.class, EntityChicken.class, EntitySquid.class, EntityRabbit.class, EntityPig.class, EntityHorse.class }));
    this.job.addRequirement((Requirement)new EntityKillRequirement(3, JobType.HUNTER, "Tuer %d animaux", 800, new Class[] { EntityCow.class, EntitySheep.class, EntityChicken.class, EntitySquid.class, EntityRabbit.class, EntityPig.class, EntityHorse.class }));
    this.job.addRequirement((Requirement)new EntityKillRequirement(4, JobType.HUNTER, "Tuer %d animaux", 1450, new Class[] { EntityCow.class, EntitySheep.class, EntityChicken.class, EntitySquid.class, EntityRabbit.class, EntityPig.class, EntityHorse.class }));
    this.job.addRequirement((Requirement)new FishingRequirement(5, JobType.HUNTER, "Pêcher %d poissons", 950));
    this.job.addRequirement((Requirement)new FishingRequirement(6, JobType.HUNTER, "Pêcher %d poissons", 1400));
    this.job.addRequirement((Requirement)new FishingRequirement(7, JobType.HUNTER, "Pêcher %d poissons", 2000));
    this.job.addRequirement((Requirement)new FishingRequirement(8, JobType.HUNTER, "Pêcher %d poissons", 2700));
    this.job.addRequirement((Requirement)new EntityKillRequirement(9, JobType.HUNTER, "Tuer %d zombies", 5750, EntityZombie.class));
    this.job.addRequirement((Requirement)new EntityKillRequirement(10, JobType.HUNTER, "Tuer %d zombies", 7600, EntityZombie.class));
    this.job.addRequirement((Requirement)new EntityKillRequirement(11, JobType.HUNTER, "Tuer %d zombies", 9000, EntityZombie.class));
    this.job.addRequirement((Requirement)new EntityKillRequirement(12, JobType.HUNTER, "Tuer %d squelettes", 8200, EntitySkeleton.class));
    this.job.addRequirement((Requirement)new EntityKillRequirement(13, JobType.HUNTER, "Tuer %d squelettes", 9800, EntitySkeleton.class));
    this.job.addRequirement((Requirement)new EntityKillRequirement(14, JobType.HUNTER, "Tuer %d squelettes", 11600, EntitySkeleton.class));
    this.job.addRequirement((Requirement)new EntityKillRequirement(15, JobType.HUNTER, "Tuer %d sorcières", 7750, EntityWitch.class));
    this.job.addRequirement((Requirement)new EntityKillRequirement(16, JobType.HUNTER, "Tuer %d sorcières", 8950, EntityWitch.class));
    this.job.addRequirement((Requirement)new EntityKillRequirement(17, JobType.HUNTER, "Tuer %d sorcières", 10250, EntityWitch.class));
    this.job.addRequirement((Requirement)new EntityKillRequirement(18, JobType.HUNTER, "Tuer %d sorcières", 11700, EntityWitch.class));
    this.job.addRequirement((Requirement)new EntityKillRequirement(19, JobType.HUNTER, "Tuer %d sorcières", 13200, EntityWitch.class));
    this.job.addRequirement((Requirement)new EntityKillRequirement(20, JobType.HUNTER, "Tuer %d sorcières", 14800, EntityWitch.class));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\registerer\jobs\type\HunterRegisterer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */