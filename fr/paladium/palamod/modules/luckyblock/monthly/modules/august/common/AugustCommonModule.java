package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.items.BaseItem;
import fr.paladium.palamod.modules.hunter.items.ItemAmulet;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.manager.LuckyStatsRewardManager;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.reward.LuckyStatsReward;
import fr.paladium.palamod.modules.luckyblock.monthly.AbstractMonthlyModule;
import fr.paladium.palamod.modules.luckyblock.monthly.SideType;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.blocks.BlockAugustLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.blocks.BlockAugustTrophy;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.blocks.BlockCrabCobbleBreaker;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.blocks.BlockDolphinPlush;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.blocks.BlockLead;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.blocks.BlockPrimaryCrabEgg;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.blocks.BlockQuickSand;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.blocks.BlockRunningTreadmill;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.blocks.BlockVentilator;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.dialog.IceCreamSellerDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.dialog.MajordomeDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.dialog.MaxDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.dialog.SummerWinterDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.entities.EntityLuckyCrab;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.entities.EntityMosquito;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.entities.EntityVolleyBall;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.ItemArmorBag;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.ItemAugustFakeMoney;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.ItemAugustTicket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.ItemEmptyWaterBottle;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.ItemIceCreamEgg;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.ItemPerfectHelmet;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.ItemPortableVolleyCourt;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.ItemSolarCream;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.ItemSuitcase;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.ItemSummerInstruction;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.ItemVolleyBall;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.consumable.ItemCrabEgg;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.consumable.ItemDoubleIceCream;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.consumable.ItemIceCream;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.consumable.ItemJobQuestPotion;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.consumable.ItemSummerWaterBottle;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets.CSSummerBodyPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets.CSSummerSalePacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets.SCHydrationPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets.SCMessPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets.SCPlanePacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets.SCSummerSalePacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityAugustLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityAugustTrophy;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityCrabCobbleBreaker;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityDolphinPlush;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityPrimaryCrabEgg;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityTreadmill;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityVentilator;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AugustCommonModule extends AbstractMonthlyModule {
  public AugustCommonModule() {
    super(SideType.BOTH, LuckyType.AUGUST);
  }
  
  public void registerEventHandlers() {
    super.registerEventHandlers();
  }
  
  public void registerDialogManagers() {
    registerDialogManager((AbstractDialogManager)new IceCreamSellerDialogManager());
    registerDialogManager((AbstractDialogManager)new MajordomeDialogManager());
    registerDialogManager((AbstractDialogManager)new SummerWinterDialogManager());
    registerDialogManager((AbstractDialogManager)new MaxDialogManager());
  }
  
  public void registerEntities() {
    PLuckyBlock instance = PLuckyBlock.instance;
    instance.registerEntity(EntityVolleyBall.class, "entityVolleyBall", 40, 1, true);
    instance.registerEntity(EntityMosquito.class, "entityMosquito", 64, 1, true);
    instance.registerEntity(EntityLuckyCrab.class, "entityLuckyCrab", 64, 1, true);
  }
  
  public void registerTileEntities() {
    GameRegistry.registerTileEntity(TileEntityPrimaryCrabEgg.class, "tileEntityPrimaryCrabEgg");
    GameRegistry.registerTileEntity(TileEntityVentilator.class, "tileEntityVentilator");
    GameRegistry.registerTileEntity(TileEntityAugustLuckyBlock.class, "tileEntityAugustLuckyBlock");
    GameRegistry.registerTileEntity(TileEntityAugustTrophy.class, "tileEntityAugustTrophy");
    GameRegistry.registerTileEntity(TileEntityDolphinPlush.class, "tileEntityDolphinPlush");
    GameRegistry.registerTileEntity(TileEntityCrabCobbleBreaker.class, "tileEntityCrabCobbleBreaker");
    GameRegistry.registerTileEntity(TileEntityTreadmill.class, "tileEntityTreadmill");
  }
  
  public void registerPackets() {
    PLuckyBlock instance = PLuckyBlock.instance;
    instance.registerPacket(SCHydrationPacket.Handler.class, SCHydrationPacket.class, Side.CLIENT);
    instance.registerPacket(SCMessPacket.Handler.class, SCMessPacket.class, Side.CLIENT);
    instance.registerPacket(SCPlanePacket.Handler.class, SCPlanePacket.class, Side.CLIENT);
    instance.registerPacket(SCSummerSalePacket.Handler.class, SCSummerSalePacket.class, Side.CLIENT);
    instance.registerPacket(CSSummerBodyPacket.Handler.class, CSSummerBodyPacket.class, Side.SERVER);
    instance.registerPacket(CSSummerSalePacket.Handler.class, CSSummerSalePacket.class, Side.SERVER);
  }
  
  public void registerItems() {
    ItemsRegister.SUMMER_WATER_BOTTLE = (Item)new ItemSummerWaterBottle();
    GameRegistry.registerItem(ItemsRegister.SUMMER_WATER_BOTTLE, ItemsRegister.SUMMER_WATER_BOTTLE.func_77658_a());
    ItemsRegister.SOLAR_CREAM = (Item)new ItemSolarCream();
    GameRegistry.registerItem(ItemsRegister.SOLAR_CREAM, ItemsRegister.SOLAR_CREAM.func_77658_a());
    ItemsRegister.ICE_CREAM = (Item)new ItemIceCream();
    GameRegistry.registerItem(ItemsRegister.ICE_CREAM, ItemsRegister.ICE_CREAM.func_77658_a());
    ItemsRegister.DOUBLE_ICE_CREAM = (Item)new ItemDoubleIceCream();
    GameRegistry.registerItem(ItemsRegister.DOUBLE_ICE_CREAM, ItemsRegister.DOUBLE_ICE_CREAM.func_77658_a());
    ItemsRegister.ICE_CREAM_EGG = (Item)new ItemIceCreamEgg();
    GameRegistry.registerItem(ItemsRegister.ICE_CREAM_EGG, ItemsRegister.ICE_CREAM_EGG.func_77658_a());
    ItemsRegister.JOB_QUEST_POTION = (Item)new ItemJobQuestPotion();
    GameRegistry.registerItem(ItemsRegister.JOB_QUEST_POTION, ItemsRegister.JOB_QUEST_POTION.func_77658_a());
    ItemsRegister.SUMMER_INSTRUCTIONS = (Item)new ItemSummerInstruction();
    GameRegistry.registerItem(ItemsRegister.SUMMER_INSTRUCTIONS, ItemsRegister.SUMMER_INSTRUCTIONS.func_77658_a());
    ItemsRegister.SUMMER_AMULET = (Item)new ItemAmulet(ItemAmulet.Type.SUMMER);
    GameRegistry.registerItem(ItemsRegister.SUMMER_AMULET, ItemsRegister.SUMMER_AMULET.func_77658_a());
    ItemsRegister.SUITCASE = (Item)new ItemSuitcase();
    GameRegistry.registerItem(ItemsRegister.SUITCASE, ItemsRegister.SUITCASE.func_77658_a());
    ItemsRegister.ARMOR_BAG = (Item)new ItemArmorBag();
    GameRegistry.registerItem(ItemsRegister.ARMOR_BAG, ItemsRegister.ARMOR_BAG.func_77658_a());
    ItemsRegister.PERFECT_HELMET = (Item)new ItemPerfectHelmet();
    GameRegistry.registerItem(ItemsRegister.PERFECT_HELMET, ItemsRegister.PERFECT_HELMET.func_77658_a());
    ItemsRegister.AUGUST_FAKE_MONEY = (Item)new ItemAugustFakeMoney();
    GameRegistry.registerItem(ItemsRegister.AUGUST_FAKE_MONEY, ItemsRegister.AUGUST_FAKE_MONEY.func_77658_a());
    ItemsRegister.EMPTY_WATER_BOTTLE = (Item)new ItemEmptyWaterBottle();
    GameRegistry.registerItem(ItemsRegister.EMPTY_WATER_BOTTLE, ItemsRegister.EMPTY_WATER_BOTTLE.func_77658_a());
    ItemsRegister.VOLLEY_BALL = (Item)new ItemVolleyBall();
    GameRegistry.registerItem(ItemsRegister.VOLLEY_BALL, ItemsRegister.VOLLEY_BALL.func_77658_a());
    ItemsRegister.PORTABLE_VOLLEY_COURT = (Item)new ItemPortableVolleyCourt();
    GameRegistry.registerItem(ItemsRegister.PORTABLE_VOLLEY_COURT, ItemsRegister.PORTABLE_VOLLEY_COURT.func_77658_a());
    ItemsRegister.CRAB_EGG = (Item)new ItemCrabEgg();
    GameRegistry.registerItem(ItemsRegister.CRAB_EGG, ItemsRegister.CRAB_EGG.func_77658_a());
    ItemsRegister.AUGUST_TICKET = (Item)new ItemAugustTicket();
    GameRegistry.registerItem(ItemsRegister.AUGUST_TICKET, ItemsRegister.AUGUST_TICKET.func_77658_a());
    ItemsRegister.PARTICLE_GREEN_PALADIUM = (new BaseItem("particle/particle_green_paladium")).func_77655_b("particle.green.paladium");
    GameRegistry.registerItem(ItemsRegister.PARTICLE_GREEN_PALADIUM, ItemsRegister.PARTICLE_GREEN_PALADIUM.func_77658_a());
  }
  
  public void registerBlocks() {
    BlocksRegister.QUICKSAND = (Block)new BlockQuickSand();
    GameRegistry.registerBlock(BlocksRegister.QUICKSAND, BlocksRegister.QUICKSAND.func_149739_a());
    BlocksRegister.LEAD = (Block)new BlockLead();
    GameRegistry.registerBlock(BlocksRegister.LEAD, BlocksRegister.LEAD.func_149739_a());
    BlocksRegister.PRIMARY_CRAB_EGG = (Block)new BlockPrimaryCrabEgg();
    GameRegistry.registerBlock(BlocksRegister.PRIMARY_CRAB_EGG, BlocksRegister.PRIMARY_CRAB_EGG.func_149739_a());
    BlocksRegister.VENTILATOR = (Block)new BlockVentilator();
    GameRegistry.registerBlock(BlocksRegister.VENTILATOR, BlocksRegister.VENTILATOR.func_149739_a());
    BlocksRegister.DOLPHIN_PLUSH = (Block)new BlockDolphinPlush();
    GameRegistry.registerBlock(BlocksRegister.DOLPHIN_PLUSH, BlocksRegister.DOLPHIN_PLUSH.func_149739_a());
    BlocksRegister.AUGUST_TROPHY = (Block)new BlockAugustTrophy();
    GameRegistry.registerBlock(BlocksRegister.AUGUST_TROPHY, BlocksRegister.AUGUST_TROPHY.func_149739_a());
    BlocksRegister.AUGUST_LUCKY_BLOCK = (Block)new BlockAugustLuckyBlock();
    GameRegistry.registerBlock(BlocksRegister.AUGUST_LUCKY_BLOCK, BlocksRegister.AUGUST_LUCKY_BLOCK.func_149739_a());
    BlocksRegister.RUNNING_TREADMILL = (Block)new BlockRunningTreadmill();
    GameRegistry.registerBlock(BlocksRegister.RUNNING_TREADMILL, BlocksRegister.RUNNING_TREADMILL.func_149739_a());
    BlocksRegister.CRAB_COBBLEBREAKER = (Block)new BlockCrabCobbleBreaker();
    GameRegistry.registerBlock(BlocksRegister.CRAB_COBBLEBREAKER, BlocksRegister.CRAB_COBBLEBREAKER.func_149739_a());
  }
  
  public void registerCrafts() {
    GameRegistry.addSmelting(ItemsRegister.EMPTY_WATER_BOTTLE, new ItemStack(BlocksRegister.DOLPHIN_PLUSH), 0.1F);
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_GREEN_INGOT), new Object[] { "XXX", "XXX", "XXX", 
          
          Character.valueOf('X'), ItemsRegister.PARTICLE_GREEN_PALADIUM });
  }
  
  public void registerLuckyStats() {
    String keyUniqueId = "63873e745b13ee37d92f79db";
    LuckyStatsRewardManager.addReward(LuckyType.AUGUST, 0, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 5), new LuckyStatsReward(ItemsRegister.SUMMER_AMULET), new LuckyStatsReward(ItemsRegister.JOB_QUEST_POTION) });
    LuckyStatsRewardManager.addReward(LuckyType.AUGUST, 1, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 5), new LuckyStatsReward(ItemsRegister.SUITCASE), new LuckyStatsReward(ItemsRegister.ARMOR_BAG), new LuckyStatsReward("63873e745b13ee37d92f79db", 2) });
    LuckyStatsRewardManager.addReward(LuckyType.AUGUST, 2, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK, 5), new LuckyStatsReward((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK), new LuckyStatsReward(ItemsRegister.JOB_QUEST_POTION), new LuckyStatsReward(ItemsRegister.JOB_QUEST_POTION), new LuckyStatsReward(ItemsRegister.JOB_QUEST_POTION), new LuckyStatsReward(
            
            ItemPerfectHelmet.create()) });
    LuckyStatsRewardManager.addReward(LuckyType.AUGUST, 3, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.AUGUST_TROPHY), new LuckyStatsReward((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, 3), new LuckyStatsReward(ItemsRegister.LEGENDARYSTONE_RANDOM), new LuckyStatsReward(ItemsRegister.AUGUST_TICKET), new LuckyStatsReward("63873e745b13ee37d92f79db", 5) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\AugustCommonModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */