package fr.paladium.palamod.modules.luckyblock.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockAlarm;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockAnvilMoney;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockAtm;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockBallBasket;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockBasket;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockBigHead;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockBlazeAndSteelFire;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockBruteforcer;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockCatDetector;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockChatDetector;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockClipboard;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockColoredLamp;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockDidier;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockDyeingMachine;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockEndium;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockFakeCommandBlock;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockFakeGreenPaladium;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockFakeLava;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockFakeOrePaladium;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockFakePaladium;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockFakePortal;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockFakeTNT;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockFishBowl;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockFlashy;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockHunterPlant;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockJawsTrap;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockKawaiiTNT;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockLeaveBanana;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockMegaSafeChest;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockPetRock;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockPlankBanana;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockSafeChest;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockSleepingBag;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockStalactites;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockStringTrapOff;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockSuperBeacon;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockSuperCraftingTable;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockTombe;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockTrophy;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockTrophyFindium;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockUltraSafeChest;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockWirelessLever;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockWoodBanana;
import fr.paladium.palamod.modules.luckyblock.blocks.ItemBlockEndium;
import fr.paladium.palamod.modules.luckyblock.blocks.LuckyBlockFlower;
import fr.paladium.palamod.modules.luckyblock.blocks.black.BlockBlackTrophy;
import fr.paladium.palamod.modules.luckyblock.blocks.black.BlockFakeFire;
import fr.paladium.palamod.modules.luckyblock.blocks.black.BlockSifflet;
import fr.paladium.palamod.modules.luckyblock.blocks.christmas.BlockChristmasLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.blocks.christmas.BlockChristmasMockup;
import fr.paladium.palamod.modules.luckyblock.blocks.christmas.BlockChristmasTree;
import fr.paladium.palamod.modules.luckyblock.blocks.christmas.BlockPalaDistributor;
import fr.paladium.palamod.modules.luckyblock.blocks.christmas.BlockTrophyChristmas;
import fr.paladium.palamod.modules.luckyblock.blocks.easter.BlockBlueEasterEgg;
import fr.paladium.palamod.modules.luckyblock.blocks.easter.BlockBunnyPlush;
import fr.paladium.palamod.modules.luckyblock.blocks.easter.BlockEasterGift;
import fr.paladium.palamod.modules.luckyblock.blocks.easter.BlockEasterLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.blocks.easter.BlockEasterTrophy;
import fr.paladium.palamod.modules.luckyblock.blocks.easter.BlockEggOfPlenty;
import fr.paladium.palamod.modules.luckyblock.blocks.easter.BlockEndiumMagicBell;
import fr.paladium.palamod.modules.luckyblock.blocks.easter.BlockFakeEasterGift;
import fr.paladium.palamod.modules.luckyblock.blocks.easter.BlockMagicBell;
import fr.paladium.palamod.modules.luckyblock.blocks.easter.BlockYellowEasterEgg;
import fr.paladium.palamod.modules.luckyblock.blocks.halloween.BlockColorFullLantern;
import fr.paladium.palamod.modules.luckyblock.blocks.halloween.BlockDevilXP;
import fr.paladium.palamod.modules.luckyblock.blocks.halloween.BlockHalloweenCommu;
import fr.paladium.palamod.modules.luckyblock.blocks.halloween.BlockHalloweenTrade;
import fr.paladium.palamod.modules.luckyblock.blocks.halloween.BlockHopperHalloween;
import fr.paladium.palamod.modules.luckyblock.blocks.halloween.BlockPumpkinCustom;
import fr.paladium.palamod.modules.luckyblock.blocks.halloween.BlockPumpkinLayer;
import fr.paladium.palamod.modules.luckyblock.blocks.halloween.BlockTrophyHalloween;
import fr.paladium.palamod.modules.luckyblock.blocks.june.BlockAlarmClock;
import fr.paladium.palamod.modules.luckyblock.blocks.june.BlockBrightSound;
import fr.paladium.palamod.modules.luckyblock.blocks.june.BlockJuneLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.blocks.june.BlockJuneTrophy;
import fr.paladium.palamod.modules.luckyblock.blocks.june.BlockRealJukebox;
import fr.paladium.palamod.modules.luckyblock.blocks.june.BlockSharkPlush;
import fr.paladium.palamod.modules.luckyblock.blocks.june.BlockSoundTest;
import fr.paladium.palamod.modules.luckyblock.blocks.june.ItemBlockSoundTest;
import fr.paladium.palamod.modules.luckyblock.blocks.may.BlockDigicode;
import fr.paladium.palamod.modules.luckyblock.blocks.may.BlockFakeGoldBlock;
import fr.paladium.palamod.modules.luckyblock.blocks.may.BlockLilyOfTheValley;
import fr.paladium.palamod.modules.luckyblock.blocks.may.BlockMagneticCompassChest;
import fr.paladium.palamod.modules.luckyblock.blocks.may.BlockMayLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.blocks.may.BlockMayTrophy;
import fr.paladium.palamod.modules.luckyblock.blocks.may.BlockTimedBedrock;
import fr.paladium.palamod.modules.luckyblock.items.ItemBlockTrophy;
import fr.paladium.palamod.modules.luckyblock.items.ItemBlockTrophyFindium;
import fr.paladium.palamod.modules.luckyblock.items.christmas.ItemChristmasMockup;
import fr.paladium.palamod.modules.luckyblock.items.halloween.ItemBlockPumpkinCommu;
import fr.paladium.palamod.modules.luckyblock.items.halloween.ItemPumpkinLayer;
import fr.paladium.palamod.modules.luckyblock.items.may.ItemBlockDigicode;
import fr.paladium.palamod.modules.luckyblock.items.may.ItemBlockLilyOfTheValley;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.block.BlockCreeperPlush;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.block.BlockEndiumHearth;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.block.BlockGoldenCage;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.block.BlockRandomStatue;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.block.BlockStoneHearth;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.block.BlockTrash;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.tile.TileEntityCreeperPlush;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.tile.TileEntityEndiumHearth;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.tile.TileEntityGoldenCage;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.tile.TileEntityRandomStatue;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.tile.TileEntityStoneHearth;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.tile.TileEntityTrash;
import fr.paladium.palamod.modules.luckyblock.structures.blocks.BlockExpirableBedrock;
import fr.paladium.palamod.modules.luckyblock.structures.tiles.TileEntityExpirableBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityATM;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityDyeingMachine;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityFakeCommandBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityMegaSafeChest;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntitySafeChest;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntitySuperBeacon;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityUltraSafeChest;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityWirelessLever;
import fr.paladium.palamod.modules.luckyblock.tileentity.black.TileEntityFakeFire;
import fr.paladium.palamod.modules.luckyblock.tileentity.black.TileEntitySifflet;
import fr.paladium.palamod.modules.luckyblock.tileentity.christmas.TileEntityChristmasLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.christmas.TileEntityChristmasMockup;
import fr.paladium.palamod.modules.luckyblock.tileentity.christmas.TileEntityChristmasTree;
import fr.paladium.palamod.modules.luckyblock.tileentity.christmas.TileEntityPalaDistributor;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityBunnyPlush;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEasterTrophy;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEggOfPlenty;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEndiumMagicBell;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityMagicBell;
import fr.paladium.palamod.modules.luckyblock.tileentity.halloween.TileEntityHalloweenTrade;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityAlarmClock;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityBrightSound;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityJuneLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityJuneTrophy;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityRealJukebox;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntitySharkPlush;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntitySoundTest;
import fr.paladium.palamod.modules.luckyblock.tileentity.may.TileEntityDigicode;
import fr.paladium.palamod.modules.luckyblock.tileentity.may.TileEntityMagneticCompassChest;
import fr.paladium.palamod.modules.luckyblock.tileentity.may.TileEntityMayLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.may.TileEntityMayTrophy;
import fr.paladium.palamod.modules.luckyblock.tileentity.may.TileEntityTimedBedrock;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Blocks {
  public static void register() {
    BlocksRegister.PALADIUM_LUCKY_BLOCK = new BlockLuckyBlock(Material.field_151576_e, LuckyType.PALADIUM, "paladiumLuckyBlock", "palamod:paladium_lucky_block");
    BlocksRegister.ENDIUM_LUCKY_BLOCK = new BlockLuckyBlock(Material.field_151576_e, LuckyType.ENDIUM, "endiumLuckyBlock", "palamod:endium_lucky_block");
    BlocksRegister.HALLOWEEN_LUCKY_BLOCK = new BlockLuckyBlock(Material.field_151576_e, LuckyType.HALLOWEEN, "halloweenLuckyBlock", "palamod:halloween_lucky_block");
    BlocksRegister.CHRISTMAS_LUCKY_BLOCK = (BlockLuckyBlock)new BlockChristmasLuckyBlock(Material.field_151576_e, LuckyType.CHRISTMAS, "christmasLuckyBlock", "palamod:christmas_luckyblock");
    BlocksRegister.FINDIUM_LUCKY_BLOCK = new BlockLuckyBlock(Material.field_151576_e, LuckyType.FINDIUM, "findiumLuckyBlock", "palamod:findium_lucky_block");
    BlocksRegister.BLACK_LUCKY_BLOCK = new BlockLuckyBlock(Material.field_151576_e, LuckyType.BLACK, "blackLuckyBlock", "palamod:black_lucky_block");
    BlocksRegister.EASTER_LUCKY_BLOCK = (BlockLuckyBlock)new BlockEasterLuckyBlock();
    BlocksRegister.MAY_LUCKY_BLOCK = (BlockLuckyBlock)new BlockMayLuckyBlock();
    BlocksRegister.JUNE_LUCKY_BLOCK = (BlockLuckyBlock)new BlockJuneLuckyBlock();
    BlocksRegister.FISH_BOWL = (Block)new BlockFishBowl(Material.field_151576_e);
    BlocksRegister.BLOCK_FAKE_PALADIUM = (Block)new BlockFakePaladium(Material.field_151573_f);
    BlocksRegister.BLOCK_FAKE_GPALADIUM = (Block)new BlockFakeGreenPaladium(Material.field_151573_f);
    BlocksRegister.FLOWER_LUCKY = (Block)new LuckyBlockFlower("flower_lucky");
    BlocksRegister.FAKE_TNT = (Block)new BlockFakeTNT();
    BlocksRegister.FAKE_LAVA = (Block)new BlockFakeLava();
    BlocksRegister.FAKE_PORTAL = (Block)new BlockFakePortal();
    BlocksRegister.BLAZE_AND_STEEL_FIRE = (Block)new BlockBlazeAndSteelFire();
    BlocksRegister.BIG_HEAD = (Block)new BlockBigHead();
    BlocksRegister.COLORED_LAMP = (Block)new BlockColoredLamp();
    BlocksRegister.FLOWER_HUNTER = (new BlockHunterPlant("flower_hunter")).func_149647_a(PLuckyBlock.TAB);
    BlocksRegister.WIRELESS_LEVER = (Block)new BlockWirelessLever();
    BlocksRegister.DYEING_MACHINE = (Block)new BlockDyeingMachine();
    BlocksRegister.KAWAII_TNT = (Block)new BlockKawaiiTNT(0);
    BlocksRegister.LESS_KAWAII_TNT = (Block)new BlockKawaiiTNT(1);
    BlocksRegister.ENDIUM_BLOCK = (Block)new BlockEndium();
    BlocksRegister.ALARM = (Block)new BlockAlarm();
    BlocksRegister.PETROCK = (Block)new BlockPetRock();
    BlocksRegister.JAWSTRAP = (Block)new BlockJawsTrap();
    BlocksRegister.FAKE_COMMAND_BLOCK = (Block)new BlockFakeCommandBlock();
    BlocksRegister.SAFE_CHEST = (Block)new BlockSafeChest("safe_chest");
    BlocksRegister.SAFE_CHEST_MEGA = (Block)new BlockMegaSafeChest("megasafe_chest");
    BlocksRegister.SAFE_CHEST_ULTRA = (Block)new BlockUltraSafeChest("ultrasafe_chest");
    BlocksRegister.BRUTEFORCER = (Block)new BlockBruteforcer("bruteforcer");
    BlocksRegister.CHRISTMAS_TREE = (Block)new BlockChristmasTree();
    BlocksRegister.CHRISTMAS_MOCKUP = (Block)new BlockChristmasMockup();
    BlocksRegister.TROPHY_CHRISTMAS = (Block)new BlockTrophyChristmas();
    BlocksRegister.PALA_DISTRIBUTOR = (Block)new BlockPalaDistributor();
    BlocksRegister.FLASHY_BLOCK = (Block)new BlockFlashy();
    BlocksRegister.DIDIER = (Block)new BlockDidier();
    BlocksRegister.STALACTITES = (Block)new BlockStalactites();
    BlocksRegister.SLEEPING_BAG = (Block)new BlockSleepingBag();
    BlocksRegister.CAT_DETECTOR = (Block)new BlockCatDetector();
    BlocksRegister.WOOD_BANANIER = (Block)new BlockWoodBanana();
    BlocksRegister.PLANKS_BANANIER = (Block)new BlockPlankBanana();
    BlocksRegister.LEAVE_BANANIER = (Block)new BlockLeaveBanana();
    BlocksRegister.FAKE_ORE_PALADIUM = (Block)new BlockFakeOrePaladium();
    BlocksRegister.STRING_TRAP_OFF = (Block)new BlockStringTrapOff();
    BlocksRegister.ANVIL_MONEY = (Block)new BlockAnvilMoney();
    BlocksRegister.CHAT_DETECTOR = new BlockChatDetector();
    BlocksRegister.TOMBE = (Block)new BlockTombe();
    BlocksRegister.BALL_BASKET = (Block)new BlockBallBasket();
    BlocksRegister.BASKET = (Block)new BlockBasket();
    BlocksRegister.SUPER_CRAFTING_TABLE = (Block)new BlockSuperCraftingTable();
    BlocksRegister.clipboard = (new BlockClipboard()).func_149663_c("BiblioClipboard");
    GameRegistry.registerBlock(BlocksRegister.clipboard, "BiblioClipboard");
    BlocksRegister.TROPHY = (Block)new BlockTrophy();
    BlocksRegister.TROPHY_FINDIUM = (Block)new BlockTrophyFindium();
    BlocksRegister.TROPHYHALLOWEEN = (Block)new BlockTrophyHalloween();
    BlocksRegister.TROPHY_FINDIUM = (Block)new BlockTrophyFindium();
    BlocksRegister.SUPER_BEACON = (Block)new BlockSuperBeacon();
    BlocksRegister.ATM = (Block)new BlockAtm();
    BlocksRegister.COLORFUL_LANTERN = (Block)new BlockColorFullLantern();
    BlocksRegister.HALLOWEEN_COMMU = (Block)new BlockHalloweenCommu(false);
    BlocksRegister.HALLOWEEN_COMMU_ON = (Block)new BlockHalloweenCommu(true);
    BlocksRegister.HALLOWEEN_TRADE = (Block)new BlockHalloweenTrade();
    for (int i = 0; i < 8; i++) {
      BlocksRegister.PUMKINCUSTOM[i] = (Block)new BlockPumpkinCustom(false, i + 1);
      GameRegistry.registerBlock(BlocksRegister.PUMKINCUSTOM[i], BlocksRegister.PUMKINCUSTOM[i]
          .func_149739_a());
    } 
    BlocksRegister.PUMKINLAYER = (Block)new BlockPumpkinLayer();
    BlocksRegister.DEVILXP = (Block)new BlockDevilXP();
    BlocksRegister.hopperHallowwen = new BlockHopperHalloween();
    BlocksRegister.CHRISTMAS_TREE = (Block)new BlockChristmasTree();
    BlocksRegister.TROPHY_CHRISTMAS = (Block)new BlockTrophyChristmas();
    BlocksRegister.PALA_DISTRIBUTOR = (Block)new BlockPalaDistributor();
    BlocksRegister.SIFFLET = (Block)new BlockSifflet();
    BlocksRegister.FAKE_FIRE = (Block)new BlockFakeFire();
    BlocksRegister.BLACK_TROPHY = (Block)new BlockBlackTrophy();
    BlocksRegister.YELLOW_EASTER_EGG = (Block)new BlockYellowEasterEgg();
    BlocksRegister.BLUE_EASTER_EGG = (Block)new BlockBlueEasterEgg();
    BlocksRegister.EASTER_GIFT = (Block)new BlockEasterGift();
    BlocksRegister.FAKE_EASTER_GIFT = (Block)new BlockFakeEasterGift();
    BlocksRegister.MAGIC_BELL = (Block)new BlockMagicBell();
    BlocksRegister.EASTER_TROPHY = (Block)new BlockEasterTrophy();
    BlocksRegister.ENDIUM_MAGIC_BELL = (Block)new BlockEndiumMagicBell();
    BlocksRegister.BUNNY_PLUSH = (Block)new BlockBunnyPlush();
    BlocksRegister.EGG_OF_PLENTY = (Block)new BlockEggOfPlenty();
    BlocksRegister.LILY_OF_THE_VALLEY = (Block)new BlockLilyOfTheValley();
    BlocksRegister.FAKE_GOLD_BLOCK = (Block)new BlockFakeGoldBlock();
    BlocksRegister.DIGICODE = (Block)new BlockDigicode();
    BlocksRegister.MAY_TROPHY = (Block)new BlockMayTrophy();
    BlocksRegister.MAGNETIC_COMPASS_CHEST = (Block)new BlockMagneticCompassChest();
    BlocksRegister.TIMED_BEDROCK = (Block)new BlockTimedBedrock();
    BlocksRegister.TRASH = (Block)new BlockTrash();
    BlocksRegister.ENDIUM_HEARTH = (Block)new BlockEndiumHearth();
    BlocksRegister.STONE_HEARTH = (Block)new BlockStoneHearth();
    BlocksRegister.CREEPER_PLUSH = (Block)new BlockCreeperPlush();
    BlocksRegister.GOLDEN_CAGE = (Block)new BlockGoldenCage();
    BlocksRegister.RANDOM_STATUE = (Block)new BlockRandomStatue();
    BlocksRegister.BRIGHT_SOUND = (Block)new BlockBrightSound();
    BlocksRegister.BRIGHT_SOUND_TEST = (Block)new BlockSoundTest();
    BlocksRegister.REAL_JUKEBOX = (Block)new BlockRealJukebox();
    BlocksRegister.JUNE_TROPHY = (Block)new BlockJuneTrophy();
    BlocksRegister.ALARM_CLOCK = (Block)new BlockAlarmClock();
    BlocksRegister.SHARK_PLUSH = (Block)new BlockSharkPlush();
    BlocksRegister.EXPIRABLE_BEDROCK = (Block)new BlockExpirableBedrock();
    GameRegistry.registerBlock(BlocksRegister.TROPHYHALLOWEEN, BlocksRegister.TROPHYHALLOWEEN
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, BlocksRegister.PALADIUM_LUCKY_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, BlocksRegister.ENDIUM_LUCKY_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.HALLOWEEN_LUCKY_BLOCK, BlocksRegister.HALLOWEEN_LUCKY_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.CHRISTMAS_LUCKY_BLOCK, BlocksRegister.CHRISTMAS_LUCKY_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.TROPHY_CHRISTMAS, BlocksRegister.TROPHY_CHRISTMAS
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FISH_BOWL, BlocksRegister.FISH_BOWL
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BLOCK_FAKE_PALADIUM, BlocksRegister.BLOCK_FAKE_PALADIUM
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BLOCK_FAKE_GPALADIUM, BlocksRegister.BLOCK_FAKE_GPALADIUM
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FLOWER_LUCKY, BlocksRegister.FLOWER_LUCKY
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FAKE_TNT, BlocksRegister.FAKE_TNT
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BLAZE_AND_STEEL_FIRE, BlocksRegister.BLAZE_AND_STEEL_FIRE
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BIG_HEAD, BlocksRegister.BIG_HEAD
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.COLORED_LAMP, BlocksRegister.COLORED_LAMP
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FLOWER_HUNTER, BlocksRegister.FLOWER_HUNTER
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.WIRELESS_LEVER, BlocksRegister.WIRELESS_LEVER
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.DYEING_MACHINE, BlocksRegister.DYEING_MACHINE
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.KAWAII_TNT, BlocksRegister.KAWAII_TNT
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.LESS_KAWAII_TNT, BlocksRegister.LESS_KAWAII_TNT
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FAKE_LAVA, BlocksRegister.FAKE_LAVA
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FAKE_PORTAL, BlocksRegister.FAKE_PORTAL
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.ENDIUM_BLOCK, ItemBlockEndium.class, "endium_block");
    GameRegistry.registerBlock(BlocksRegister.ALARM, BlocksRegister.ALARM.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.PETROCK, BlocksRegister.PETROCK.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.JAWSTRAP, BlocksRegister.JAWSTRAP
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FAKE_COMMAND_BLOCK, BlocksRegister.FAKE_COMMAND_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.SAFE_CHEST, BlocksRegister.SAFE_CHEST
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.SAFE_CHEST_MEGA, BlocksRegister.SAFE_CHEST_MEGA
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.SAFE_CHEST_ULTRA, BlocksRegister.SAFE_CHEST_ULTRA
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BRUTEFORCER, BlocksRegister.BRUTEFORCER
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK, BlocksRegister.FINDIUM_LUCKY_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.BLACK_LUCKY_BLOCK, BlocksRegister.BLACK_LUCKY_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FLASHY_BLOCK, BlocksRegister.FLASHY_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.DIDIER, BlocksRegister.DIDIER.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.CAT_DETECTOR, BlocksRegister.CAT_DETECTOR
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.STALACTITES, BlocksRegister.STALACTITES
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.SLEEPING_BAG, BlocksRegister.SLEEPING_BAG
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.WOOD_BANANIER, BlocksRegister.WOOD_BANANIER
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.PLANKS_BANANIER, BlocksRegister.PLANKS_BANANIER
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.LEAVE_BANANIER, BlocksRegister.LEAVE_BANANIER
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FAKE_ORE_PALADIUM, BlocksRegister.FAKE_ORE_PALADIUM
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.STRING_TRAP_OFF, BlocksRegister.STRING_TRAP_OFF
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.ANVIL_MONEY, BlocksRegister.ANVIL_MONEY
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.CHAT_DETECTOR, BlocksRegister.CHAT_DETECTOR
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.TOMBE, BlocksRegister.TOMBE.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BALL_BASKET, BlocksRegister.BALL_BASKET
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.SUPER_CRAFTING_TABLE, BlocksRegister.SUPER_CRAFTING_TABLE
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BASKET, BlocksRegister.BASKET.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.HALLOWEEN_COMMU_ON, BlocksRegister.HALLOWEEN_COMMU_ON
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.HALLOWEEN_COMMU, ItemBlockPumpkinCommu.class, BlocksRegister.HALLOWEEN_COMMU
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.COLORFUL_LANTERN, BlocksRegister.COLORFUL_LANTERN
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.DEVILXP, BlocksRegister.DEVILXP.func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.hopperHallowwen, BlocksRegister.hopperHallowwen
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.PUMKINLAYER, ItemPumpkinLayer.class, BlocksRegister.PUMKINLAYER
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.HALLOWEEN_TRADE, BlocksRegister.HALLOWEEN_TRADE
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.CHRISTMAS_TREE, BlocksRegister.CHRISTMAS_TREE
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.CHRISTMAS_MOCKUP, ItemChristmasMockup.class, BlocksRegister.CHRISTMAS_MOCKUP.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.PALA_DISTRIBUTOR, BlocksRegister.PALA_DISTRIBUTOR
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.TROPHY, ItemBlockTrophy.class, BlocksRegister.TROPHY
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.TROPHY_FINDIUM, ItemBlockTrophyFindium.class, BlocksRegister.TROPHY_FINDIUM
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.SUPER_BEACON, BlocksRegister.SUPER_BEACON
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.ATM, BlocksRegister.ATM.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.SIFFLET, BlocksRegister.SIFFLET.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FAKE_FIRE, BlocksRegister.FAKE_FIRE
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BLACK_TROPHY, ItemBlockTrophy.class, "blockBlackTrophy");
    GameRegistry.registerBlock((Block)BlocksRegister.EASTER_LUCKY_BLOCK, BlocksRegister.EASTER_LUCKY_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.YELLOW_EASTER_EGG, BlocksRegister.YELLOW_EASTER_EGG
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BLUE_EASTER_EGG, BlocksRegister.BLUE_EASTER_EGG
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.EASTER_GIFT, BlocksRegister.EASTER_GIFT
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FAKE_EASTER_GIFT, BlocksRegister.FAKE_EASTER_GIFT
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.MAGIC_BELL, BlocksRegister.MAGIC_BELL
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.EASTER_TROPHY, BlocksRegister.EASTER_TROPHY
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.ENDIUM_MAGIC_BELL, BlocksRegister.ENDIUM_MAGIC_BELL
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BUNNY_PLUSH, BlocksRegister.BUNNY_PLUSH
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.EGG_OF_PLENTY, BlocksRegister.EGG_OF_PLENTY
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.MAY_LUCKY_BLOCK, BlocksRegister.MAY_LUCKY_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.LILY_OF_THE_VALLEY, ItemBlockLilyOfTheValley.class, BlocksRegister.LILY_OF_THE_VALLEY
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FAKE_GOLD_BLOCK, BlocksRegister.FAKE_GOLD_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.DIGICODE, ItemBlockDigicode.class, BlocksRegister.DIGICODE
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.MAY_TROPHY, BlocksRegister.MAY_TROPHY
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.MAGNETIC_COMPASS_CHEST, BlocksRegister.MAGNETIC_COMPASS_CHEST
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.TIMED_BEDROCK, BlocksRegister.TIMED_BEDROCK
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.TRASH, BlocksRegister.TRASH
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.ENDIUM_HEARTH, BlocksRegister.ENDIUM_HEARTH
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.STONE_HEARTH, BlocksRegister.STONE_HEARTH
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.CREEPER_PLUSH, BlocksRegister.CREEPER_PLUSH
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.GOLDEN_CAGE, BlocksRegister.GOLDEN_CAGE
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.RANDOM_STATUE, BlocksRegister.RANDOM_STATUE
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.JUNE_LUCKY_BLOCK, BlocksRegister.JUNE_LUCKY_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BRIGHT_SOUND, BlocksRegister.BRIGHT_SOUND
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BRIGHT_SOUND_TEST, ItemBlockSoundTest.class, BlocksRegister.BRIGHT_SOUND_TEST
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.REAL_JUKEBOX, BlocksRegister.REAL_JUKEBOX
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.JUNE_TROPHY, BlocksRegister.JUNE_TROPHY
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.ALARM_CLOCK, BlocksRegister.ALARM_CLOCK
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.SHARK_PLUSH, BlocksRegister.SHARK_PLUSH
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.EXPIRABLE_BEDROCK, BlocksRegister.EXPIRABLE_BEDROCK
        .func_149739_a());
    GameRegistry.registerTileEntity(TileEntityLuckyBlock.class, "palamod:tileEntityLuckyBlock");
    GameRegistry.registerTileEntity(TileEntityChristmasLuckyBlock.class, "palamod:tileEntityChristmasLuckyBlock");
    GameRegistry.registerTileEntity(TileEntityWirelessLever.class, "palamod:tileEntityWirelessLever");
    GameRegistry.registerTileEntity(TileEntityDyeingMachine.class, "palamod:tileEntityDyeingMachine");
    GameRegistry.registerTileEntity(TileEntitySuperBeacon.class, "palamod:tileEntitySuperBeacon");
    GameRegistry.registerTileEntity(TileEntityFakeCommandBlock.class, "palamod:tileEntityFakeCommandBlock");
    GameRegistry.registerTileEntity(TileEntitySafeChest.class, "palamod:tileEntitySafeChest");
    GameRegistry.registerTileEntity(TileEntityMegaSafeChest.class, "palamod:tileEntityMegaSafeChest");
    GameRegistry.registerTileEntity(TileEntityUltraSafeChest.class, "palamod:tileEntityUltraSafeChest");
    GameRegistry.registerTileEntity(TileEntityATM.class, "palamod:tileEntityATM");
    GameRegistry.registerTileEntity(TileEntityChristmasTree.class, "palamod:tileEntityChristmasTree");
    GameRegistry.registerTileEntity(TileEntityChristmasMockup.class, "palamod:tileEntityChristmasMockup");
    GameRegistry.registerTileEntity(TileEntityPalaDistributor.class, "palamod:tileEntityPalaDistributor");
    GameRegistry.registerTileEntity(TileEntitySifflet.class, "palamod:tileEntitySifflet");
    GameRegistry.registerTileEntity(TileEntityFakeFire.class, ":tileEntityFakeFire");
    GameRegistry.registerTileEntity(TileEntityHalloweenTrade.class, "palamod:tileEntityHalloweenTrade");
    GameRegistry.registerTileEntity(TileEntityEasterTrophy.class, "palamod:tileEntityEasterTrophy");
    GameRegistry.registerTileEntity(TileEntityMagicBell.class, "palamod:tileEntityMagicBell");
    GameRegistry.registerTileEntity(TileEntityBunnyPlush.class, "palamod:tileEntityBunnyPlush");
    GameRegistry.registerTileEntity(TileEntityEggOfPlenty.class, "palamod:tileEntityEggOfPlenty");
    GameRegistry.registerTileEntity(TileEntityEndiumMagicBell.class, "palamod:tileEntityEndiumMagicBell");
    GameRegistry.registerTileEntity(TileEntityDigicode.class, "palamod:tileEntityDigicode");
    GameRegistry.registerTileEntity(TileEntityMagneticCompassChest.class, "palamod:tileEntityMagneticCompassChest");
    GameRegistry.registerTileEntity(TileEntityTimedBedrock.class, "palamod:tileEntityTimedBedrock");
    GameRegistry.registerTileEntity(TileEntityMayLuckyBlock.class, "palamod:tileEntityMayLuckyBlock");
    GameRegistry.registerTileEntity(TileEntityMayTrophy.class, "palamod:tileEntityMayTrophy");
    GameRegistry.registerTileEntity(TileEntityBrightSound.class, "palamod:tileEntityBrightSound");
    GameRegistry.registerTileEntity(TileEntitySoundTest.class, "palamod:tileEntitySoundTest");
    GameRegistry.registerTileEntity(TileEntityRealJukebox.class, "palamod:tileEntityRealJukebox");
    GameRegistry.registerTileEntity(TileEntityJuneLuckyBlock.class, "palamod:tileEntityJuneLuckyBlock");
    GameRegistry.registerTileEntity(TileEntityJuneTrophy.class, "palamod:tileEntityJuneTrophy");
    GameRegistry.registerTileEntity(TileEntityAlarmClock.class, "palamod:tileEntityAlarmClock");
    GameRegistry.registerTileEntity(TileEntitySharkPlush.class, "palamod:tileEntitySharkPlush");
    GameRegistry.registerTileEntity(TileEntityCreeperPlush.class, "palamod:tileEntityCreeperPlush");
    GameRegistry.registerTileEntity(TileEntityEndiumHearth.class, "palamod:tileEntityEndiumHearth");
    GameRegistry.registerTileEntity(TileEntityGoldenCage.class, "palamod:tileEntityGoldenCage");
    GameRegistry.registerTileEntity(TileEntityRandomStatue.class, "palamod:tileEntityRandomStatue");
    GameRegistry.registerTileEntity(TileEntityStoneHearth.class, "palamod:tileEntityStoneHearth");
    GameRegistry.registerTileEntity(TileEntityTrash.class, "palamod:tileEntityTrash");
    GameRegistry.registerTileEntity(TileEntityExpirableBlock.class, "palamod:tileEntityExpirableBlock");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\init\Blocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */