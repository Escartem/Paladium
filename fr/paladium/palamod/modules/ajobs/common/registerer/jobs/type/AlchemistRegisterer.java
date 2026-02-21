package fr.paladium.palamod.modules.ajobs.common.registerer.jobs.type;

import fr.paladium.palajobs.api.reward.IReward;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.RewardType;
import fr.paladium.palajobs.core.jobs.AbstractJob;
import fr.paladium.palajobs.core.jobs.Requirement;
import fr.paladium.palajobs.core.jobs.requirement.BreakBlockRequirement;
import fr.paladium.palajobs.core.jobs.type.AlchemistJob;
import fr.paladium.palajobs.core.pojo.objectives.AbstractObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.BreakBlockObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.CraftCauldronObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.CraftObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.CraftPortalObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.DropCauldronObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.EntityKillSpecialObjective;
import fr.paladium.palajobs.core.pojo.rewards.Reward;
import fr.paladium.palajobs.core.registry.JobRegistry;
import fr.paladium.palajobs.core.tokens.LvlTokenRegistry;
import fr.paladium.palajobs.core.tokens.rewards.EnumLvlTokenRewardCategory;
import fr.paladium.palajobs.core.tokens.rewards.EnumLvlTokenRewardRarity;
import fr.paladium.palajobs.core.tokens.rewards.LvlTokenReward;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.ajobs.common.objectives.ExtractSeveObjective;
import fr.paladium.palamod.modules.ajobs.common.registerer.BlocksRegistry;
import fr.paladium.palamod.modules.ajobs.common.registerer.jobs.JobRegisterer;
import fr.paladium.palamod.modules.ajobs.common.registerer.requirement.CraftPortalRequirement;
import fr.paladium.palamod.modules.ajobs.common.registerer.requirement.DropCauldronRequirement;
import fr.paladium.palamod.modules.ajobs.common.registerer.requirement.ExtractSeveRequirement;
import fr.paladium.palamod.modules.alchimiste.common.init.EnchantMod;
import fr.paladium.palamod.modules.hunter.entites.EntityFlowerMonster;
import fr.paladium.palamod.modules.pillage.registerer.PPRegisterer;
import fr.paladium.palamod.modules.world.PWorld;
import fr.paladium.palaspawner.common.registry.SpawnerItemRegistry;
import fr.paladium.palawither.api.ItemsRegister;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AlchemistRegisterer extends JobRegisterer {
  public AlchemistRegisterer(AlchemistJob job) {
    super((AbstractJob)job);
  }
  
  public void init() {
    JobRegistry.getInstance().setAlchemistJob((AlchemistJob)this.job);
  }
  
  public void registerObjectives() {
    BreakBlockObjective breakBlockObjective = new BreakBlockObjective(this.job.getType());
    breakBlockObjective.add(getJob().buildValue(10.0D, 0), new ItemStack(PWorld.LOG_JACARANDA));
    breakBlockObjective.add(getJob().buildValue(10.0D, 0), new ItemStack(PWorld.LOG_JUDEECERCIS));
    breakBlockObjective.add(getJob().buildValue(10.0D, 0), new ItemStack(PWorld.LOG_ERABLE));
    CraftObjective craftObjective = new CraftObjective(this.job.getType());
    craftObjective.add(getJob().buildValue(0.2D, 0), new ItemStack((Item)ItemsRegister.FLASK));
    craftObjective.add(getJob().buildValue(20.0D, 0), new ItemStack((Block)BlocksRegister.EXTRACTOR));
    craftObjective.add(getJob().buildValue(30.0D, 0), new ItemStack((Item)ItemsRegister.LIGHTNING_POTION));
    CraftPortalObjective craftPortalObjective = new CraftPortalObjective(this.job.getType());
    craftPortalObjective.add(getJob().buildValue(6.0D, 3), new ItemStack(ItemsRegister.AMETHYST_INGOT));
    craftPortalObjective.add(getJob().buildValue(20.0D, 6), new ItemStack(ItemsRegister.TITANE_INGOT));
    craftPortalObjective.add(getJob().buildValue(40.0D, 10), new ItemStack(ItemsRegister.PALADIUM_INGOT));
    CraftCauldronObjective craftCauldronObjective = new CraftCauldronObjective(this.job.getType());
    craftCauldronObjective.add(getJob().buildValue(2.0D, 6), new ItemStack((Item)ItemsRegister.BLUE_GLUEBALL));
    craftCauldronObjective.add(getJob().buildValue(15.0D, 10), new ItemStack((Item)ItemsRegister.CYAN_GLUEBALL));
    craftCauldronObjective.add(getJob().buildValue(15.0D, 10), new ItemStack((Item)ItemsRegister.GRAY_GLUEBALL));
    craftCauldronObjective.add(getJob().buildValue(15.0D, 10), new ItemStack((Item)ItemsRegister.GREEN_DARK_GLUEBALL));
    craftCauldronObjective.add(getJob().buildValue(15.0D, 10), new ItemStack((Item)ItemsRegister.GREEN_FLASH_GLUEBALL));
    craftCauldronObjective.add(getJob().buildValue(2.0D, 6), new ItemStack((Item)ItemsRegister.GREEN_GLUEBALL));
    craftCauldronObjective.add(getJob().buildValue(15.0D, 10), new ItemStack((Item)ItemsRegister.ORANGE_GLUEBALL));
    craftCauldronObjective.add(getJob().buildValue(15.0D, 10), new ItemStack((Item)ItemsRegister.PURPLE_GLUEBALL));
    craftCauldronObjective.add(getJob().buildValue(2.0D, 6), new ItemStack((Item)ItemsRegister.RED_GLUEBALL));
    craftCauldronObjective.add(getJob().buildValue(15.0D, 10), new ItemStack((Item)ItemsRegister.YELLOW_GLUEBALL));
    ExtractSeveObjective extractSeveObjective = new ExtractSeveObjective(this.job.getType());
    extractSeveObjective.add(getJob().buildValue(15.0D, 0), new ItemStack(PWorld.LOG_JACARANDA));
    extractSeveObjective.add(getJob().buildValue(40.0D, 5), new ItemStack(PWorld.LOG_JUDEECERCIS));
    extractSeveObjective.add(getJob().buildValue(80.0D, 10), new ItemStack(PWorld.LOG_ERABLE));
    DropCauldronObjective dropCauldronObjective = new DropCauldronObjective(this.job.getType());
    dropCauldronObjective.add(getJob().buildValue(1.0D, 3), new ItemStack((Block)Blocks.field_150327_N, 1, 0));
    dropCauldronObjective.add(getJob().buildValue(1.0D, 3), new ItemStack((Block)Blocks.field_150328_O, 1, 0));
    dropCauldronObjective.add(getJob().buildValue(1.0D, 3), new ItemStack((Block)Blocks.field_150328_O, 1, 1));
    dropCauldronObjective.add(getJob().buildValue(2.0D, 3), new ItemStack((Block)Blocks.field_150328_O, 1, 2));
    dropCauldronObjective.add(getJob().buildValue(2.0D, 3), new ItemStack((Block)Blocks.field_150328_O, 1, 3));
    dropCauldronObjective.add(getJob().buildValue(2.0D, 3), new ItemStack((Block)Blocks.field_150328_O, 1, 4));
    dropCauldronObjective.add(getJob().buildValue(2.0D, 3), new ItemStack((Block)Blocks.field_150328_O, 1, 5));
    dropCauldronObjective.add(getJob().buildValue(2.0D, 3), new ItemStack((Block)Blocks.field_150328_O, 1, 6));
    dropCauldronObjective.add(getJob().buildValue(2.0D, 3), new ItemStack((Block)Blocks.field_150328_O, 1, 7));
    dropCauldronObjective.add(getJob().buildValue(2.0D, 3), new ItemStack((Block)Blocks.field_150328_O, 1, 8));
    dropCauldronObjective.add(getJob().buildValue(100.0D, 16), new ItemStack(BlocksRegister.FLOWER_PALADIUM));
    EntityKillSpecialObjective entityKillSpecialObjective = new EntityKillSpecialObjective(this.job.getType());
    entityKillSpecialObjective.add(getJob().buildValue(0.0D, 7), EntityFlowerMonster.class);
    this.job.addObjectives(new AbstractObjective[] { (AbstractObjective)breakBlockObjective, (AbstractObjective)craftObjective, (AbstractObjective)craftPortalObjective, (AbstractObjective)craftCauldronObjective, (AbstractObjective)extractSeveObjective, (AbstractObjective)dropCauldronObjective, (AbstractObjective)entityKillSpecialObjective });
  }
  
  public void registerRewards() {
    this.job.addReward(Integer.valueOf(2), new IReward[] { (IReward)Reward.builder().level(2).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 10)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(2), new IReward[] { (IReward)Reward.builder().level(2).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 10)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(2), new IReward[] { (IReward)Reward.builder().level(2).moneyAmount(Double.valueOf(1000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(3), new IReward[] { (IReward)Reward.builder().level(3).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 15)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(3), new IReward[] { (IReward)Reward.builder().level(3).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 15)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(3), new IReward[] { (IReward)Reward.builder().level(3).moneyAmount(Double.valueOf(1500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(3), new IReward[] { (IReward)Reward.builder().level(3).rewardItem(new ItemStack(Items.field_151072_bj, 5)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(4), new IReward[] { (IReward)Reward.builder().level(4).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 20)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(4), new IReward[] { (IReward)Reward.builder().level(4).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 20)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(4), new IReward[] { (IReward)Reward.builder().level(4).moneyAmount(Double.valueOf(2000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(4), new IReward[] { (IReward)Reward.builder().level(4).rewardItem(new ItemStack(PWorld.LOG_JACARANDA, 64)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(5), new IReward[] { (IReward)Reward.builder().level(5).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 25)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(5), new IReward[] { (IReward)Reward.builder().level(5).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 25)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(5), new IReward[] { (IReward)Reward.builder().level(5).moneyAmount(Double.valueOf(2500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(5), new IReward[] { (IReward)Reward.builder().level(5).rewardItem(new ItemStack((Item)ItemsRegister.LIGHTNING_POTION, 5)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(6), new IReward[] { (IReward)Reward.builder().level(6).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 30)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(6), new IReward[] { (IReward)Reward.builder().level(6).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 30)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(6), new IReward[] { (IReward)Reward.builder().level(6).moneyAmount(Double.valueOf(3000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(6), new IReward[] { (IReward)Reward.builder().level(6).rewardItem(new ItemStack(PWorld.LOG_JUDEECERCIS, 64)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(7), new IReward[] { (IReward)Reward.builder().level(7).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 35)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(7), new IReward[] { (IReward)Reward.builder().level(7).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 35)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(7), new IReward[] { (IReward)Reward.builder().level(7).moneyAmount(Double.valueOf(3500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(8), new IReward[] { (IReward)Reward.builder().level(8).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 40)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(8), new IReward[] { (IReward)Reward.builder().level(8).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 40)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(8), new IReward[] { (IReward)Reward.builder().level(8).moneyAmount(Double.valueOf(4000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(8), new IReward[] { (IReward)Reward.builder().level(8).rewardItem(new ItemStack(Blocks.field_150449_bY, 64)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(9), new IReward[] { (IReward)Reward.builder().level(9).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 45)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(9), new IReward[] { (IReward)Reward.builder().level(9).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 45)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(9), new IReward[] { (IReward)Reward.builder().level(9).moneyAmount(Double.valueOf(4500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(10), new IReward[] { (IReward)Reward.builder().level(10).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 50)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(10), new IReward[] { (IReward)Reward.builder().level(10).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 50)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(10), new IReward[] { (IReward)Reward.builder().level(10).moneyAmount(Double.valueOf(5000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(10), new IReward[] { (IReward)Reward.builder().level(10).rewardItem(new ItemStack(PWorld.LEAVE_ERABLE, 64)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(10), new IReward[] { (IReward)Reward.builder().level(10).rewardItem(new ItemStack((Item)ItemsRegister.LIGHTNING_POTION, 16)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 55)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 55)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(11), new IReward[] { (IReward)Reward.builder().level(11).moneyAmount(Double.valueOf(5500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(12), new IReward[] { (IReward)Reward.builder().level(12).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 60)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(12), new IReward[] { (IReward)Reward.builder().level(12).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 60)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(12), new IReward[] { (IReward)Reward.builder().level(12).moneyAmount(Double.valueOf(6000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(12), new IReward[] { (IReward)Reward.builder().level(12).rewardItem(new ItemStack(PPRegisterer.PPBlocks.COMPRESSED_QUARTZ, 10)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(13), new IReward[] { (IReward)Reward.builder().level(13).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 65)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(13), new IReward[] { (IReward)Reward.builder().level(13).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 65)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(13), new IReward[] { (IReward)Reward.builder().level(13).moneyAmount(Double.valueOf(6500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(14), new IReward[] { (IReward)Reward.builder().level(14).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 70)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(14), new IReward[] { (IReward)Reward.builder().level(14).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 70)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(14), new IReward[] { (IReward)Reward.builder().level(14).moneyAmount(Double.valueOf(7000.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(15), new IReward[] { (IReward)Reward.builder().level(15).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 75)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(15), new IReward[] { (IReward)Reward.builder().level(15).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 75)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(15), new IReward[] { (IReward)Reward.builder().level(15).moneyAmount(Double.valueOf(7500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(15), new IReward[] { (IReward)Reward.builder().level(15).rewardItem(new ItemStack(ItemsRegister.EXTRAPOLATED_BUCKET, 2)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(16), new IReward[] { (IReward)Reward.builder().level(16).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 80)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(16), new IReward[] { (IReward)Reward.builder().level(16).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 80)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(16), new IReward[] { (IReward)Reward.builder().level(16).moneyAmount(Double.valueOf(8000.0D)).type(RewardType.MONEY_GIVE).build() });
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
    this.job.addReward(Integer.valueOf(19), new IReward[] { (IReward)Reward.builder().level(19).rewardItem(new ItemStack(Items.field_151144_bL, 1, 1)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(20), new IReward[] { (IReward)Reward.builder().level(20).rewardItem(new ItemStack(ItemsRegister.PALADIUM_INGOT, 105)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(20), new IReward[] { (IReward)Reward.builder().level(20).rewardItem(new ItemStack(ItemsRegister.TRIXIUM, 105)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(20), new IReward[] { (IReward)Reward.builder().level(20).moneyAmount(Double.valueOf(10500.0D)).type(RewardType.MONEY_GIVE).build() });
    this.job.addReward(Integer.valueOf(20), new IReward[] { (IReward)Reward.builder().level(20).rewardItem(new ItemStack(PWorld.LOG_OSTRYA, 64)).type(RewardType.GIVE).build() });
    this.job.addReward(Integer.valueOf(20), new IReward[] { (IReward)Reward.builder().level(20).rewardItem(new ItemStack(ItemsRegister.ENDIUM_NUGGET)).type(RewardType.GIVE).build() });
  }
  
  public void registerBlacklistedCrafts() {
    this.job.addBlacklistedCraft(2, new ItemStack((Block)BlocksRegister.TANK_GOLD));
    this.job.addBlacklistedCraft(3, new ItemStack((Block)BlocksRegister.ALCHEMY_CREATOR_BLOCK));
    this.job.addBlacklistedCraft(4, new ItemStack((Block)BlocksRegister.CAULDRON_BLOCK));
    this.job.addBlacklistedCraft(4, new ItemStack((Block)BlocksRegister.CAULDRON_CORE));
    this.job.addBlacklistedCraft(5, new ItemStack((Block)BlocksRegister.SHINY_JACARANDA_WOOD));
    this.job.addBlacklistedCraft(6, new ItemStack((Item)ItemsRegister.AMETHYSTE_PORTAL_KEY));
    this.job.addBlacklistedCraft(6, new ItemStack((Block)BlocksRegister.AMETHYSTE_PORTAL_BLOCK));
    this.job.addBlacklistedCraft(7, new ItemStack((Block)BlocksRegister.TANK_AMETHYSTE));
    this.job.addBlacklistedCraft(8, new ItemStack((Block)BlocksRegister.SHINY_JUDEECERCIS_WOOD));
    this.job.addBlacklistedCraft(8, new ItemStack(BlocksRegister.FLOWER_FARM));
    this.job.addBlacklistedCraft(9, new ItemStack((Item)ItemsRegister.TITANE_PORTAL_KEY));
    this.job.addBlacklistedCraft(9, new ItemStack((Block)BlocksRegister.TITANE_PORTAL_BLOCK));
    this.job.addBlacklistedCraft(10, new ItemStack((Block)BlocksRegister.TANK_TITANE));
    this.job.addBlacklistedCraft(11, new ItemStack((Block)BlocksRegister.SHINY_ERABLE_WOOD));
    this.job.addBlacklistedCraft(12, new ItemStack((Item)ItemsRegister.PALADIUM_PORTAL_KEY));
    this.job.addBlacklistedCraft(12, new ItemStack((Block)BlocksRegister.PALADIUM_PORTAL_BLOCK));
    this.job.addBlacklistedCraft(13, new ItemStack(ItemsRegister.THIRSTY_WITHER_HEAD));
    this.job.addBlacklistedCraft(14, new ItemStack(ItemsRegister.THIRSTY_WITHER_GEM));
    this.job.addBlacklistedCraft(15, new ItemStack((Item)SpawnerItemRegistry.UPGRADE_SLIME));
    this.job.addBlacklistedCraft(16, new ItemStack((Block)BlocksRegister.TANK_PALADIUM));
    this.job.addBlacklistedCraft(17, new ItemStack(ItemsRegister.PREDATOR_WITHER_HEAD));
    this.job.addBlacklistedCraft(18, new ItemStack(ItemsRegister.PREDATOR_WITHER_GEM));
    this.job.addBlacklistedCraft(18, new ItemStack((Item)SpawnerItemRegistry.UPGRADE_MORE));
    this.job.addBlacklistedCraft(19, new ItemStack(BlocksRegistry.ALCHEMIST_THRONE));
    this.job.addBlacklistedCraft(20, new ItemStack((Block)BlocksRegister.SHINY_OSTRYA_WOOD));
    this.job.addBlacklistedCraft(20, new ItemStack((Item)ItemsRegister.ENDIUM_PORTAL_KEY));
    this.job.addBlacklistedCraft(20, new ItemStack((Block)BlocksRegister.ENDIUM_PORTAL_BLOCK));
    this.job.addBlacklistedCraft(20, new ItemStack((Block)BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK));
    this.job.addBlacklistedCraft(20, new ItemStack(ItemsRegister.ENDIUM_LEGGINGS));
    this.job.addBlacklistedCraft(20, new ItemStack(ItemsRegister.ENDIUM_BOOTS));
    this.job.addBlacklistedCraft(20, new ItemStack(ItemsRegister.ENDIUM_POLLEN));
    this.job.addBlacklistedCraft(20, new ItemStack((Item)ItemsRegister.ENDIUM_HEART));
    this.job.addBlacklistedCraft(20, new ItemStack(ItemsRegister.SUPREME_WITHER_HEAD));
    this.job.addBlacklistedCraft(20, new ItemStack(ItemsRegister.SUPREME_WITHER_GEM));
    this.job.addBlacklistedCraft(20, new ItemStack(ItemsRegister.PASSIVE_WITHER_HEAD));
  }
  
  public void registerBlacklistedUsages() {
    this.job.addBlacklistedUsage(4, new ItemStack((Block)BlocksRegister.CAULDRON_BLOCK));
    this.job.addBlacklistedUsage(4, new ItemStack((Block)BlocksRegister.CAULDRON_CORE));
    this.job.addBlacklistedUsage(6, new ItemStack((Item)ItemsRegister.AMETHYSTE_PORTAL_KEY));
    this.job.addBlacklistedUsage(6, new ItemStack((Block)BlocksRegister.AMETHYSTE_PORTAL_BLOCK));
    this.job.addBlacklistedUsage(8, new ItemStack(BlocksRegister.FLOWER_FARM));
    this.job.addBlacklistedUsage(9, new ItemStack((Item)ItemsRegister.TITANE_PORTAL_KEY));
    this.job.addBlacklistedUsage(9, new ItemStack((Block)BlocksRegister.TITANE_PORTAL_BLOCK));
    this.job.addBlacklistedUsage(12, new ItemStack((Item)ItemsRegister.PALADIUM_PORTAL_KEY));
    this.job.addBlacklistedUsage(12, new ItemStack((Block)BlocksRegister.PALADIUM_PORTAL_BLOCK));
    this.job.addBlacklistedUsage(15, new ItemStack((Item)SpawnerItemRegistry.UPGRADE_SLIME));
    this.job.addBlacklistedUsage(18, new ItemStack((Item)SpawnerItemRegistry.UPGRADE_MORE));
    this.job.addBlacklistedUsage(20, new ItemStack((Block)BlocksRegister.SHINY_OSTRYA_WOOD));
    this.job.addBlacklistedUsage(20, new ItemStack((Item)ItemsRegister.ENDIUM_PORTAL_KEY));
    this.job.addBlacklistedUsage(20, new ItemStack((Block)BlocksRegister.ENDIUM_PORTAL_BLOCK));
    this.job.addBlacklistedUsage(20, new ItemStack(ItemsRegister.ENDIUM_LEGGINGS));
    this.job.addBlacklistedUsage(20, new ItemStack(ItemsRegister.ENDIUM_BOOTS));
    this.job.addBlacklistedUsage(20, new ItemStack((Item)ItemsRegister.ENDIUM_HEART));
    this.job.addBlacklistedUsage(20, new ItemStack(BlocksRegistry.ALCHEMIST_THRONE));
  }
  
  public void registerLvlTokensRewards() {
    LvlTokenRegistry.register(new LvlTokenReward(2, "2-common-gold-ingot-1", "1 Gold Ingot", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(Items.field_151043_k), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(2, "2-rare-gold-ingot-2", "2 Gold Ingots", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(Items.field_151043_k), 2, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(2, "2-EPIC-gold-ingot-4", "4 Gold Ingots", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(Items.field_151043_k), 4, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(4, "4-common-shiny-jacaranda-wood-16", "16 Shiny Jacaranda Wood", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack((Block)BlocksRegister.SHINY_JACARANDA_WOOD), 16, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(4, "4-rare-chaurdon-heart-1", "1 Chaurdon Heart", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack((Block)BlocksRegister.CAULDRON_CORE), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(4, "4-EPIC-hardness-potion-3", "3 Hardness Potions", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(ItemsRegister.HARDNESS_POTION), 3, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(6, "6-common-titanium-ingot-3", "3 Titanium Ingots", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.TITANE_INGOT), 3, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(6, "6-rare-titanium-stick-3", "3 Titanium Sticks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(ItemsRegister.STICK_TITANE), 3, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(6, "6-EPIC-compressed-titanium-1", "1 Compressed Titanium", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(ItemsRegister.COMPRESSED_TITANE), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(8, "8-common-titanium-tank-1", "1 Titanium Tank", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack((Block)BlocksRegister.TANK_TITANE), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(8, "8-rare-extractor-4", "4 Extractors", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack((Block)BlocksRegister.EXTRACTOR), 4, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(8, "8-EPIC-titanium-tank-6", "6 Titanium Tanks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack((Block)BlocksRegister.TANK_TITANE), 6, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(10, "10-common-paladium-portal-block-1", "1 Paladium Portal Block", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack((Block)BlocksRegister.PALADIUM_PORTAL_BLOCK), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(10, "10-rare-glueball-pattern-16", "16 Glueball Patterns", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack((Item)ItemsRegister.GLUEBALL_PATTERN), 16, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(10, "10-EPIC-compressed-paladium-1", "1 Compressed Paladium", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(11, "11-common-red-glueball-4", "4 Red Glueballs", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack((Item)ItemsRegister.RED_GLUEBALL), 4, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(11, "11-rare-yellow-glueball-8", "8 Yellow Glueballs", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack((Item)ItemsRegister.YELLOW_GLUEBALL), 8, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(11, "11-EPIC-cyan-glueball-6", "6 Cyan Glueballs", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack((Item)ItemsRegister.CYAN_GLUEBALL), 6, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(12, "12-common-extractor-8", "8 Extractors", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack((Block)BlocksRegister.EXTRACTOR), 8, this.job.getType()));
    ItemStack lvl60 = new ItemStack((Item)Items.field_151134_bR);
    Items.field_151134_bR.func_92115_a(lvl60, new EnchantmentData((Enchantment)EnchantMod.unclaimHelmet, 1));
    LvlTokenRegistry.register(new LvlTokenReward(12, "12-rare-unclaim-helmet-1-1", "1 Unclaim Helmet (Tier 1)", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, lvl60, 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(12, "12-EPIC-double-xp-potion-30min-1", "1 Double XP Potion (30 minutes)", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 0), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(13, "13-common-sage-3", "3 Sage", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(BlocksRegister.FLOWER_SAUGE), 3, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(14, "14-common-paladium-ingot-16", "16 Paladium Ingots", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.PALADIUM_INGOT), 16, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(14, "14-rare-paladium-ingot-32", "32 Paladium Ingots", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(ItemsRegister.PALADIUM_INGOT), 32, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(14, "14-EPIC-paladium-block-12", "12 Paladium Blocks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(BlocksRegister.BLOCK_PALADIUM), 12, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(15, "15-EPIC-double-xp-potion-1h-1", "1 Double XP Potion (1 hour)", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 1), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(16, "16-common-liquid-gun-1", "1 Liquid Gun", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(ItemsRegister.PISTOL_TANK), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(16, "16-rare-double-xp-potion-1h-2", "2 Double XP Potions (1 hour each)", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 1), 2, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(17, "17-common-paladium-block-16", "16 Paladium Blocks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(BlocksRegister.BLOCK_PALADIUM), 16, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(17, "17-rare-green-paladium-block-3", "3 Green Paladium Blocks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack(BlocksRegister.BLOCK_GPALADIUM), 3, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(17, "17-EPIC-unwrapping-candy-1", "1 Unwrapping Candy", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(ItemsRegister.JOB_CANDY), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(18, "18-common-green-paladium-block-4", "4 Green Paladium Blocks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.COMMON, new ItemStack(BlocksRegister.BLOCK_GPALADIUM), 4, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(18, "18-rare-double-xp-potion-1h-1", "1 Double XP Potion (1 hour)", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 1), 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(18, "18-EPIC-green-paladium-block-16", "16 Green Paladium Blocks", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(BlocksRegister.BLOCK_GPALADIUM), 16, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(19, "19-rare-shiny-ostrya-wood-16", "16 Shiny Ostrya Wood", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.RARE, new ItemStack((Block)BlocksRegister.SHINY_OSTRYA_WOOD), 16, this.job.getType()));
    ItemStack lvl19 = new ItemStack(ItemsRegister.PALADIUM_CHESTPLATE);
    lvl19.func_77966_a(Enchantment.field_77332_c, 4);
    LvlTokenRegistry.register(new LvlTokenReward(19, "19-EPIC-mending-paladium-chestplate-1", "1 Mending Paladium Chestplate (Protection 4)", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, lvl19, 1, this.job.getType()));
    LvlTokenRegistry.register(new LvlTokenReward(20, "20-EPIC-endium-fragment-4", "4 Endium Fragments", EnumLvlTokenRewardCategory.ITEM, EnumLvlTokenRewardRarity.EPIC, new ItemStack(ItemsRegister.ENDIUM_FRAGMENT), 4, this.job.getType()));
  }
  
  public void registerRequirements() {
    this.job.addRequirement((Requirement)new BreakBlockRequirement(2, JobType.ALCHEMIST, "Casser %d buches moddées", 450, new ItemStack[] { new ItemStack(PWorld.LOG_ERABLE), new ItemStack(PWorld.LOG_OSTRYA), new ItemStack(PWorld.LOG_JACARANDA), new ItemStack(PWorld.LOG_JUDEECERCIS) }));
    this.job.addRequirement((Requirement)new BreakBlockRequirement(3, JobType.ALCHEMIST, "Casser %d buches moddées", 800, new ItemStack[] { new ItemStack(PWorld.LOG_ERABLE), new ItemStack(PWorld.LOG_OSTRYA), new ItemStack(PWorld.LOG_JACARANDA), new ItemStack(PWorld.LOG_JUDEECERCIS) }));
    this.job.addRequirement((Requirement)new ExtractSeveRequirement(4, JobType.ALCHEMIST, "Extraire la sève de Jacaranda %d fois", 950, "jacaranda"));
    this.job.addRequirement((Requirement)new ExtractSeveRequirement(5, JobType.ALCHEMIST, "Extraire la sève de Jacaranda %d fois", 1600, "jacaranda"));
    this.job.addRequirement((Requirement)new ExtractSeveRequirement(6, JobType.ALCHEMIST, "Extraire la sève de Jacaranda %d fois", 2350, "jacaranda"));
    this.job.addRequirement((Requirement)new ExtractSeveRequirement(7, JobType.ALCHEMIST, "Extraire la sève de Judeecercis %d fois", 1250, "judeecercis"));
    this.job.addRequirement((Requirement)new ExtractSeveRequirement(8, JobType.ALCHEMIST, "Extraire la sève de Judeecercis %d fois", 1650, "judeecercis"));
    this.job.addRequirement((Requirement)new DropCauldronRequirement(9, JobType.ALCHEMIST, "Jeter %d fleurs dans le chaudron", 86300));
    this.job.addRequirement((Requirement)new DropCauldronRequirement(10, JobType.ALCHEMIST, "Jeter %d fleurs dans le chaudron", 109200));
    this.job.addRequirement((Requirement)new DropCauldronRequirement(11, JobType.ALCHEMIST, "Jeter %d fleurs dans le chaudron", 135100));
    this.job.addRequirement((Requirement)new DropCauldronRequirement(12, JobType.ALCHEMIST, "Jeter %d fleurs dans le chaudron", 164200));
    this.job.addRequirement((Requirement)new ExtractSeveRequirement(13, JobType.ALCHEMIST, "Extraire la sève d'érable %d fois", 2450, "erable"));
    this.job.addRequirement((Requirement)new ExtractSeveRequirement(14, JobType.ALCHEMIST, "Extraire la sève d'érable %d fois", 2900, "erable"));
    this.job.addRequirement((Requirement)new ExtractSeveRequirement(15, JobType.ALCHEMIST, "Extraire la sève d'érable %d fois", 3400, "erable"));
    this.job.addRequirement((Requirement)new ExtractSeveRequirement(16, JobType.ALCHEMIST, "Extraire la sève d'érable %d fois", 3900, "erable"));
    this.job.addRequirement((Requirement)new ExtractSeveRequirement(17, JobType.ALCHEMIST, "Extraire la sève d'érable %d fois", 4500, "erable"));
    this.job.addRequirement((Requirement)new CraftPortalRequirement(18, JobType.ALCHEMIST, "Fabriquer %d lingots de paladium avec le portail", 10200, new ItemStack(ItemsRegister.PALADIUM_INGOT)));
    this.job.addRequirement((Requirement)new CraftPortalRequirement(19, JobType.ALCHEMIST, "Fabriquer %d lingots de paladium avec le portail", 11550, new ItemStack(ItemsRegister.PALADIUM_INGOT)));
    this.job.addRequirement((Requirement)new CraftPortalRequirement(20, JobType.ALCHEMIST, "Fabriquer %d lingots de paladium avec le portail", 12950, new ItemStack(ItemsRegister.PALADIUM_INGOT)));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\registerer\jobs\type\AlchemistRegisterer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */