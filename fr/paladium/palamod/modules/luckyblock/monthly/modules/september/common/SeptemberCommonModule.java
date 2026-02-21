package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.manager.LuckyStatsRewardManager;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.reward.LuckyStatsReward;
import fr.paladium.palamod.modules.luckyblock.monthly.AbstractMonthlyModule;
import fr.paladium.palamod.modules.luckyblock.monthly.SideType;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.blocks.BlockPrimaryTaupikoEgg;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.blocks.BlockSeptemberLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.blocks.BlockSeptemberTrophy;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.dialogs.CareerDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.dialogs.DevilDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.dialogs.JourneyDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.dialogs.JudgeDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.dialogs.SandmanDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities.EntityBodyGuard;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities.EntityPaperAirplane;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities.EntityTaupiko;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities.EntityWorker;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items.ItemBossSuitcase;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items.ItemButcherKnife;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items.ItemEmptyTradeFile;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items.ItemJobChunkAnalyzer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items.ItemJobOffer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items.ItemJobTransferor;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items.ItemPaperAirplane;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items.ItemSeptemberTicket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items.ItemTaupikoEgg;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items.ItemTradeFile;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items.ItemWeldingGlasses;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.client.CSGoodFortunePacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.client.CSGoodPayPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.client.CSJobOfferPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.client.CSJobTransferorPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.client.CSTaupikoPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.server.SCDarkScreenPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.server.SCGoodPayPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.server.SCTimedLanguagePacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.tiles.TileEntityPrimaryTaupikoEgg;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.tiles.TileEntitySeptemberLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.tiles.TileEntitySeptemberTrophy;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class SeptemberCommonModule extends AbstractMonthlyModule {
  public static SeptemberCommonModule INSTANCE;
  
  public SeptemberCommonModule() {
    super(SideType.BOTH, LuckyType.SEPTEMBER);
    INSTANCE = this;
  }
  
  public void registerEventHandlers() {
    super.registerEventHandlers();
  }
  
  public void registerDialogManagers() {
    registerDialogManager((AbstractDialogManager)new DevilDialogManager());
    registerDialogManager((AbstractDialogManager)new JudgeDialogManager());
    registerDialogManager((AbstractDialogManager)new SandmanDialogManager());
    registerDialogManager((AbstractDialogManager)new JourneyDialogManager());
    registerDialogManager((AbstractDialogManager)new CareerDialogManager());
  }
  
  public void registerEntities() {
    PLuckyBlock instance = PLuckyBlock.instance;
    instance.registerEntity(EntityWorker.class, "entityWorker", 64, 1, true);
    instance.registerEntity(EntityPaperAirplane.class, "entityPaperAirplane", 64, 1, true);
    instance.registerEntity(EntityTaupiko.class, "entityTaupiko", 64, 1, true);
    instance.registerEntity(EntityBodyGuard.class, "entityBodyGuard", 64, 1, true);
  }
  
  public void registerTileEntities() {
    GameRegistry.registerTileEntity(TileEntitySeptemberLuckyBlock.class, "tileEntitySeptemberLuckyBlock");
    GameRegistry.registerTileEntity(TileEntitySeptemberTrophy.class, "tileEntitySeptemberTrophy");
    GameRegistry.registerTileEntity(TileEntityPrimaryTaupikoEgg.class, "tileEntityPrimaryTaupikoEgg");
  }
  
  public void registerPackets() {
    PLuckyBlock instance = PLuckyBlock.instance;
    registerPacket(SCDarkScreenPacket.Handler.class, SCDarkScreenPacket.class, Side.CLIENT);
    registerPacket(SCTimedLanguagePacket.Handler.class, SCTimedLanguagePacket.class, Side.CLIENT);
    registerPacket(SCGoodPayPacket.Handler.class, SCGoodPayPacket.class, Side.CLIENT);
    registerPacket(CSJobOfferPacket.Handler.class, CSJobOfferPacket.class, Side.SERVER);
    registerPacket(CSGoodFortunePacket.Handler.class, CSGoodFortunePacket.class, Side.SERVER);
    registerPacket(CSTaupikoPacket.Handler.class, CSTaupikoPacket.class, Side.SERVER);
    registerPacket(CSGoodPayPacket.Handler.class, CSGoodPayPacket.class, Side.SERVER);
    registerPacket(CSJobTransferorPacket.Handler.class, CSJobTransferorPacket.class, Side.SERVER);
  }
  
  public void registerItems() {
    ItemsRegister.JOB_OFFER = (Item)new ItemJobOffer();
    GameRegistry.registerItem(ItemsRegister.JOB_OFFER, ItemsRegister.JOB_OFFER.func_77658_a());
    ItemsRegister.TRADE_FILE = (Item)new ItemTradeFile();
    GameRegistry.registerItem(ItemsRegister.TRADE_FILE, ItemsRegister.TRADE_FILE.func_77658_a());
    ItemsRegister.EMPTY_TRADE_FILE = (Item)new ItemEmptyTradeFile();
    GameRegistry.registerItem(ItemsRegister.EMPTY_TRADE_FILE, ItemsRegister.EMPTY_TRADE_FILE.func_77658_a());
    ItemsRegister.BOSS_SUITCASE = (Item)new ItemBossSuitcase();
    GameRegistry.registerItem(ItemsRegister.BOSS_SUITCASE, ItemsRegister.BOSS_SUITCASE.func_77658_a());
    ItemsRegister.JOB_CHUNK_ANALYZER = (Item)new ItemJobChunkAnalyzer();
    GameRegistry.registerItem(ItemsRegister.JOB_CHUNK_ANALYZER, ItemsRegister.JOB_CHUNK_ANALYZER.func_77658_a());
    ItemsRegister.PAPER_AIRPLANE = (Item)new ItemPaperAirplane();
    GameRegistry.registerItem(ItemsRegister.PAPER_AIRPLANE, ItemsRegister.PAPER_AIRPLANE.func_77658_a());
    ItemsRegister.TAUPIKO_EGG = (Item)new ItemTaupikoEgg();
    GameRegistry.registerItem(ItemsRegister.TAUPIKO_EGG, ItemsRegister.TAUPIKO_EGG.func_77658_a());
    ItemsRegister.BUTCHER_KNIFE = (Item)new ItemButcherKnife();
    GameRegistry.registerItem(ItemsRegister.BUTCHER_KNIFE, ItemsRegister.BUTCHER_KNIFE.func_77658_a());
    ItemsRegister.JOB_TRANSFEROR = (Item)new ItemJobTransferor();
    GameRegistry.registerItem(ItemsRegister.JOB_TRANSFEROR, ItemsRegister.JOB_TRANSFEROR.func_77658_a());
    ItemsRegister.SEPTEMBER_TICKET = (Item)new ItemSeptemberTicket();
    GameRegistry.registerItem(ItemsRegister.SEPTEMBER_TICKET, ItemsRegister.SEPTEMBER_TICKET.func_77658_a());
    ItemsRegister.WELDING_GLASSES = (Item)new ItemWeldingGlasses();
    GameRegistry.registerItem(ItemsRegister.WELDING_GLASSES, ItemsRegister.WELDING_GLASSES.func_77658_a());
  }
  
  public void registerBlocks() {
    BlocksRegister.PRIMARY_TAUPIKO_EGG = (Block)new BlockPrimaryTaupikoEgg();
    GameRegistry.registerBlock(BlocksRegister.PRIMARY_TAUPIKO_EGG, BlocksRegister.PRIMARY_TAUPIKO_EGG.func_149739_a());
    BlocksRegister.SEPTEMBER_LUCKY_BLOCK = (Block)new BlockSeptemberLuckyBlock();
    GameRegistry.registerBlock(BlocksRegister.SEPTEMBER_LUCKY_BLOCK, BlocksRegister.SEPTEMBER_LUCKY_BLOCK.func_149739_a());
    BlocksRegister.SEPTEMBER_TROPHY = (Block)new BlockSeptemberTrophy();
    GameRegistry.registerBlock(BlocksRegister.SEPTEMBER_TROPHY, BlocksRegister.SEPTEMBER_TROPHY.func_149739_a());
  }
  
  public void registerCrafts() {}
  
  public void registerLuckyStats() {
    String keyUniqueId = "63873e745b13ee37d92f79db";
    LuckyStatsRewardManager.addReward(LuckyType.SEPTEMBER, 0, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 5), new LuckyStatsReward(ItemsRegister.WELDING_GLASSES), new LuckyStatsReward(ItemsRegister.JOB_QUEST_POTION) });
    LuckyStatsRewardManager.addReward(LuckyType.SEPTEMBER, 1, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 5), new LuckyStatsReward(ItemsRegister.JOB_CHUNK_ANALYZER), new LuckyStatsReward(ItemsRegister.PAPER_AIRPLANE), new LuckyStatsReward("63873e745b13ee37d92f79db", 2) });
    LuckyStatsRewardManager.addReward(LuckyType.SEPTEMBER, 2, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK, 5), new LuckyStatsReward((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK), new LuckyStatsReward(ItemsRegister.JOB_QUEST_POTION), new LuckyStatsReward(ItemsRegister.JOB_QUEST_POTION), new LuckyStatsReward(ItemsRegister.JOB_QUEST_POTION), new LuckyStatsReward(ItemsRegister.JOB_TRANSFEROR) });
    LuckyStatsRewardManager.addReward(LuckyType.SEPTEMBER, 3, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.SEPTEMBER_TROPHY), new LuckyStatsReward((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, 3), new LuckyStatsReward(ItemsRegister.LEGENDARYSTONE_JOBS), new LuckyStatsReward(ItemsRegister.SEPTEMBER_TICKET), new LuckyStatsReward("63873e745b13ee37d92f79db", 5) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\SeptemberCommonModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */