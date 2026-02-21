package fr.paladium.palamod.api;

import fr.paladium.palamod.common.blocks.BaseFood;
import fr.paladium.palamod.common.items.BaseItemSeed;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.ItemAlchemist;
import fr.paladium.palamod.modules.paladium.common.items.BaseItemBerry;
import fr.paladium.palamod.modules.paladium.common.items.ItemArrowBase;
import fr.paladium.palamod.modules.paladium.common.items.ItemBackpack;
import fr.paladium.palamod.modules.paladium.common.items.ItemBucketBase;
import fr.paladium.palamod.modules.paladium.common.items.ItemChestExplorer;
import fr.paladium.palamod.modules.paladium.common.items.ItemHangGlider;
import fr.paladium.palamod.modules.paladium.common.items.ItemStickBase;
import fr.paladium.palamod.modules.paladium.common.items.ItemUnclaimFinder;
import fr.paladium.palamod.modules.paladium.common.items.ItemVoidStone;
import fr.paladium.palamod.modules.paladium.common.items.ItemVoidStoneMinage;
import fr.paladium.palamod.modules.paladium.common.items.SeedPlanterSeedFood;
import fr.paladium.palamod.modules.paladium.common.items.armors.ItemArmorTravel;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.ItemAncientArmor;
import fr.paladium.palamod.modules.paladium.common.items.boost.ItemBoostDoubleXp;
import fr.paladium.palamod.modules.paladium.common.items.boost.ItemBoostMinerFou;
import fr.paladium.palamod.modules.paladium.common.items.decorative.ItemObsidianDoor;
import fr.paladium.palamod.modules.paladium.common.items.explosive.BaseItemDynamite;
import fr.paladium.palamod.modules.paladium.common.items.explosive.ItemDynamiteBig;
import fr.paladium.palamod.modules.paladium.common.items.explosive.ItemDynamiteEndium;
import fr.paladium.palamod.modules.paladium.common.items.explosive.ItemDynamiteNinja;
import fr.paladium.palamod.modules.paladium.common.items.potion.ItemPotion;
import fr.paladium.palamod.modules.paladium.common.items.potion.ItemSplashPotion;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.ItemAncientHammer;
import fr.paladium.palamod.modules.paladium.common.items.upgrade.ItemBowRangeModifier;
import fr.paladium.palamod.modules.paladium.common.items.upgrade.ItemBowSpeedModifier;
import fr.paladium.palamod.modules.paladium.common.items.weapons.ItemInfernalKnocker;
import fr.paladium.palamod.modules.paladium.common.items.weapons.ItemPaladiumBow;
import fr.paladium.palamod.modules.paladium.utils.Item;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;

public class ItemsRegister {
  public static Item AMETHYST_INGOT;
  
  public static Item TITANE_INGOT;
  
  public static Item PALADIUM_INGOT;
  
  public static Item PALADIUM_GREEN_INGOT;
  
  public static Item ENDIUM_INGOT;
  
  public static Item ENDIUM_FRAGMENT;
  
  public static Item FINDIUM;
  
  public static Item TRIXIUM;
  
  public static Item POCKET_ENDERCHEST;
  
  public static Item PLAYER_SADDLE;
  
  public static Item CLOUD;
  
  public static Item money;
  
  public static Item PB;
  
  public static Item ENDIUM_NUGGET;
  
  public static Item AMETHYST_PICKAXE;
  
  public static Item AMETHYST_SHOVEL;
  
  public static Item AMETHYST_AXE;
  
  public static Item AMETHYST_SWORD;
  
  public static Item AMETHYST_HELMET;
  
  public static Item AMETHYST_CHESTPLATE;
  
  public static Item AMETHYST_LEGGINGS;
  
  public static Item AMETHYST_BOOTS;
  
  public static Item TITANE_PICKAXE;
  
  public static Item TITANE_SHOVEL;
  
  public static Item TITANE_AXE;
  
  public static Item TITANE_SWORD;
  
  public static Item TITANE_HELMET;
  
  public static Item TITANE_CHESTPLATE;
  
  public static Item TITANE_LEGGINGS;
  
  public static Item TITANE_BOOTS;
  
  public static Item PALADIUM_PICKAXE;
  
  public static Item PALADIUM_SHOVEL;
  
  public static Item PALADIUM_AXE;
  
  public static Item PALADIUM_SWORD;
  
  public static Item PALADIUM_HELMET;
  
  public static Item PALADIUM_CHESTPLATE;
  
  public static Item PALADIUM_LEGGINGS;
  
  public static Item PALADIUM_BOOTS;
  
  public static Item PALADIUM_APPLE;
  
  public static Item PALADIUM_GREEN_PICKAXE;
  
  public static Item PALADIUM_GREEN_SHOVEL;
  
  public static Item PALADIUM_GREEN_AXE;
  
  public static Item PALADIUM_GREEN_SWORD;
  
  public static Item PALADIUM_GREEN_HELMET;
  
  public static Item PALADIUM_GREEN_CHESTPLATE;
  
  public static Item PALADIUM_GREEN_LEGGINGS;
  
  public static Item PALADIUM_GREEN_BOOTS;
  
  public static Item ENDIUM_PICKAXE;
  
  public static Item ENDIUM_AXE;
  
  public static Item ENDIUM_SWORD;
  
  public static Item ENDIUM_HELMET;
  
  public static Item ENDIUM_CHESTPLATE;
  
  public static Item ENDIUM_LEGGINGS;
  
  public static Item ENDIUM_BOOTS;
  
  public static Item MIXED_ENDIUM_INGOT;
  
  public static Item MIXED_ENDIUM_HELMET;
  
  public static Item MIXED_ENDIUM_CHESTPLATE;
  
  public static Item MIXED_ENDIUM_LEGGINGS;
  
  public static Item MIXED_ENDIUM_BOOTS;
  
  public static ItemChestExplorer CHESTEXPLORER;
  
  public static ItemVoidStone VOIDSTONE;
  
  public static ItemVoidStoneMinage VOIDSTONE_MINAGE;
  
  public static Item SMALL_RING;
  
  public static Item MEDIUM_RING;
  
  public static Item BIG_RING;
  
  public static Item RING_ENDIUM_SMALL;
  
  public static Item RING_ENDIUM_MEDIUM;
  
  public static Item RING_ENDIUM_BIG;
  
  public static ItemStickBase STICK_HEAL;
  
  public static ItemStickBase STICK_SPEED;
  
  public static ItemStickBase STICK_STRENGHT;
  
  public static ItemStickBase STICK_JUMP;
  
  public static ItemStickBase STICK_GOD;
  
  public static ItemStickBase STICK_DAMAGE;
  
  public static ItemStickBase STICK_HYPERJUMP;
  
  public static ItemStickBase STICK_TELEPORT;
  
  public static ItemUnclaimFinder UNCLAIMFINDER;
  
  public static ItemUnclaimFinder UNCLAIMFINDER_ORANGE;
  
  public static ItemUnclaimFinder UNCLAIMFINDER_ROUGE;
  
  public static ItemUnclaimFinder UNCLAIMFINDER_BLEU;
  
  public static Item PLATE;
  
  public static Item PLATE_ENCHANTED;
  
  public static Item CHASE;
  
  public static Item PALADIUM_INK;
  
  public static Item TOURNAMENT_RED_HELMET;
  
  public static Item TOURNAMENT_RED_CHESTPLATE;
  
  public static Item TOURNAMENT_RED_LEGGINGS;
  
  public static Item TOURNAMENT_RED_BOOTS;
  
  public static Item TOURNAMENT_RED_SWORD;
  
  public static Item TOURNAMENT_BLUE_HELMET;
  
  public static Item TOURNAMENT_BLUE_CHESTPLATE;
  
  public static Item TOURNAMENT_BLUE_LEGGINGS;
  
  public static Item TOURNAMENT_BLUE_BOOTS;
  
  public static Item TOURNAMENT_BLUE_SWORD;
  
  public static ItemPaladiumBow PALADIUM_BOW;
  
  public static ItemInfernalKnocker INFERNAL_KNOCKER;
  
  public static ItemArrowBase ARROW_POISON;
  
  public static ItemArrowBase ARROW_SLOWNESS;
  
  public static ItemArrowBase ARROW_SWITCH;
  
  public static ItemArrowBase ARROW_WITHER;
  
  public static ItemArrowBase ARROW_FROST;
  
  public static Item PARTICLE_IRON;
  
  public static Item PARTICLE_GOLD;
  
  public static Item PARTICLE_DIAMOND;
  
  public static Item PARTICLE_AMETHYST;
  
  public static Item PARTICLE_TITANE;
  
  public static Item PARTICLE_PALADIUM;
  
  public static Item FRUIT_ORANGEBLUE;
  
  public static Item FRUIT_KIWANO;
  
  public static Item FRUIT_CHERVIL;
  
  public static Item FRUIT_EGGPLANT;
  
  public static BaseItemBerry COMPRESSED_XP_BERRY;
  
  public static BaseItemBerry XP_BERRY;
  
  public static BaseItemSeed SEED_ORANGEBLUE;
  
  public static BaseItemSeed SEED_KIWANO;
  
  public static BaseItemSeed SEED_CHERVIL;
  
  public static BaseItemSeed SEED_EGGPLANT;
  
  public static BaseItemSeed SEED_ICE;
  
  public static Item FURNACE_UPGRADE;
  
  public static Item PALAMIXEDCOAL;
  
  public static Item GOLDMIXEDCOAL;
  
  public static Item AMETHYSTMIXEDCOAL;
  
  public static Item TITANEMIXEDCOAL;
  
  public static Item BOWUPGRADE_EMPTY;
  
  public static ItemBowRangeModifier BOWUPGRADE_RANGEMODIFIER;
  
  public static ItemBowSpeedModifier BOWUPGRADE_SPEEDMODIFIER;
  
  public static Item COMPRESSED_ENDIUM;
  
  public static Item COMPRESSED_PALADIUM;
  
  public static Item COMPRESSED_TITANE;
  
  public static Item COMPRESSED_AMETHYST;
  
  public static Item ORB_HEAL;
  
  public static Item ORB_JUMP;
  
  public static Item ORB_KNOCKBACK;
  
  public static Item ORB_SPEED;
  
  public static Item ORB_STRENGHT;
  
  public static ItemArmorTravel TRAVEL_LEGGINGS;
  
  public static ItemArmorTravel TRAVEL_BOOTS;
  
  public static ItemArmorTravel TRAVEL_JUMPCHEST;
  
  public static ItemArmorTravel TRAVEL_SLIMYHELMET;
  
  public static ItemArmorTravel TRAVEL_SCUBAHELMET;
  
  public static ItemArmorTravel TRAVEL_HOODHELMET;
  
  public static ItemAncientArmor ANCIENT_HELMET;
  
  public static ItemAncientArmor ANCIENT_CHESTPLATE;
  
  public static ItemAncientArmor ANCIENT_LEGGINGS;
  
  public static ItemAncientArmor ANCIENT_BOOTS;
  
  public static ItemAncientHammer ANCIENT_HAMMER;
  
  public static ItemHangGlider HANGGLIDER;
  
  public static ItemBackpack BACKPACK;
  
  public static BaseItemDynamite DYNAMITE;
  
  public static ItemDynamiteNinja DYNAMITE_NINJA;
  
  public static ItemDynamiteBig DYNAMITE_BIG;
  
  public static ItemDynamiteEndium DYNAMITE_ENDIUM;
  
  public static ItemBucketBase BUCKET_SULFURIC;
  
  public static ItemBucketBase BUCKET_ANGELIC;
  
  public static SeedPlanterSeedFood SEEDPLANTER_LVL1;
  
  public static SeedPlanterSeedFood SEEDPLANTER_LVL2;
  
  public static SeedPlanterSeedFood SEEDPLANTER_LVL3;
  
  public static SeedPlanterSeedFood SEEDPLANTER_LVL4;
  
  public static SeedPlanterSeedFood SEEDPLANTER_LVL5;
  
  public static BaseFood FOOD_HAM;
  
  public static BaseFood FOOD_MARINATED_PORKCHOP;
  
  public static BaseFood FOOD_MARINATED_CHICKEN;
  
  public static BaseFood FOOD_MARINATED_STEAK;
  
  public static BaseFood FOOD_MARINATED_ROTTENFLESH;
  
  public static BaseFood FOOD_MARINATED_MUTTON;
  
  public static ItemObsidianDoor OBSIDIAN_ITEM_DOOR;
  
  public static Item Farmer;
  
  public static Item Miner;
  
  public static Item Hunter;
  
  public static Item Alchemist;
  
  public static Item Job;
  
  public static Item JOB_BOTTLE_MINER;
  
  public static Item JOB_BOTTLE_FARMER;
  
  public static Item JOB_BOTTLE_HUNTER;
  
  public static Item JOB_BOTTLE_ALCHEMIST;
  
  public static Item STICK_PALADIUM;
  
  public static Item STICK_TITANE;
  
  public static Item STICK_AMETHYST;
  
  public static Item STICK_MODERATION;
  
  public static ItemPotion POTION_WITHER;
  
  public static ItemPotion POTION_FIRE;
  
  public static ItemPotion POTION_POSION;
  
  public static ItemSplashPotion POTION_SICKNESS;
  
  public static ItemSplashPotion POTION_WEB;
  
  public static ItemBoostDoubleXp DOUBLE_XP_POTION;
  
  public static ItemBoostMinerFou POTION_MINER_FOU;
  
  public static Item WITHER_SKULLFRAGMENT;
  
  public static Item PALADIUM_CORE;
  
  public static Item DIAMOND_STRING;
  
  public static Item WING;
  
  public static Item POTION_LAUNCHER;
  
  public static Item MAGICAL_TOOL;
  
  public static Item ENDIUM_GAUNTLET;
  
  public static Item WITHER_DUST;
  
  public static Item LEGENDARYSTONE_RANDOM;
  
  public static Item LEGENDARYSTONE_TELEPORTATION;
  
  public static Item LEGENDARYSTONE_INVISIBILITY;
  
  public static Item LEGENDARYSTONE_FORTUNE;
  
  public static Item LEGENDARYSTONE_POWER;
  
  public static Item LEGENDARYSTONE_JOBS;
  
  public static Item LEGENDARYSTONE_CHAOS;
  
  public static Item ORB_CHAOS;
  
  public static Item itemDummy;
  
  public static Item CHUNK_ANALYSER;
  
  public static Item BLAZE_AND_STEEL;
  
  public static Item WEIGHTED_BOOTS;
  
  public static Item DISC_FUZEIII;
  
  public static Item GRAPPIN;
  
  public static Item GRAPPIN_HOOK;
  
  public static Item NAMETAG_FIREWORK;
  
  public static Item SPACE_FOOD;
  
  public static Item MAGIC_POTION;
  
  public static Item LASSO;
  
  public static Item ROLLER;
  
  public static Item JETPACK;
  
  public static Item BIOME_PAINTER;
  
  public static Item LUCKY_PAINTING;
  
  public static Item PIG_HELMET;
  
  public static Item PIG_CHESTPLATE;
  
  public static Item PIG_LEGGINGS;
  
  public static Item PIG_BOOTS;
  
  public static Item CAVE_HELMET;
  
  public static Item INVISIBLE_HELMET;
  
  public static Item INVISIBLE_CHESTPLATE;
  
  public static Item INVISIBLE_LEGGINGS;
  
  public static Item INVISIBLE_BOOTS;
  
  public static Item SPEED_APPLE;
  
  public static Item POULPOGUN;
  
  public static Item METER;
  
  public static Item ENDER_BAG;
  
  public static Item SMOKEBOMB_WHITE;
  
  public static Item SMOKEBOMB_ORANGE;
  
  public static Item SMOKEBOMB_MAGENTA;
  
  public static Item SMOKEBOMB_LIGHT_BLUE;
  
  public static Item SMOKEBOMB_YELLOW;
  
  public static Item SMOKEBOMB_LIME;
  
  public static Item SMOKEBOMB_PINK;
  
  public static Item SMOKEBOMB_GRAY;
  
  public static Item SMOKEBOMB_LIGHT_GRAY;
  
  public static Item SMOKEBOMB_CYAN;
  
  public static Item SMOKEBOMB_PURPLE;
  
  public static Item SMOKEBOMB_BLUE;
  
  public static Item SMOKEBOMB_BROWN;
  
  public static Item SMOKEBOMB_GREEN;
  
  public static Item SMOKEBOMB_RED;
  
  public static Item SMOKEBOMB_BLACK;
  
  public static Item DISC_HALLOWEEN;
  
  public static Item BROOM;
  
  public static Item GHOSTCAPE;
  
  public static Item PUMPKINSHEAR;
  
  public static Item SPIDERGLOVES;
  
  public static Item PUMPKINCRUSH;
  
  public static Item HALLOWEENSTONE;
  
  public static Item SURVIVINGSTONE;
  
  public static Item HALLOWEEN_HELMET;
  
  public static Item HALLOWEEN_CHESTPLATE;
  
  public static Item HALLOWEEN_LEGGINGS;
  
  public static Item HALLOWEEN_BOOTS;
  
  public static Item HALLOWEEN_HAT;
  
  public static Item JOB_CANDY;
  
  public static Item HALLOWEEN_AXE;
  
  public static Item PALADIUM_KEY;
  
  @Deprecated
  @Item(name = "candy", texture = "halloween/candy", type = "item", valid = false)
  public static Item CANDY_OLD;
  
  @Deprecated
  @Item(name = "candy_bag", texture = "halloween/candy_bag", type = "item", valid = false)
  public static Item CANDY_BAG_OLD;
  
  @Deprecated
  @Item(name = "candy_piece", texture = "halloween/candy_piece", type = "item", valid = false)
  public static Item CANDY_PIECE_OLD;
  
  @Item(name = "halloween_candy", texture = "halloween/candy", type = "item")
  public static Item CANDY;
  
  @Item(name = "halloween_candy_bag", texture = "halloween/candy_bag", type = "item")
  public static Item CANDY_BAG;
  
  @Item(name = "halloween_candy_piece", texture = "halloween/candy_piece", type = "item")
  public static Item CANDY_PIECE;
  
  public static Item CHRISTMAS_STONE;
  
  public static Item PRESENT;
  
  public static Item PRESENT_BAG;
  
  public static Item CHOCOLATE_MILK;
  
  public static Item CHRISTMAS_AXE;
  
  public static Item CHRISTMAS_BALL;
  
  public static Item ORE_PRESENT;
  
  public static Item CHRISTMAS_HELMET;
  
  public static Item CHRISTMAS_CHESTPLATE;
  
  public static Item CHRISTMAS_LEGGINGS;
  
  public static Item CHRISTMAS_BOOTS;
  
  public static Item CHRISTMAS_BOOK;
  
  public static Item CHRISTMAS_MULTICOLOR_GIFT;
  
  public static Item WISH_CARD;
  
  public static Item FOLLOW_COMPASS;
  
  public static Item FAKE_MONEY;
  
  public static Item HAPPY_NEW_YEAR_FIREWORK;
  
  public static Item STATS_STONE;
  
  public static Item PALA_ITEM_FRAME;
  
  public static ItemArmor BALACLAVA_HELMET;
  
  public static Item SLEEPING_BAG;
  
  public static ItemPotion SODA;
  
  public static ItemSplashPotion SODA_SPLASH;
  
  public static Item CALCIUM;
  
  public static ItemArmor AIRMIN_BOOTS;
  
  public static ItemArmor SQUIDGAME_HELMET;
  
  public static ItemFood BANANA;
  
  public static Item ENDIUM_FAKE_HELMET;
  
  public static Item ENDIUM_FAKE_CHESTPLATE;
  
  public static Item ENDIUM_FAKE_LEGGINGS;
  
  public static Item ENDIUM_FAKE_BOOTS;
  
  public static Item COUAC_SWORD;
  
  public static Item MARIO_MUSHROOM;
  
  public static Item BOAT_FURNACE;
  
  public static Item KEY_TOMBE;
  
  public static Item clippy;
  
  public static Item CHOCOLATE_EGG;
  
  public static Item FAKE_CHOCOLATE_EGG;
  
  public static Item GOLDEN_EGG;
  
  public static Item APRIL_FISH;
  
  public static Item EASTER_EGG;
  
  public static Item RAINBOW_COLORING;
  
  public static Item RAINBOW_LEATHER;
  
  public static Item RAINBOW_LEATHER_HELMET;
  
  public static Item RAINBOW_LEATHER_CHESTPLATE;
  
  public static Item RAINBOW_LEATHER_LEGGINGS;
  
  public static Item RAINBOW_LEATHER_BOOTS;
  
  public static Item SHEEP_NOISE_BOX;
  
  public static Item RESURRECTION_STONE;
  
  public static Item GIANT_FISH;
  
  public static Item CURSED_CHOCOLATE_EGG;
  
  public static Item EASTER_TICKET;
  
  public static Item EASTER_TOTEM;
  
  public static Item EASTER_BUNNY_HELMET;
  
  public static Item EASTER_BUNNY_CHESTPLATE;
  
  public static Item EASTER_BUNNY_LEGGINGS;
  
  public static Item EASTER_BUNNY_BOOTS;
  
  public static Item OLD_STORY;
  
  public static Item DARK_KNIGHT_HELMET;
  
  public static Item MAY_TICKET;
  
  public static Item STRANGE_METAL_PIECE;
  
  public static Item SWORD_TOO_BIG;
  
  public static Item ENERGETIC_BOW_SWORD;
  
  public static Item IRON_SKULL_HAMMER;
  
  public static Item RUNIC_AXE;
  
  public static Item MAGNETIC_COMPASS;
  
  public static Item LIGHT_SABER;
  
  public static Item PET_SNACK;
  
  public static Item PET_XP_MEDIUM;
  
  public static Item PET_XP_BIG;
  
  public static Item PET_XP_SMALL;
  
  public static Item MONSTROUS_SWORD;
  
  public static Item MAY_TICKET_BLACKSMITH;
  
  public static Item GOLD_RECORD;
  
  public static Item TRUMPET;
  
  public static Item ELECTRIC_GUITAR;
  
  public static Item RADIO;
  
  public static Item PARALYZING_RADIO;
  
  public static Item DISC_ANTI_FUZE;
  
  public static Item DISC_CLASH_KUMIZ;
  
  public static Item DISC_ROULETTE_PALADIENNE;
  
  public static Item DISC_NATIONS_GLORY;
  
  public static Item DISC_PALADIUM_BEST_SOUND;
  
  public static Item SOUND_DETECTOR;
  
  public static Item FOGHORN;
  
  public static Item SITAR;
  
  public static Item SWORD_GUITAR_OF_THE_APOCALYPSE;
  
  public static Item JUNE_TICKET;
  
  public static Item ADMIN_RADIO;
  
  public static Item SKULL_CURSED_KING;
  
  public static Item KING_SKULL;
  
  public static Item ORANGE_CONSUMABLE;
  
  public static Item PIPE;
  
  public static Item INVULNERABILITY_POTION;
  
  public static Item DRY_MESSAGE;
  
  public static Item PIRATE_KING_MESSAGE;
  
  public static Item SEA_BOTTLE;
  
  public static Item SAILOR_TOOTH;
  
  public static Item TELESCOPE;
  
  public static Item GHOSTLY_SWORD;
  
  public static Item JULY_TICKET;
  
  public static Item TREASURE_MAP;
  
  public static Item WOODEN_LEG;
  
  public static Item ACCORDION;
  
  public static Item DRUM;
  
  public static Item DUTCH_BOAT;
  
  public static Item EYE_PATCH;
  
  public static Item PARROT_SEEDS;
  
  public static Item BLUNDERBUSS;
  
  public static Item SWORD_PISTOL;
  
  public static Item FLINT_EGG;
  
  public static Item TOOTH_SKELETON_SKULL;
  
  public static Item SUMMER_WATER_BOTTLE;
  
  public static Item SOLAR_CREAM;
  
  public static Item ICE_CREAM;
  
  public static Item DOUBLE_ICE_CREAM;
  
  public static Item ICE_CREAM_EGG;
  
  public static Item SUMMER_INSTRUCTIONS;
  
  public static Item JOB_QUEST_POTION;
  
  public static Item SUMMER_AMULET;
  
  public static Item SUITCASE;
  
  public static Item ARMOR_BAG;
  
  public static Item PERFECT_HELMET;
  
  public static Item AUGUST_FAKE_MONEY;
  
  public static Item EMPTY_WATER_BOTTLE;
  
  public static Item VOLLEY_BALL;
  
  public static Item PORTABLE_VOLLEY_COURT;
  
  public static Item CRAB_EGG;
  
  public static Item AUGUST_TICKET;
  
  public static Item PARTICLE_GREEN_PALADIUM;
  
  public static Item JOB_OFFER;
  
  public static Item TRADE_FILE;
  
  public static Item EMPTY_TRADE_FILE;
  
  public static Item BOSS_SUITCASE;
  
  public static Item JOB_CHUNK_ANALYZER;
  
  public static Item PAPER_AIRPLANE;
  
  public static Item TAUPIKO_EGG;
  
  public static Item BUTCHER_KNIFE;
  
  public static Item JOB_TRANSFEROR;
  
  public static Item SEPTEMBER_TICKET;
  
  public static Item WELDING_GLASSES;
  
  public static Item SLAYER_EGG;
  
  public static Item CORRUPTED_CHRONO;
  
  public static Item CORRUPTED_TICKET;
  
  public static Item CORRUPTED_SPLASH_POTION;
  
  public static Item CORRUPTED_LEGENDARY_STONE;
  
  public static Item CORRUPTED_LUCKY_SWORD;
  
  public static Item CORRUPTED_SWORD;
  
  public static Item CORRUPTED_POISON_POTION;
  
  public static Item ITEM_CORRUPTED_BED;
  
  public static Item POTION_JOB_TEN_MULTIPLIER;
  
  public static Item BIONIC_SNOWBALL;
  
  public static Item MOON_PIECE;
  
  public static Item TALISMAN_CALM;
  
  public static Item SUPERMAN_CAPE;
  
  public static Item LUCKY_SWORD;
  
  public static Item LUCKY_SWORD_BONUS;
  
  public static Item LUCKY_SWORD_MALUS;
  
  public static Item SPEAKER;
  
  public static Item VISION_HELMET;
  
  public static Item FOREUSE;
  
  public static Item POWER_HELMET;
  
  public static Item FAUX;
  
  public static Item DISC_NOEL;
  
  public static Item COSMETIC_SKIN_NOEL;
  
  public static Item CHRISMAS_HAMMER;
  
  public static Item HUNTER_BACKPACK;
  
  public static Item ENDIUM_NAMETAG;
  
  public static Item CAPTURE_STONE;
  
  public static Item CAPTURE_SWORD;
  
  public static Item HUNTER_AMULET;
  
  public static Item SEEING_AMULET;
  
  public static Item DISCRETION_AMULET;
  
  public static Item COMBAT_AMULET;
  
  public static Item MAGMA_AMULET;
  
  public static Item DAEMON_AMULET;
  
  public static Item SNAIL_SHELL;
  
  public static Item HUNTER_COMPASS;
  
  public static Item TURTLE_SCALES;
  
  public static Item PANDA_DROOL;
  
  public static Item PARROT_FEATHER;
  
  public static Item CRAB_PLIERS;
  
  public static Item TUSK;
  
  public static Item SNAKE_VENOM;
  
  public static Item MEDUSE_HOOK;
  
  public static Item GOAT_SHOE;
  
  public static Item DOLPHIN_NOISE_BOX;
  
  public static Item BAMBOUS;
  
  public static Item SPAWNER_FINDER;
  
  public static Item BUILDER_WAND;
  
  public static Item AMETHYST_EXCAVATOR;
  
  public static Item TITANE_EXCAVATOR;
  
  public static Item PALADIUM_EXCAVATOR;
  
  public static Item COBBLEBREAKER_AMETHYST_UPGRADE;
  
  public static Item COBBLEBREAKER_TITANE_UPGRADE;
  
  public static Item COBBLEBREAKER_PALADIUM_UPGRADE;
  
  public static Item MAGNET;
  
  public static Item HARDNESS_POTION;
  
  public static Item SEALED_XP_BOTTLE;
  
  public static Item GOD_PICKAXE;
  
  public static Item GOD_PICKAXE_AUTOSMELT;
  
  public static Item GOD_PICKAXE_BIGHOLE;
  
  public static Item MOULA_STONE;
  
  public static Item INVOKER_STONE;
  
  public static Item SPELL_SCROLL;
  
  public static ItemAlchemist FLASK;
  
  public static ItemAlchemist GREEN_GLUEBALL;
  
  public static ItemAlchemist BLUE_GLUEBALL;
  
  public static ItemAlchemist YELLOW_GLUEBALL;
  
  public static ItemAlchemist RED_GLUEBALL;
  
  public static ItemAlchemist ORANGE_GLUEBALL;
  
  public static ItemAlchemist GRAY_GLUEBALL;
  
  public static ItemAlchemist PURPLE_GLUEBALL;
  
  public static ItemAlchemist GREEN_FLASH_GLUEBALL;
  
  public static ItemAlchemist GREEN_DARK_GLUEBALL;
  
  public static ItemAlchemist CYAN_GLUEBALL;
  
  public static ItemAlchemist LIGHTNING_POTION;
  
  public static ItemAlchemist GLUEBALL_PATTERN;
  
  public static ItemAlchemist ENDIUM_HEART;
  
  public static ItemAlchemist AMETHYSTE_PORTAL_KEY;
  
  public static ItemAlchemist TITANE_PORTAL_KEY;
  
  public static ItemAlchemist PALADIUM_PORTAL_KEY;
  
  public static ItemAlchemist ENDIUM_PORTAL_KEY;
  
  public static Item TITANE_POLLEN;
  
  public static Item AMETHYST_POLLEN;
  
  public static Item PALADIUM_POLLEN;
  
  public static Item ENDIUM_POLLEN;
  
  public static Item EXTRAPOLATED_BUCKET;
  
  public static Item FUNNY_WITHER_GEM;
  
  public static Item DISC_MII;
  
  public static Item FLASQUE;
  
  public static Item GADGETO;
  
  public static Item MISSILE_METEO;
  
  public static Item SONIC_BOOTS;
  
  public static Item WATER_PISTOL;
  
  public static Item WATER_DROP;
  
  public static Item PISTOL_TANK;
  
  public static Item STAR_HELMET;
  
  public static Item SUPERSONIC_ROCKET;
  
  public static Item GALACTIC_PILE;
  
  public static Item WAR_STAR;
  
  public static Item PERSONAL_THRUSTER;
  
  public static Item MOON_BOOTS;
  
  public static Item UNIVERSAL_DOLLAR_STONE;
  
  public static Item LASER_GUN;
  
  public static Item MARCH_TICKET;
  
  public static Item ENDIUM_BATTERY;
  
  public static Item SPLASH_POTION_MOON_GRAVITY;
  
  public static Item SPLASH_POTION_MARS_GRAVITY;
  
  public static Item SPLASH_POTION_JUPITER_GRAVITY;
  
  @Item(name = "dark_sword", type = "sword")
  public static Item DARK_SWORD;
  
  @Item(name = "dark_pickaxe", type = "pickaxe")
  public static Item DARK_PICKAXE;
  
  @Item(name = "dark_axe", type = "axe")
  public static Item DARK_AXE;
  
  @Item(name = "dark_shovel", type = "shovel")
  public static Item DARK_SHOVEL;
  
  @Item(name = "fallen_sword", type = "sword")
  public static Item FALLEN_SWORD;
  
  @Item(name = "fallen_pickaxe", type = "pickaxe")
  public static Item FALLEN_PICKAXE;
  
  @Item(name = "fallen_axe", type = "axe")
  public static Item FALLEN_AXE;
  
  @Item(name = "fallen_shovel", type = "shovel")
  public static Item FALLEN_SHOVEL;
  
  @Item(name = "fire_sword", type = "sword")
  public static Item FIRE_SWORD;
  
  @Item(name = "fire_pickaxe", type = "pickaxe")
  public static Item FIRE_PICKAXE;
  
  @Item(name = "fire_axe", type = "axe")
  public static Item FIRE_AXE;
  
  @Item(name = "fire_shovel", type = "shovel")
  public static Item FIRE_SHOVEL;
  
  @Item(name = "light_sword", type = "sword")
  public static Item LIGHT_SWORD;
  
  @Item(name = "light_pickaxe", type = "pickaxe")
  public static Item LIGHT_PICKAXE;
  
  @Item(name = "light_axe", type = "axe")
  public static Item LIGHT_AXE;
  
  @Item(name = "light_shovel", type = "shovel")
  public static Item LIGHT_SHOVEL;
  
  @Item(name = "ice_sword", type = "sword")
  public static Item ICE_SWORD;
  
  @Item(name = "ice_pickaxe", type = "pickaxe")
  public static Item ICE_PICKAXE;
  
  @Item(name = "ice_axe", type = "axe")
  public static Item ICE_AXE;
  
  @Item(name = "ice_shovel", type = "shovel")
  public static Item ICE_SHOVEL;
  
  @Item(name = "rainbow_sword", type = "sword")
  public static Item RAINBOW_SWORD;
  
  @Item(name = "rainbow_pickaxe", type = "pickaxe")
  public static Item RAINBOW_PICKAXE;
  
  @Item(name = "rainbow_axe", type = "axe")
  public static Item RAINBOW_AXE;
  
  @Item(name = "rainbow_shovel", type = "shovel")
  public static Item RAINBOW_SHOVEL;
  
  @Item(name = "box_key", type = "item")
  public static Item BOX_KEY;
  
  @Item(name = "halloween_sword", type = "sword")
  public static Item HALLOWEEN_SWORD;
  
  @Item(name = "halloween_pickaxe", type = "pickaxe")
  public static Item HALLOWEEN_PICKAXE;
  
  @Item(name = "halloween_shovel", type = "shovel")
  public static Item HALLOWEEN_SHOVEL;
  
  public static Item PIRATE_CHEST;
  
  public static Item GALETTE;
  
  public static Item LUCKY_BEAN;
  
  public static Item RANDOM_BEAN;
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\api\ItemsRegister.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */