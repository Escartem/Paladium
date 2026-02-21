package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.items.may.BaseItemTicket;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.manager.LuckyStatsRewardManager;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.reward.LuckyStatsReward;
import fr.paladium.palamod.modules.luckyblock.monthly.AbstractMonthlyModule;
import fr.paladium.palamod.modules.luckyblock.monthly.SideType;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.blocks.BlockBarrel;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.blocks.BlockDecryptedComputer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.blocks.BlockEncryptedComputer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.blocks.BlockJulyLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.blocks.BlockJulyTrophy;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.blocks.BlockParrotPlush;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.dialogs.FlintDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.dialogs.PirateKingDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.dialogs.XavierDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.entities.EntityCaptainFlint;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.entities.EntityDutchBoat;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.events.PhantomBoatEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemBlunderbuss;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemDryMessage;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemDutchBoat;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemEyePatch;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemFlintEgg;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemGhostlySword;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemKingSkull;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemParrotSeeds;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemPipe;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemPirateKingMessage;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemSailorTooth;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemSeaBottle;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemSkeletonSkull;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemSkullCursedKing;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemSwordPistol;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemTelescope;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemTreasureMap;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemWoodenLeg;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.consumables.ItemInvulnerabilityPotion;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.consumables.ItemOrangeConsumable;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.instruments.impl.ItemAccordion;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.instruments.impl.ItemDrum;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.packets.CSEncryptedComputerPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.packets.CSJumpPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tiles.TileEntityBarrel;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tiles.TileEntityDecryptedComputer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tiles.TileEntityEncryptedComputer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tiles.TileEntityJulyLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tiles.TileEntityJulyTrophy;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tiles.TileEntityParrotPlush;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class JulyCommonModule extends AbstractMonthlyModule {
  public JulyCommonModule() {
    super(SideType.BOTH, LuckyType.JULY);
  }
  
  public void registerEventHandlers() {
    super.registerEventHandlers();
    LuckyEvents.values(LuckyType.JULY).forEach(e -> {
          ALuckyEvent event = e.getEvent();
          if (!(event instanceof fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.PhantomBoatEvent))
            registerEventHandler(event); 
        });
    registerEventHandler(new PhantomBoatEventHandler());
  }
  
  public void registerDialogManagers() {
    registerDialogManager((AbstractDialogManager)new XavierDialogManager());
    registerDialogManager((AbstractDialogManager)new PirateKingDialogManager());
    registerDialogManager((AbstractDialogManager)new FlintDialogManager());
  }
  
  public void registerEntities() {
    PLuckyBlock.instance.registerEntity(EntityCaptainFlint.class, "entityCaptainFlint", 64, 1, true);
    PLuckyBlock.instance.registerEntity(EntityDutchBoat.class, "entityDutchBoat", 64, 1, true);
  }
  
  public void registerTileEntities() {
    GameRegistry.registerTileEntity(TileEntityBarrel.class, "barrel");
    GameRegistry.registerTileEntity(TileEntityParrotPlush.class, "tileEntityParrotPlush");
    GameRegistry.registerTileEntity(TileEntityJulyLuckyBlock.class, "tileEntityJulyLuckyBlock");
    GameRegistry.registerTileEntity(TileEntityJulyTrophy.class, "tileEntityJulyTrophy");
    GameRegistry.registerTileEntity(TileEntityEncryptedComputer.class, "tileEntityEncryptedComputer");
    GameRegistry.registerTileEntity(TileEntityDecryptedComputer.class, "tileEntityDecryptedComputer");
  }
  
  public void registerPackets() {
    PLuckyBlock instance = PLuckyBlock.instance;
    instance.registerPacket(CSJumpPacket.Handler.class, CSJumpPacket.class, Side.SERVER);
    instance.registerPacket(CSEncryptedComputerPacket.Handler.class, CSEncryptedComputerPacket.class, Side.SERVER);
  }
  
  public void registerItems() {
    ItemsRegister.PIPE = (Item)new ItemPipe();
    GameRegistry.registerItem(ItemsRegister.PIPE, ItemsRegister.PIPE.func_77658_a());
    ItemsRegister.SKULL_CURSED_KING = (Item)new ItemSkullCursedKing();
    GameRegistry.registerItem(ItemsRegister.SKULL_CURSED_KING, ItemsRegister.SKULL_CURSED_KING.func_77658_a());
    ItemsRegister.KING_SKULL = (Item)new ItemKingSkull();
    GameRegistry.registerItem(ItemsRegister.KING_SKULL, ItemsRegister.KING_SKULL.func_77658_a());
    ItemsRegister.ORANGE_CONSUMABLE = (Item)new ItemOrangeConsumable();
    GameRegistry.registerItem(ItemsRegister.ORANGE_CONSUMABLE, ItemsRegister.ORANGE_CONSUMABLE.func_77658_a());
    ItemsRegister.INVULNERABILITY_POTION = (Item)new ItemInvulnerabilityPotion();
    GameRegistry.registerItem(ItemsRegister.INVULNERABILITY_POTION, ItemsRegister.INVULNERABILITY_POTION.func_77658_a());
    ItemsRegister.DRY_MESSAGE = (Item)new ItemDryMessage();
    GameRegistry.registerItem(ItemsRegister.DRY_MESSAGE, ItemsRegister.DRY_MESSAGE.func_77658_a());
    ItemsRegister.PIRATE_KING_MESSAGE = (Item)new ItemPirateKingMessage();
    GameRegistry.registerItem(ItemsRegister.PIRATE_KING_MESSAGE, ItemsRegister.PIRATE_KING_MESSAGE.func_77658_a());
    ItemsRegister.SEA_BOTTLE = (Item)new ItemSeaBottle();
    GameRegistry.registerItem(ItemsRegister.SEA_BOTTLE, ItemsRegister.SEA_BOTTLE.func_77658_a());
    ItemsRegister.SAILOR_TOOTH = (Item)new ItemSailorTooth();
    GameRegistry.registerItem(ItemsRegister.SAILOR_TOOTH, ItemsRegister.SAILOR_TOOTH.func_77658_a());
    ItemsRegister.TELESCOPE = (Item)new ItemTelescope();
    GameRegistry.registerItem(ItemsRegister.TELESCOPE, ItemsRegister.TELESCOPE.func_77658_a());
    ItemsRegister.GHOSTLY_SWORD = (Item)new ItemGhostlySword();
    GameRegistry.registerItem(ItemsRegister.GHOSTLY_SWORD, ItemsRegister.GHOSTLY_SWORD.func_77658_a());
    ItemsRegister.JULY_TICKET = (Item)new BaseItemTicket("july_ticket");
    GameRegistry.registerItem(ItemsRegister.JULY_TICKET, ItemsRegister.JULY_TICKET.func_77658_a());
    ItemsRegister.TREASURE_MAP = (Item)new ItemTreasureMap();
    GameRegistry.registerItem(ItemsRegister.TREASURE_MAP, ItemsRegister.TREASURE_MAP.func_77658_a());
    ItemsRegister.WOODEN_LEG = (Item)new ItemWoodenLeg();
    GameRegistry.registerItem(ItemsRegister.WOODEN_LEG, ItemsRegister.WOODEN_LEG.func_77658_a());
    ItemsRegister.ACCORDION = (Item)new ItemAccordion();
    GameRegistry.registerItem(ItemsRegister.ACCORDION, ItemsRegister.ACCORDION.func_77658_a());
    ItemsRegister.DRUM = (Item)new ItemDrum();
    GameRegistry.registerItem(ItemsRegister.DRUM, ItemsRegister.DRUM.func_77658_a());
    ItemsRegister.DUTCH_BOAT = (Item)new ItemDutchBoat();
    GameRegistry.registerItem(ItemsRegister.DUTCH_BOAT, ItemsRegister.DUTCH_BOAT.func_77658_a());
    ItemsRegister.EYE_PATCH = (Item)new ItemEyePatch();
    GameRegistry.registerItem(ItemsRegister.EYE_PATCH, ItemsRegister.EYE_PATCH.func_77658_a());
    ItemsRegister.PARROT_SEEDS = (Item)new ItemParrotSeeds();
    GameRegistry.registerItem(ItemsRegister.PARROT_SEEDS, ItemsRegister.PARROT_SEEDS.func_77658_a());
    ItemsRegister.BLUNDERBUSS = (Item)new ItemBlunderbuss();
    GameRegistry.registerItem(ItemsRegister.BLUNDERBUSS, ItemsRegister.BLUNDERBUSS.func_77658_a());
    ItemsRegister.SWORD_PISTOL = (Item)new ItemSwordPistol();
    GameRegistry.registerItem(ItemsRegister.SWORD_PISTOL, ItemsRegister.SWORD_PISTOL.func_77658_a());
    ItemsRegister.FLINT_EGG = (Item)new ItemFlintEgg();
    GameRegistry.registerItem(ItemsRegister.FLINT_EGG, ItemsRegister.FLINT_EGG.func_77658_a());
    ItemsRegister.TOOTH_SKELETON_SKULL = (Item)new ItemSkeletonSkull();
    GameRegistry.registerItem(ItemsRegister.TOOTH_SKELETON_SKULL, ItemsRegister.TOOTH_SKELETON_SKULL.func_77658_a());
  }
  
  public void registerBlocks() {
    BlocksRegister.BARREL = (Block)new BlockBarrel();
    GameRegistry.registerBlock(BlocksRegister.BARREL, BlocksRegister.BARREL.func_149739_a());
    BlocksRegister.PARROT_PLUSH = (Block)new BlockParrotPlush();
    GameRegistry.registerBlock(BlocksRegister.PARROT_PLUSH, BlocksRegister.PARROT_PLUSH.func_149739_a());
    BlocksRegister.JULY_LUCKY_BLOCK = (Block)new BlockJulyLuckyBlock();
    GameRegistry.registerBlock(BlocksRegister.JULY_LUCKY_BLOCK, BlocksRegister.JULY_LUCKY_BLOCK.func_149739_a());
    BlocksRegister.ENCRYPTED_COMPUTER = (Block)new BlockEncryptedComputer();
    GameRegistry.registerBlock(BlocksRegister.ENCRYPTED_COMPUTER, BlocksRegister.ENCRYPTED_COMPUTER.func_149739_a());
    BlocksRegister.DECRYPTED_COMPUTER = (Block)new BlockDecryptedComputer();
    GameRegistry.registerBlock(BlocksRegister.DECRYPTED_COMPUTER, BlocksRegister.DECRYPTED_COMPUTER.func_149739_a());
    BlocksRegister.JULY_TROPHY = (Block)new BlockJulyTrophy();
    GameRegistry.registerBlock(BlocksRegister.JULY_TROPHY, BlocksRegister.JULY_TROPHY.func_149739_a());
  }
  
  public void registerCrafts() {
    GameRegistry.addSmelting(ItemsRegister.DRY_MESSAGE, new ItemStack(ItemsRegister.PIRATE_KING_MESSAGE), 0.1F);
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151100_aR, 8, 15), new Object[] { ItemsRegister.SAILOR_TOOTH });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.KING_SKULL), new Object[] { "   ", "ABA", "AAA", 
          Character.valueOf('A'), ItemsRegister.SAILOR_TOOTH, 
          Character.valueOf('B'), ItemsRegister.TOOTH_SKELETON_SKULL });
  }
  
  public void registerLuckyStats() {
    LuckyStatsRewardManager.addReward(LuckyType.JULY, 0, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 5), new LuckyStatsReward(BlocksRegister.BARREL, 3) });
    LuckyStatsRewardManager.addReward(LuckyType.JULY, 1, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 5), new LuckyStatsReward(ModBlocks.banner, 1, 16), new LuckyStatsReward(ItemsRegister.DUTCH_BOAT) });
    LuckyStatsRewardManager.addReward(LuckyType.JULY, 2, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK, 5), new LuckyStatsReward(ItemsRegister.FLINT_EGG), new LuckyStatsReward(ItemsRegister.SKULL_CURSED_KING) });
    LuckyStatsRewardManager.addReward(LuckyType.JULY, 3, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.JULY_TROPHY), new LuckyStatsReward((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, 3), new LuckyStatsReward(ItemsRegister.LEGENDARYSTONE_RANDOM), new LuckyStatsReward(ItemsRegister.JULY_TICKET) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\JulyCommonModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */