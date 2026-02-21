package fr.paladium.palamod.api;

import fr.paladium.palamod.common.blocks.BaseBlockCrops;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.BlockAlchemist;
import fr.paladium.palamod.modules.enderchest.block.BlockPaladiumEnderChest;
import fr.paladium.palamod.modules.homefinder.common.blocks.BlockHomeFinder;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockChatDetector;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.blocks.halloween.BlockHopperHalloween;
import fr.paladium.palamod.modules.miner.dimminer.common.block.BlockMinerPortal;
import fr.paladium.palamod.modules.miner.dimminer.common.block.BlockWitheredReinforcedPiston;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockAmethystChest;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockCave;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockConnectedDirt;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockCustomWeb;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockEndiumChest;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockGPaladiumChest;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockHardenedObsidian;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockInvisible;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockInvisibleCollide;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockPaladiumChest;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockSpike;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockTitaneChest;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockTotem;
import fr.paladium.palamod.modules.paladium.common.blocks.bush.BlockXPBush;
import fr.paladium.palamod.modules.paladium.common.blocks.decorative.BaseBlockSlab;
import fr.paladium.palamod.modules.paladium.common.blocks.decorative.BaseBlockSlabHardened;
import fr.paladium.palamod.modules.paladium.common.blocks.decorative.BaseBlockStairs;
import fr.paladium.palamod.modules.paladium.common.blocks.decorative.BaseBlockStairsHardened;
import fr.paladium.palamod.modules.paladium.common.blocks.decorative.BlockObsidianDoor;
import fr.paladium.palamod.modules.paladium.common.blocks.decorative.BlockObsidianTrapDoor;
import fr.paladium.palamod.modules.paladium.common.blocks.fluids.BlockAngelicWater;
import fr.paladium.palamod.modules.paladium.common.blocks.fluids.BlockSulfuricWater;
import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockAlchemyCreator;
import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockBowMachine;
import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockCobbleBreaker;
import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockCrusher;
import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockForge;
import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockOnlineDetector;
import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockPaladiumFurnace;
import net.minecraft.block.Block;

public class BlocksRegister {
  public static Block PALADIUM_MACHINE_BLOCK;
  
  public static Block SLIMEPAD_BLOCK;
  
  public static BlockPaladiumChest PALADIUM_CHEST;
  
  public static BlockEndiumChest ENDIUM_CHEST;
  
  public static BlockTitaneChest TITANE_CHEST;
  
  public static BlockAmethystChest AMETHYST_CHEST;
  
  public static BlockGPaladiumChest GPALADIUM_CHEST;
  
  public static BlockPaladiumEnderChest PALADIUM_ENDER_CHEST;
  
  public static Block PRINT_PRESS;
  
  public static Block TYPE_MACHINE;
  
  public static Block SHULKER_WHITE;
  
  public static Block SHULKER_GREEN;
  
  public static Block SHULKER_RED;
  
  public static Block SHULKER_BLUE;
  
  public static Block FLOWER_HARPAGOPHYTUM;
  
  public static Block FLOWER_ABSINTHE;
  
  public static Block FLOWER_ACTAEAPACHYPODA;
  
  public static Block FLOWER_CLATHRUSARCHERI;
  
  public static Block FLOWER_ENDIUM;
  
  public static Block FLOWER_PALADIUM;
  
  public static Block FLOWER_MINT;
  
  public static Block FLOWER_ORTIE;
  
  public static Block FLOWER_SAUGE;
  
  public static Block FLOWER_TREFLE;
  
  public static Block FLOWER_MINERAL;
  
  public static Block BLOCK_FINDIUM;
  
  public static Block BLOCK_PALADIUM;
  
  public static Block BLOCK_TITANE;
  
  public static Block BLOCK_AMETHYST;
  
  public static Block BLOCK_GPALADIUM;
  
  public static Block BLOCK_FAKE_PALADIUM;
  
  public static Block BLOCK_FAKE_GPALADIUM;
  
  public static Block BLOCK_TRIXIUM;
  
  public static Block BLOCK_CLOUD;
  
  public static BlockForge FORGE;
  
  public static BlockForge LIT_FORGE;
  
  public static BlockPaladiumFurnace PALADIUM_FURNACE;
  
  public static BlockPaladiumFurnace LIT_PALADIUM_FURNACE;
  
  public static BlockAlchemyCreator ALCHEMY_CREATOR_BLOCK;
  
  public static BlockBowMachine BOW_MACHINE_BLOCK;
  
  public static BlockCobbleBreaker COBBLEBREAKER;
  
  public static BlockCrusher CRUSHER;
  
  public static BlockOnlineDetector ONLINE_DETECTOR_BLOCK;
  
  public static BlockTotem Totem;
  
  public static Block FLOWER_FARM;
  
  public static BlockSulfuricWater FLUIDS_SULFURICWATER;
  
  public static BlockAngelicWater FLUIDS_ANGELICWATER;
  
  public static BaseBlockCrops CROPS_ORANGEBLUE;
  
  public static BaseBlockCrops CROPS_KIWANO;
  
  public static BaseBlockCrops CROPS_CHERVIL;
  
  public static BaseBlockCrops CROPS_EGGPLANT;
  
  public static BaseBlockCrops CROPS_ICE;
  
  public static BlockXPBush BUSH_XP_BERRY;
  
  public static BlockConnectedDirt connectedDirt;
  
  public static BlockConnectedDirt connectedDirtTilled;
  
  public static BlockObsidianDoor OBSIDIAN_BLOCK_DOOR;
  
  public static BlockObsidianTrapDoor OBSIDIAN_TRAP_DOOR;
  
  public static BaseBlockStairs OBSIDIAN_STAIRS;
  
  public static BaseBlockSlab OBSIDIAN_SLAB;
  
  public static BaseBlockStairsHardened OBSIDIAN_HARDENED_STAIRS;
  
  public static BaseBlockSlabHardened OBSIDIAN_HARDENED_SLAB;
  
  public static BaseBlockStairs FINDIUM_STAIRS;
  
  public static BaseBlockSlab FINDIUM_SLAB;
  
  public static BaseBlockStairs PALADIUM_STAIRS;
  
  public static BaseBlockSlab PALADIUM_SLAB;
  
  public static BaseBlockStairs TITANE_STAIRS;
  
  public static BaseBlockSlab TITANE_SLAB;
  
  public static BaseBlockStairs AMETHYST_STAIRS;
  
  public static BaseBlockSlab AMETHYST_SLAB;
  
  public static Block STATUE;
  
  public static Block FACTORY;
  
  public static Block FUTURA;
  
  public static BlockSpike SPIKE_WOOD;
  
  public static BlockSpike SPIKE_IRON;
  
  public static BlockSpike SPIKE_GOLD;
  
  public static BlockSpike SPIKE_DIAMOND;
  
  public static BlockSpike SPIKE_AMETHYST;
  
  public static BlockSpike SPIKE_TITANE;
  
  public static BlockSpike SPIKE_PALADIUM;
  
  public static BlockCustomWeb WEB_CUSTOM;
  
  public static BlockCave CAVE_BLOCK;
  
  public static BlockInvisible INVISIBLE_BLOCK;
  
  public static BlockInvisibleCollide COLLIDE_INVISIBLE_BLOCK;
  
  public static BlockHardenedObsidian HARDENED_OBSIDIAN;
  
  public static Block CORRUPTED_BLOCK;
  
  public static Block LAVA_SPONGE;
  
  public static BlockLuckyBlock PALADIUM_LUCKY_BLOCK;
  
  public static BlockLuckyBlock ENDIUM_LUCKY_BLOCK;
  
  public static BlockLuckyBlock FINDIUM_LUCKY_BLOCK;
  
  public static BlockLuckyBlock HALLOWEEN_LUCKY_BLOCK;
  
  public static BlockLuckyBlock CHRISTMAS_LUCKY_BLOCK;
  
  public static BlockLuckyBlock BLACK_LUCKY_BLOCK;
  
  public static BlockLuckyBlock EASTER_LUCKY_BLOCK;
  
  public static BlockLuckyBlock MAY_LUCKY_BLOCK;
  
  public static BlockLuckyBlock JUNE_LUCKY_BLOCK;
  
  public static Block EXPIRABLE_BEDROCK;
  
  public static Block FISH_BOWL;
  
  public static Block FAKE_TNT;
  
  public static Block FAKE_LAVA;
  
  public static Block FAKE_PORTAL;
  
  public static Block BLAZE_AND_STEEL_FIRE;
  
  public static Block BIG_HEAD;
  
  public static Block COLORED_LAMP;
  
  public static Block FLOWER_LUCKY;
  
  public static Block FLOWER_HUNTER;
  
  public static Block WIRELESS_LEVER;
  
  public static Block DYEING_MACHINE;
  
  public static Block KAWAII_TNT;
  
  public static Block LESS_KAWAII_TNT;
  
  public static Block ENDIUM_BLOCK;
  
  public static Block ALARM;
  
  public static Block PETROCK;
  
  public static Block JAWSTRAP;
  
  public static Block FAKE_COMMAND_BLOCK;
  
  public static Block SAFE_CHEST;
  
  public static Block SAFE_CHEST_MEGA;
  
  public static Block SAFE_CHEST_ULTRA;
  
  public static Block BRUTEFORCER;
  
  public static Block COLORFUL_LANTERN;
  
  public static Block HALLOWEEN_COMMU;
  
  public static Block HALLOWEEN_TRADE;
  
  public static Block[] PUMKINCUSTOM = new Block[8];
  
  public static Block PUMKINLAYER;
  
  public static Block DEVILXP;
  
  public static BlockHopperHalloween hopperHallowwen;
  
  public static Block CHRISTMAS_TREE;
  
  public static Block CHRISTMAS_MOCKUP;
  
  public static Block PALA_DISTRIBUTOR;
  
  public static Block FLASHY_BLOCK;
  
  public static Block DIDIER;
  
  public static Block CAT_DETECTOR;
  
  public static Block STALACTITES;
  
  public static Block SLEEPING_BAG;
  
  public static Block WOOD_BANANIER;
  
  public static Block PLANKS_BANANIER;
  
  public static Block LEAVE_BANANIER;
  
  public static Block FAKE_ORE_PALADIUM;
  
  public static Block STRING_TRAP_OFF;
  
  public static Block ANVIL_MONEY;
  
  public static BlockChatDetector CHAT_DETECTOR;
  
  public static Block TOMBE;
  
  public static Block BASKET;
  
  public static Block BALL_BASKET;
  
  public static Block SUPER_CRAFTING_TABLE;
  
  public static Block clipboard;
  
  public static Block TROPHY;
  
  public static Block TROPHYHALLOWEEN;
  
  public static Block SUPER_BEACON;
  
  public static Block ATM;
  
  public static Block TROPHY_CHRISTMAS;
  
  public static Block TROPHY_FINDIUM;
  
  public static Block TNT_EFFECT;
  
  public static Block TNT_BIG;
  
  public static Block TNT_ENDIUM;
  
  public static Block TNT_WITHER;
  
  public static Block TNT_SPONGE;
  
  public static Block OBSI_BIG;
  
  public static Block OBSI_LAVA;
  
  public static Block OBSI_FAKE;
  
  public static Block OBSI_BOOM;
  
  public static Block OBSI_MEGABOOM;
  
  public static Block OBSI_SPIKE;
  
  public static Block OBSI_SLIME;
  
  public static Block OBSI_POISON;
  
  public static Block OBSI_WITHER;
  
  public static Block OBSI_REDSTONE;
  
  public static Block OBSI_ANTIAGGRO;
  
  public static Block OBSI_FROZEN;
  
  public static Block OBSI_KNOCKBACK;
  
  public static Block ELEVATOR_BLOCK;
  
  public static Block PERLINPINPIN_POWER;
  
  public static Block SACRIFICE_HOTEL;
  
  public static Block ENDIUM_TOTEM;
  
  public static Block BAMBOO;
  
  public static Block BAMBOO_BLOCK;
  
  public static Block PALADIUM_HOPPER;
  
  public static Block DRAWBRIDGE;
  
  public static Block BEDROCK_TNT;
  
  public static BlockWitheredReinforcedPiston WITHERED_REINFORCED_PISTON;
  
  public static Block VOID_STONE;
  
  public static Block AUTOCRAFTER;
  
  public static Block WITHERED_OBSIDIAN;
  
  public static BlockMinerPortal MINER_PORTAL;
  
  public static Block STRANGE_GRASS;
  
  public static Block STRANGE_SAND;
  
  public static Block STRANGE_DIRT;
  
  public static Block STRANGE_COBBLE;
  
  public static Block STRANGE_STONE;
  
  public static Block STRANGE_OAK_LOG;
  
  public static Block STRANGE_SPRUCE_LOG;
  
  public static Block STRANGE_JUNGLE_LOG;
  
  public static Block STRANGE_ACACIA_LOG;
  
  public static Block STRANGE_DARK_OAK_LOG;
  
  public static Block STRANGE_OAK_LEAVES;
  
  public static Block STRANGE_SPRUCE_LEAVES;
  
  public static Block STRANGE_JUNGLE_LEAVES;
  
  public static Block STRANGE_ACACIA_LEAVES;
  
  public static Block STRANGE_DARK_OAK_LEAVES;
  
  public static Block REED;
  
  public static Block THISTLE;
  
  public static Block HOLLOW_LOG;
  
  public static Block MUD;
  
  public static Block ENDIUM_CAVE_BLOCK;
  
  public static Block ENDIUM_NUGGET_ORE;
  
  public static Block INVOKER_ORE;
  
  public static BlockAlchemist EXTRACTOR;
  
  public static BlockAlchemist TANK_GOLD;
  
  public static BlockAlchemist TANK_AMETHYSTE;
  
  public static BlockAlchemist TANK_TITANE;
  
  public static BlockAlchemist TANK_PALADIUM;
  
  public static BlockAlchemist CAULDRON_BLOCK;
  
  public static BlockAlchemist CAULDRON_CORE;
  
  public static BlockAlchemist CAULDRON_TANKSUPPORT;
  
  public static BlockAlchemist GREEN_GLUEBALL_BLOCK;
  
  public static BlockAlchemist BLUE_GLUEBALL_BLOCK;
  
  public static BlockAlchemist YELLOW_GLUEBALL_BLOCK;
  
  public static BlockAlchemist RED_GLUEBALL_BLOCK;
  
  public static BlockAlchemist ORANGE_GLUEBALL_BLOCK;
  
  public static BlockAlchemist GRAY_GLUEBALL_BLOCK;
  
  public static BlockAlchemist PURPLE_GLUEBALL_BLOCK;
  
  public static BlockAlchemist GREEN_FLASH_GLUEBALL_BLOCK;
  
  public static BlockAlchemist GREEN_DARK_GLUEBALL_BLOCK;
  
  public static BlockAlchemist CYAN_GLUEBALL_BLOCK;
  
  public static BlockAlchemist SHINY_JUDEECERCIS_WOOD;
  
  public static BlockAlchemist SHINY_JACARANDA_WOOD;
  
  public static BlockAlchemist SHINY_ERABLE_WOOD;
  
  public static BlockAlchemist SHINY_OSTRYA_WOOD;
  
  public static BlockAlchemist AMETHYSTE_PORTAL_BLOCK;
  
  public static BlockAlchemist TITANE_PORTAL_BLOCK;
  
  public static BlockAlchemist PALADIUM_PORTAL_BLOCK;
  
  public static BlockAlchemist ENDIUM_PORTAL_BLOCK;
  
  public static BlockAlchemist ENDIUM_HEART_BLOCK;
  
  public static BlockAlchemist ENDIUM_ANGLE_PORTAL_BLOCK;
  
  public static BlockAlchemist MYSTICAL_BOOKSHELF;
  
  public static BlockAlchemist PORTAL_BLOCK;
  
  public static Block BLOCK_WOOD;
  
  public static Block DEAD_WOOD;
  
  public static Block PLANKS_DEAD_WOOD;
  
  public static Block LOTO;
  
  public static Block MailBox;
  
  public static Block ANVIL_AMETHYST;
  
  public static Block ANVIL_TITANE;
  
  public static Block ANVIL_PALADIUM;
  
  public static Block HALLOWEEN_COMMU_ON;
  
  public static Block SIFFLET;
  
  public static Block FAKE_FIRE;
  
  public static Block BLACK_TROPHY;
  
  public static Block YELLOW_EASTER_EGG;
  
  public static Block BLUE_EASTER_EGG;
  
  public static Block EASTER_GIFT;
  
  public static Block FAKE_EASTER_GIFT;
  
  public static Block MAGIC_BELL;
  
  public static Block EASTER_TROPHY;
  
  public static Block ENDIUM_MAGIC_BELL;
  
  public static Block EGG_OF_PLENTY;
  
  public static Block BUNNY_PLUSH;
  
  public static Block LILY_OF_THE_VALLEY;
  
  public static Block FAKE_GOLD_BLOCK;
  
  public static Block DIGICODE;
  
  public static Block MAY_TROPHY;
  
  public static Block MAGNETIC_COMPASS_CHEST;
  
  public static Block TIMED_BEDROCK;
  
  public static Block TRASH;
  
  public static Block ENDIUM_HEARTH;
  
  public static Block STONE_HEARTH;
  
  public static Block CREEPER_PLUSH;
  
  public static Block GOLDEN_CAGE;
  
  public static Block RANDOM_STATUE;
  
  public static Block JUNE_TROPHY;
  
  public static Block BRIGHT_SOUND;
  
  public static Block BRIGHT_SOUND_TEST;
  
  public static Block REAL_JUKEBOX;
  
  public static Block ALARM_CLOCK;
  
  public static Block SHARK_PLUSH;
  
  public static Block BARREL;
  
  public static Block PARROT_PLUSH;
  
  public static Block ENCRYPTED_COMPUTER;
  
  public static Block DECRYPTED_COMPUTER;
  
  public static Block JULY_LUCKY_BLOCK;
  
  public static Block JULY_TROPHY;
  
  public static Block QUICKSAND;
  
  public static Block LEAD;
  
  public static Block PRIMARY_CRAB_EGG;
  
  public static Block VENTILATOR;
  
  public static Block AUGUST_TROPHY;
  
  public static Block AUGUST_LUCKY_BLOCK;
  
  public static Block DOLPHIN_PLUSH;
  
  public static Block RUNNING_TREADMILL;
  
  public static Block CRAB_COBBLEBREAKER;
  
  public static Block SEPTEMBER_TROPHY;
  
  public static Block SEPTEMBER_LUCKY_BLOCK;
  
  public static Block PRIMARY_TAUPIKO_EGG;
  
  public static Block NOVEMBER_TROPHY;
  
  public static Block NOVEMBER_LUCKY_BLOCK;
  
  public static Block PRIMARY_SLAYER_EGG;
  
  public static Block CORRUPTED_ORE;
  
  public static Block CORRUPTED_BED;
  
  public static Block CORRUPTED_HUNTER_PLANT;
  
  public static Block CORRUPTED_PLANT;
  
  public static Block FAKE_CORRUPTED_CHEST;
  
  public static Block CORRUPTED_LUCKY_DRAWER;
  
  public static Block CORRUPTED_ENCHANTMENT_TABLE;
  
  public static Block CHRISTMAS_WREATH;
  
  public static Block FIRE_PLACE;
  
  public static Block GIFT_CHEST;
  
  public static Block BET_BLOCK;
  
  public static BlockHomeFinder HOME_FINDER;
  
  public static Block RAINBOW_SKULL;
  
  public static Block BARREL_WOOD;
  
  public static Block GALETTE_CAKE;
  
  public static Block FEVE_ARTY;
  
  public static Block FEVE_DANCAROK;
  
  public static Block FEVE_TEDAROK;
  
  public static Block FEVE_RAVIROK;
  
  public static Block ALIEN_PLUSH;
  
  public static Block MARCH_TROPHY;
  
  public static Block TELESCOPE;
  
  public static Block MONSTER_BLACK_HOLE;
  
  public static Block MARCH_LUCKY_BLOCK;
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\api\BlocksRegister.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */