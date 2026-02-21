package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.potion.PotionUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.api.PotionsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.items.ItemLuckySword;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.manager.LuckyStatsRewardManager;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.reward.LuckyStatsReward;
import fr.paladium.palamod.modules.luckyblock.monthly.AbstractMonthlyModule;
import fr.paladium.palamod.modules.luckyblock.monthly.SideType;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.blocks.BlockCorruptedBed;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.blocks.BlockCorruptedEnchantmentTable;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.blocks.BlockCorruptedHunterPlant;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.blocks.BlockCorruptedLuckyDrawer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.blocks.BlockCorruptedOre;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.blocks.BlockCorruptedPlant;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.blocks.BlockFakeCorruptedChest;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.blocks.BlockNovemberLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.blocks.BlockNovemberTrophy;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.blocks.BlockPrimarySlayerEgg;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.entities.EntityCorruptedSplashPotion;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.entities.EntityMimic;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.entities.EntitySlayer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.items.ItemCorruptedBed;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.items.ItemCorruptedChrono;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.items.ItemCorruptedLegendaryStone;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.items.ItemCorruptedSplashPotion;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.items.ItemCorruptedSword;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.items.ItemCorruptedTicket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.items.ItemPoisonCorrupted;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.items.ItemSlayerEgg;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.packets.CSOpenInventoryPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.potions.PotionCorruptedPoison;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityCorruptedEnchantmentTable;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityCorruptedPlant;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityFakeCorruptedChest;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityHunterCorruptedPlant;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityNovemberLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityNovemberTrophy;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityPrimarySlayerEgg;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class NovemberCommonModule extends AbstractMonthlyModule {
  public static NovemberCommonModule INSTANCE;
  
  public NovemberCommonModule() {
    super(SideType.BOTH, LuckyType.NOVEMBER);
    INSTANCE = this;
  }
  
  public void registerEventHandlers() {
    super.registerEventHandlers();
  }
  
  public void registerPackets() {
    PLuckyBlock instance = PLuckyBlock.instance;
    registerPacket(CSOpenInventoryPacket.Handler.class, CSOpenInventoryPacket.class, Side.SERVER);
  }
  
  public void registerPotions() {
    PotionUtils.registerPotionEffect(PotionsRegister.CORRUPTED_POISON = (new PotionCorruptedPoison()).setIcon(ItemsRegister.CORRUPTED_POISON_POTION));
  }
  
  public void registerLuckyStats() {
    LuckyStatsRewardManager.addReward(LuckyType.NOVEMBER, 0, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.CORRUPTED_ORE, 3), new LuckyStatsReward(ItemsRegister.ITEM_CORRUPTED_BED), new LuckyStatsReward(ItemsRegister.ITEM_CORRUPTED_BED), new LuckyStatsReward(ItemsRegister.ITEM_CORRUPTED_BED), new LuckyStatsReward(ItemsRegister.CORRUPTED_CHRONO), new LuckyStatsReward(ItemsRegister.CORRUPTED_CHRONO), new LuckyStatsReward(ItemsRegister.CORRUPTED_SPLASH_POTION), new LuckyStatsReward(ItemsRegister.CORRUPTED_SPLASH_POTION) });
    LuckyStatsRewardManager.addReward(LuckyType.NOVEMBER, 1, new LuckyStatsReward[] { new LuckyStatsReward(ItemsRegister.CORRUPTED_SPLASH_POTION), new LuckyStatsReward(ItemsRegister.CORRUPTED_SPLASH_POTION), new LuckyStatsReward(ItemsRegister.CORRUPTED_SPLASH_POTION), new LuckyStatsReward(BlocksRegister.CORRUPTED_HUNTER_PLANT, 3), new LuckyStatsReward(BlocksRegister.CORRUPTED_PLANT, 8), new LuckyStatsReward(BlocksRegister.FAKE_CORRUPTED_CHEST), new LuckyStatsReward(BlocksRegister.CORRUPTED_ORE, 5) });
    LuckyStatsRewardManager.addReward(LuckyType.NOVEMBER, 2, new LuckyStatsReward[] { new LuckyStatsReward(ItemsRegister.CORRUPTED_SWORD), new LuckyStatsReward(ItemsRegister.CORRUPTED_LUCKY_SWORD), new LuckyStatsReward(ItemsRegister.CORRUPTED_LUCKY_SWORD), new LuckyStatsReward(ItemsRegister.CORRUPTED_POISON_POTION), new LuckyStatsReward(ItemsRegister.CORRUPTED_POISON_POTION), new LuckyStatsReward(ItemsRegister.CORRUPTED_POISON_POTION), new LuckyStatsReward(ItemsRegister.CORRUPTED_POISON_POTION), new LuckyStatsReward(ItemsRegister.CORRUPTED_POISON_POTION), new LuckyStatsReward(BlocksRegister.CORRUPTED_LUCKY_DRAWER, 3), new LuckyStatsReward(BlocksRegister.CORRUPTED_ORE, 10) });
    LuckyStatsRewardManager.addReward(LuckyType.NOVEMBER, 3, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.NOVEMBER_TROPHY), new LuckyStatsReward(BlocksRegister.CORRUPTED_ENCHANTMENT_TABLE), new LuckyStatsReward(ItemsRegister.CORRUPTED_LEGENDARY_STONE), new LuckyStatsReward(BlocksRegister.CORRUPTED_LUCKY_DRAWER, 7), new LuckyStatsReward(BlocksRegister.CORRUPTED_ORE, 20) });
  }
  
  public void registerEntities() {
    PLuckyBlock instance = PLuckyBlock.instance;
    instance.registerEntity(EntitySlayer.class, "entitySlayer", 64, 1, true);
    instance.registerEntity(EntityMimic.class, "entityMimic", 64, 1, true);
    instance.registerEntity(EntityCorruptedSplashPotion.class, "entityCorruptedSplashPotion", 64, 1, true);
  }
  
  public void registerTileEntities() {
    GameRegistry.registerTileEntity(TileEntityNovemberLuckyBlock.class, "tileEntityNovemberLuckyBlock");
    GameRegistry.registerTileEntity(TileEntityNovemberTrophy.class, "tileEntityNovemberTrophy");
    GameRegistry.registerTileEntity(TileEntityPrimarySlayerEgg.class, "tileEntityPrimarySlayerEgg");
    GameRegistry.registerTileEntity(TileEntityCorruptedPlant.class, "tileEntityCorruptedPlant");
    GameRegistry.registerTileEntity(TileEntityHunterCorruptedPlant.class, "tileEntityHunterCorruptedPlant");
    GameRegistry.registerTileEntity(TileEntityFakeCorruptedChest.class, "tileEntityFakeCorruptedChest");
    GameRegistry.registerTileEntity(TileEntityCorruptedEnchantmentTable.class, "tileEntityCorruptedEnchantmentTable");
  }
  
  public void registerItems() {
    ItemsRegister.SLAYER_EGG = (Item)new ItemSlayerEgg();
    GameRegistry.registerItem(ItemsRegister.SLAYER_EGG, ItemsRegister.SLAYER_EGG.func_77658_a());
    ItemsRegister.CORRUPTED_CHRONO = (Item)new ItemCorruptedChrono();
    GameRegistry.registerItem(ItemsRegister.CORRUPTED_CHRONO, ItemsRegister.CORRUPTED_CHRONO.func_77658_a());
    ItemsRegister.CORRUPTED_TICKET = (Item)new ItemCorruptedTicket();
    GameRegistry.registerItem(ItemsRegister.CORRUPTED_TICKET, ItemsRegister.CORRUPTED_TICKET.func_77658_a());
    ItemsRegister.CORRUPTED_SPLASH_POTION = (Item)new ItemCorruptedSplashPotion();
    GameRegistry.registerItem(ItemsRegister.CORRUPTED_SPLASH_POTION, ItemsRegister.CORRUPTED_SPLASH_POTION.func_77658_a());
    ItemsRegister.CORRUPTED_LEGENDARY_STONE = (Item)new ItemCorruptedLegendaryStone();
    GameRegistry.registerItem(ItemsRegister.CORRUPTED_LEGENDARY_STONE, ItemsRegister.CORRUPTED_LEGENDARY_STONE.func_77658_a());
    ItemsRegister.CORRUPTED_LUCKY_SWORD = (Item)new ItemLuckySword(3);
    GameRegistry.registerItem(ItemsRegister.CORRUPTED_LUCKY_SWORD, ItemsRegister.CORRUPTED_LUCKY_SWORD.func_77658_a());
    ItemsRegister.CORRUPTED_SWORD = (Item)new ItemCorruptedSword();
    GameRegistry.registerItem(ItemsRegister.CORRUPTED_SWORD, ItemsRegister.CORRUPTED_SWORD.func_77658_a());
    ItemsRegister.CORRUPTED_POISON_POTION = (Item)new ItemPoisonCorrupted();
    GameRegistry.registerItem(ItemsRegister.CORRUPTED_POISON_POTION, ItemsRegister.CORRUPTED_POISON_POTION.func_77658_a());
    ItemsRegister.ITEM_CORRUPTED_BED = (Item)new ItemCorruptedBed();
    GameRegistry.registerItem(ItemsRegister.ITEM_CORRUPTED_BED, ItemsRegister.ITEM_CORRUPTED_BED.func_77658_a());
  }
  
  public void registerBlocks() {
    BlocksRegister.NOVEMBER_LUCKY_BLOCK = (Block)new BlockNovemberLuckyBlock();
    GameRegistry.registerBlock(BlocksRegister.NOVEMBER_LUCKY_BLOCK, BlocksRegister.NOVEMBER_LUCKY_BLOCK.func_149739_a());
    BlocksRegister.NOVEMBER_TROPHY = (Block)new BlockNovemberTrophy();
    GameRegistry.registerBlock(BlocksRegister.NOVEMBER_TROPHY, BlocksRegister.NOVEMBER_TROPHY.func_149739_a());
    BlocksRegister.PRIMARY_SLAYER_EGG = (Block)new BlockPrimarySlayerEgg();
    GameRegistry.registerBlock(BlocksRegister.PRIMARY_SLAYER_EGG, BlocksRegister.PRIMARY_SLAYER_EGG.func_149739_a());
    BlocksRegister.CORRUPTED_ORE = (Block)new BlockCorruptedOre();
    GameRegistry.registerBlock(BlocksRegister.CORRUPTED_ORE, BlocksRegister.CORRUPTED_ORE.func_149739_a());
    BlocksRegister.CORRUPTED_BED = (Block)new BlockCorruptedBed();
    GameRegistry.registerBlock(BlocksRegister.CORRUPTED_BED, BlocksRegister.CORRUPTED_BED.func_149739_a());
    BlocksRegister.CORRUPTED_HUNTER_PLANT = (Block)new BlockCorruptedHunterPlant();
    GameRegistry.registerBlock(BlocksRegister.CORRUPTED_HUNTER_PLANT, BlocksRegister.CORRUPTED_HUNTER_PLANT.func_149739_a());
    BlocksRegister.CORRUPTED_PLANT = (Block)new BlockCorruptedPlant();
    GameRegistry.registerBlock(BlocksRegister.CORRUPTED_PLANT, BlocksRegister.CORRUPTED_PLANT.func_149739_a());
    BlocksRegister.CORRUPTED_LUCKY_DRAWER = (Block)new BlockCorruptedLuckyDrawer();
    GameRegistry.registerBlock(BlocksRegister.CORRUPTED_LUCKY_DRAWER, BlocksRegister.CORRUPTED_LUCKY_DRAWER.func_149739_a());
    BlocksRegister.FAKE_CORRUPTED_CHEST = (Block)new BlockFakeCorruptedChest();
    GameRegistry.registerBlock(BlocksRegister.FAKE_CORRUPTED_CHEST, BlocksRegister.FAKE_CORRUPTED_CHEST.func_149739_a());
    BlocksRegister.CORRUPTED_ENCHANTMENT_TABLE = (Block)new BlockCorruptedEnchantmentTable();
    GameRegistry.registerBlock(BlocksRegister.CORRUPTED_ENCHANTMENT_TABLE, BlocksRegister.CORRUPTED_ENCHANTMENT_TABLE.func_149739_a());
  }
  
  public void registerCrafts() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\NovemberCommonModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */