package fr.paladium.palamod.modules.luckyblock.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.entity.EntityFunnyWither;
import fr.paladium.palamod.modules.luckyblock.items.ItemAirMin;
import fr.paladium.palamod.modules.luckyblock.items.ItemBalaclavaHelmet;
import fr.paladium.palamod.modules.luckyblock.items.ItemBanana;
import fr.paladium.palamod.modules.luckyblock.items.ItemBiomePainter;
import fr.paladium.palamod.modules.luckyblock.items.ItemBionicSnowball;
import fr.paladium.palamod.modules.luckyblock.items.ItemBlazeAndSteel;
import fr.paladium.palamod.modules.luckyblock.items.ItemBoatFurnace;
import fr.paladium.palamod.modules.luckyblock.items.ItemCalcium;
import fr.paladium.palamod.modules.luckyblock.items.ItemChunkAnalyser;
import fr.paladium.palamod.modules.luckyblock.items.ItemClipboard;
import fr.paladium.palamod.modules.luckyblock.items.ItemCouacSword;
import fr.paladium.palamod.modules.luckyblock.items.ItemDisc;
import fr.paladium.palamod.modules.luckyblock.items.ItemEnderBag;
import fr.paladium.palamod.modules.luckyblock.items.ItemFakeEndiumArmor;
import fr.paladium.palamod.modules.luckyblock.items.ItemFakeMoney;
import fr.paladium.palamod.modules.luckyblock.items.ItemFollowCompass;
import fr.paladium.palamod.modules.luckyblock.items.ItemGrappin;
import fr.paladium.palamod.modules.luckyblock.items.ItemHappyNewYearFirework;
import fr.paladium.palamod.modules.luckyblock.items.ItemJetpack;
import fr.paladium.palamod.modules.luckyblock.items.ItemKeyTombe;
import fr.paladium.palamod.modules.luckyblock.items.ItemLasso;
import fr.paladium.palamod.modules.luckyblock.items.ItemLuckyPainting;
import fr.paladium.palamod.modules.luckyblock.items.ItemLuckySword;
import fr.paladium.palamod.modules.luckyblock.items.ItemMagicPotion;
import fr.paladium.palamod.modules.luckyblock.items.ItemMarioMushroom;
import fr.paladium.palamod.modules.luckyblock.items.ItemMeter;
import fr.paladium.palamod.modules.luckyblock.items.ItemMoonPiece;
import fr.paladium.palamod.modules.luckyblock.items.ItemNameTagFirework;
import fr.paladium.palamod.modules.luckyblock.items.ItemPaladiumItemFrame;
import fr.paladium.palamod.modules.luckyblock.items.ItemPoulpoGun;
import fr.paladium.palamod.modules.luckyblock.items.ItemRoller;
import fr.paladium.palamod.modules.luckyblock.items.ItemSleepingBag;
import fr.paladium.palamod.modules.luckyblock.items.ItemSmokeBomb;
import fr.paladium.palamod.modules.luckyblock.items.ItemSpaceFood;
import fr.paladium.palamod.modules.luckyblock.items.ItemSquidGameHelmet;
import fr.paladium.palamod.modules.luckyblock.items.ItemStatsStone;
import fr.paladium.palamod.modules.luckyblock.items.ItemSupermanArmor;
import fr.paladium.palamod.modules.luckyblock.items.ItemTalismanCalm;
import fr.paladium.palamod.modules.luckyblock.items.ItemWeightedArmor;
import fr.paladium.palamod.modules.luckyblock.items.black.ItemFlasque;
import fr.paladium.palamod.modules.luckyblock.items.black.ItemGadgeto;
import fr.paladium.palamod.modules.luckyblock.items.black.ItemMissileMeteo;
import fr.paladium.palamod.modules.luckyblock.items.black.ItemSonicBoots;
import fr.paladium.palamod.modules.luckyblock.items.black.ItemWaterPistol;
import fr.paladium.palamod.modules.luckyblock.items.christmas.ItemChocolateMilk;
import fr.paladium.palamod.modules.luckyblock.items.christmas.ItemChristmasAxe;
import fr.paladium.palamod.modules.luckyblock.items.christmas.ItemChristmasBook;
import fr.paladium.palamod.modules.luckyblock.items.christmas.ItemChristmasStone;
import fr.paladium.palamod.modules.luckyblock.items.christmas.ItemMulticolorGift;
import fr.paladium.palamod.modules.luckyblock.items.christmas.ItemPresent;
import fr.paladium.palamod.modules.luckyblock.items.christmas.ItemPresentBag;
import fr.paladium.palamod.modules.luckyblock.items.christmas.ItemWishCard;
import fr.paladium.palamod.modules.luckyblock.items.easter.ItemAprilFish;
import fr.paladium.palamod.modules.luckyblock.items.easter.ItemChocolateEgg;
import fr.paladium.palamod.modules.luckyblock.items.easter.ItemCursedChocolateEgg;
import fr.paladium.palamod.modules.luckyblock.items.easter.ItemEasterEgg;
import fr.paladium.palamod.modules.luckyblock.items.easter.ItemEasterTicket;
import fr.paladium.palamod.modules.luckyblock.items.easter.ItemEasterTotem;
import fr.paladium.palamod.modules.luckyblock.items.easter.ItemFakeChocolateEgg;
import fr.paladium.palamod.modules.luckyblock.items.easter.ItemGiantFish;
import fr.paladium.palamod.modules.luckyblock.items.easter.ItemGoldenEgg;
import fr.paladium.palamod.modules.luckyblock.items.easter.ItemRainbowColoring;
import fr.paladium.palamod.modules.luckyblock.items.easter.ItemRainbowLeather;
import fr.paladium.palamod.modules.luckyblock.items.easter.ItemRainbowLeatherArmor;
import fr.paladium.palamod.modules.luckyblock.items.easter.ItemResurrectionStone;
import fr.paladium.palamod.modules.luckyblock.items.easter.ItemSheepNoiseBox;
import fr.paladium.palamod.modules.luckyblock.items.halloween.ItemGhostCape;
import fr.paladium.palamod.modules.luckyblock.items.halloween.ItemHalloweenStone;
import fr.paladium.palamod.modules.luckyblock.items.halloween.ItemPumpkinCrush;
import fr.paladium.palamod.modules.luckyblock.items.halloween.ItemPumpkinShear;
import fr.paladium.palamod.modules.luckyblock.items.halloween.ItemSpiderGlove;
import fr.paladium.palamod.modules.luckyblock.items.halloween.ItemSurvivingStone;
import fr.paladium.palamod.modules.luckyblock.items.halloween.ItemWitchBroom;
import fr.paladium.palamod.modules.luckyblock.items.june.ItemAdminRadio;
import fr.paladium.palamod.modules.luckyblock.items.june.ItemFoghorn;
import fr.paladium.palamod.modules.luckyblock.items.june.ItemGoldRecord;
import fr.paladium.palamod.modules.luckyblock.items.june.ItemPaladiumBestSoundRecord;
import fr.paladium.palamod.modules.luckyblock.items.june.ItemParalyzingRadio;
import fr.paladium.palamod.modules.luckyblock.items.june.ItemRadio;
import fr.paladium.palamod.modules.luckyblock.items.june.ItemSoundDetector;
import fr.paladium.palamod.modules.luckyblock.items.june.ItemTrumpet;
import fr.paladium.palamod.modules.luckyblock.items.may.BaseItemTicket;
import fr.paladium.palamod.modules.luckyblock.items.may.ItemDarkKnightHelmet;
import fr.paladium.palamod.modules.luckyblock.items.may.ItemEnergeticBowSword;
import fr.paladium.palamod.modules.luckyblock.items.may.ItemMagneticCompass;
import fr.paladium.palamod.modules.luckyblock.items.may.ItemOldStory;
import fr.paladium.palamod.modules.luckyblock.items.may.ItemStrangeMetalPiece;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.instruments.impl.ItemElectricGuitar;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.item.ItemMayTicket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.item.ItemPetSnack;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.item.ItemPetXp;
import fr.paladium.palamod.modules.paladium.common.items.BaseItemApple;
import fr.paladium.palamod.modules.paladium.common.items.armors.BaseItemArmor;
import fr.paladium.palamod.modules.paladium.common.items.armors.BaseItemArmorEffect;
import fr.paladium.palamod.modules.paladium.common.items.armors.ItemRepairableArmor;
import fr.paladium.palamod.modules.paladium.common.items.potion.ItemPotion;
import fr.paladium.palamod.modules.paladium.common.items.potion.ItemSplashPotion;
import fr.paladium.palamod.modules.paladium.common.items.weapons.BaseItemSword;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import fr.paladium.palawither.common.item.ItemWitherGem;
import java.awt.Color;
import java.util.Arrays;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Items {
  public static void register() {
    ItemsRegister.CHUNK_ANALYSER = (Item)new ItemChunkAnalyser();
    ItemsRegister.BLAZE_AND_STEEL = (Item)new ItemBlazeAndSteel();
    ItemsRegister.WEIGHTED_BOOTS = (new ItemWeightedArmor(ItemArmor.ArmorMaterial.GOLD, 3, "weighted_boots", "weighted_armor")).func_77655_b("weighted_boots");
    ItemsRegister.DISC_FUZEIII = (new ItemDisc("fuzeiii")).func_77655_b("fuzeiii").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.DISC_HALLOWEEN = (new ItemDisc("halloween")).func_77655_b("halloween").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.GRAPPIN = (Item)new ItemGrappin();
    ItemsRegister.GRAPPIN_HOOK = (new Item()).func_77637_a(PLuckyBlock.TAB).func_77655_b("grappin_hook").func_111206_d("palamod:grappin_hook").func_77625_d(64);
    ItemsRegister.NAMETAG_FIREWORK = (Item)new ItemNameTagFirework();
    ItemsRegister.SPACE_FOOD = (Item)new ItemSpaceFood();
    ItemsRegister.MAGIC_POTION = (Item)new ItemMagicPotion();
    ItemsRegister.LASSO = (Item)new ItemLasso();
    ItemsRegister.ROLLER = (Item)new ItemRoller();
    ItemsRegister.JETPACK = (Item)new ItemJetpack();
    ItemsRegister.BROOM = (Item)new ItemWitchBroom();
    ItemsRegister.GHOSTCAPE = (Item)new ItemGhostCape();
    ItemsRegister.BIOME_PAINTER = (Item)new ItemBiomePainter();
    ItemsRegister.POULPOGUN = (Item)new ItemPoulpoGun();
    ItemsRegister.LUCKY_PAINTING = (Item)new ItemLuckyPainting();
    ItemsRegister.METER = (Item)new ItemMeter();
    ItemsRegister.ENDER_BAG = (Item)new ItemEnderBag();
    ItemsRegister.SMOKEBOMB_WHITE = (Item)new ItemSmokeBomb((new Color(236, 240, 241)).getRGB(), "white");
    ItemsRegister.SMOKEBOMB_ORANGE = (Item)new ItemSmokeBomb((new Color(230, 126, 34)).getRGB(), "orange");
    ItemsRegister.SMOKEBOMB_MAGENTA = (Item)new ItemSmokeBomb((new Color(155, 89, 182)).getRGB(), "magenta");
    ItemsRegister.SMOKEBOMB_LIGHT_BLUE = (Item)new ItemSmokeBomb((new Color(52, 152, 219)).getRGB(), "light_blue");
    ItemsRegister.SMOKEBOMB_YELLOW = (Item)new ItemSmokeBomb((new Color(241, 196, 15)).getRGB(), "yellow");
    ItemsRegister.SMOKEBOMB_LIME = (Item)new ItemSmokeBomb((new Color(46, 204, 113)).getRGB(), "lime");
    ItemsRegister.SMOKEBOMB_PINK = (Item)new ItemSmokeBomb((new Color(243, 104, 224)).getRGB(), "pink");
    ItemsRegister.SMOKEBOMB_GRAY = (Item)new ItemSmokeBomb(16777215, "gray");
    ItemsRegister.SMOKEBOMB_LIGHT_GRAY = (Item)new ItemSmokeBomb((new Color(189, 195, 199)).getRGB(), "light_gray");
    ItemsRegister.SMOKEBOMB_CYAN = (Item)new ItemSmokeBomb((new Color(72, 219, 251)).getRGB(), "cyan");
    ItemsRegister.SMOKEBOMB_PURPLE = (Item)new ItemSmokeBomb((new Color(95, 39, 205)).getRGB(), "purple");
    ItemsRegister.SMOKEBOMB_BLUE = (Item)new ItemSmokeBomb((new Color(46, 134, 222)).getRGB(), "blue");
    ItemsRegister.SMOKEBOMB_BROWN = (Item)new ItemSmokeBomb((new Color(160, 82, 45)).getRGB(), "brown");
    ItemsRegister.SMOKEBOMB_GREEN = (Item)new ItemSmokeBomb((new Color(0, 255, 0)).getRGB(), "green");
    ItemsRegister.SMOKEBOMB_RED = (Item)new ItemSmokeBomb((new Color(255, 0, 0)).getRGB(), "red");
    ItemsRegister.SMOKEBOMB_BLACK = (Item)new ItemSmokeBomb((new Color(44, 62, 80)).getRGB(), "black");
    ItemsRegister.PUMPKINSHEAR = (Item)new ItemPumpkinShear();
    ItemsRegister.SPIDERGLOVES = (Item)new ItemSpiderGlove();
    ItemsRegister.PUMPKINCRUSH = (Item)new ItemPumpkinCrush();
    ItemsRegister.HALLOWEENSTONE = (Item)new ItemHalloweenStone();
    ItemsRegister.SURVIVINGSTONE = (Item)new ItemSurvivingStone();
    ItemsRegister.FOLLOW_COMPASS = (Item)new ItemFollowCompass();
    ItemsRegister.FAKE_MONEY = (Item)new ItemFakeMoney();
    ItemsRegister.HAPPY_NEW_YEAR_FIREWORK = (Item)new ItemHappyNewYearFirework();
    ItemsRegister.STATS_STONE = (Item)new ItemStatsStone();
    ItemsRegister.PALA_ITEM_FRAME = (Item)new ItemPaladiumItemFrame();
    ItemsRegister.BALACLAVA_HELMET = (ItemArmor)new ItemBalaclavaHelmet();
    ItemsRegister.SLEEPING_BAG = (Item)new ItemSleepingBag();
    ItemsRegister.SODA = new ItemPotion("potion.soda", "potionsoda", new PotionEffect(PLuckyBlock.REMOVE_HEART.field_76415_H, 4320000));
    ItemsRegister.SODA_SPLASH = new ItemSplashPotion("potion.soda.splash", "potionsodaplash", new PotionEffect(PLuckyBlock.REMOVE_HEART.field_76415_H, 4320000), 0);
    ItemsRegister.CALCIUM = (Item)new ItemCalcium();
    ItemsRegister.AIRMIN_BOOTS = (ItemArmor)new ItemAirMin();
    ItemsRegister.SQUIDGAME_HELMET = (ItemArmor)new ItemSquidGameHelmet();
    ItemsRegister.BANANA = (ItemFood)new ItemBanana();
    ItemsRegister.COUAC_SWORD = (Item)new ItemCouacSword();
    ItemsRegister.MARIO_MUSHROOM = (Item)new ItemMarioMushroom();
    ItemsRegister.BOAT_FURNACE = (Item)new ItemBoatFurnace();
    ItemsRegister.KEY_TOMBE = (Item)new ItemKeyTombe();
    ItemsRegister.clippy = (new ItemClipboard()).func_77655_b("BiblioClipboard").func_77625_d(1);
    GameRegistry.registerItem(ItemsRegister.clippy, ItemsRegister.clippy.func_77658_a());
    RegistryUtils.item(new Item[] { ItemsRegister.FUNNY_WITHER_GEM = (Item)new ItemWitherGem("funny_wither_gem", "palamod:wither/funny_wither_gem", EntityFunnyWither.class) });
    ItemsRegister.ENDIUM_FAKE_HELMET = (new ItemFakeEndiumArmor(ItemArmor.ArmorMaterial.IRON, 0, "endium_helmet", "endium_armor", ItemsRegister.PALADIUM_INGOT)).func_77655_b("endium_fake.helmet");
    ItemsRegister.ENDIUM_FAKE_CHESTPLATE = (new ItemFakeEndiumArmor(ItemArmor.ArmorMaterial.IRON, 1, "endium_chestplate", "endium_armor", ItemsRegister.PALADIUM_INGOT)).func_77655_b("endium_fake.chestplate");
    ItemsRegister.ENDIUM_FAKE_LEGGINGS = (new ItemFakeEndiumArmor(ItemArmor.ArmorMaterial.IRON, 2, "endium_leggings", "endium_armor", ItemsRegister.PALADIUM_INGOT)).func_77655_b("endium_fake.leggings");
    ItemsRegister.ENDIUM_FAKE_BOOTS = (new ItemFakeEndiumArmor(ItemArmor.ArmorMaterial.IRON, 3, "endium_boots", "endium_armor", ItemsRegister.PALADIUM_INGOT)).func_77655_b("endium_fake.boots");
    PotionEffect[] effects = new PotionEffect[4];
    int[] maxRepair = { 8000, 12000, 10000, 7000 };
    ItemsRegister.PIG_HELMET = (new ItemRepairableArmor(PArmorMaterial.pig, 0, "pig_helmet", "pig_armor", ItemsRegister.TITANE_INGOT, effects, maxRepair)).func_77655_b("pig.helmet");
    ItemsRegister.PIG_CHESTPLATE = (new ItemRepairableArmor(PArmorMaterial.pig, 1, "pig_chestplate", "pig_armor", ItemsRegister.TITANE_INGOT, effects, maxRepair)).func_77655_b("pig.chestplate");
    ItemsRegister.PIG_LEGGINGS = (new ItemRepairableArmor(PArmorMaterial.pig, 2, "pig_leggings", "pig_armor", ItemsRegister.TITANE_INGOT, effects, maxRepair)).func_77655_b("pig.leggings");
    ItemsRegister.PIG_BOOTS = (new ItemRepairableArmor(PArmorMaterial.pig, 3, "pig_boots", "pig_armor", ItemsRegister.TITANE_INGOT, effects, maxRepair)).func_77655_b("pig.boots");
    effects = new PotionEffect[4];
    maxRepair = new int[] { 8000, 12000, 10000, 7000 };
    ItemsRegister.CAVE_HELMET = (new ItemRepairableArmor(PArmorMaterial.pig, 0, "cave_helmet", "cave_helmet", ItemsRegister.TITANE_INGOT, effects, maxRepair)).func_77655_b("cave.helmet");
    ItemsRegister.CAVE_HELMET.func_77656_e(300);
    effects = new PotionEffect[4];
    maxRepair = new int[] { 8000, 12000, 10000, 7000 };
    ItemsRegister.INVISIBLE_HELMET = (new ItemRepairableArmor(PArmorMaterial.pig, 0, "invisible_helmet", "invisible_armor", ItemsRegister.TITANE_INGOT, effects, maxRepair)).func_77655_b("invisible.helmet");
    ItemsRegister.INVISIBLE_CHESTPLATE = (new ItemRepairableArmor(PArmorMaterial.pig, 1, "invisible_chestplate", "invisible_armor", ItemsRegister.TITANE_INGOT, effects, maxRepair)).func_77655_b("invisible.chestplate");
    ItemsRegister.INVISIBLE_LEGGINGS = (new ItemRepairableArmor(PArmorMaterial.pig, 2, "invisible_leggings", "invisible_armor", ItemsRegister.TITANE_INGOT, effects, maxRepair)).func_77655_b("invisible.leggings");
    ItemsRegister.INVISIBLE_BOOTS = (new ItemRepairableArmor(PArmorMaterial.pig, 3, "invisible_boots", "invisible_armor", ItemsRegister.TITANE_INGOT, effects, maxRepair)).func_77655_b("invisible.boots");
    effects = new PotionEffect[1];
    effects[0] = new PotionEffect(Potion.field_76424_c.field_76415_H, 200, 7, true);
    ItemsRegister.SPEED_APPLE = (new BaseItemApple(100, 0.6F, 10.0F, effects, "speed_apple")).func_77655_b("speed.apple");
    effects = new PotionEffect[4];
    maxRepair = new int[] { 8000, 12000, 10000, 7000 };
    ItemsRegister.HALLOWEEN_HELMET = (new ItemRepairableArmor(PArmorMaterial.paladium, 0, "halloween_armor_helmet", "halloween_armor", ItemsRegister.PALADIUM_INGOT, effects, maxRepair)).func_77655_b("halloween.helmet");
    ItemsRegister.HALLOWEEN_CHESTPLATE = (new ItemRepairableArmor(PArmorMaterial.paladium, 1, "halloween_armor_chestplate", "halloween_armor", ItemsRegister.PALADIUM_INGOT, effects, maxRepair)).func_77655_b("halloween.chestplate");
    ItemsRegister.HALLOWEEN_LEGGINGS = (new ItemRepairableArmor(PArmorMaterial.paladium, 2, "halloween_armor_leggings", "halloween_armor", ItemsRegister.PALADIUM_INGOT, effects, maxRepair)).func_77655_b("halloween.leggings");
    ItemsRegister.HALLOWEEN_BOOTS = (new ItemRepairableArmor(PArmorMaterial.paladium, 3, "halloween_armor_boots", "halloween_armor", ItemsRegister.PALADIUM_INGOT, effects, maxRepair)).func_77655_b("halloween.boots");
    ItemsRegister.CHRISTMAS_STONE = (Item)new ItemChristmasStone();
    ItemsRegister.PRESENT = (Item)new ItemPresent();
    ItemsRegister.PRESENT_BAG = (Item)new ItemPresentBag();
    ItemsRegister.CHOCOLATE_MILK = (Item)new ItemChocolateMilk();
    ItemsRegister.CHRISTMAS_AXE = (Item)new ItemChristmasAxe();
    ItemsRegister.CHRISTMAS_BALL = (new Item()).func_77637_a(PLuckyBlock.TAB).func_77655_b("christmas_ball").func_111206_d("palamod:christmas_ball");
    ItemsRegister.CHRISTMAS_BOOK = (Item)new ItemChristmasBook();
    ItemsRegister.CHRISTMAS_MULTICOLOR_GIFT = (Item)new ItemMulticolorGift();
    ItemsRegister.WISH_CARD = (Item)new ItemWishCard();
    effects = new PotionEffect[4];
    maxRepair = new int[] { 8000, 12000, 10000, 7000 };
    ItemsRegister.CHRISTMAS_HELMET = (new ItemRepairableArmor(PArmorMaterial.paladium, 0, "christmas_armor_helmet", "christmas_armor", ItemsRegister.PALADIUM_INGOT, effects, maxRepair)).func_77655_b("christmas.helmet");
    ItemsRegister.CHRISTMAS_CHESTPLATE = (new ItemRepairableArmor(PArmorMaterial.paladium, 1, "christmas_armor_chestplate", "christmas_armor", ItemsRegister.PALADIUM_INGOT, effects, maxRepair)).func_77655_b("christmas.chestplate");
    ItemsRegister.CHRISTMAS_LEGGINGS = (new ItemRepairableArmor(PArmorMaterial.paladium, 2, "christmas_armor_leggings", "christmas_armor", ItemsRegister.PALADIUM_INGOT, effects, maxRepair)).func_77655_b("christmas.leggings");
    ItemsRegister.CHRISTMAS_BOOTS = (new ItemRepairableArmor(PArmorMaterial.paladium, 3, "christmas_armor_boots", "christmas_armor", ItemsRegister.PALADIUM_INGOT, effects, maxRepair)).func_77655_b("christmas.boots");
    ItemsRegister.SONIC_BOOTS = (new ItemSonicBoots(PArmorMaterial.sonic)).func_111206_d("palamod:sonic_boots").func_77655_b("sonic_boots").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.FLASQUE = (new ItemFlasque()).func_77655_b("flasque").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.MISSILE_METEO = (new ItemMissileMeteo()).func_77655_b("missile_meteo").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.GADGETO = (new ItemGadgeto()).func_77655_b("gadgeto").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.WATER_PISTOL = (new ItemWaterPistol()).func_77655_b("water_pistol").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.WATER_DROP = (new Item()).func_77655_b("water_drop").func_111206_d("palamod:goutte");
    ItemsRegister.CHOCOLATE_EGG = (Item)new ItemChocolateEgg();
    ItemsRegister.FAKE_CHOCOLATE_EGG = (Item)new ItemFakeChocolateEgg();
    ItemsRegister.GOLDEN_EGG = (Item)new ItemGoldenEgg();
    ItemsRegister.APRIL_FISH = (Item)new ItemAprilFish();
    ItemsRegister.EASTER_EGG = (Item)new ItemEasterEgg();
    ItemsRegister.SHEEP_NOISE_BOX = (Item)new ItemSheepNoiseBox();
    ItemsRegister.RAINBOW_COLORING = (Item)new ItemRainbowColoring();
    ItemsRegister.RAINBOW_LEATHER = (Item)new ItemRainbowLeather();
    ItemsRegister.RESURRECTION_STONE = (Item)new ItemResurrectionStone();
    ItemsRegister.GIANT_FISH = (Item)new ItemGiantFish();
    ItemsRegister.CURSED_CHOCOLATE_EGG = (Item)new ItemCursedChocolateEgg();
    ItemsRegister.EASTER_TICKET = (Item)new ItemEasterTicket();
    ItemsRegister.EASTER_TOTEM = (Item)new ItemEasterTotem();
    effects = new PotionEffect[4];
    effects[0] = new PotionEffect(Potion.field_76439_r.field_76415_H, 1200, 1, true);
    effects[3] = new PotionEffect(Potion.field_76424_c.field_76415_H, 1200, 0, true);
    maxRepair = new int[] { 8000, 12000, 10000, 7000 };
    ItemsRegister.RAINBOW_LEATHER_HELMET = (Item)new ItemRainbowLeatherArmor(0, "rainbow_leather_helmet", effects, maxRepair);
    ItemsRegister.RAINBOW_LEATHER_CHESTPLATE = (Item)new ItemRainbowLeatherArmor(1, "rainbow_leather_chestplate", effects, maxRepair);
    ItemsRegister.RAINBOW_LEATHER_LEGGINGS = (Item)new ItemRainbowLeatherArmor(2, "rainbow_leather_leggings", effects, maxRepair);
    ItemsRegister.RAINBOW_LEATHER_BOOTS = (Item)new ItemRainbowLeatherArmor(3, "rainbow_leather_boots", effects, maxRepair);
    ItemsRegister.EASTER_BUNNY_HELMET = (new BaseItemArmorEffect(PArmorMaterial.paladium, 0, "easter_bunny_helmet", "easter_bunny_armor", null, effects)).func_77655_b("easter_bunny_helmet").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.EASTER_BUNNY_CHESTPLATE = (new BaseItemArmor(PArmorMaterial.paladium, 1, "easter_bunny_chestplate", "easter_bunny_armor", null)).func_77655_b("easter_bunny_chestplate").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.EASTER_BUNNY_LEGGINGS = (new BaseItemArmor(PArmorMaterial.paladium, 2, "easter_bunny_leggings", "easter_bunny_armor", null) {
        public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
          return "palamod:textures/models/" + this.model + "_1.png";
        }
      }).func_77655_b("easter_bunny_leggings").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.EASTER_BUNNY_BOOTS = (new BaseItemArmorEffect(PArmorMaterial.paladium, 3, "easter_bunny_boots", "easter_bunny_armor", null, effects)).func_77655_b("easter_bunny_boots").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.DARK_KNIGHT_HELMET = (Item)new ItemDarkKnightHelmet();
    ItemsRegister.OLD_STORY = (Item)new ItemOldStory();
    ItemsRegister.MAY_TICKET = (Item)new BaseItemTicket("may_ticket");
    ItemsRegister.STRANGE_METAL_PIECE = (Item)new ItemStrangeMetalPiece();
    ItemsRegister.SWORD_TOO_BIG = (new BaseItemSword(PToolMaterial.paladium, "sword_too_big", null, Arrays.asList(new String[] { "Ça va pas être pratique à utiliser, non ?" }))).func_77655_b("sword_too_big").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.IRON_SKULL_HAMMER = (new BaseItemSword(PToolMaterial.paladium, "iron_skull_hammer", null, Arrays.asList(new String[] { "Bien bourrin, comme on aime" }))).func_77655_b("iron_skull_hammer").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.RUNIC_AXE = (new BaseItemSword(PToolMaterial.paladium, "runic_axe", null, Arrays.asList(new String[] { "Qui a dit que l’utile ne pouvait pas", "aussi être agréable à l'œil ?" }))).func_77655_b("runic_axe").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.ENERGETIC_BOW_SWORD = (Item)new ItemEnergeticBowSword();
    ItemsRegister.MAGNETIC_COMPASS = (Item)new ItemMagneticCompass();
    ItemsRegister.LIGHT_SABER = (new BaseItemSword(PToolMaterial.paladium, "light_saber", null)).func_77655_b("light_saber").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.EASTER_TICKET = (Item)new ItemEasterTicket();
    ItemsRegister.MONSTROUS_SWORD = (new BaseItemSword(PToolMaterial.paladium, "monstrous_sword", null, Arrays.asList(new String[] { "Si elle est utilisée pour tuer un monstre farmland elle donne naturellement le meilleur butin du monstre." }))).func_77655_b("monstrous_sword").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.PET_SNACK = (Item)new ItemPetSnack();
    ItemsRegister.PET_XP_SMALL = (Item)new ItemPetXp("pet_xp_small", 2500);
    ItemsRegister.PET_XP_MEDIUM = (Item)new ItemPetXp("pet_xp_medium", 5000);
    ItemsRegister.PET_XP_BIG = (Item)new ItemPetXp("pet_xp_big", 10000);
    ItemsRegister.MAY_TICKET_BLACKSMITH = (Item)new ItemMayTicket();
    ItemsRegister.GOLD_RECORD = (Item)new ItemGoldRecord();
    ItemsRegister.TRUMPET = (Item)new ItemTrumpet();
    ItemsRegister.ELECTRIC_GUITAR = (Item)new ItemElectricGuitar();
    ItemsRegister.RADIO = (Item)new ItemRadio();
    ItemsRegister.PARALYZING_RADIO = (Item)new ItemParalyzingRadio();
    ItemsRegister.DISC_ANTI_FUZE = (new ItemDisc("anti_fuze")).func_77655_b("anti_fuze").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.DISC_CLASH_KUMIZ = (new ItemDisc("clash_kumiz")).func_77655_b("clash_kumiz").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.DISC_ROULETTE_PALADIENNE = (new ItemDisc("roulette_paladienne")).func_77655_b("roulette_paladienne").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.DISC_NATIONS_GLORY = (new ItemDisc("nations_glory")).func_77655_b("nations_glory").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.DISC_PALADIUM_BEST_SOUND = (Item)new ItemPaladiumBestSoundRecord();
    ItemsRegister.SOUND_DETECTOR = (Item)new ItemSoundDetector();
    ItemsRegister.SITAR = (new BaseItemSword(PToolMaterial.paladium, "sitar", null, Arrays.asList(new String[] { "Un son mélodieux, parfait pour réaliser un doux carnage." }))).func_77655_b("sitar").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.SWORD_GUITAR_OF_THE_APOCALYPSE = (new BaseItemSword(PToolMaterial.paladium, "sword_guitar_of_the_apocalypse", null, Arrays.asList(new String[] { "Une petite chanson en regardant le monde brûler ?" }))).func_77655_b("sword_guitar_of_the_apocalypse").func_77637_a(PLuckyBlock.TAB);
    ItemsRegister.JUNE_TICKET = (Item)new BaseItemTicket("june_ticket");
    ItemsRegister.ADMIN_RADIO = (Item)new ItemAdminRadio();
    ItemsRegister.FOGHORN = (Item)new ItemFoghorn();
    ItemsRegister.BIONIC_SNOWBALL = (Item)new ItemBionicSnowball();
    ItemsRegister.MOON_PIECE = (Item)new ItemMoonPiece();
    ItemsRegister.TALISMAN_CALM = (Item)new ItemTalismanCalm();
    ItemsRegister.SUPERMAN_CAPE = (new ItemSupermanArmor(ItemArmor.ArmorMaterial.DIAMOND, 1, "superman_cape", "superman_cape")).func_77655_b("superman_cape");
    ItemsRegister.LUCKY_SWORD = (Item)new ItemLuckySword(0);
    ItemsRegister.LUCKY_SWORD_BONUS = (Item)new ItemLuckySword(1);
    ItemsRegister.LUCKY_SWORD_MALUS = (Item)new ItemLuckySword(2);
    GameRegistry.registerItem(ItemsRegister.CHRISTMAS_STONE, ItemsRegister.CHRISTMAS_STONE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PRESENT, ItemsRegister.PRESENT.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PRESENT_BAG, ItemsRegister.PRESENT_BAG
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.CHOCOLATE_MILK, ItemsRegister.CHOCOLATE_MILK
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.CHRISTMAS_AXE, ItemsRegister.CHRISTMAS_AXE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.CHRISTMAS_BALL, ItemsRegister.CHRISTMAS_BALL
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.CHRISTMAS_BOOK, ItemsRegister.CHRISTMAS_BOOK
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.CHRISTMAS_MULTICOLOR_GIFT, ItemsRegister.CHRISTMAS_MULTICOLOR_GIFT
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.WISH_CARD, ItemsRegister.WISH_CARD
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.CHRISTMAS_HELMET, ItemsRegister.CHRISTMAS_HELMET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.CHRISTMAS_CHESTPLATE, ItemsRegister.CHRISTMAS_CHESTPLATE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.CHRISTMAS_LEGGINGS, ItemsRegister.CHRISTMAS_LEGGINGS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.CHRISTMAS_BOOTS, ItemsRegister.CHRISTMAS_BOOTS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.HALLOWEEN_HELMET, ItemsRegister.HALLOWEEN_HELMET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.HALLOWEEN_CHESTPLATE, ItemsRegister.HALLOWEEN_CHESTPLATE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.HALLOWEEN_LEGGINGS, ItemsRegister.HALLOWEEN_LEGGINGS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.HALLOWEEN_BOOTS, ItemsRegister.HALLOWEEN_BOOTS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SONIC_BOOTS, ItemsRegister.SONIC_BOOTS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.FLASQUE, ItemsRegister.FLASQUE.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.MISSILE_METEO, ItemsRegister.MISSILE_METEO
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.GADGETO, ItemsRegister.GADGETO.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.WATER_PISTOL, ItemsRegister.WATER_PISTOL.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.WATER_DROP, ItemsRegister.WATER_DROP.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SURVIVINGSTONE, ItemsRegister.SURVIVINGSTONE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.HALLOWEENSTONE, ItemsRegister.HALLOWEENSTONE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PUMPKINCRUSH, ItemsRegister.PUMPKINCRUSH
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SPIDERGLOVES, ItemsRegister.SPIDERGLOVES
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PUMPKINSHEAR, ItemsRegister.PUMPKINSHEAR
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.DISC_HALLOWEEN, ItemsRegister.DISC_HALLOWEEN
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.BROOM, ItemsRegister.BROOM.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.GHOSTCAPE, ItemsRegister.GHOSTCAPE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.CHUNK_ANALYSER, ItemsRegister.CHUNK_ANALYSER
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.BLAZE_AND_STEEL, ItemsRegister.BLAZE_AND_STEEL
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.WEIGHTED_BOOTS, ItemsRegister.WEIGHTED_BOOTS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.DISC_FUZEIII, ItemsRegister.DISC_FUZEIII
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.GRAPPIN, ItemsRegister.GRAPPIN.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.GRAPPIN_HOOK, ItemsRegister.GRAPPIN_HOOK
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.NAMETAG_FIREWORK, ItemsRegister.NAMETAG_FIREWORK
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SPACE_FOOD, ItemsRegister.SPACE_FOOD
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.MAGIC_POTION, ItemsRegister.MAGIC_POTION
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.LASSO, ItemsRegister.LASSO.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ROLLER, ItemsRegister.ROLLER.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.JETPACK, ItemsRegister.JETPACK.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.BIOME_PAINTER, ItemsRegister.BIOME_PAINTER
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.LUCKY_PAINTING, ItemsRegister.LUCKY_PAINTING
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PIG_HELMET, ItemsRegister.PIG_HELMET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PIG_CHESTPLATE, ItemsRegister.PIG_CHESTPLATE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PIG_LEGGINGS, ItemsRegister.PIG_LEGGINGS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PIG_BOOTS, ItemsRegister.PIG_BOOTS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.CAVE_HELMET, ItemsRegister.CAVE_HELMET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.INVISIBLE_HELMET, ItemsRegister.INVISIBLE_HELMET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.INVISIBLE_CHESTPLATE, ItemsRegister.INVISIBLE_CHESTPLATE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.INVISIBLE_LEGGINGS, ItemsRegister.INVISIBLE_LEGGINGS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.INVISIBLE_BOOTS, ItemsRegister.INVISIBLE_BOOTS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SPEED_APPLE, ItemsRegister.SPEED_APPLE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.POULPOGUN, ItemsRegister.POULPOGUN
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.METER, ItemsRegister.METER.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ENDER_BAG, ItemsRegister.ENDER_BAG
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SMOKEBOMB_BLACK, ItemsRegister.SMOKEBOMB_BLACK
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SMOKEBOMB_BLUE, ItemsRegister.SMOKEBOMB_BLUE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SMOKEBOMB_BROWN, ItemsRegister.SMOKEBOMB_BROWN
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SMOKEBOMB_CYAN, ItemsRegister.SMOKEBOMB_CYAN
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SMOKEBOMB_GRAY, ItemsRegister.SMOKEBOMB_GRAY
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SMOKEBOMB_GREEN, ItemsRegister.SMOKEBOMB_GREEN
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SMOKEBOMB_LIGHT_BLUE, ItemsRegister.SMOKEBOMB_LIGHT_BLUE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SMOKEBOMB_LIGHT_GRAY, ItemsRegister.SMOKEBOMB_LIGHT_GRAY
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SMOKEBOMB_LIME, ItemsRegister.SMOKEBOMB_LIME
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SMOKEBOMB_MAGENTA, ItemsRegister.SMOKEBOMB_MAGENTA
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SMOKEBOMB_ORANGE, ItemsRegister.SMOKEBOMB_ORANGE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SMOKEBOMB_PINK, ItemsRegister.SMOKEBOMB_PINK
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SMOKEBOMB_PURPLE, ItemsRegister.SMOKEBOMB_PURPLE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SMOKEBOMB_RED, ItemsRegister.SMOKEBOMB_RED
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SMOKEBOMB_WHITE, ItemsRegister.SMOKEBOMB_WHITE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SMOKEBOMB_YELLOW, ItemsRegister.SMOKEBOMB_YELLOW
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.FOLLOW_COMPASS, ItemsRegister.FOLLOW_COMPASS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.FAKE_MONEY, ItemsRegister.FAKE_MONEY
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.HAPPY_NEW_YEAR_FIREWORK, ItemsRegister.HAPPY_NEW_YEAR_FIREWORK
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.STATS_STONE, ItemsRegister.STATS_STONE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALA_ITEM_FRAME, ItemsRegister.PALA_ITEM_FRAME
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.BALACLAVA_HELMET, ItemsRegister.BALACLAVA_HELMET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SLEEPING_BAG, ItemsRegister.SLEEPING_BAG
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.SODA, ItemsRegister.SODA.func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.SODA_SPLASH, ItemsRegister.SODA_SPLASH
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.CALCIUM, ItemsRegister.CALCIUM.func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.AIRMIN_BOOTS, ItemsRegister.AIRMIN_BOOTS
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.SQUIDGAME_HELMET, ItemsRegister.SQUIDGAME_HELMET
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.BANANA, ItemsRegister.BANANA.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ENDIUM_FAKE_HELMET, ItemsRegister.ENDIUM_FAKE_HELMET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ENDIUM_FAKE_CHESTPLATE, ItemsRegister.ENDIUM_FAKE_CHESTPLATE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ENDIUM_FAKE_LEGGINGS, ItemsRegister.ENDIUM_FAKE_LEGGINGS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ENDIUM_FAKE_BOOTS, ItemsRegister.ENDIUM_FAKE_BOOTS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.COUAC_SWORD, ItemsRegister.COUAC_SWORD
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.MARIO_MUSHROOM, ItemsRegister.MARIO_MUSHROOM
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.BOAT_FURNACE, ItemsRegister.BOAT_FURNACE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.KEY_TOMBE, ItemsRegister.KEY_TOMBE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.BIONIC_SNOWBALL, ItemsRegister.BIONIC_SNOWBALL
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.MOON_PIECE, ItemsRegister.MOON_PIECE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TALISMAN_CALM, ItemsRegister.TALISMAN_CALM
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SUPERMAN_CAPE, ItemsRegister.SUPERMAN_CAPE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.LUCKY_SWORD, ItemsRegister.LUCKY_SWORD
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.LUCKY_SWORD_BONUS, ItemsRegister.LUCKY_SWORD_BONUS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.LUCKY_SWORD_MALUS, ItemsRegister.LUCKY_SWORD_MALUS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.CHOCOLATE_EGG, ItemsRegister.CHOCOLATE_EGG
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.FAKE_CHOCOLATE_EGG, ItemsRegister.FAKE_CHOCOLATE_EGG
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.GOLDEN_EGG, ItemsRegister.GOLDEN_EGG
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.EASTER_BUNNY_HELMET, ItemsRegister.EASTER_BUNNY_HELMET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.EASTER_BUNNY_CHESTPLATE, ItemsRegister.EASTER_BUNNY_CHESTPLATE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.EASTER_BUNNY_LEGGINGS, ItemsRegister.EASTER_BUNNY_LEGGINGS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.EASTER_BUNNY_BOOTS, ItemsRegister.EASTER_BUNNY_BOOTS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.APRIL_FISH, ItemsRegister.APRIL_FISH
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.EASTER_EGG, ItemsRegister.EASTER_EGG
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SHEEP_NOISE_BOX, ItemsRegister.SHEEP_NOISE_BOX
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.RAINBOW_COLORING, ItemsRegister.RAINBOW_COLORING
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.RAINBOW_LEATHER, ItemsRegister.RAINBOW_LEATHER
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.RAINBOW_LEATHER_HELMET, ItemsRegister.RAINBOW_LEATHER_HELMET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.RAINBOW_LEATHER_CHESTPLATE, ItemsRegister.RAINBOW_LEATHER_CHESTPLATE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.RAINBOW_LEATHER_LEGGINGS, ItemsRegister.RAINBOW_LEATHER_LEGGINGS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.RAINBOW_LEATHER_BOOTS, ItemsRegister.RAINBOW_LEATHER_BOOTS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.RESURRECTION_STONE, ItemsRegister.RESURRECTION_STONE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.GIANT_FISH, ItemsRegister.GIANT_FISH
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.CURSED_CHOCOLATE_EGG, ItemsRegister.CURSED_CHOCOLATE_EGG
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.EASTER_TICKET, ItemsRegister.EASTER_TICKET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.EASTER_TOTEM, ItemsRegister.EASTER_TOTEM
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.OLD_STORY, ItemsRegister.OLD_STORY
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.DARK_KNIGHT_HELMET, ItemsRegister.DARK_KNIGHT_HELMET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.MAY_TICKET, ItemsRegister.MAY_TICKET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.STRANGE_METAL_PIECE, ItemsRegister.STRANGE_METAL_PIECE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SWORD_TOO_BIG, ItemsRegister.SWORD_TOO_BIG
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.IRON_SKULL_HAMMER, ItemsRegister.IRON_SKULL_HAMMER
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.RUNIC_AXE, ItemsRegister.RUNIC_AXE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ENERGETIC_BOW_SWORD, ItemsRegister.ENERGETIC_BOW_SWORD
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.MAGNETIC_COMPASS, ItemsRegister.MAGNETIC_COMPASS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.LIGHT_SABER, ItemsRegister.LIGHT_SABER
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.MONSTROUS_SWORD, ItemsRegister.MONSTROUS_SWORD
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PET_SNACK, ItemsRegister.PET_SNACK
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PET_XP_SMALL, ItemsRegister.PET_XP_SMALL
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PET_XP_MEDIUM, ItemsRegister.PET_XP_MEDIUM
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PET_XP_BIG, ItemsRegister.PET_XP_BIG
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.MAY_TICKET_BLACKSMITH, ItemsRegister.MAY_TICKET_BLACKSMITH
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.GOLD_RECORD, ItemsRegister.GOLD_RECORD
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TRUMPET, ItemsRegister.TRUMPET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ELECTRIC_GUITAR, ItemsRegister.ELECTRIC_GUITAR
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.RADIO, ItemsRegister.RADIO
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PARALYZING_RADIO, ItemsRegister.PARALYZING_RADIO
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.DISC_ANTI_FUZE, ItemsRegister.DISC_ANTI_FUZE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.DISC_CLASH_KUMIZ, ItemsRegister.DISC_CLASH_KUMIZ
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.DISC_ROULETTE_PALADIENNE, ItemsRegister.DISC_ROULETTE_PALADIENNE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.DISC_NATIONS_GLORY, ItemsRegister.DISC_NATIONS_GLORY
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.DISC_PALADIUM_BEST_SOUND, ItemsRegister.DISC_PALADIUM_BEST_SOUND
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SOUND_DETECTOR, ItemsRegister.SOUND_DETECTOR
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SITAR, ItemsRegister.SITAR
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SWORD_GUITAR_OF_THE_APOCALYPSE, ItemsRegister.SWORD_GUITAR_OF_THE_APOCALYPSE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.JUNE_TICKET, ItemsRegister.JUNE_TICKET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ADMIN_RADIO, ItemsRegister.ADMIN_RADIO
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.FOGHORN, ItemsRegister.FOGHORN
        .func_77658_a());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\init\Items.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */